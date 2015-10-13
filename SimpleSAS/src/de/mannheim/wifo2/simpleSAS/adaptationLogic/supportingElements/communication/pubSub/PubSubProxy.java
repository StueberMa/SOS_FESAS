
package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class PubSubProxy extends info.pppc.base.system.Proxy implements de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub {
	
	/**
	 * Default constructor to create a new object.
	 */
	public PubSubProxy() { }
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publisher see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void registerByType(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType eventType, java.lang.String publisher, java.lang.String subscriberID)  {
		Object[] __args = new Object[3];
		__args[0] = eventType;
		__args[1] = publisher;
		__args[2] = subscriberID;
		String __method = "void registerByType(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, java.lang.String, java.lang.String)";
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
	 * @param eventType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publisher see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult registerByTypeDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType eventType, java.lang.String publisher, java.lang.String subscriberID)  {
		Object[] __args = new Object[3];
		__args[0] = eventType;
		__args[1] = publisher;
		__args[2] = subscriberID;
		String __method = "void registerByType(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publisher see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void registerByCategory(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory eventCategory, java.lang.String publisher, java.lang.String subscriberID)  {
		Object[] __args = new Object[3];
		__args[0] = eventCategory;
		__args[1] = publisher;
		__args[2] = subscriberID;
		String __method = "void registerByCategory(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory, java.lang.String, java.lang.String)";
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
	 * @param eventCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publisher see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult registerByCategoryDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory eventCategory, java.lang.String publisher, java.lang.String subscriberID)  {
		Object[] __args = new Object[3];
		__args[0] = eventCategory;
		__args[1] = publisher;
		__args[2] = subscriberID;
		String __method = "void registerByCategory(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory, java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void registerByTypeAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType eventType, java.lang.String subscriberID)  {
		Object[] __args = new Object[2];
		__args[0] = eventType;
		__args[1] = subscriberID;
		String __method = "void registerByTypeAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, java.lang.String)";
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
	 * @param eventType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult registerByTypeAtAllSystemsDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType eventType, java.lang.String subscriberID)  {
		Object[] __args = new Object[2];
		__args[0] = eventType;
		__args[1] = subscriberID;
		String __method = "void registerByTypeAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriber see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void registerExternalByCategory(java.lang.String eventCategory, java.lang.String subscriber)  {
		Object[] __args = new Object[2];
		__args[0] = eventCategory;
		__args[1] = subscriber;
		String __method = "void registerExternalByCategory(java.lang.String, java.lang.String)";
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
	 * @param eventCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriber see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult registerExternalByCategoryDef(java.lang.String eventCategory, java.lang.String subscriber)  {
		Object[] __args = new Object[2];
		__args[0] = eventCategory;
		__args[1] = subscriber;
		String __method = "void registerExternalByCategory(java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param event see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void publishEvent(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent event)  {
		Object[] __args = new Object[1];
		__args[0] = event;
		String __method = "void publishEvent(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent)";
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
	 * @param event see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult publishEventDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent event)  {
		Object[] __args = new Object[1];
		__args[0] = event;
		String __method = "void publishEvent(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void registerByCategoryAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory eventCategory, java.lang.String subscriberID)  {
		Object[] __args = new Object[2];
		__args[0] = eventCategory;
		__args[1] = subscriberID;
		String __method = "void registerByCategoryAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory, java.lang.String)";
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
	 * @param eventCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriberID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult registerByCategoryAtAllSystemsDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory eventCategory, java.lang.String subscriberID)  {
		Object[] __args = new Object[2];
		__args[0] = eventCategory;
		__args[1] = subscriberID;
		String __method = "void registerByCategoryAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriber see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void registerExternalByType(java.lang.String eventType, java.lang.String subscriber)  {
		Object[] __args = new Object[2];
		__args[0] = eventType;
		__args[1] = subscriber;
		String __method = "void registerExternalByType(java.lang.String, java.lang.String)";
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
	 * @param eventType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param subscriber see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult registerExternalByTypeDef(java.lang.String eventType, java.lang.String subscriber)  {
		Object[] __args = new Object[2];
		__args[0] = eventType;
		__args[1] = subscriber;
		String __method = "void registerExternalByType(java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param eventAsString see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publishingSystem see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publisher see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param cat see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param type see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param knowledgeID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param eventID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public void notify(java.lang.String eventAsString, java.lang.String publishingSystem, java.lang.String publisher, java.lang.String cat, java.lang.String type, java.lang.String knowledgeID, java.lang.String eventID)  {
		Object[] __args = new Object[7];
		__args[0] = eventAsString;
		__args[1] = publishingSystem;
		__args[2] = publisher;
		__args[3] = cat;
		__args[4] = type;
		__args[5] = knowledgeID;
		__args[6] = eventID;
		String __method = "void notify(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)";
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
	 * @param eventAsString see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publishingSystem see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param publisher see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param cat see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param type see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param knowledgeID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @param eventID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub
	 */
	public info.pppc.base.system.FutureResult notifyDef(java.lang.String eventAsString, java.lang.String publishingSystem, java.lang.String publisher, java.lang.String cat, java.lang.String type, java.lang.String knowledgeID, java.lang.String eventID)  {
		Object[] __args = new Object[7];
		__args[0] = eventAsString;
		__args[1] = publishingSystem;
		__args[2] = publisher;
		__args[3] = cat;
		__args[4] = type;
		__args[5] = knowledgeID;
		__args[6] = eventID;
		String __method = "void notify(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
}
