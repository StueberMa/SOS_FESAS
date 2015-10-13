package de.mannheim.wifo2.fesas.logicRepository.logicElements.monitor.de.mannheim.wifo2.dummymonitor;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.dummymonitor.MonitorLogicAbstractDummy;
import java.util.Random;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IMonitorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class MonitorLogicDummy_1Var extends MonitorLogicAbstractDummy implements IMonitorLogic {
	
	public MonitorLogicDummy_1Var() {
		// needed for Logic Loading mechanism
		super();
		this.informationType = InformationType.Monitoring_SIMPLESAS;
		expectedMonitorValues = 1;
	}
	
	public MonitorLogicDummy_1Var(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,informationType);
		threshold = 5;
		this.informationType = InformationType.Monitoring_SIMPLESAS;
		
		expectedMonitorValues = 1;
	
	}

	private static final LogicType type = LogicType.MONITOR;
	private static final String id = "MonitorLogicDummy_1Var";
	
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
