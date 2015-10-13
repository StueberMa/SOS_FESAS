
package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.knowledge;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class KnowledgeProxy extends info.pppc.base.system.Proxy implements de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge {
	
	/**
	 * Default constructor to create a new object.
	 */
	public KnowledgeProxy() { }
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param knowledgeID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @param whereFrom see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @throws de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeNotFoundException see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String knowledgeID, java.lang.String whereFrom) throws de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeNotFoundException {
		Object[] __args = new Object[2];
		__args[0] = knowledgeID;
		__args[1] = whereFrom;
		String __method = "de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeNotFoundException) {
				throw (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeNotFoundException)__result.getException();
			}
			throw (RuntimeException)__result.getException();
		}
		return (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param knowledgeID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @param whereFrom see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public info.pppc.base.system.FutureResult getKnowledgeDef(java.lang.String knowledgeID, java.lang.String whereFrom)  {
		Object[] __args = new Object[2];
		__args[0] = knowledgeID;
		__args[1] = whereFrom;
		String __method = "de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param data see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @param ownerID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @return seede.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord data, java.lang.String ownerID)  {
		Object[] __args = new Object[2];
		__args[0] = data;
		__args[1] = ownerID;
		String __method = "java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord, java.lang.String)";
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
	 * @param data see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @param ownerID see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public info.pppc.base.system.FutureResult saveKnowledgeDef(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord data, java.lang.String ownerID)  {
		Object[] __args = new Object[2];
		__args[0] = data;
		__args[1] = ownerID;
		String __method = "java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param contract see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract contract) throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException {
		Object[] __args = new Object[1];
		__args[0] = contract;
		String __method = "void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract)";
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
	 * @param contract see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public info.pppc.base.system.FutureResult implementLogicDef(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract contract)  {
		Object[] __args = new Object[1];
		__args[0] = contract;
		String __method = "void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	/**
	 * Proxy method that creates and transfers an asynchronous call.
	 *
	 * @param contract see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 * @see de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge
	 */
	public void implementLogicAsync(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract contract) throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException {
		Object[] __args = new Object[1];
		__args[0] = contract;
		String __method = "void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract)";
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
	
}
