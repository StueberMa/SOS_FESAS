
package de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry;

/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class IDRegistrySkeleton extends info.pppc.base.system.Skeleton  {
	
	/**
	 * Default constructor to create a new object.
	 */
	public IDRegistrySkeleton() { }
	
	/**
	 * Dispatch method that dispatches incoming invocations to the skeleton's implementation.
	 *
	 * @param method The signature of the method to call.
	 * @param args The parameters of the method call.
	 * @return The result of the method call.
	 */
	@Override
	protected info.pppc.base.system.Result dispatch(String method, Object[] args) {
		de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry impl = (de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry)getImplementation();
		try {
			if (method.equals("info.pppc.base.system.ObjectID getObjectID(java.lang.String)")) {
				Object result = impl.getObjectID((java.lang.String)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void addID(java.lang.String, info.pppc.base.system.ObjectID)")) {
				Object result = null;
				impl.addID((java.lang.String)args[0], (info.pppc.base.system.ObjectID)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("info.pppc.base.system.ObjectID externalRequest(java.lang.String)")) {
				Object result = impl.externalRequest((java.lang.String)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}return new info.pppc.base.system.Result(null, new info.pppc.base.system.InvocationException("Illegal signature."));
		} catch (Throwable t) {
			return new info.pppc.base.system.Result(null, t);
		}
	}
	
}
