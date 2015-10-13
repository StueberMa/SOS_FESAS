package de.mannheim.wifo2.simpleSAS.adaptationLogic;

import info.pppc.base.service.Service;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.system.InvocationBroker;
import info.pppc.base.system.ObjectID;
import info.pppc.base.system.event.Event;
import info.pppc.base.system.event.IListener;
import info.pppc.base.system.operation.IMonitor;
import info.pppc.base.system.operation.IOperation;
import info.pppc.base.system.operation.NullMonitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.mannheim.wifo2.fesas.almProxy.ALMProxy;
import de.mannheim.wifo2.fesas.almStructure.data.ALMALElementStatus;
import de.mannheim.wifo2.fesas.logicRepository.localLogicRepository.LocalLogicRepository;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.ILogic;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.IDConversion;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.ICommunicationLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.UnknownDataTypeException;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeRecord;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub.PubSubBASELogic;

public abstract class AdaptationLogicService extends Service implements IOperation, IAdaptationLogic {

	protected ObjectID objectID;
	protected String fesasID;
	protected String name;
	
	protected ArrayList<ILogic> implementedLogics = new ArrayList<ILogic>();
	protected ICommunicationLogic commLogic;
	
//	protected IInformationType type;
	protected InformationCategory category;
	
	
	protected ALMProxy almProxy;
	

	/**
	 * A null monitor that observes the operation
	 */
	protected NullMonitor monitor = new NullMonitor();
	
	protected static int initFactor = 1; // + (int)(Math.random() * 7);

	/**
	 * A reference to the local service registry.
	 */
	protected ServiceRegistry registry = ServiceRegistry.getInstance();
	
	/**
	 * Creates a new AdaptationLogicService service.
	 */
	public AdaptationLogicService(String name, String fesasID) {
		this.name = name;
		this.fesasID = fesasID;
		almProxy = ALMProxy.getInstance();
		
//		this.implementCommunicationLogic("", "");
	}
	
	@Override
	public void start() {
		
		if (Constants.DEBUG_FESASSETUP) Logging.logIntoFile(getClass(), 
				"AL Info Type", objectID.toString(), fesasID, category.toString());
		InvocationBroker broker = InvocationBroker.getInstance();
		broker.performOperation(this, monitor);
		broker.addBrokerListener(InvocationBroker.EVENT_BROKER_SHUTDOWN, new IListener() {
			@Override
			public void handleEvent(Event event) {
				monitor.cancel();
			}
		});
		
		
		sendDataToALMProxy();

	}
	
	
	private void sendDataToALMProxy() {
		
		ALMALElementStatus status = new ALMALElementStatus(this.fesasID, 
				this.objectID.toString(), this.getName(), 
				this.implementedLogics.get(0).toString(),
				this.category);

		// Send data to the ALM proxy
		almProxy.updateSystemStatus(status);	
	}

	/**
	 * Performs a query every X seconds (determined by Constant.PERIOD_CYCLE)
	 * and calls the service's logic.
	 * 
	 * @param monitor The monitor to cancel the operation.
	 * @throws Exception Should not be thrown.
	 */
	@Override
	public void perform(IMonitor monitor) throws Exception {
		synchronized (monitor) {
			try {
				monitor.wait(initFactor * Constants.PERIOD_INIT);
				initFactor++;
			} catch (InterruptedException e) {
				Logging.errorIntoFile(getClass(), "Thread (" + category + ") got interrupted.", 
						e, objectID.toString(), fesasID, null);					
			}
			while (! monitor.isCanceled()) {
				
				try {
					monitor.wait(Constants.PERIOD_CYCLE);
//					if(Constants.DEBUG_LOGIC) Logging.debug(getClass(), 
//							"Call Logic");
//					for (ILogic logic : implementedLogics) {
//						callLogic(logic, "CALLED_FROM_LOOP");
//					}
				} catch (InterruptedException e) {
					Logging.errorIntoFile(getClass(), "Thread (" + category + ") got interrupted.", 
							e, objectID.toString(), fesasID, null);					
				}
			}
		}
	}
	
	/**
	 * This method is responsible for loading and implementing a new logic.
	 */
	@Override
	public void implementLogic(Contract contract, IInformationCategory infoCategory) throws LogicRepositoryNotFoundException {
		
		// get LocalLogicRepository from FESAS middleware
		LocalLogicRepository rep = LocalLogicRepository.getInstance();
		
		//loads logic from the local repository
		/*
		 * LogicType logicType, IInformationType infoType, 
		 * IInformationCategory infoCategory, String name, 
		 * String programmingLanguage
		 */
		
		ILogic functionalLogic = rep.loadLogicFromLocalRepository(contract);
		
		// after loading a logic
		functionalLogic.setAL(this);
		functionalLogic.setInformationCategory(infoCategory);
		implementedLogics.add(functionalLogic);	
		
		
		sendDataToALMProxy();
		
	}
	
	
	@Override
	public void implementCommunicationLogic(String communicationType, String communicationID) {
		// TODO: in future: use communication logic repository? Requires MW and MW-Impl as libs
		commLogic = new PubSubBASELogic(IDConversion.transformObjIDToString(objectID));
	}
	
	protected void callLogic(ILogic logic, Object data) {
		if (logic == null) {
			if(Constants.DEBUG_LOGIC_RESULT) Logging.logIntoFile(getClass(), "Logic  (" + category + ") is null!", 
					objectID.toString(), fesasID, null);
		}
		//TODO: choose logic depending on supported data type (add supported data type to logic and config file [in logic with XML?])
		String result = logic.callLogic(data);
		if (result == null) {
			if(Constants.DEBUG_LOGIC_RESULT) Logging.logIntoFile(logic.getClass(), "Null after logic (" + category + ")",
					objectID.toString(), fesasID, null);
		} else {
			if(Constants.DEBUG_LOGIC_RESULT) Logging.logIntoFile(logic.getClass(), "Logic (" + category + ") called! ", 
					objectID.toString(), fesasID, result);
		}
	}

	@Override
	public void prepareDataForSending(Object data, String type) {
		System.out.println("TYPE in AL: " + type);
		KnowledgeRecord knowledege = new KnowledgeRecord(
				data, type, category.toString(),
				fesasID, new Date());
		CommunicationProperties properties = 
				new CommunicationProperties(
						(IInformationCategory) category,
						InformationType.getInformationTypeByString(type),
						IDConversion.transformObjIDToString(this.objectID)); 
		commLogic.sendData(knowledege, properties);
		
	}
	
//	public abstract void sendData(Object data);

//	/**
//	 * Called by element for push of data to successor.
//	 * 
//	 * @param succesors
//	 * @param data
//	 */
//	protected abstract void sendData(List<IAdaptationLogic> receivers, Object data, 
//			IInformationCategory infoCat, IInformationType infoType);
			
	/**
	 * Called by element for pull of data from defined predecessor.
	 * 
	 * @param predecessors
	 */
	protected void requestData(List<IAdaptationLogic> predecessors) {
		//TODO: implement me
	}
	
	/**
	 * Called by element for pull of data from predecessor.
	 * 
	 * @param predecessors
	 */
	protected void requestData(CommunicationProperties dataType) {
		//TODO: implement me
	}
	
	

	@Override
	public Object receiveData(String predecessor, String informationType, Object data) {
		
		System.out.println("Receive Request " + this.getClass());
		
		Logging.logIntoFile(getClass(), "Data received in AL - " + getClass().getSimpleName(),
				objectID.toString(), fesasID, informationType);
		
		//TODO: was soll das dataAsString?
		for (ILogic logic : implementedLogics) {
//			System.out.println(logic.toString());
//			System.out.println("data received: " + data.toString());
			if (logic.isCompatibleDataType(informationType)) {
//				System.out.println("Logic found");
				final Object extractedData;
				final ILogic logicToUse = logic;
				final String dataAsString;
				try {
					extractedData = commLogic.receiveData(data);
					if (extractedData instanceof KnowledgeRecord) {
						KnowledgeRecord kRecord = (KnowledgeRecord) extractedData;
						Object kData = kRecord.getData();
						if (kData instanceof String) {
							dataAsString = (String) kData;
						} else {
							dataAsString = "";
						}
					} else {
						dataAsString = "";
					}
//					System.out.println("Extracted Data: " + dataAsString);
					new Thread(){
						@Override
						public void run() {
							Logging.logIntoFile(getClass(), "Data received (logic will be called)!",
									objectID.toString(), fesasID, 
									dataAsString);
							callLogic(logicToUse, dataAsString);
						}
					}.start();
				} catch (UnknownDataTypeException e1) {
					e1.printStackTrace();
				}
				
			}
		}

		return null;

	}

	// not used in PubSub
	@Override
	public void addCommunicationToByType(String receiver, CommunicationProperties properties) {
		commLogic.initializeCommunicationChannel(this.getID(), receiver, properties);
	}
	
	// register as subscriber by the sender
	// in this case, the sender is the publisher of events!
	@Override
	public void addCommunicationFromByType(String sender, CommunicationProperties properties)  {
		commLogic.initializeCommunicationChannel(sender, this.getID(), properties);
	}
	
	// not used in PubSub
	@Override
	public void addCommunicationToByCategory(String receiver, CommunicationProperties properties) {
		commLogic.initializeCommunicationChannel(this.getID(), receiver, properties);
	}
	
	// register as subscriber by the sender
	// in this case, the sender is the publisher of events!
	@Override
	public void addCommunicationFromByCategory(String sender, CommunicationProperties properties) {
		commLogic.initializeCommunicationChannel(sender, this.getID(), properties);
	}
	
	
	/**
	 * Called by monitor for push of data to monitors.
	 * 
	 * @param monitors
	 * @param data
	 */
	@Override
	public String saveKnowledge(IKnowledgeRecord data) {
		
		String kID = null;
		kID = commLogic.saveKnowledge(data, fesasID);
//		
//		ServiceDescriptor[] descriptors = registry.lookup
//				(new String[] {IKnowledge.class.getName() }, ServiceRegistry.LOOKUP_LOCAL_ONLY);
//		KnowledgeProxy k = new KnowledgeProxy();
//		k.setSourceID(new ReferenceID(SystemID.SYSTEM));
//		k.setTargetID(descriptors[0].getIdentifier());
//		kID = k.saveKnowledge(data, objectID.toString());
//		
		return kID;
	}
	
	/**
	 * Called by monitor for push of data to monitors.
	 * 
	 * @param monitors
	 * @param data
	 */
	@Override
	public IKnowledgeRecord getKnowledge(String knowledgeID) {
		return commLogic.getKnowledge(knowledgeID);
	}


	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getID() {
		return objectID.toString();
	}
	
	public void setObjectID (ObjectID objectID) {
		this.objectID = objectID;
	}
	
	@Override
	public String toString() {
		
		StringBuffer info = new StringBuffer();
		info.append(getClass() + " - " + name + "\n");
		info.append("Object ID: " + objectID.toString() + "\n");
		info.append("FESAS ID: " + fesasID);

		return info.toString();
	}
	
	public void externalRequest(String sucessor) {
	}
	
}
