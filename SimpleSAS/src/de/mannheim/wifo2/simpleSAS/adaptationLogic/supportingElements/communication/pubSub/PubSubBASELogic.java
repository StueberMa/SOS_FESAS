package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub;

import info.pppc.base.service.ServiceDescriptor;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.system.ReferenceID;
import info.pppc.base.system.SystemID;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.IDConversion;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.ICommunicationLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.UnknownDataTypeException;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.Event;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants.PubSubCoverage;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants.PubSubMode;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.knowledge.KnowledgeProxy;

/**
 * PubSub logic as BASE service.
 * 
 * @author Krupitzer
 *
 */
public class PubSubBASELogic implements ICommunicationLogic {

	/**
	 * A reference to the local service registry.
	 */
	protected ServiceRegistry registry = ServiceRegistry.getInstance();
	
	private String serviceID;
	
	public PubSubBASELogic(String serviceID) {
		this.serviceID = serviceID;
	}
	

	@Override
	public String sendData(IKnowledgeRecord data, CommunicationProperties properties) {
		//receiver not used for PubSub
		
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Send data called! ",
				serviceID, "PubSubBASELogic",
				properties.getInformationCategory().toString());
		
//		Logging.debugIntoFile(getClass(), 
//				"Send data called! ",
//				serviceID, "PubSubBASELogic",
//				properties.getInformationCategory().toString());
//		
//		Logging.debugIntoFile(getClass(), 
//				"Send data called! Type: ",
//				serviceID, "PubSubBASELogic",
//				properties.getInformationType().toString());
		
		
		String knowledgeID = this.saveKnowledge(data, properties.getPublisher());
		
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Knowledege saved and ready for sending",
				serviceID, "PubSubBASELogic", properties.toString());

		// create event
		if (knowledgeID != null) {
			Event event = new Event(properties.getInformationCategory(), properties.getInformationType(),
				properties.getPublisher(), IDConversion.transformSysIDToHexString(SystemID.SYSTEM), 
				knowledgeID);
			if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
					"Event created",
					serviceID, "PubSubBASELogic", null);
			
			// publish event
			PubSubProxy pubSub = new PubSubProxy();
			ServiceDescriptor[] descriptors = registry.lookup
					(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
			pubSub.setSourceID(IDConversion.transformStringToRefID(properties.getPublisher()));
			pubSub.setTargetID(descriptors[0].getIdentifier());
			pubSub.publishEvent(event);
			if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
					"Event handed to PubSub",
					serviceID, "PubSubBASELogic", null);
		}
		
		return knowledgeID;
		
	}

	@Override
	public void initializeCommunicationChannel(String sender, String receiver, CommunicationProperties properties) {
		// in this case, the sender is the publisher of events!
		
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Initialize communication channel!",
				serviceID, "PubSubBASELogic", null);
		
		// PubSub communication is set to category-based
		if (PubSubConstants.PUBSUB_MODE == PubSubMode.CATEGORY) {
			if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
					"PubSubMode.CATEGORY",
					serviceID, "PubSubBASELogic", null);
			PubSubProxy pubSub = new PubSubProxy();
			ServiceDescriptor[] descriptors = registry.lookup
					(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
			pubSub.setSourceID(IDConversion.transformStringToRefID(receiver));
			pubSub.setTargetID(descriptors[0].getIdentifier());

			// if sender = ALL, then register at all services
			if (PubSubConstants.PUBSUB_COVERAGE == PubSubCoverage.ALLSYSTEMS) {
				if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
						"PubSubCoverage.ALLSYSTEMS",
						serviceID, "PubSubBASELogic", null);
				pubSub.registerByCategoryAtAllSystems(properties.getInformationCategory(), receiver);
				// register for a specific system
			} else {
				if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
						"PubSubCoverage.SINGLESYSTEM",
						serviceID, "PubSubBASELogic", null);
				pubSub.registerByCategory(properties.getInformationCategory(), sender, receiver);
			}
		// PubSub communication is set to type-based
		} else if (PubSubConstants.PUBSUB_MODE == PubSubMode.TYPE) {
			if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
					"PubSubMode.TYPE",
					serviceID, "PubSubBASELogic", null);
			PubSubProxy pubSub = new PubSubProxy();
			ServiceDescriptor[] descriptors = registry.lookup
					(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
			pubSub.setSourceID(IDConversion.transformStringToRefID(receiver));
			pubSub.setTargetID(descriptors[0].getIdentifier());

			// if sender = ALL, then register at all services
			if (PubSubConstants.PUBSUB_COVERAGE == PubSubCoverage.ALLSYSTEMS) {
				if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
						"PubSubCoverage.ALLSYSTEMS",
						serviceID, "PubSubBASELogic", null);
				pubSub.registerByTypeAtAllSystems(properties.getInformationType(), receiver);
				// register for a specific system
			} else {
				if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
						"PubSubCoverage.SINGLESYSTEM",
						serviceID, "PubSubBASELogic", null);
				pubSub.registerByType(properties.getInformationType(), sender, receiver);
			}
		}
	}
	

	@Override
	public String saveKnowledge(IKnowledgeRecord knowledge, String savingInstance) {
		
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Save knowledge",
				serviceID, "PubSubBASELogic", null);
		String knowledgeID;
		
		// save knowledge
		KnowledgeProxy k = new KnowledgeProxy();
		ServiceDescriptor[] descriptors = registry.lookup
				(new String[] {IKnowledge.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
		k.setSourceID(IDConversion.transformStringToRefID(savingInstance));
		k.setTargetID(descriptors[0].getIdentifier());
		knowledgeID = k.saveKnowledge(knowledge, savingInstance);
		
		return knowledgeID;
		
	}

	@Override
	public IKnowledgeRecord getKnowledge(String knowledgeID) {
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Get Knowledge",
				serviceID, "PubSubBASELogic", null);
		ServiceDescriptor[] descriptors = registry.lookup
				(new String[] {IKnowledge.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
		KnowledgeProxy k = new KnowledgeProxy();
		k.setSourceID(new ReferenceID(SystemID.SYSTEM,IDConversion.transformStringToObjectID(serviceID)));
		k.setTargetID(descriptors[0].getIdentifier());
		return k.getKnowledge(knowledgeID,"INTERNAL");
	}


	@Override
	public Object receiveData(Object data) throws UnknownDataTypeException {
		// check, whether this is really an event (if not, some strange things happened...)
		if (!(data instanceof Event)) throw new UnknownDataTypeException
			("Communication logic expects an event, but received a " + data.getClass().getName());
		
		// load the data that is related to the event
		String kID = ((Event) data).getKnowledgeID();
		IKnowledgeRecord realData = this.getKnowledge(kID);
		if(Constants.DEBUG_COMMUNICATION) Logging.debugIntoFile(getClass(), 
				"Data extracted!", 
				serviceID, "PubSubBASELogic", realData.toString());
		
		// return the data of the event
		return realData;
	}


	@Override
	public void requestData(Object bla) {
		// TODO: implement me
		
	}

//	@Override
//	public void initializeCommunicationChannelByCategory(String sender, String receiver, CommunicationProperties properties) {
//		// in this case, the sender is the publisher of events!
//		
//		PubSubProxy pubSub = new PubSubProxy();
//		ServiceDescriptor[] descriptors = registry.lookup
//				(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
//		pubSub.setSourceID(IDConversion.transformStringToRefID(receiver));
//		pubSub.setTargetID(descriptors[0].getIdentifier());
//		
//		// if sender = ALL, then register at all services
//		if (sender.equals(CommunicationProperties.ALL_SYSTEMS)) {
//			pubSub.registerByCategoryAtAllSystems(properties.getInformationCategory(), receiver);
//		// register for a specific system
//		} else {
//			pubSub.registerByCategory(properties.getInformationCategory(), sender, receiver);
//		}
//	}

//	@Override
//	public void initializeCommunicationChannelByType(String sender,
//			String receiver, CommunicationProperties properties) {
//		// in this case, the sender is the publisher of events!
//
//		PubSubProxy pubSub = new PubSubProxy();
//		ServiceDescriptor[] descriptors = registry.lookup
//				(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
//		pubSub.setSourceID(IDConversion.transformStringToRefID(receiver));
//		pubSub.setTargetID(descriptors[0].getIdentifier());
//
//		// if sender = ALL, then register at all services
//		if (sender.equals(CommunicationProperties.ALL_SYSTEMS)) {
//			pubSub.registerByTypeAtAllSystems(properties.getInformationType(), receiver);
//			// register for a specific system
//		} else {
//			pubSub.registerByType(properties.getInformationType(), sender, receiver);
//		}
//		
//	}
}
