
package de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry;

/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class IDRegistryProxy extends info.pppc.base.system.Proxy implements de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry {
	
	/**
	 * Default constructor to create a new object.
	 */
	public IDRegistryProxy() { }
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param fesasID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @return seede.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @see de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry
	 */
	@Override
	public info.pppc.base.system.ObjectID getObjectID(java.lang.String fesasID)  {
		Object[] __args = new Object[1];
		__args[0] = fesasID;
		String __method = "info.pppc.base.system.ObjectID getObjectID(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (info.pppc.base.system.ObjectID)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param fesasID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 */
	public info.pppc.base.system.FutureResult getObjectIDDef(java.lang.String fesasID)  {
		Object[] __args = new Object[1];
		__args[0] = fesasID;
		String __method = "info.pppc.base.system.ObjectID getObjectID(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param fesasID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @param objectID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @see de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry
	 */
	@Override
	public void addID(java.lang.String fesasID, info.pppc.base.system.ObjectID objectID)  {
		Object[] __args = new Object[2];
		__args[0] = fesasID;
		__args[1] = objectID;
		String __method = "void addID(java.lang.String, info.pppc.base.system.ObjectID)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return ;
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param fesasID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @param objectID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 */
	public info.pppc.base.system.FutureResult addIDDef(java.lang.String fesasID, info.pppc.base.system.ObjectID objectID)  {
		Object[] __args = new Object[2];
		__args[0] = fesasID;
		__args[1] = objectID;
		String __method = "void addID(java.lang.String, info.pppc.base.system.ObjectID)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param fesasID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @return seede.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @see de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry
	 */
	@Override
	public info.pppc.base.system.ObjectID externalRequest(java.lang.String fesasID)  {
		Object[] __args = new Object[1];
		__args[0] = fesasID;
		String __method = "info.pppc.base.system.ObjectID externalRequest(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (info.pppc.base.system.ObjectID)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param fesasID see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.smartHighway.testbed.IIDRegistry
	 */
	public info.pppc.base.system.FutureResult externalRequestDef(java.lang.String fesasID)  {
		Object[] __args = new Object[1];
		__args[0] = fesasID;
		String __method = "info.pppc.base.system.ObjectID externalRequest(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
}
