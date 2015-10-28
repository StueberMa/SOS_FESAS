package de.mannheim.sos.tunnelSAS.monitor;

import java.util.Random;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IMonitorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public abstract class MonitorLogicAbstractDummy extends AbstractLogic implements IMonitorLogic {

	protected int expectedMonitorValues = 1;
	
	public MonitorLogicAbstractDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.MONITOR,informationType);
		
		//TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Sensor_SIMPLESAS);
	}

	public MonitorLogicAbstractDummy() {
		// needed for Logic Loading mechanism
		super();
		
		//TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Sensor_SIMPLESAS);
	}

	private static final LogicType type = LogicType.MONITOR;
	private static final String id = "MonitorLogicDummy";
	
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
		
		String monitoringResult = produceMonitorData(expectedMonitorValues);
		this.sendData(monitoringResult);
		
		return monitoringResult;
	}
	
	protected String produceMonitorData(int noOfValues) {
		String result = "";
		Random generator = new Random();
		
		for(int i = 0; i < noOfValues; i++) {
			result += "_" + (generator.nextInt(10) + 1);
		}
		
		return result.substring(1);		
	}

}
