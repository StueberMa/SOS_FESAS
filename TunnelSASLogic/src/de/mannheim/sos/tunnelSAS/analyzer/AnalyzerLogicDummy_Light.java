package de.mannheim.sos.tunnelSAS.analyzer;

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
		
		System.out.println("Data type in Analyzer Logic: " + data.getClass());
		if (data instanceof String) {
			String result = "";
			String input = (String) data;
			System.out.println("Analyzer Logic called with: " + data);
			// above minimum value - TRUE or FALSE? FALSE = Adaptation plan necessary
			int average = Integer.parseInt(input);
			if (average < threshold) {
				result += "FALSE_";
			} else {
				result += "TRUE";
				System.out.println("Analyzer Logic returns: " + result);
				return result;
			}
			result += average + "_";
			result += input;
			System.out.println("Analyzer Logic returns: " + result);
			this.sendData(result);
			return result;
		}
		return null;
	}
	
}
