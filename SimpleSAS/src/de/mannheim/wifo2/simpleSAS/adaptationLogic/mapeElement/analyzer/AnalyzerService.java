package de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.analyzer;

import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.analyzer.IAnalyzer;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.AdaptationLogicService;


public class AnalyzerService extends AdaptationLogicService implements IAnalyzer {
	
	public AnalyzerService(String name, String fesasID) {
		super(name, fesasID);
		category = InformationCategory.ANALYZER;
	}

}
