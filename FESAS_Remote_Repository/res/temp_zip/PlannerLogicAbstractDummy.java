package  de.mannheim.wifo2.dummyplanner;

import java.util.ArrayList;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IPlannerLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public abstract class PlannerLogicAbstractDummy extends AbstractLogic implements IPlannerLogic {

	protected int threshold;
	
	public PlannerLogicAbstractDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.PLANNER,informationType);
		threshold = 5;
		
		//TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Analyzing_SIMPLESAS);
	}

	public PlannerLogicAbstractDummy() {
		// needed for Logic Loading mechanism
		super();
		
		//TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Analyzing_SIMPLESAS);
	}

	private static final LogicType type = LogicType.PLANNER;
	private static final String id = "PlannerLogicAbstractDummy";
	
	@Override
	public LogicType getLogicType() {
		return type;
	}

	@Override
	public String getID() {
		return id;
	}

	protected boolean isAboveTreshold(int value) {
		return value >= threshold;
	}
	
	protected ArrayList<Integer> extractMonitorValues(String analyzeResult) {
		String monitorValue;
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		int firstUnderscore = analyzeResult.indexOf("_");
		int secondUnderscore = analyzeResult.indexOf("_", firstUnderscore + 1);
		
		while (secondUnderscore < analyzeResult.length()) {
			firstUnderscore = secondUnderscore + 1;
			secondUnderscore = analyzeResult.indexOf("_", firstUnderscore);
			if (secondUnderscore == -1) {
				secondUnderscore = analyzeResult.length();
			}
			monitorValue = analyzeResult.substring(firstUnderscore,secondUnderscore);
			System.out.println("Planner : " + monitorValue);
			values.add(new Integer(monitorValue));
		}
		
		return values;
	}
	

}
