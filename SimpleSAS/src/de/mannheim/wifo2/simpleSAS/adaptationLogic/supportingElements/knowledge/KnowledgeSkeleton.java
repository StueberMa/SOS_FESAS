
package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.knowledge;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class KnowledgeSkeleton extends info.pppc.base.system.Skeleton  {
	
	/**
	 * Default constructor to create a new object.
	 */
	public KnowledgeSkeleton() { }
	
	/**
	 * Dispatch method that dispatches incoming invocations to the skeleton's implementation.
	 *
	 * @param method The signature of the method to call.
	 * @param args The parameters of the method call.
	 * @return The result of the method call.
	 */
	protected info.pppc.base.system.Result dispatch(String method, Object[] args) {
		de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge impl = (de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.knowledge.IKnowledge)getImplementation();
		try {
			if (method.equals("de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String, java.lang.String)")) {
				Object result = impl.getKnowledge((java.lang.String)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord, java.lang.String)")) {
				Object result = impl.saveKnowledge((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract)")) {
				Object result = null;
				impl.implementLogic((de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}return new info.pppc.base.system.Result(null, new info.pppc.base.system.InvocationException("Illegal signature."));
		} catch (Throwable t) {
			return new info.pppc.base.system.Result(null, t);
		}
	}
	
}
