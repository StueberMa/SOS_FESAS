package de.mannheim.wifo2.fesas.logicRepository.remoteLogicRepository;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import de.mannheim.wifo2.fesas.logicRepository.remoteLogicRepository.ui.RemoteLogicRepositoryManagementUI;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.IRemoteLogicRepository;
import de.mannheim.wifo2.fesas.settings.FesasConstants;

public class RemoteLogicRepositoryStarter {

	public static void main(String[] args) {

		try {
			
			//---Logging---
//			initializeLogging();
			
			//For local use with default registry
//			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		
			//set the security manager
			//without it the class loader does not load classes
			System.setProperty("java.security.policy", "AllPermission.policy");
			System.setProperty("java.rmi.server.hostname", FesasConstants.LOGIC_REPOSITORY_ADRESS);
			
			//create a local instance of the object and export the service
			RemoteLogicRepositoryManagementUI insertLogicConsole = new RemoteLogicRepositoryManagementUI();
			insertLogicConsole.setVisible(true);
			
			LocateRegistry.createRegistry(FesasConstants.LOGIC_REPOSITORY_REGISTRY_PORT);
			RemoteLogicRepository rep = RemoteLogicRepository.getInstance();
			IRemoteLogicRepository stub = (IRemoteLogicRepository) UnicastRemoteObject.exportObject(rep, 0);

			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(FesasConstants.LOGIC_REPOSITORY_IDENTIFIER, stub);
			System.out.println("Logic Repository has been started");
			
			insertLogicConsole.startUI();
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
}
