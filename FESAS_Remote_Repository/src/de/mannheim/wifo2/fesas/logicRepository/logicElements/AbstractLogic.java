package de.mannheim.wifo2.fesas.logicRepository.logicElements;

import java.util.ArrayList;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.ILogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord;
import de.mannheim.wifo2.fesas.settings.Constants;

public abstract class AbstractLogic implements ILogic {

	// for sending or saving data at the end of operations
	protected IAdaptationLogic adaptationLogic;
	
	/**
	 * CONTEXT,	MONITORING,	ANALYZING,	PLANNING,	EXECUTING,	SENSOR
	 */
	protected IInformationCategory informationCategory;
	
	/**
	 * see package de.mannheim.wifo2.smartHighway.data.fesasData.adaptationLogic.communication.information
	 */
	//TODO: change to inputType (change all logics)
	protected IInformationType informationType;
	
	//TODO: change to outputType (change all logics)
	protected ArrayList<IInformationType> supportedInformationTypes = new ArrayList<IInformationType>();
	
	protected double threshold;
	
	
	public AbstractLogic(IAdaptationLogic adaptationLogic, IInformationCategory informationCategory, 
			IInformationType informationType) {
//		if(Constants.DEBUG_LOGIC) System.out.println("Logic info type before usage : " + getClass().getSimpleName() 
//				+ "-" + informationType);
		this.adaptationLogic = adaptationLogic;
		this.informationCategory = informationCategory;
		this.informationType = informationType;
//		System.out.println("Type in Logic:" + informationType.toString());
		threshold = 1.0;
		if(Constants.DEBUG_LOGIC) System.out.println("Logic info type : " + getClass().getSimpleName() 
				+ "-" + informationType);
		
	}

	public AbstractLogic() {
		// needed for Logic Loading mechanism
	}

	@Override
	public boolean isCompatibleDataType(String dataType) {
		for (IInformationType type : supportedInformationTypes) {
			if (type.toString().equals(dataType)) return true;
		}
		return false;
	}	
	
	@Override
	public String getInformationCategoryAsString() {
		return informationCategory.toString();
	}


	@Override
	public String getInformationTypeAsString() {
		return informationType.toString();
	}
	
	@Override
	public String callLogic(Object data) {
		
		// Null object for data -> Error!
		if (data == null) {
			if(Constants.DEBUG_LOGIC) System.out.println(getClass() + " - call Logic: null received");
			return "NULL data object - Error in " + informationCategory;
		} 
		
		
		// just return the event received
		if (data instanceof IKnowledgeRecord) {
			if(Constants.DEBUG_LOGIC) System.out.println(getClass() + " - call Logic: " + ((IKnowledgeRecord) data).toString());
			sendData(data);
			return "Logic (" + informationCategory + " received data : " + ((IKnowledgeRecord) data).toString();
		}
		
		
		// called from loop or received event
		if (data instanceof String) {
			if (((String) data).equals("CALLED_FROM_LOOP")) {
				// maybe send an event
				if (Math.random() > threshold) {
					data = "" + informationCategory + " - " + informationType;
					if(Constants.DEBUG_LOGIC) System.out.println(getClass() + " - call Logic: Send event");
					sendData(data);
					return "CALLED_FROM_LOOP - Event sent by " + informationCategory;
				}
				if(Constants.DEBUG_LOGIC) System.out.println(getClass() + " - call Logic: no event (under threshold)");
				return "CALLED_FROM_LOOP - No event by " + informationCategory;
			}
		}
		
		return "SCHMUU!!! by " + informationCategory; 
		
	}

	protected void sendData(Object data) {
		System.out.println("AL_AbstractLogic : " + adaptationLogic);
		System.out.println("InfoType_AbstractLogic : " + informationType.toString());
		System.out.println("Data_AbstractLogic : " + data);
		adaptationLogic.prepareDataForSending(data,informationType.toString());	
	}
	
	@Override
	public IInformationCategory getInformationCategory() {
		return informationCategory;
	}

	@Override
	public void setInformationCategory(IInformationCategory informationCategory) {
		this.informationCategory = informationCategory;
	}

	@Override
	public IInformationType getInformationType() {
		return informationType;
	}

	@Override
	public void setAL(IAdaptationLogic al) {
		
		this.adaptationLogic = al;
		System.out.println("LOGIC - AL : " + al.getClass().getName());
		
	}
	
	
} 
