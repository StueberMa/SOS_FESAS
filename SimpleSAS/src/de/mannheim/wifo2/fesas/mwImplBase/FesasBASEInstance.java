package de.mannheim.wifo2.fesas.mwImplBase;

import info.pppc.base.service.ServiceProperties;
import info.pppc.base.service.ServiceRegistry;
import info.pppc.base.service.ServiceRegistryException;
import info.pppc.base.system.InvocationBroker;
import info.pppc.base.system.PluginManager;
import info.pppc.base.system.SystemID;
import info.pppc.basex.plugin.discovery.ProactiveDiscovery;
import info.pppc.basex.plugin.semantic.RmiSemantic;
import info.pppc.basex.plugin.serializer.ObjectSerializer;
import info.pppc.basex.plugin.transceiver.MxIPMulticastTransceiver;

import java.io.File;

import de.mannheim.wifo2.fesas.fesasMW.starter.FesasMiddlewareMain;
import de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.ISASStarter;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.IDConversion;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IDRegistryService;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IDRegistrySkeleton;
import de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry.IIDRegistry;
import de.mannheim.wifo2.fesas.settings.FesasConstants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;


/**
 * This class starts BASE. Next, it starts the FESAS middleware.
 * 
 * @author Krupitzer
 *
 */
public class FesasBASEInstance implements ISASStarter {
	
	private static FesasSetUpService setupService = null;
	
//	private static IDeviceType deviceType;
//	private static String deviceName;
//	private static FesasDeviceID fesasDeviceID;
	
//	private static boolean active = false;
	
	/**
	 * Method for starting a new BASE device.
	 * 
	 * @param args 
	 * args[0] = DEVICE_ID;
	 * args[1] = AL config file
	 * args{2] = communication config file
	 * args[3] = scenario name (= folder for config and log files)
	 * args[4] = delay before initialization of AL in ms
	 */
	public static void main(String args[]) {
		
		// load meta data of the system (e.g., ID)
//		deviceType = SmartHighwayDeviceType.getDeviceTypeByNumber(0);
//		fesasDeviceID = new FesasDeviceID(args[0]);
		
		Logging.setFile(args[3] + File.separator + args[0] + "_" + System.currentTimeMillis());

		// setup BASE device (arg: FESAS ID as hex string)
//		String sysIDInHex = setUpBASE(args[0].substring(args[0].indexOf('-') + 1)
//				.replaceAll("_", ""));
		setUpBASE(args[0].substring(args[0].indexOf('-') + 1)
				.replaceAll("_", ""));
		Logging.log(FesasBASEInstance.class.getSimpleName(), 
				"FESAS started", SystemID.SYSTEM.toString(), null, null);
		
		//start FESAS middleware and load data for setup service
		FesasMiddlewareMain middleware = FesasMiddlewareMain.getInstance();
		middleware.configStarter(args);
		new Thread(middleware).start();
		
		// load FESAS Setup Service
		setupService = FesasSetUpService.getInstance();
		middleware.registerSetupService(setupService);
		
	}



	/**
	 * Private method for setting up the BASE instance.
	 */
	private static String setUpBASE(String fesasID) {

		System.setProperty(InvocationBroker.PROPERTY_DEVICE_IDENTIFIER, 
				"0x" + fesasID);

		// add plugins default to the broker
		InvocationBroker invocationBroker = InvocationBroker.getInstance();
		PluginManager pluginManager = invocationBroker.getPluginManager();
		pluginManager.addPlugin(new MxIPMulticastTransceiver());
		pluginManager.addPlugin(new ProactiveDiscovery());
		pluginManager.addPlugin(new RmiSemantic());
		pluginManager.addPlugin(new ObjectSerializer());
		
		//load service registry
		ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();
		
		//System.out.println("SysID in hex : " + IDConversion.transformSysIDToHexString(SystemID.SYSTEM));

		IDRegistrySkeleton skel = new IDRegistrySkeleton();
		IDRegistryService idRegistry = IDRegistryService.getInstance();
		skel.setImplementation(idRegistry);
		
		// register service at local service registry
		try {
			serviceRegistry.export(
					"IDRegistry_" + SystemID.SYSTEM, 
					new String[] { IIDRegistry.class.getName()},
					new ServiceProperties(),
					skel,
					idRegistry
					);
		} catch (ServiceRegistryException e) {
			Logging.errorIntoFile(FesasBASEInstance.class, "Could not register service.", e, 
					SystemID.SYSTEM.toString(), fesasID, null);
			System.exit(1);
		}
		
		try {
			Thread.sleep(FesasConstants.INITIALIZATION_WAITING_TIME);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		return IDConversion.transformSysIDToHexString(SystemID.SYSTEM);
	}
	


}



