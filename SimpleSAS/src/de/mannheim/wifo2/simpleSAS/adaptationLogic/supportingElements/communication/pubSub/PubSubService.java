package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub;

import info.pppc.base.service.Service;
import info.pppc.base.service.ServiceDescriptor;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.system.InvocationBroker;
import info.pppc.base.system.ObjectID;
import info.pppc.base.system.ReferenceID;
import info.pppc.base.system.SystemID;
import info.pppc.base.system.event.IListener;
import info.pppc.base.system.operation.IMonitor;
import info.pppc.base.system.operation.IOperation;
import info.pppc.base.system.operation.NullMonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.IDConversion;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.Event;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants.PubSubCoverage;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants.PubSubMode;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.Subscriber;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.AdaptationLogicProxy;



public class PubSubService extends Service implements IPubSub,
		IOperation {

	/** List with BASE instances that are interested in a specific event category. */
//	private HashMap<IInformationCategory,ArrayList<SystemID>> publishingListByCategory;
	private HashMap<String,ArrayList<SystemID>> publishingListByCategory;
	
	/** List with BASE instances that are interested in a specific event type. */
//	private HashMap<IInformationType,ArrayList<SystemID>> publishingListByType;
	private HashMap<String,ArrayList<SystemID>> publishingListByType;
	
	/** List of local services that have subscribed to events of a specific system. */
	private ArrayList<Subscriber> subscribersList;
	
	public static PubSubService instance = null;
	
	private static AtomicLong idCounter = new AtomicLong();
	
	private ObjectID serviceID;

	
	/**
	 * A null monitor that observes the operation
	 */
	protected NullMonitor monitor = new NullMonitor();

	/**
	 * A reference to the local service registry.
	 */
	protected ServiceRegistry registry = ServiceRegistry.getInstance();
	
	/**
	 * Creates a new tutorial service.
	 */
	private PubSubService() {
		InvocationBroker broker = InvocationBroker.getInstance();
		broker.performOperation(this, monitor);
		broker.addBrokerListener(InvocationBroker.EVENT_BROKER_SHUTDOWN, new IListener() {
			@Override
			public void handleEvent(info.pppc.base.system.event.Event event) {
				monitor.cancel();
			}
		});
		
		// initialize subscribers list
		subscribersList = new ArrayList<Subscriber>();
		
		// intialize publishing list
		if (PubSubConstants.PUBSUB_MODE == PubSubMode.CATEGORY) {
//			publishingListByCategory = new HashMap<IInformationCategory,ArrayList<SystemID>>();
			publishingListByCategory = new HashMap<String,ArrayList<SystemID>>();
		} else {
//			publishingListByType = new HashMap<IInformationType,ArrayList<SystemID>>();
			publishingListByType = new HashMap<String,ArrayList<SystemID>>();
		}
	}
	
	/**
	 * Delivers the SIngleton instance.
	 * @return the single PubSubService instance
	 */
	public static PubSubService getInstance() {
		if (instance == null) {
			instance = new PubSubService();
		} 
		return instance;
	}
	
	
	/**
	 * Performs a query every 2.5 seconds and calls print on the 
	 * remote services that have been found until then.
	 * 
	 * @param monitor The monitor to cancel the operation.
	 * @throws Exception Should not be thrown.
	 */
	@Override
	public void perform(IMonitor monitor) throws Exception {
		synchronized (monitor) {
			try {
				monitor.wait(Constants.PERIOD_INIT);
			} catch (InterruptedException e) {
				Logging.errorIntoFile(getClass(), "Thread got interrupted.", e,
						SystemID.SYSTEM.toString(), "PUBSUB", null);					
			}
			while (! monitor.isCanceled()) {
				
				try {
					monitor.wait(Constants.COMMUNICATION_PERIOD_CYCLE);
//					Logging.log(getClass(), "Thread woke up.");
				} catch (InterruptedException e) {
					Logging.errorIntoFile(getClass(), "Thread got interrupted.", e,
							SystemID.SYSTEM.toString(), "PUBSUB", null);					
				}
			}
		}
	}
	
	
	
	
	@Override
	public void publishEvent(final IEvent event) {
		
		// set event ID
		event.setEventID(getNextID());
		
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"New event arrived for publication!",
				SystemID.SYSTEM.toString(), "PUBSUB", event.toString());
		// this ArrayList saves all SystemIDs of pubsubs, that have subscribers of the event
		ArrayList<SystemID> ids = null;
		
	
		// either use publishingListByCategory or publishingListByType
		if (PubSubConstants.PUBSUB_MODE == PubSubConstants.PubSubMode.CATEGORY) {
			if (publishingListByCategory.containsKey(event.getEventCategory().toString())) {
				ids = publishingListByCategory.get(event.getEventCategory().toString());
			}
		} else if (PubSubConstants.PUBSUB_MODE == PubSubConstants.PubSubMode.TYPE) {
			if (publishingListByType.containsKey(event.getEventType().toString())) {
				ids = publishingListByType.get(event.getEventType().toString());
			}
		}
			
		// load all pub sub services
		ServiceDescriptor[] descriptors = registry.lookup
				(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_BOTH);

//		if(Constants.DEBUG_COMMUNICATION && ids.size() > 0) Logging.debug(getClass(), "ID length: " + ids.size());
		
		// if there are no subscriber, then finish publishing
		if (ids == null || ids.size() == 0) {
			Logging.logIntoFile(getClass(), 
					"Event not published (no subscribers)!",
					SystemID.SYSTEM.toString(), "PUBSUB", 
					event.getEventType().toString() + ": " + event.getEventID());
			return;
		}
		
		// there are subscribers -> publish the event
		// run through all pusubs
		for (int i = 0; i < descriptors.length; i++) {
			// if pubsub has subscrribers -> transfer event
			if (ids.contains(descriptors[i].getIdentifier().getSystem())) {
				// initialize proxy
				final PubSubProxy p = new PubSubProxy();
				p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
				p.setTargetID(descriptors[i].getIdentifier());
				if(Constants.DEBUG_COMMUNICATION)Logging.logIntoFile(getClass(), 
						"Event handed to Publisher Thread!",
						SystemID.SYSTEM.toString(), "PUBSUB", event.getEventID());
				// use a new thread for publication
				System.out.println("PUBSUB_Service : " + event.toString());
				new	PublisherThread(p, 	
						event.getEventCategory().toString(),
						event.getEventType().toString(), 
						event.getPublisher(), 
						event.getSystem(),
						event.getKnowledgeID(), 
						event.getEventID(), 
						SystemID.SYSTEM.toString()).start();
			}
		}

		
		
		
	}

//	@Override
	private void transferEvent(Event event, String publishingSystem) { //, String publisher,
//			String cat, String type) {
		
		try {
		
		//publisher = SystemID
		if (event == null) {
			System.out.println("TRANSFER got null event from " + publishingSystem);
			return;
		} 
		
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"New event arrived with transferEvent()! ",
				SystemID.SYSTEM.toString(), "PUBSUB", event.toString());
		
		// add all relevant services (all subsribers) to the list
		ArrayList<String> services = new ArrayList<String>();
		for (Subscriber s : subscribersList) {
			if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Subscriber is: " + s.toString());
			
			// if we look at specific publishing systems -> add only, if systems fits
			if (PubSubConstants.PUBSUB_COVERAGE == PubSubCoverage.SPECIFICSYSTEM) {
//				System.out.println("Pub S: " + s.getPublisher());
//				System.out.println("Pub E: " + event.getPublisher());
				if (!s.getPublisher().equals(event.getPublisher())) {
					continue;
				} //else {
//					services.add(s.getSubscriber());
//				}
			}
			
			// check for category
			if (PubSubConstants.PUBSUB_MODE == PubSubConstants.PubSubMode.CATEGORY) {
//				System.out.println("Cat S: " + s.getEventCategory() + " - " + s.getEventCategory().getClass().getSimpleName());
//				System.out.println("Cat E: " + event.getEventCategory() + " - " + event.getEventCategory().getClass().getSimpleName());
//				System.out.println("Equals: " + (s.getEventCategory() == event.getEventCategory()));
				//TODO: category in event = InformationCategory; cat in subscriber = ExtendedInformationCategory -> fix it!
				if (s.getEventCategory().toString().equals(event.getEventCategory().toString())) {
					if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
							"Service relevant for publication! ",
							SystemID.SYSTEM.toString(), "PUBSUB", s.getSubscriber());
					services.add(s.getSubscriber());
				}
			// or check for type
			} else if (PubSubConstants.PUBSUB_MODE == PubSubConstants.PubSubMode.TYPE) {
				if (s.getEventType() == event.getEventType()) {
					if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
							"Service relevant for publication! ",
							SystemID.SYSTEM.toString(), "PUBSUB", s.getSubscriber());
					services.add(s.getSubscriber());
				}
			}
		}
		
//		for (String s : services) {
//			System.out.println("SubscriberString: " + s);
//		}

		// transfer the event to the relevant subscribers
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Publish the event to the relevant subscribers! ",
				SystemID.SYSTEM.toString(), "PUBSUB", services.size() + "");
		ServiceDescriptor[] descriptors = registry.lookup
				(new String[] {IAdaptationLogic.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
		
//		System.out.println("Descriptors: " + descriptors.length);
		
		for (int i = 0; i < descriptors.length; i++) {
			if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Descriptors[" + i + "]: " + IDConversion.transformObjIDToString(descriptors[i].getIdentifier().getObject()));
			if (services.contains(IDConversion.transformObjIDToString(descriptors[i].getIdentifier().getObject()))) {
//				if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Service[0]: " + services.get(0));
				AdaptationLogicProxy p = new AdaptationLogicProxy();
				p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
				p.setTargetID(descriptors[i].getIdentifier());
				if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "descriptors[i].getIdentifier(): " + descriptors[i].getIdentifier());
				
				new	ReceiverThread(p, event).start();;
				
			}
		
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		
//		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
//				"New event arrived with transferEvent()! ",
//				SystemID.SYSTEM.toString(), "PUBSUB", event.toString());
//		
//		// add all relevant services (all subsribers) to the list
//		ArrayList<String> services = new ArrayList<String>();
//		for (Subscriber s : subscribersList) {
//			// if we look at specific publishing systems -> add only, if systems fits
//			if (PubSubConstants.PUBSUB_COVERAGE == PubSubCoverage.SPECIFICSYSTEM) {
//				if (!s.getPublisher().equals(event.getPublisher())) {
//					continue;
//				}
//			}
//			
//			// check for category
//			if (PubSubConstants.PUBSUB_MODE == PubSubConstants.PubSubMode.CATEGORY) {
//				if (s.getEventCategory().toString().equals(event.getEventCategory())) {
//					if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
//							"Service relevant for publication! ",
//							SystemID.SYSTEM.toString(), "PUBSUB", s.getSubscriber());
//					services.add(s.getSubscriber());
//				}
//			// or check for type
//			} else if (PubSubConstants.PUBSUB_MODE == PubSubConstants.PubSubMode.TYPE) {
//				if (s.getEventType().toString().equals(event.getEventType())) {
//					if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
//							"Service relevant for publication! ",
//							SystemID.SYSTEM.toString(), "PUBSUB", s.getSubscriber());
//					services.add(s.getSubscriber());
//				}
//			}
//		}
//
//		// transfer the event to the relevant subscribers
//		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
//				"Transfer the event to the relevant subscribers! ",
//				SystemID.SYSTEM.toString(), "PUBSUB", services.size() + "");
//		ServiceDescriptor[] descriptors = registry.lookup
//				(new String[] {IAdaptationLogic.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
//		
//		for (int i = 0; i < descriptors.length; i++) {
////			if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Descriptors[i]: " + IDConversion.transformObjIDToString(descriptors[i].getIdentifier().getObject()));
//			if (services.contains(IDConversion.transformObjIDToString(descriptors[i].getIdentifier().getObject()))) {
////				if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Service[0]: " + services.get(0));
//				AdaptationLogicProxy p = new AdaptationLogicProxy();
//				p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
//				p.setTargetID(descriptors[i].getIdentifier());
////				if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "descriptors[i].getIdentifier(): " + descriptors[i].getIdentifier());
//				new	ReceiverThread(p, (Event) event);	
//			}
//		}

	}

	
	/*
	 * Register for events.
	 * 
	 */

	@Override
	public void registerByCategory(IInformationCategory eventCategory, String publisher,
		String subscriberID) {
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"registerByCategory!",
				SystemID.SYSTEM.toString(), "PUBSUB",
				eventCategory + " - Pub: " + publisher + 
				" - Sub: " + subscriberID);

		// publisher ID (HashMap<Event,ArrayList<Subscriber>>)
		Subscriber subscriber = new Subscriber(eventCategory, null, subscriberID, publisher);
		register(subscriber, publisher, subscriberID,
				PubSubCoverage.SPECIFICSYSTEM, PubSubMode.CATEGORY);			
	}

	@Override
	public void registerByCategoryAtAllSystems(
			IInformationCategory eventCategory, String subscriberID) {
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"registerByCategoryAtAllSystems! ",
				SystemID.SYSTEM.toString(), "PUBSUB",
				eventCategory + " - Sub: " + subscriberID);
		
		Subscriber subscriber = new Subscriber(eventCategory, null, subscriberID, 
				PubSubCoverage.ALLSYSTEMS.name());
		register(subscriber, PubSubCoverage.ALLSYSTEMS.name(), subscriberID,
				PubSubCoverage.ALLSYSTEMS, PubSubMode.CATEGORY);	
		
	}

	@Override
	public void registerByType(IInformationType eventType, String publisher,
			String subscriberID) {
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"registerByType!",
				SystemID.SYSTEM.toString(), "PUBSUB",
				eventType + " - Pub: " + publisher + 
				" - Sub: " + subscriberID);
		
		Subscriber subscriber = new Subscriber(null, eventType, subscriberID, publisher);
		register(subscriber, publisher, subscriberID,
				PubSubCoverage.SPECIFICSYSTEM, PubSubMode.TYPE);
		
	}

	@Override
	public void registerByTypeAtAllSystems(IInformationType eventType,
			String subscriberID) {
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"registerByTypeAtAllSystems! ",
				SystemID.SYSTEM.toString(), "PUBSUB",
				eventType + " - Sub: " + subscriberID);
		
		Subscriber subscriber = new Subscriber(null, eventType, subscriberID, 
				PubSubCoverage.ALLSYSTEMS.name());
		register(subscriber, PubSubCoverage.ALLSYSTEMS.name(), subscriberID,
				PubSubCoverage.ALLSYSTEMS, PubSubMode.TYPE);
		
	}
	
	
	// subscriberID = ObjectID that is subscribing
	// publisher = ObjectID (as String) of the publisher
	private void register(Subscriber subscriber, String publisher,
			String subscriberID, PubSubCoverage coverage, PubSubMode mode) {
		
		
		// TODO: check, if already registered!
		
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Register called with: ");
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), subscriber.toString());
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), publisher);
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), subscriberID);
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), coverage.toString());
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), mode.toString());
		
		System.out.println(subscriber.toString());
		// add to subscriber list
		subscribersList.add(subscriber);
		Logging.logIntoFile(getClass(), "Subscriber internally registered!",
				SystemID.SYSTEM.toString(), "PUBSUB", subscriberID);
		
		// publisher system ID
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Before convert :" + publisher);
		SystemID publisherSysID = IDConversion.transformStringToObjectID(publisher).getSystemID();
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "After convert :" + publisherSysID.toString());
		
		
		
		
		// system ID of the pub/sub Component
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Before convert :" + SystemID.SYSTEM.toString());
		String sysIDAsString = IDConversion.transformSysIDToHexString(SystemID.SYSTEM);
//		if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "After convert :" + sysIDAsString);
		
		
		// registration for a specific system
		if (coverage == PubSubCoverage.SPECIFICSYSTEM) {	
			if (mode == PubSubMode.CATEGORY) {
				if (publisherSysID.equals(SystemID.SYSTEM)) {
//					if(Constants.DEBUG_COMMUNICATION) Logging.debug(getClass(), "Prove successful");
//					this.registerExternalByCategory(subscriber.getEventCategory().toString(), 
//							sysIDAsString);
					this.registerExternalByCategory(subscriber.getEventCategory().toString(), 
							sysIDAsString);
				} else {
					ServiceDescriptor[] descriptors = registry.lookup
							(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_REMOTE_ONLY);
					PubSubProxy p = new PubSubProxy();
					p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
					for (int i = 0; i < descriptors.length; i++) {
						if (descriptors[i].getIdentifier().getSystem().equals(publisherSysID)) {
							p.setTargetID(descriptors[i].getIdentifier());
//							p.registerExternalByCategory(subscriber.getEventCategory(), 
//									IDConversion.transformSysIDToHexString(SystemID.SYSTEM));
							if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
									"Want to call regExt with! ",
									SystemID.SYSTEM.toString(), "PUBSUB",
									subscriber.getEventCategory().toString() + " - " +
									IDConversion.transformSysIDToHexString(SystemID.SYSTEM));
							System.out.println(p.toString());
							System.out.println(subscriber.toString());
							System.out.println(sysIDAsString);
							p.registerExternalByCategory(subscriber.getEventCategory().toString(), 
									sysIDAsString);
						}
					}	
				}
			} else if (mode == PubSubMode.TYPE) {
				if (publisherSysID.equals(SystemID.SYSTEM)) {
					this.registerExternalByType(subscriber.getEventType().toString(),sysIDAsString);
				} else {
					ServiceDescriptor[] descriptors = registry.lookup
							(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_BOTH);
					PubSubProxy p = new PubSubProxy();
					p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
					for (int i = 0; i < descriptors.length; i++) {
						if (descriptors[i].getIdentifier().getSystem().equals(publisherSysID)) {
							p.setTargetID(descriptors[i].getIdentifier());
							p.registerExternalByType(subscriber.getEventType().toString(),sysIDAsString);			
						}
					}	
				}
			}
		} 
		
		// registration for all systems
		else if (coverage == PubSubCoverage.ALLSYSTEMS) {
			if (mode == PubSubMode.CATEGORY) {
				this.registerExternalByCategory(subscriber.getEventCategory().toString(), sysIDAsString);
				ServiceDescriptor[] descriptors = registry.lookup
						(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_REMOTE_ONLY);
				PubSubProxy p = new PubSubProxy();
				p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
				for (int i = 0; i < descriptors.length; i++) {
					p.setTargetID(descriptors[i].getIdentifier());
					p.registerExternalByCategory(subscriber.getEventCategory().toString(), 
							sysIDAsString);
				}	
			} else if (mode == PubSubMode.TYPE) {
				this.registerExternalByType(subscriber.getEventType().toString(), sysIDAsString);
				ServiceDescriptor[] descriptors = registry.lookup
						(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_REMOTE_ONLY);
				PubSubProxy p = new PubSubProxy();
				p.setSourceID(new ReferenceID(SystemID.SYSTEM,serviceID));
				for (int i = 0; i < descriptors.length; i++) {
					p.setTargetID(descriptors[i].getIdentifier());
					p.registerExternalByType(subscriber.getEventType().toString(), 
							sysIDAsString);			
				}
			}		
		}
		
		
		
		
	}
	
	
	
	/*
	 * Send a registration for events to another system.
	 * 
	 */

//	subscriber = SystemID of the pub/sub that wants to get notified of events
	@Override
	public void registerExternalByCategory(String eventCategory, String subscriber) {
		// publisher ID (HashMap<Event,ArrayList<Subscriber>>)
		SystemID subscriberID = IDConversion.transformStringToSysID(subscriber);		

		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"At registerExternal: ",
				SystemID.SYSTEM.toString(), "PUBSUB",
				eventCategory + " - " +
				subscriber);
		
		
		// add key to the subscriber table
		if (publishingListByCategory.containsKey(eventCategory)) {
			publishingListByCategory.get(eventCategory).add(subscriberID);		
		} else {
			ArrayList<SystemID> value = new ArrayList<SystemID>();
			value.add(subscriberID);
			publishingListByCategory.put(eventCategory, value);			
		}
		
		Logging.logIntoFile(getClass(), "Subscriber registered (ext. req.) from! ",
				SystemID.SYSTEM.toString(), "PUBSUB",
				IDConversion.transformSysIDToHexString(subscriberID));
	}
	
//	subscriber = SystemID of the pub/sub that wants to get notified of events
	@Override
	public void registerExternalByType(String eventType, String subscriber) {
		// publisher ID (HashMap<Event,ArrayList<Subscriber>>)
		SystemID subscriberID = IDConversion.transformStringToSysID(subscriber);
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"At registerExternal: ",
				SystemID.SYSTEM.toString(), "PUBSUB",
				eventType + " - " +
				subscriber);

		// add key to the subscriber table
		if (publishingListByType.containsKey(eventType)) {
			publishingListByType.get(eventType).add(subscriberID);		
		} else {
			ArrayList<SystemID> value = new ArrayList<SystemID>();
			value.add(subscriberID);
			publishingListByType.put(eventType, value);			
		}
		
		Logging.logIntoFile(getClass(), "Subscriber registered (ext. req.)!",
				SystemID.SYSTEM.toString(), "PUBSUB",null);
	}
	
	private synchronized String getNextID() {
		return SystemID.SYSTEM.toString() + "_" + idCounter.getAndIncrement();
	}

	public void setServiceID(ObjectID serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	public void notify(String eventAsString, String publishingSystem,
			String publisher, String cat, String type, String knowledgeID,
			String eventID) {
		
		Event event = new Event(cat, type, publisher, publishingSystem, 
				knowledgeID, eventID);
		transferEvent(event, publishingSystem);
		
	}
	
//	private ReferenceID createRefID(String object, String system) {
//		ReferenceID id = new ReferenceID();
//		id.setObject(ObjectIDUtilities.transformStringToObjectID(object));
//		id.setSystem(SystemIDUtilities.transformStringToSysID(system));
//		return id;
//	}
}
