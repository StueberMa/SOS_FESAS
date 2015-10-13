
package de.mannheim.wifo2.fesas.mwImplBase;


/**
 * Do not modify this file. This class has been generated.
 * Use inheritance or composition to add functionality.
 *
 * @author 3PC Base Tools
 */
public class FesasSetUpProxy extends info.pppc.base.system.Proxy implements de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp {
	
	/**
	 * Default constructor to create a new object.
	 */
	public FesasSetUpProxy() { }
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @return seede.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public java.lang.String getDeviceName()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getDeviceName()";
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
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult getDeviceNameDef()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getDeviceName()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param elementFesasID see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param logics see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param name see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param infoCategory see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param keyValueProperties see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws java.lang.InstantiationException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws java.lang.IllegalAccessException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	@SuppressWarnings("rawtypes")
	public void startElement(java.lang.String type, java.lang.String elementFesasID, java.util.ArrayList logics, java.lang.String name, java.lang.String infoCategory, java.util.HashMap keyValueProperties) throws java.lang.InstantiationException, java.lang.IllegalAccessException, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException, de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException {
		Object[] __args = new Object[6];
		__args[0] = type;
		__args[1] = elementFesasID;
		__args[2] = logics;
		__args[3] = name;
		__args[4] = infoCategory;
		__args[5] = keyValueProperties;
		String __method = "void startElement(java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.util.HashMap)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof java.lang.InstantiationException) {
				throw (java.lang.InstantiationException)__result.getException();
			}
			if (__result.getException() instanceof java.lang.IllegalAccessException) {
				throw (java.lang.IllegalAccessException)__result.getException();
			}
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException) {
				throw (de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException)__result.getException();
			}
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
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param elementFesasID see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param logics see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param name see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param infoCategory see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param keyValueProperties see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	@SuppressWarnings("rawtypes")
	public info.pppc.base.system.FutureResult startElementDef(java.lang.String type, java.lang.String elementFesasID, java.util.ArrayList logics, java.lang.String name, java.lang.String infoCategory, java.util.HashMap keyValueProperties)  {
		Object[] __args = new Object[6];
		__args[0] = type;
		__args[1] = elementFesasID;
		__args[2] = logics;
		__args[3] = name;
		__args[4] = infoCategory;
		__args[5] = keyValueProperties;
		String __method = "void startElement(java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.util.HashMap)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @return seede.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType getDeviceType()  {
		Object[] __args = new Object[0];
		String __method = "de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType getDeviceType()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			throw (RuntimeException)__result.getException();
		}
		return (de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult getDeviceTypeDef()  {
		Object[] __args = new Object[0];
		String __method = "de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType getDeviceType()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param p see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void setDeviceProperty(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty p)  {
		Object[] __args = new Object[1];
		__args[0] = p;
		String __method = "void setDeviceProperty(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property)";
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
	 * @param p see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult setDevicePropertyDef(de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty p)  {
		Object[] __args = new Object[1];
		__args[0] = p;
		String __method = "void setDeviceProperty(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param alType see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param logics see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param infoCategory see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param name see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param id see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return seede.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws java.lang.InstantiationException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws java.lang.IllegalAccessException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	@SuppressWarnings("rawtypes")
	public java.lang.String implementAdaptationLogicElement(java.lang.String alType, java.util.ArrayList logics, java.lang.String infoCategory, java.lang.String name, java.lang.String id) throws java.lang.InstantiationException, java.lang.IllegalAccessException, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException, de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException {
		Object[] __args = new Object[5];
		__args[0] = alType;
		__args[1] = logics;
		__args[2] = infoCategory;
		__args[3] = name;
		__args[4] = id;
		String __method = "java.lang.String implementAdaptationLogicElement(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof java.lang.InstantiationException) {
				throw (java.lang.InstantiationException)__result.getException();
			}
			if (__result.getException() instanceof java.lang.IllegalAccessException) {
				throw (java.lang.IllegalAccessException)__result.getException();
			}
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException) {
				throw (de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException)__result.getException();
			}
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException) {
				throw (de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicRepositoryNotFoundException)__result.getException();
			}
			throw (RuntimeException)__result.getException();
		}
		return (java.lang.String)__result.getValue();
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param alType see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param logics see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param infoCategory see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param name see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param id see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	@SuppressWarnings("rawtypes")
	public info.pppc.base.system.FutureResult implementAdaptationLogicElementDef(java.lang.String alType, java.util.ArrayList logics, java.lang.String infoCategory, java.lang.String name, java.lang.String id)  {
		Object[] __args = new Object[5];
		__args[0] = alType;
		__args[1] = logics;
		__args[2] = infoCategory;
		__args[3] = name;
		__args[4] = id;
		String __method = "java.lang.String implementAdaptationLogicElement(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void setDeviceType(int type) throws de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException {
		Object[] __args = new Object[1];
		__args[0] = new Integer(type);
		String __method = "void setDeviceType(int)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeSynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException) {
				throw (de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException)__result.getException();
			}
			throw (RuntimeException)__result.getException();
		}
		return ;
	}
	/**
	 * Proxy method that creates and transfers a deferred synchronous invocation.
	 *
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult setDeviceTypeDef(int type)  {
		Object[] __args = new Object[1];
		__args[0] = new Integer(type);
		String __method = "void setDeviceType(int)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	/**
	 * Proxy method that creates and transfers an asynchronous call.
	 *
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @throws de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void setDeviceTypeAsync(int type) throws de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException {
		Object[] __args = new Object[1];
		__args[0] = new Integer(type);
		String __method = "void setDeviceType(int)";
		info.pppc.base.system.Invocation __invocation = proxyCreateAsynchronous(__method, __args);
		info.pppc.base.system.Result __result = proxyInvokeAsynchronous(__invocation);
		if (__result.hasException()) {
			if (__result.getException() instanceof de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException) {
				throw (de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException)__result.getException();
			}
			throw (RuntimeException)__result.getException();
		}
		return ;
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param receiver see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param sender see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param informationType see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param category see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void implementCommunicationStructure(java.lang.String type, java.lang.String receiver, java.lang.String sender, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType informationType, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory category)  {
		Object[] __args = new Object[5];
		__args[0] = type;
		__args[1] = receiver;
		__args[2] = sender;
		__args[3] = informationType;
		__args[4] = category;
		String __method = "void implementCommunicationStructure(java.lang.String, java.lang.String, java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)";
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
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param receiver see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param sender see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param informationType see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param category see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult implementCommunicationStructureDef(java.lang.String type, java.lang.String receiver, java.lang.String sender, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType informationType, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory category)  {
		Object[] __args = new Object[5];
		__args[0] = type;
		__args[1] = receiver;
		__args[2] = sender;
		__args[3] = informationType;
		__args[4] = category;
		String __method = "void implementCommunicationStructure(java.lang.String, java.lang.String, java.lang.String, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType, de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @return seede.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public java.lang.String getFESASID()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getFESASID()";
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
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult getFESASIDDef()  {
		Object[] __args = new Object[0];
		String __method = "java.lang.String getFESASID()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param fesasID see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param p see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void setElementProperty(java.lang.String fesasID, de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty p)  {
		Object[] __args = new Object[2];
		__args[0] = fesasID;
		__args[1] = p;
		String __method = "void setElementProperty(java.lang.String, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property)";
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
	 * @param fesasID see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param p see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult setElementPropertyDef(java.lang.String fesasID, de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.properties.KeyValueProperty p)  {
		Object[] __args = new Object[2];
		__args[0] = fesasID;
		__args[1] = p;
		String __method = "void setElementProperty(java.lang.String, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param deviceName see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void setDeviceName(java.lang.String deviceName)  {
		Object[] __args = new Object[1];
		__args[0] = deviceName;
		String __method = "void setDeviceName(java.lang.String)";
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
	 * @param deviceName see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult setDeviceNameDef(java.lang.String deviceName)  {
		Object[] __args = new Object[1];
		__args[0] = deviceName;
		String __method = "void setDeviceName(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param id see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void setFESASID(java.lang.String id)  {
		Object[] __args = new Object[1];
		__args[0] = id;
		String __method = "void setFESASID(java.lang.String)";
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
	 * @param id see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult setFESASIDDef(java.lang.String id)  {
		Object[] __args = new Object[1];
		__args[0] = id;
		String __method = "void setFESASID(java.lang.String)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @param fesasID see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void configureSetupService(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID fesasID, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType type)  {
		Object[] __args = new Object[2];
		__args[0] = fesasID;
		__args[1] = type;
		String __method = "void configureSetupService(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType)";
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
	 * @param fesasID see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @param type see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult configureSetupServiceDef(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID fesasID, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType type)  {
		Object[] __args = new Object[2];
		__args[0] = fesasID;
		__args[1] = type;
		String __method = "void configureSetupService(de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID, de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType)";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
	/**
	 * Proxy method that creates and transfers an invocation for the interface method.
	 *
	 * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public void startAdaptationLogic()  {
		Object[] __args = new Object[0];
		String __method = "void startAdaptationLogic()";
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
	 * @return A future result that delivers the return value and exceptions. * @see de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp
	 */
	public info.pppc.base.system.FutureResult startAdaptationLogicDef()  {
		Object[] __args = new Object[0];
		String __method = "void startAdaptationLogic()";
		info.pppc.base.system.Invocation __invocation = proxyCreateSynchronous(__method, __args);
		return proxyInvokeDeferred(__invocation);
	}
	
}
