package de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities.idRegistry;

import info.pppc.base.system.ObjectID;

public interface IIDRegistry {

	public ObjectID getObjectID(String fesasID);
	
	public void addID(String fesasID, ObjectID objectID);
	
	public ObjectID externalRequest(String fesasID);
}
