
package de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.planner;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class PlannerSkeleton extends info.pppc.base.system.Skeleton  {
	
	/**
	 * Default constructor to create a new object.
	 */
	public PlannerSkeleton() { }
	
	/**
	 * Dispatch method that dispatches incoming invocations to the skeleton's implementation.
	 *
	 * @param method The signature of the method to call.
	 * @param args The parameters of the method call.
	 * @return The result of the method call.
	 */
	protected info.pppc.base.system.Result dispatch(String method, Object[] args) {
		de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.planner.IPlanner impl = (de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.planner.IPlanner)getImplementation();
		try {
			if (method.equals("void start()")) {
				Object result = null;
				impl.start();
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void addCommunicationFromByCategory(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)")) {
				Object result = null;
				impl.addCommunicationFromByCategory((java.lang.String)args[0], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord getKnowledge(java.lang.String)")) {
				Object result = impl.getKnowledge((java.lang.String)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.String saveKnowledge(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)")) {
				Object result = impl.saveKnowledge((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void prepareDataForSending(java.lang.Object, java.lang.String)")) {
				Object result = null;
				impl.prepareDataForSending((java.lang.Object)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void addCommunicationFromByType(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)")) {
				Object result = null;
				impl.addCommunicationFromByType((java.lang.String)args[0], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void externalRequest(java.lang.String)")) {
				Object result = null;
				impl.externalRequest((java.lang.String)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void implementLogic(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.Contract, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)")) {
				Object result = null;
				impl.implementLogic((de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract)args[0], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void implementCommunicationLogic(java.lang.String, java.lang.String)")) {
				Object result = null;
				impl.implementCommunicationLogic((java.lang.String)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void addCommunicationToByType(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)")) {
				Object result = null;
				impl.addCommunicationToByType((java.lang.String)args[0], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void addCommunicationToByCategory(java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)")) {
				Object result = null;
				impl.addCommunicationToByCategory((java.lang.String)args[0], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.CommunicationProperties)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.String getName()")) {
				Object result = impl.getName();
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.String getID()")) {
				Object result = impl.getID();
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.Object receiveData(java.lang.String, java.lang.String, java.lang.Object)")) {
				Object result = impl.receiveData((java.lang.String)args[0], (java.lang.String)args[1], (java.lang.Object)args[2]);
				return new info.pppc.base.system.Result(result, null);
			}return new info.pppc.base.system.Result(null, new info.pppc.base.system.InvocationException("Illegal signature."));
		} catch (Throwable t) {
			return new info.pppc.base.system.Result(null, t);
		}
	}
	
}
