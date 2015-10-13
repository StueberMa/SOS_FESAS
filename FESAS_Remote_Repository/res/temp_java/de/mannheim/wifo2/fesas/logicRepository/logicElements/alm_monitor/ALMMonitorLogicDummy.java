package de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_monitor.de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_monitor;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IALMMonitorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeRecord;

public class ALMMonitorLogicDummy extends AbstractLogic implements IALMMonitorLogic {
	
	public ALMMonitorLogicDummy() {
		// needed for Logic Loading mechanism
		super();
		threshold = 5;
		
		//TODO: create it based on meta data
		this.informationType = InformationType.Monitoring_ALM;
		supportedInformationTypes.add(InformationType.Sensor_ALM);
	}
	
	public ALMMonitorLogicDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.ALM_MONITOR,informationType);
		threshold = 5;
		
		this.informationType = InformationType.Monitoring_ALM;
		this.supportedInformationTypes.add(InformationType.Sensor_ALM);
	}

	private static final LogicType type = LogicType.ALM_MONITOR;
	private static final String id = "ALMMonitorLogicDummy";
	
	@Override
	public LogicType getLogicType() {
		return type;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String callLogic(Object data) {
		
		String dataAsString = null;
		
		if (data instanceof KnowledgeRecord) {
			KnowledgeRecord kRecord = (KnowledgeRecord) data;
			Object kData = kRecord.getData();
			if (kData instanceof String) {
				dataAsString = (String) kData;
			} else {
				dataAsString = "";
			}
		} else {
			dataAsString = "";
		}
		
		if (dataAsString != null && dataAsString != "") {
			this.sendData(dataAsString);
			return "BlaBlubb";

		}
		
		return null;
		
	}

}
