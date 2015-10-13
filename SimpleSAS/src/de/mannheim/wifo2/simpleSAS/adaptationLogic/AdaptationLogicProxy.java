
package de.mannheim.wifo2.simpleSAS.adaptationLogic;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class AdaptationLogicProxy extends info.pppc.base.system.Proxy implements de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic {
	
	/**
	 * Default constructor to create a new object.
	 */
	public AdaptationLogicProxy() { }
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void start()  {
		Object[] __args = new Object[0];
		String __method = "void start()";
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
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult startDef()  {
		Object[] __args = new Object[0];
		String __method = "void start()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param sender see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void addCommunicationFromByCategory(java.lang.String sender, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = sender;
		__args[1] = properties;
		String __method = "void addCommunicationFromByCategory(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
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
	 * @param sender see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult addCommunicationFromByCategoryDef(java.lang.String sender, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = sender;
		__args[1] = properties;
		String __method = "void addCommunicationFromByCategory(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param knowledgeID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String knowledgeID)  {
		Object[] __args = new Object[1];
		__args[0] = knowledgeID;
		String __method = "de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param knowledgeID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult getKnowledgeDef(java.lang.String knowledgeID)  {
		Object[] __args = new Object[1];
		__args[0] = knowledgeID;
		String __method = "de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param knowledge see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord knowledge)  {
		Object[] __args = new Object[1];
		__args[0] = knowledge;
		String __method = "java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (java.lang.String)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param knowledge see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult saveKnowledgeDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord knowledge)  {
		Object[] __args = new Object[1];
		__args[0] = knowledge;
		String __method = "java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param data see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param type see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void prepareDataForSending(java.lang.Object data, java.lang.String type)  {
		Object[] __args = new Object[2];
		__args[0] = data;
		__args[1] = type;
		String __method = "void prepareDataForSending(java.lang.Object, java.lang.String)";
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
	 * @param data see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param type see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult prepareDataForSendingDef(java.lang.Object data, java.lang.String type)  {
		Object[] __args = new Object[2];
		__args[0] = data;
		__args[1] = type;
		String __method = "void prepareDataForSending(java.lang.Object, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param sender see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void addCommunicationFromByType(java.lang.String sender, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = sender;
		__args[1] = properties;
		String __method = "void addCommunicationFromByType(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
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
	 * @param sender see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult addCommunicationFromByTypeDef(java.lang.String sender, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = sender;
		__args[1] = properties;
		String __method = "void addCommunicationFromByType(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param sucessor see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void externalRequest(java.lang.String sucessor)  {
		Object[] __args = new Object[1];
		__args[0] = sucessor;
		String __method = "void externalRequest(java.lang.String)";
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
	 * @param sucessor see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult externalRequestDef(java.lang.String sucessor)  {
		Object[] __args = new Object[1];
		__args[0] = sucessor;
		String __method = "void externalRequest(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param contract see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param infoCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory infoCategory) throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException {
		Object[] __args = new Object[2];
		__args[0] = contract;
		__args[1] = infoCategory;
		String __method = "void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException) {
				throw (de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException)__result.getException();
			}
			throw (RuntimeException)__result.getException();
		}
		return ;
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param contract see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param infoCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult implementLogicDef(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory infoCategory)  {
		Object[] __args = new Object[2];
		__args[0] = contract;
		__args[1] = infoCategory;
		String __method = "void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	/**
	 * Proxy method that creates and transfers an asynchronous call.
	 *
	 * @param contract see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param infoCategory see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void implementLogicAsync(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory infoCategory) throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException {
		Object[] __args = new Object[2];
		__args[0] = contract;
		__args[1] = infoCategory;
		String __method = "void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)";
		info.pppc.base.system.Invocation __invocation = proxyCreateAsynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeAsynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException) {
				throw (de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException)__result.getException();
			}
			throw (RuntimeException)__result.getException();
		}
		return ;
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param communicationType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param communicationID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void implementCommunicationLogic(java.lang.String communicationType, java.lang.String communicationID)  {
		Object[] __args = new Object[2];
		__args[0] = communicationType;
		__args[1] = communicationID;
		String __method = "void implementCommunicationLogic(java.lang.String, java.lang.String)";
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
	 * @param communicationType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param communicationID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult implementCommunicationLogicDef(java.lang.String communicationType, java.lang.String communicationID)  {
		Object[] __args = new Object[2];
		__args[0] = communicationType;
		__args[1] = communicationID;
		String __method = "void implementCommunicationLogic(java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param receiver see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void addCommunicationToByType(java.lang.String receiver, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = receiver;
		__args[1] = properties;
		String __method = "void addCommunicationToByType(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
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
	 * @param receiver see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult addCommunicationToByTypeDef(java.lang.String receiver, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = receiver;
		__args[1] = properties;
		String __method = "void addCommunicationToByType(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param receiver see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public void addCommunicationToByCategory(java.lang.String receiver, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = receiver;
		__args[1] = properties;
		String __method = "void addCommunicationToByCategory(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
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
	 * @param receiver see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param properties see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult addCommunicationToByCategoryDef(java.lang.String receiver, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties properties)  {
		Object[] __args = new Object[2];
		__args[0] = receiver;
		__args[1] = properties;
		String __method = "void addCommunicationToByCategory(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public java.lang.String getName()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getName()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (java.lang.String)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult getNameDef()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getName()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public java.lang.String getID()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getID()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (java.lang.String)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult getIDDef()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getID()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param sender see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param informationType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param data see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public java.lang.Object receiveData(java.lang.String sender, java.lang.String informationType, java.lang.Object data)  {
		Object[] __args = new Object[3];
		__args[0] = sender;
		__args[1] = informationType;
		__args[2] = data;
		String __method = "java.lang.Object receiveData(java.lang.String, java.lang.String, java.lang.Object)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (java.lang.Object)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param sender see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param informationType see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @param data see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic
	 */
	public info.pppc.base.system.FutureResult receiveDataDef(java.lang.String sender, java.lang.String informationType, java.lang.Object data)  {
		Object[] __args = new Object[3];
		__args[0] = sender;
		__args[1] = informationType;
		__args[2] = data;
		String __method = "java.lang.Object receiveData(java.lang.String, java.lang.String, java.lang.Object)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
}
