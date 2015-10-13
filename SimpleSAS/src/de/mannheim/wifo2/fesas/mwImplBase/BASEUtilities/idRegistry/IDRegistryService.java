package de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry;

import info.pppc.base.service.Service;
import info.pppc.base.service.ServiceDescriptor;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.system.InvocationBroker;
import info.pppc.base.system.ObjectID;
import info.pppc.base.system.ReferenceID;
import info.pppc.base.system.SystemID;
import info.pppc.base.system.event.Event;
import info.pppc.base.system.event.IListener;
import info.pppc.base.system.operation.IOperation;
import info.pppc.base.system.operation.NullMonitor;

import java.util.HashMap;

import de.mannheim.wifo2.fesas.tools.logging.Logging;

public class IDRegistryService extends Service implements IOperation, IIDRegistry {

	/**
	 * Key is the fesasID as String, value the corresponding BASE ID as ReferenceID.
	 */
	private HashMap<String,ObjectID> idTable;
	
	/**
	 * Creates, registers and returns the local instance of the IDTable.
	 * 
	 * @return The local instance of the IDTable.
	 */
	private static IDRegistryService instance = null;
	
	/**
	 * A null monitor that observes the operation
	 */
	protected NullMonitor monitor = new NullMonitor();
	
	
	/**
	 * A reference to the local service registry.
	 */
	protected ServiceRegistry registry = ServiceRegistry.getInstance();
	
	
	private IDRegistryService() {
		idTable = new HashMap<String,ObjectID>();
		
		InvocationBroker broker = InvocationBroker.getInstance();
		broker.performOperation(this, monitor);
		broker.addBrokerListener(InvocationBroker.EVENT_BROKER_SHUTDOWN, new IListener() {
			@Override
			public void handleEvent(Event event) {
				monitor.cancel();
			}
		});
	}
	
	public static IDRegistryService getInstance() {
		if (instance == null) {
			instance = new IDRegistryService();
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
	public void perform(info.pppc.base.system.operation.IMonitor monitor) throws Exception {
		synchronized (monitor) {
			try {
				monitor.wait(1000);
				Logging.logIntoFile(getClass(), "ID Registry started!", "ID Registry", null, null);
			} catch (InterruptedException e) {
				Logging.errorIntoFile(getClass(), "Thread got interrupted.", e, "ID Registry", null, null);	
				
			}
			while (! monitor.isCanceled()) {
				try {
					monitor.wait(100);
				} catch (InterruptedException e) {
					Logging.errorIntoFile(getClass(), "Thread got interrupted.", e, "ID Registry", null, null);					
				}
			}
		}
	}
	
	
	@Override
	public ObjectID getObjectID(String fesasID) {
		ObjectID result = null;
		
		// check whether it is saved internally
		if (idTable.containsKey(fesasID)) {
			result = idTable.get(fesasID);
		// not saved internally, ask for it
		} else {
//			ServiceDescriptor[] descriptors = registry.lookup
//					(ServiceRegistry.LOOKUP_REMOTE_ONLY);
			
			ServiceDescriptor[] descriptors = registry.lookup
					(new String[] {IIDRegistry.class.getName() }, ServiceRegistry.LOOKUP_REMOTE_ONLY);
			
			ObjectID temp;
			IDRegistryProxy p = new IDRegistryProxy();
			p.setSourceID(new ReferenceID(SystemID.SYSTEM));
			
			for (int i = 0; i < descriptors.length; i++) {
//				System.out.println(descriptors[i].getIdentifier());
				p.setTargetID(descriptors[i].getIdentifier());
//				descriptors[i].getInterfaces()
				temp = p.externalRequest(fesasID);
				
				if (temp != null) {
//					Logging.debug(getClass(), "Got an external result: " + temp);
					result = temp;
					break;
				} else {
//					Logging.debug(getClass(), "Got an external result: null");
				}
			}
		}
		
		return result;
	}
	
	@Override
	public void addID(String fesasID, ObjectID objectID) {
		idTable.put(fesasID, objectID);
//		Logging.log(getClass(), "ID added : " + fesasID + " - " + objectID);
	}
	
	@Override
	public ObjectID externalRequest(String fesasID) {
		ObjectID result = null;
//		System.out.println("Fesas ID on other side: " + fesasID);
		
		// check whether it is saved internally
		if (idTable.containsKey(fesasID)) {
//			Logging.log(getClass(), "Found an Object ID for remote request");
			result = idTable.get(fesasID);
		}
		return result;
	}
	
}
