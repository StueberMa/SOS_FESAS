package de.mannheim.wifo2.fesas.mwImplBase;

import info.pppc.base.service.Service;
import info.pppc.base.service.ServiceDescriptor;
import info.pppc.base.service.ServiceProperties;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.service.ServiceRegistryException;
import info.pppc.base.system.InvocationBroker;
import info.pppc.base.system.ObjectID;
import info.pppc.base.system.ReferenceID;
import info.pppc.base.system.SystemID;
import info.pppc.base.system.event.Event;
import info.pppc.base.system.event.IListener;
import info.pppc.base.system.operation.IOperation;
import info.pppc.base.system.operation.NullMonitor;

import java.util.ArrayList;
import java.util.HashMap;

import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType;
import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException;
import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID;
import de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.IDConversion;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IDRegistryService;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.analyzer.IAnalyzer;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.executor.IExecutor;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.monitor.IMonitor;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.planner.IPlanner;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants.PubSubCoverage;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.PubSubConstants.PubSubMode;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.analyzer.AnalyzerService;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.analyzer.AnalyzerSkeleton;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.executor.ExecutorService;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.executor.ExecutorSkeleton;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.monitor.MonitorService;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.monitor.MonitorSkeleton;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.planner.PlannerService;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.planner.PlannerSkeleton;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub.PubSubProxy;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub.PubSubService;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub.PubSubSkeleton;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.knowledge.KnowledgeService;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.knowledge.KnowledgeSkeleton;

public class FesasSetUpService extends Service implements IOperation, IFesasSetUp {

	FesasDeviceID fesasDeviceID;
	private static IDRegistryService idRegistry;
	
	/** SmartHighwayDeviceType */
	IDeviceType deviceType;
	String sysIDInHex = IDConversion.transformSysIDToHexString(SystemID.SYSTEM);
	String deviceName;
	
	static FesasSetUpService instance = null;
	
	ArrayList<IAdaptationLogic> adaptationLogicElements;
	
	/**
	 * A null monitor that observes the operation
	 */
	protected NullMonitor monitor = new NullMonitor();

	/**
	 * A reference to the local service registry.
	 */
	protected ServiceRegistry registry = ServiceRegistry.getInstance();
	
	
	
	/*
	 * 
	 *	Initialize service.
	 *  
	 */
	
	
	/**
	 * Creates a new tutorial service.
	 * @throws InitializationException 
	 */
	private FesasSetUpService() throws InitializationException {
		
//		implementSensor();
//		implementEffector();
		
		adaptationLogicElements = new ArrayList<IAdaptationLogic>();	
		
		InvocationBroker broker = InvocationBroker.getInstance();
		Logging.logIntoFile(getClass(),"FESAS service started on device: ", "FesasSetUp", SystemID.SYSTEM.toString(),
				SystemID.SYSTEM.toString());
		broker.performOperation(this, monitor);
		broker.addBrokerListener(InvocationBroker.EVENT_BROKER_SHUTDOWN, new IListener() {
			@Override
			public void handleEvent(Event event) {
				monitor.cancel();
			}
		});
		
		idRegistry = IDRegistryService.getInstance();
	}
	
	@Override
	public void configureSetupService(FesasDeviceID fesasID, IDeviceType type) {
		this.deviceType = type;
		this.fesasDeviceID = fesasID;
		
	}
	
	public static FesasSetUpService getInstance() {
		if (instance == null) {
			try {
				instance = new FesasSetUpService();
			} catch (InitializationException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	
	
//	/*
//	 * 
//	 *	perform method.
//	 *  
//	 */
//
//	private void startLogicRepositoryService() {
//		
//		LocalLogicRepositorySkeleton skel = new LocalLogicRepositorySkeleton();
//		LocalLogicRepositoryService serv = LocalLogicRepositoryService.getInstance();
//		skel.setImplementation(serv);
//
//		//register service at local service registry
//		try {
//			registry.export(
//					sysIDInHex + "_LocalLogicRepository", 
//					new String[] {ILocalLogicRepository.class.getName()},
//					new ServiceProperties(),
//					skel,
//					serv
//					);	
//			Logging.log(getClass(), "Local logic repository service started on : " + fesasID);
//		} catch (ServiceRegistryException e) {
//			Logging.error(getClass(), "Could not start local logic repository service.", e);
//		}
//	}


	/**
	 * Performs a query every 2.5 seconds and calls print on the 
	 * remote services that have been found until then.
	 * 
	 * @param monitor The monitor to cancel the operation.
	 * @throws Exception Should not be thrown.
	 */
	@Override
	public void perform(info.pppc.base.system.operation.IMonitor monitor) throws Exception {
		synchronized (monitor) {
			try {
				monitor.wait(Constants.PERIOD_INIT);
				
			} catch (InterruptedException e) {
				Logging.errorIntoFile(getClass(), "Thread got interrupted.", e, "FesasSetUp", fesasDeviceID.toString(), null);					
			}
			while (! monitor.isCanceled()) {
				try {
					monitor.wait(Constants.PERIOD_CYCLE);
				} catch (InterruptedException e) {
					Logging.errorIntoFile(getClass(), "Thread got interrupted.", e, "FesasSetUp", fesasDeviceID.toString(), null);					
				}
			}
		}
	}

	
	/*
	 * 
	 *	Management of fesas id and device type.
	 *  
	 */
	
	@Override
	public String getFESASID() {
		return fesasDeviceID.getFesasDeviceID();
	}


	@Override
	public void setFESASID(String id) {
		this.fesasDeviceID = new FesasDeviceID(id);
	}


	@Override
	public IDeviceType getDeviceType() {
		return deviceType;
	}


	@Override
	public void setDeviceType(int type) throws InitializationException {
//		deviceType = SmartHighwayDeviceType.getDeviceTypeByNumber(type);
	}

		
	@Override
	public String getDeviceName() {
		return deviceName;
	}

	@Override
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	/*
	 * 
	 *	Implement the adaptation Logic elements.
	 *  
	 */
	@Override
	public String implementAdaptationLogicElement(String alType, 
			ArrayList<Contract> logics, String infoCategory, String name, String id) 
		throws InstantiationException, IllegalAccessException, InitializationException, 
		LogicRepositoryNotFoundException {
		
		//TODO: add type!
		
		LogicType alDeviceType = LogicType.valueOf(alType);
		String result = null;
		
		switch (alDeviceType) {
			
		//	Start a logic repository	
			case LOGICREPOSITORY:
				Logging.logIntoFile(getClass(), "Start logic repository!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				break;
				
		// MAPE-K elements
			case MONITOR: 
				Logging.logIntoFile(getClass(), "Start monitor!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				result = implementMonitor(logics, name, id, infoCategory);
				break;
				
			case ANALYZER: 
				Logging.logIntoFile(getClass(), "Start analyzer!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				result = implementAnalyzer(logics, name, id, infoCategory);
				break;
					
			case PLANNER: 
				Logging.logIntoFile(getClass(), "Start planner!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				result = implementPlanner(logics, name, id, infoCategory);
				break;
					
			case EXECUTOR:
				Logging.logIntoFile(getClass(), "Start executor!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				result = implementExecutor(logics, name, id, infoCategory);
				break;
				
			case KNOWLEDGE:
				Logging.logIntoFile(getClass(), "Start knowledge!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				result = implementKnowledge(logics, name, id);
				break;

		// sensor and effector		
//			case SENSOR:
//				Logging.log(getClass(), "Start sensor!");
//				implementSensor(logic, name, id);
//				break;
				
//			case EFFECTOR:
//				Logging.log(getClass(), "Start effector!");
//				break;

		// context manager
//			case CONTEXTMANAGER:
//				Logging.logIntoFile(getClass(), "Start ctx manager!", 
//						"FesasSetUp", fesasDeviceID.toString(), null);
//				result = implementContextManager(logic, name, id);
//				break;
				
		
		// start a PubSub module
			case PUBSUB:
				Logging.logIntoFile(getClass(), "Start pub sub!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				result = startPubSubService(id);
				break;
		
		// supporting components
			case INTERFERENCEMANAGER:
				Logging.logIntoFile(getClass(), "Start interference manager!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				break;
				
			case LEARNER:
				Logging.logIntoFile(getClass(), "Start learner!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				break;
				
			case QUALITYMANAGER:
				Logging.logIntoFile(getClass(), "Start quality manager!", 
						"FesasSetUp", fesasDeviceID.toString(), null);
				break;
		default:
			break;
				
		}
		
		return result;
	
	}
	
	private String implementMonitor(ArrayList<Contract> logics, String name, 
			String fesasID,	String infoCategory) 
					throws LogicRepositoryNotFoundException {
				
		if(Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), "implement Monitor!", 
				"FesasSetUp", fesasDeviceID.toString(), "logic_elements : " + logics.size() + " - " +
				"name : " + name + " - fesasID : " + fesasID);
		
		MonitorSkeleton skel = new MonitorSkeleton();
		MonitorService serv = new MonitorService(name, fesasID);
		adaptationLogicElements.add(serv);
		skel.setImplementation(serv);

		//register service at local service registry
		try {
			ObjectID objectID = registry.export(
					sysIDInHex + "_" + name, 
					new String[] {IAdaptationLogic.class.getName(), 
							IMonitor.class.getName()},
					new ServiceProperties(),
					skel,
					serv
					);	
			
			serv.setObjectID(objectID);
//			IAdaptationLogic.implementLogic(LogicType, IInformationType,
//				IInformationCategory, String logicID)
//			serv.implementLogic(LogicType.MONITOR, InformationType.getInformationTypeByString(infoType), 
//					InformationCategory.getInformationCategoryByString(infoCategory), logicID);
			for (Contract c : logics) {
				serv.implementLogic(c, InformationCategory.getInformationCategoryByString(infoCategory));
			}
			serv.implementCommunicationLogic("", "");
			Logging.logIntoFile(getClass(), "Monitor service started!" , 
						"FesasSetUp", fesasDeviceID.toString(), "name: " + name + "objectID: " + objectID + " (BASE) / " + fesasID + " (FESAS)");
			return IDConversion.transformObjIDToString(objectID);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(getClass(), "Could not start monitor service.", e, "FesasSetUp", fesasDeviceID.toString(), null);
		}
		
		return null;

	}


	private String implementAnalyzer(ArrayList<Contract> logics, String name, 
			String fesasID,	String infoCategory) 
					throws LogicRepositoryNotFoundException {
		
		if(Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), "implement Analyzer!", 
				"FesasSetUp", fesasDeviceID.toString(), "logic_elements : " + logics.size() + " - " +
				"name : " + name + " - fesasID : " + fesasID);
		
		AnalyzerSkeleton skel = new AnalyzerSkeleton();
		AnalyzerService serv = new AnalyzerService(name, fesasID);
		adaptationLogicElements.add(serv);
		skel.setImplementation(serv);
		

		// register service at local service registry
		try {
			ObjectID objectID = registry.export(
					sysIDInHex + "_" + name, 
					new String[] {IAdaptationLogic.class.getName(), 
							 IAnalyzer.class.getName()},
					new ServiceProperties(),
					skel,
					serv
					);		
//			System.out.println("ObjectID : " + IDConversion.transformObjIDToString(objectID));
			serv.setObjectID(objectID);
//			serv.implementLogic(LogicType.ANALYZER, InformationType.getInformationTypeByString(infoType), 
//					InformationCategory.getInformationCategoryByString(infoCategory), logicID);
			for (Contract c : logics) {
				serv.implementLogic(c, InformationCategory.getInformationCategoryByString(infoCategory));
			}
			serv.implementCommunicationLogic("", "");
			Logging.logIntoFile(getClass(), "Analyzer service started!" , 
						"FesasSetUp", fesasDeviceID.toString(), "name: " + name + "objectID: " + objectID + " (BASE) / " + fesasID + " (FESAS)");
			return IDConversion.transformObjIDToString(objectID);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(getClass(), "Could not start monitor service.", e, "FesasSetUp", fesasDeviceID.toString(), null);
		}
		
		return null;
		
	}


	private String implementPlanner(ArrayList<Contract> logics, String name, 
			String fesasID,	String infoCategory) 
					throws LogicRepositoryNotFoundException {
		
		if(Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), "implement Planner!", 
				"FesasSetUp", fesasDeviceID.toString(), "logic_elements : " + logics.size() + " - " +
				"name : " + name + " - fesasID : " + fesasID);
		
		PlannerSkeleton skel = new PlannerSkeleton();
		PlannerService serv = new PlannerService(name,fesasID);
		adaptationLogicElements.add(serv);
		skel.setImplementation(serv);
		

		// register service at local service registry
		try {
			ObjectID objectID = registry.export(
					sysIDInHex + "_" + name, 
					new String[] {IAdaptationLogic.class.getName(), 
							IPlanner.class.getName()},
					new ServiceProperties(),
					skel,
					serv
					);		
			serv.setObjectID(objectID);
//			serv.implementLogic(LogicType.PLANNER, InformationType.getInformationTypeByString(infoType), 
//					InformationCategory.getInformationCategoryByString(infoCategory), logicID);
			for (Contract c : logics) {
				serv.implementLogic(c, InformationCategory.getInformationCategoryByString(infoCategory));
			}
			serv.implementCommunicationLogic("", "");
			Logging.logIntoFile(getClass(), "Planner service  started!" , 
						"FesasSetUp", fesasDeviceID.toString(), "name: " + name + "objectID: " + objectID + " (BASE) / " + fesasID + " (FESAS)");
			return IDConversion.transformObjIDToString(objectID);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(getClass(), "Could not start monitor service.", e, "FesasSetUp", fesasDeviceID.toString(), null);
		}
		
		return null;
		
	}


	private String implementExecutor(ArrayList<Contract> logics, String name, 
			String fesasID,	String infoCategory) 
					throws LogicRepositoryNotFoundException {
		
		if(Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), "implement Executor!", 
				"FesasSetUp", fesasDeviceID.toString(), "logic_elements : " + logics.size() + " - " +
				"name : " + name + " - fesasID : " + fesasID);;
		
		ExecutorSkeleton skel = new ExecutorSkeleton();
		ExecutorService serv = new ExecutorService(name,fesasID);
		adaptationLogicElements.add(serv);
		skel.setImplementation(serv);
		

		// register service at local service registry
		try {
			ObjectID objectID = registry.export(
					sysIDInHex + "_" + name, 
					new String[] {IAdaptationLogic.class.getName(), 
							IExecutor.class.getName()},
					new ServiceProperties(),
					skel,
					serv
					);		
			serv.setObjectID(objectID);
//			serv.implementLogic(LogicType.EXECUTOR, InformationType.getInformationTypeByString(infoType), 
//					InformationCategory.getInformationCategoryByString(infoCategory), logicID);
			for (Contract c : logics) {
				serv.implementLogic(c, InformationCategory.getInformationCategoryByString(infoCategory));
			}
			serv.implementCommunicationLogic("", "");
			Logging.logIntoFile(getClass(), "Executor service started!" , 
						"FesasSetUp", fesasDeviceID.toString(), "name: " + name + "objectID: " + objectID + " (BASE) / " + fesasID + " (FESAS)");
			return IDConversion.transformObjIDToString(objectID);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(getClass(), "Could not start monitor service.", e, "FesasSetUp", fesasDeviceID.toString(), null);
		}
		
		return null;
		
	}

	private String implementKnowledge(ArrayList<Contract> logics, String name, String fesasID) throws LogicRepositoryNotFoundException {
		
		if(Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), "implement Knowledge!", 
				"FesasSetUp", fesasDeviceID.toString(), "logic_elements : " + logics.size() + " - " +
				"name : " + name + " - fesasID : " + fesasID);
		
		KnowledgeSkeleton skel = new KnowledgeSkeleton();
		KnowledgeService serv = new KnowledgeService(name, fesasID);
		skel.setImplementation(serv);
		for (Contract c : logics) {
			serv.implementLogic(c);
		}
		

		//register service at local service registry
		try {
			ObjectID objectID = registry.export(
					sysIDInHex + "_" + name, 
					new String[] {IKnowledge.class.getName()},
					new ServiceProperties(),
					skel,
					serv
					);	
//			serv.setObjectID(objectID);
			Logging.logIntoFile(getClass(), "Knowledge service started!" , 
						"FesasSetUp", fesasDeviceID.toString(), "name: " + name + "objectID: " + objectID + " (BASE) / " + fesasID + " (FESAS)");
			return IDConversion.transformObjIDToString(objectID);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(getClass(), "Could not start monitor service.", e, "FesasSetUp", fesasDeviceID.toString(), null);
		}
		
		return null;
	}
	
	
	
	public String implementContextManager(String logicID, String name, String fesasID, HashMap<String,Integer> actuators) throws LogicRepositoryNotFoundException {
		// intended to do nothing
		return null;
	}

	public String startSensor(String logic, String name, String fesasID, int port, String ip) {
//		if (logic.contains(LogicIDs.SENSOR_CAMERA_LOGIC)) {
//			System.out.println("Camera Sensor!");
//			return startCameraSensor(logic, name, fesasID, port, ip);
//		} else {
//			System.out.println("SENSOR LOGIC UNKNOWN!");
//			return "SENSOR LOGIC UNKNOWN!";
//		}
		return null;
	}
	
	
//	private String startCameraSensor(String logic, String name, String fesasID, int port, String ip) {
//		// intended to do nothing
//		return null;
//	}
	
	
	public String startEffector(String logic, String name, String fesasID, int actuatorPort, 
			String ip, int contextMgrPort) {
		// intended to do nothing
		return null;
	}
	
	
	
//	private void implementLearner();	
//	private void implementQualityManager();
//	private void implementInterferenceManager();
	


	
	/*
	 * 
	 *	Establish the communication structure.
	 *  
	 */
	@Override
	public void implementCommunicationStructure(String type, String receiver, 
			String sender, IInformationType informationType, IInformationCategory category) {
		
		//TODO: Change the method for establishing communication structures to AL
		
		// for PubSub: publisher = sender, subscriber = receiver!
		// type: type of the communication, e.g., PUBSUB
		// informationType: type of information, e.g., ANALYZING
		
		// TODO: implement Type properly - abstract way, or definied by communication middleware
		
		if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "Implement Communication Structure! ",
				"FesasSetUp", fesasDeviceID.toString(),
				"Type : " + type + " - " +
				"Receiver : " + receiver + " - " +
				"Sender : " + sender + " - " +
				"Information Type : " + informationType + " - " +
				"Information Category : " + category);
		
			String receiverObjectID = IDConversion.transformObjIDToString(idRegistry.getObjectID(receiver));
			String senderObjectID =IDConversion.transformObjIDToString(idRegistry.getObjectID(sender));
		
			
			 
			
			// PubSub communication is set to category-based
			if (PubSubConstants.PUBSUB_MODE == PubSubMode.CATEGORY) {
				if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "PUBSUB_MODE : Category", "FesasSetUp", fesasDeviceID.toString(), null);
				PubSubProxy pubSub = new PubSubProxy();
				ServiceDescriptor[] descriptors = registry.lookup
						(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
				if (descriptors.length == 1) {
					pubSub.setSourceID(new ReferenceID(SystemID.SYSTEM));
					pubSub.setTargetID(descriptors[0].getIdentifier());
				} else {
					Logging.logIntoFile(getClass(), "Fault in finding a PubSub module! " +
							"Amount of modules found : " + descriptors.length, "FesasSetUp", fesasDeviceID.toString(), null);
				}

				// if sender = ALL, then register at all services
				if (PubSubConstants.PUBSUB_COVERAGE == PubSubCoverage.ALLSYSTEMS) {
					if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "PUBSUB_COVERAGE: All systems", "FesasSetUp", fesasDeviceID.toString(), null);
					pubSub.registerByCategoryAtAllSystems(category, receiverObjectID);
					// register for a specific system
				} else {
					if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "PUBSUB_COVERAGE: Single system", "FesasSetUp", fesasDeviceID.toString(), null);
					pubSub.registerByCategory(category, senderObjectID, receiverObjectID);
				}
				
				
			// PubSub communication is set to type-based
			} else if (PubSubConstants.PUBSUB_MODE == PubSubMode.TYPE) {
				if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "PUBSUB_MODE : Type", "FesasSetUp", fesasDeviceID.toString(), null);
				PubSubProxy pubSub = new PubSubProxy();
				ServiceDescriptor[] descriptors = registry.lookup
						(new String[] {IPubSub.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
				if (descriptors.length == 1) {
					pubSub.setSourceID(new ReferenceID(SystemID.SYSTEM));
					pubSub.setTargetID(descriptors[0].getIdentifier());
				} else {
					Logging.logIntoFile(getClass(), "Fault in finding a PubSub module!", "FesasSetUp", fesasDeviceID.toString(), null);
				}

				// if sender = ALL, then register at all services
				if (PubSubConstants.PUBSUB_COVERAGE == PubSubCoverage.ALLSYSTEMS) {
					if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "PUBSUB_COVERAGE: All systems", "FesasSetUp", fesasDeviceID.toString(), null);
					pubSub.registerByTypeAtAllSystems(informationType, receiverObjectID);
					// register for a specific system
				} else {
					if(Constants.DEBUG_FESASSETUP) Logging.debugIntoFile(getClass(), "PUBSUB_COVERAGE: Single system", "FesasSetUp", fesasDeviceID.toString(), null);
					pubSub.registerByType(informationType, senderObjectID, receiverObjectID);
				}
		}	
		
	}
	
	private String startPubSubService(String fesasID) throws InitializationException {
		
		if(Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), "Start Pub Sub service", "FesasSetUp", fesasDeviceID.toString(), null);
		
		PubSubSkeleton skel = new PubSubSkeleton();
		PubSubService serv = PubSubService.getInstance();
		skel.setImplementation(serv);

		// register service at local service registry
		try {
			ObjectID objectID = registry.export(
					sysIDInHex + "_PubSub", 
					new String[] {IPubSub.class.getName()},
					new ServiceProperties(),
					skel,
					serv
					);			
			serv.setServiceID(objectID);
			Logging.logIntoFile(getClass(), "PubSub service started!" , 
						"FesasSetUp", fesasDeviceID.toString(), 
						"objectID: " + objectID + " (BASE) / " + fesasID + " (FESAS)");
			return IDConversion.transformObjIDToString(objectID);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(getClass(), "Could not start monitor service.", e, "FesasSetUp", fesasDeviceID.toString(), null);
			throw new InitializationException("Could not start pub sub service.");
		}
	}

	
	
	@Override
	public void setDeviceProperty(KeyValueProperty p) {
//		BASEDevice.properties.add(p);
		
	}
	
	@Override
	public void setElementProperty(String fesasID, KeyValueProperty p) {
		// TODO: set Element properties
		
	}


	@Override
	public void startAdaptationLogic() {
//		this.finishSetUp();
		for(IAdaptationLogic a : adaptationLogicElements) {
			System.out.println("FesasSetUpService - start : " + a.getID());
			a.start();
		}
		
	}
	
	
	@Override
	public void startElement(String type, String elementFesasID, ArrayList<Contract> logics, String name,
			String infoCategory, HashMap<String,String> keyValueProperties) throws InstantiationException, IllegalAccessException, InitializationException, LogicRepositoryNotFoundException {
		String objectID;

		//TODO: implement CtxManager, Sensor and Effector with template
//		if (type.equals("CONTEXTMANAGER")) {
//			objectID = implementContextManager(logicID, name, elementFesasID, actuators);
//		} else if (type.equals("SENSOR")) {
//			objectID = startSensor(logicID, name, elementFesasID, port, ip);
//		} else if (type.equals("EFFECTOR")) {
//			objectID = startEffector(logicID, name, elementFesasID, port, ip, actuatorPort);
//		} else {
//			IFesasSetUp.implementAdaptationLogicElement(String alType, String logic, 
//					String infoType, String infoCategory, String name, String id)
			objectID = implementAdaptationLogicElement(type, logics, 
					infoCategory, name, elementFesasID);
//		}

		System.out.println("FesasSetUpService - registry: " + idRegistry);
		System.out.println("FesasSetUpService - elFesasID: " + elementFesasID);
		System.out.println("FesasSetUpService - objID_String: " + objectID);
		System.out.println("FesasSetUpService - obj_Converted: " + IDConversion.transformStringToObjectID(objectID));
			
		idRegistry.addID(elementFesasID, 
				IDConversion.transformStringToObjectID(objectID));
		
	}

}
