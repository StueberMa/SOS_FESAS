package de.mannheim.sos.tunnelSAS.analyzer;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IAnalyzerLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public abstract class AnalyzerLogicAbstractDummy extends AbstractLogic implements IAnalyzerLogic {
	
	protected int expectedValues;
	protected int threshold;
	
	public AnalyzerLogicAbstractDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.ANALYZER,informationType);
		expectedValues = 2;
		threshold = 5;
		
		//TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Monitoring_SIMPLESAS);
	}

	public AnalyzerLogicAbstractDummy() {
		// needed for Logic Loading mechanism
		super();
		
		//TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Monitoring_SIMPLESAS);
	}

	private static final LogicType type = LogicType.ANALYZER;
	private static final String id = "AnalyzerLogicAbstractDummy";
	
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
		if (data instanceof String) {
			String input = (String) data;
			System.out.println("Analyzer Logic called with: " + data);
			int noOfValues = countLetter(input,'_'); 
			if (noOfValues == expectedValues) {
				int[] values = splitInput(input,noOfValues);
				double average = calculateAverage(values);
				String result = "";
				// above minimum value - TRUE or FALSE? FALSE = Adaptation plan necessary
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
		}
		return null;
	}

	protected double calculateAverage(int[] input) {
		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += input[i];
		}
		return sum / input.length;
	}

	protected int countLetter(String str, char letter) {
		str = str.toLowerCase();		
		letter = Character.toLowerCase(letter);		
		int count = 0;
 
		for (int i = 0; i < str.length(); i++) {
			char currentLetter = str.charAt(i);
			if (currentLetter == letter)
				count++;			
		}
 
		return count+1;
	}
	
	// input = the string to split
	// length = amount of numbers
	protected int[] splitInput(String input, int length) {
		int[] values = new int[length];
		String[] valuesAsString = input.split("_");
		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.parseInt(valuesAsString[i]);
		}
		return values;
	}
	
	
}
