package de.mannheim.sos.tunnelSAS.monitor;

import java.util.Random;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IMonitorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class MonitorLogicDummy_Light extends MonitorLogicAbstractDummy implements IMonitorLogic {
	
	public MonitorLogicDummy_Light() {
		// needed for Logic Loading mechanism
		super();
		this.informationType = InformationType.Monitoring_SIMPLESAS;
		expectedMonitorValues = 1;
	}
	
	public MonitorLogicDummy_Light(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,informationType);
		this.informationType = InformationType.Monitoring_SIMPLESAS;
		
		expectedMonitorValues = 1;
	
	}

	private static final LogicType type = LogicType.MONITOR;
	private static final String id = "MonitorLogicDummy_Light";
	
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
		// Separaten Service für Ermittlung von Sensordaten ansprechen und Werte zurückgeben
		// -> REST-Web Service (GET). IP-Addresse ist fest hinterlegt.
		
		// Attribute des Service
		// - Environment
		// Attr. envBrightness
		// - Array von Lampen 
		// Attr. lampBrightness
		
		
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
