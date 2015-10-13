package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.knowledge;

import info.pppc.base.service.Service;
import info.pppc.base.service.ServiceDescriptor;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.system.InvocationBroker;
import info.pppc.base.system.ReferenceID;
import info.pppc.base.system.SystemID;
import info.pppc.base.system.event.IListener;
import info.pppc.base.system.operation.IMonitor;
import info.pppc.base.system.operation.IOperation;
import info.pppc.base.system.operation.NullMonitor;

import java.util.concurrent.atomic.AtomicLong;

import de.mannheim.wifo2.fesas.logicRepository.localLogicRepository.LocalLogicRepository;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.ILogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IKnowledgeLogic;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.IDConversion;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeNotFoundException;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;

public class KnowledgeService extends Service implements IOperation, IKnowledge {
	
	private static AtomicLong idCounter = new AtomicLong();
	
	private IKnowledgeLogic logic;
	
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
	public KnowledgeService(String name, String fesasID) {
		InvocationBroker broker = InvocationBroker.getInstance();
		broker.performOperation(this, monitor);
		broker.addBrokerListener(InvocationBroker.EVENT_BROKER_SHUTDOWN, new IListener() {
			@Override
			public void handleEvent(info.pppc.base.system.event.Event event) {
				monitor.cancel();
			}
		});
		
		
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
						SystemID.SYSTEM.toString(), "KNOWLEDGE", null);					
			}
			while (! monitor.isCanceled()) {
				
				try {
					monitor.wait(Constants.PERIOD_CYCLE);
//					Logging.log(getClass(), "Thread woke up.");
				} catch (InterruptedException e) {
					Logging.errorIntoFile(getClass(), "Thread got interrupted.", e,
							SystemID.SYSTEM.toString(), "KNOWLEDGE", null);					
				}
			}
		}
	}
	
	
	@Override
	public String saveKnowledge(IKnowledgeRecord data, String ownerID) {
		
		if(Constants.DEBUG_KNOWLEDGE) System.out.println("Save Knowledge request");
		
		String id = getNextKnowledgeID();
		if(Constants.DEBUG_KNOWLEDGE) System.out.println("Knowledge ID assigned : " + id);
		
		String result = logic.saveKnowledge(data, id);
		Logging.logIntoFile(getClass(), result, SystemID.SYSTEM.toString(), "KNOWLEDGE", id);
		
		return id;
	}

	
	@Override
	public IKnowledgeRecord getKnowledge(String knowledgeIDRequest, String whereFrom) throws KnowledgeNotFoundException {
		
		if(Constants.DEBUG_KNOWLEDGE) Logging.logIntoFile(getClass(), whereFrom + " Knowledge request arrived", "KNOWLEDGE", "KNOWLEDGE", knowledgeIDRequest);
		
		if(isInteralID(knowledgeIDRequest)) {
			
			if(Constants.DEBUG_KNOWLEDGE) Logging.logIntoFile(getClass(), "Request for Internal Knowledge from " + whereFrom, "KNOWLEDGE", "KNOWLEDGE", knowledgeIDRequest);
			IKnowledgeRecord result = logic.getKnowledge(knowledgeIDRequest);
			System.out.println("Internal knowledge result: " + result);
			
			return result;
			
			
			
		} else {
			if(Constants.DEBUG_KNOWLEDGE) Logging.logIntoFile(getClass(), "Request for external Knowledge", "KNOWLEDGE", "KNOWLEDGE", knowledgeIDRequest);
			ServiceDescriptor[] descriptors = registry.lookup
					(new String[] {IKnowledge.class.getName() }, 
					ServiceRegistry.LOOKUP_REMOTE_ONLY);
			KnowledgeProxy k = new KnowledgeProxy();
			k.setSourceID(new ReferenceID(SystemID.SYSTEM));
			SystemID targetSystem = getSystemID(knowledgeIDRequest);
			for (int i = 0; i < descriptors.length; i++) {
				if (descriptors[i].getIdentifier().getSystem().equals(targetSystem)) {
					if(Constants.DEBUG_KNOWLEDGE) Logging.logIntoFile(getClass(), "External knowledge service found", "KNOWLEDGE", "KNOWLEDGE", knowledgeIDRequest);
					k.setTargetID(descriptors[i].getIdentifier());
					if(Constants.DEBUG_KNOWLEDGE) Logging.logIntoFile(getClass(), "External K service ID", "KNOWLEDGE", "KNOWLEDGE", descriptors[i].getIdentifier().toString());
					
//					System.out.println("" + 111111111);
					IKnowledgeRecord result = k.getKnowledge(knowledgeIDRequest,"EXTERNAL");
//					System.out.println("" + 22222222);
					
					return result;
				}
			}
			
			if(Constants.DEBUG_KNOWLEDGE) Logging.logIntoFile(getClass(), "Knowledge service not found", "KNOWLEDGE", "KNOWLEDGE", knowledgeIDRequest);
			return null;
			
		}
		
	}

	private synchronized String getNextKnowledgeID () {
		return SystemID.SYSTEM.toString() + "_" + idCounter.getAndIncrement();
	}

	private SystemID getSystemID(String knowledgeID) {
		return IDConversion.transformStringToSysID(knowledgeID.substring(0, knowledgeID.indexOf('_')));
	}


	private boolean isInteralID(String knowledgeID) {
		return getSystemID(knowledgeID).equals(SystemID.SYSTEM);
	}


	@Override
	public void implementLogic(Contract contract) throws LogicRepositoryNotFoundException {
		
		if(Constants.DEBUG_KNOWLEDGE) System.out.println("Load Logic : " + contract.toString());
//		logic = (IKnowledgeLogic) LocalLogicRepository.getInstance().loadLogicFromRepository(
//				LogicType.KNOWLEDGE, logicID);
				
		// get LocalLogicRepository from FESAS middleware
		LocalLogicRepository rep = LocalLogicRepository.getInstance();

		//loads logic from the local repository
		
		ILogic functionalLogic = rep.loadLogicFromLocalRepository(contract);
		logic = (IKnowledgeLogic) functionalLogic;
		logic.setInformationCategory(InformationCategory.KNOWLEDGE);
		
	}
	
}
