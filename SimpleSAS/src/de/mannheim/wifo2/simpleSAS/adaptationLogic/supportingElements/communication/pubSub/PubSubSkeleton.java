
package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class PubSubSkeleton extends info.pppc.base.system.Skeleton  {
	
	/**
	 * Default constructor to create a new object.
	 */
	public PubSubSkeleton() { }
	
	/**
	 * Dispatch method that dispatches incoming invocations to the skeleton's implementation.
	 *
	 * @param method The signature of the method to call.
	 * @param args The parameters of the method call.
	 * @return The result of the method call.
	 */
	protected info.pppc.base.system.Result dispatch(String method, Object[] args) {
		de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub impl = (de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.IPubSub)getImplementation();
		try {
			if (method.equals("void registerByType(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, java.lang.String, java.lang.String)")) {
				Object result = null;
				impl.registerByType((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType)args[0], (java.lang.String)args[1], (java.lang.String)args[2]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void registerByCategory(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory, java.lang.String, java.lang.String)")) {
				Object result = null;
				impl.registerByCategory((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)args[0], (java.lang.String)args[1], (java.lang.String)args[2]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void registerByTypeAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, java.lang.String)")) {
				Object result = null;
				impl.registerByTypeAtAllSystems((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void registerExternalByCategory(java.lang.String, java.lang.String)")) {
				Object result = null;
				impl.registerExternalByCategory((java.lang.String)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void publishEvent(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent)")) {
				Object result = null;
				impl.publishEvent((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void registerByCategoryAtAllSystems(de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory, java.lang.String)")) {
				Object result = null;
				impl.registerByCategoryAtAllSystems((de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void registerExternalByType(java.lang.String, java.lang.String)")) {
				Object result = null;
				impl.registerExternalByType((java.lang.String)args[0], (java.lang.String)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void notify(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)")) {
				Object result = null;
				impl.notify((java.lang.String)args[0], (java.lang.String)args[1], (java.lang.String)args[2], (java.lang.String)args[3], (java.lang.String)args[4], (java.lang.String)args[5], (java.lang.String)args[6]);
				return new info.pppc.base.system.Result(result, null);
			}return new info.pppc.base.system.Result(null, new info.pppc.base.system.InvocationException("Illegal signature."));
		} catch (Throwable t) {
			return new info.pppc.base.system.Result(null, t);
		}
	}
	
}
