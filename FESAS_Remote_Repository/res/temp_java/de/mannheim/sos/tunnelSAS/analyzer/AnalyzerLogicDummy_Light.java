package de.mannheim.wifo2.fesas.logicRepository.logicElements.analyzer.de.mannheim.sos.tunnelSAS.analyzer;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.analyzer.AnalyzerLogicAbstractDummy;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Environment;
import java.util.ArrayList;

import com.google.gson.Gson;

import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.BrightnessDTO;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Lamp;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Tunnel;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IAnalyzerLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class AnalyzerLogicDummy_Light extends AnalyzerLogicAbstractDummy implements IAnalyzerLogic {
	
	public AnalyzerLogicDummy_Light() {
		// needed for Logic Loading mechanism
		super();
		this.informationType = InformationType.Analyzing_SIMPLESAS;
	}
	
	public AnalyzerLogicDummy_Light(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,informationType);	
		this.informationType = InformationType.Analyzing_SIMPLESAS;
	}

	private static final LogicType type = LogicType.ANALYZER;
	private static final String id = "AnalyzerLogicDummy_Light";
	
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
		// Ermittle die Mitte des Arrays
		
		if (data instanceof String) {
			//Format of input from analyzer: FALSE_AV_INPUT
			
			Gson gson = new Gson();
			
            Tunnel tunnelResponse = gson.fromJson((String) data, Tunnel.class);
			this.sendData(gson.toJson(tunnelResponse));
			
			ArrayList<Integer> lightBrightnessList = new ArrayList<Integer>();
			int totalBrightness = 0; 
			
			for (Lamp lamp : tunnelResponse.getLamps()) {
				lightBrightnessList.add(lamp.getBrightness());
				totalBrightness = totalBrightness + lamp.getBrightness();
			}
			
			System.out.println("Analyzer - Average Brightness: " + totalBrightness / tunnelResponse.getLamps().size());
			
			BrightnessDTO brightnessDTO = new BrightnessDTO();
			
			brightnessDTO.setEnvBrightness(tunnelResponse.getEnvironment().getBrightness());
			brightnessDTO.setLampBrightness(lightBrightnessList);
			
			this.sendData(gson.toJson(brightnessDTO));
			
			return gson.toJson(brightnessDTO);
		}
		return null;
	}
	
}
