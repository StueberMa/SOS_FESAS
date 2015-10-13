
package de.mannheim.wifo2.fesas.mwImplBase;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class FesasSetUpSkeleton extends info.pppc.base.system.Skeleton  {
	
	/**
	 * Default constructor to create a new object.
	 */
	public FesasSetUpSkeleton() { }
	
	/**
	 * Dispatch method that dispatches incoming invocations to the skeleton's implementation.
	 *
	 * @param method The signature of the method to call.
	 * @param args The parameters of the method call.
	 * @return The result of the method call.
	 */
	@SuppressWarnings("unchecked")
	protected info.pppc.base.system.Result dispatch(String method, Object[] args) {
		de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp impl = (de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp)getImplementation();
		try {
			if (method.equals("java.lang.String getDeviceName()")) {
				Object result = impl.getDeviceName();
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void startElement(java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.util.HashMap)")) {
				Object result = null;
				impl.startElement((java.lang.String)args[0], (java.lang.String)args[1], (java.util.ArrayList<Contract>)args[2], (java.lang.String)args[3], (java.lang.String)args[4], (java.util.HashMap<String, String>)args[5]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType getDeviceType()")) {
				Object result = impl.getDeviceType();
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void setDeviceProperty(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property)")) {
				Object result = null;
				impl.setDeviceProperty((de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.String implementAdaptationLogicElement(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)")) {
				Object result = impl.implementAdaptationLogicElement((java.lang.String)args[0], (java.util.ArrayList<Contract>)args[1], (java.lang.String)args[2], (java.lang.String)args[3], (java.lang.String)args[4]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void setDeviceType(int)")) {
				Object result = null;
				impl.setDeviceType(((Integer)args[0]).intValue());
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void implementCommunicationStructure(java.lang.String, java.lang.String, java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)")) {
				Object result = null;
				impl.implementCommunicationStructure((java.lang.String)args[0], (java.lang.String)args[1], (java.lang.String)args[2], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType)args[3], (de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)args[4]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("java.lang.String getFESASID()")) {
				Object result = impl.getFESASID();
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void setElementProperty(java.lang.String, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property)")) {
				Object result = null;
				impl.setElementProperty((java.lang.String)args[0], (de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void setDeviceName(java.lang.String)")) {
				Object result = null;
				impl.setDeviceName((java.lang.String)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void setFESASID(java.lang.String)")) {
				Object result = null;
				impl.setFESASID((java.lang.String)args[0]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void configureSetupService(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType)")) {
				Object result = null;
				impl.configureSetupService((de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID)args[0], (de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType)args[1]);
				return new info.pppc.base.system.Result(result, null);
			}
			else if (method.equals("void startAdaptationLogic()")) {
				Object result = null;
				impl.startAdaptationLogic();
				return new info.pppc.base.system.Result(result, null);
			}return new info.pppc.base.system.Result(null, new info.pppc.base.system.InvocationException("Illegal signature."));
		} catch (Throwable t) {
			return new info.pppc.base.system.Result(null, t);
		}
	}
	
}
