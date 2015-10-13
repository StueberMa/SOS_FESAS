package  de.mannheim.wifo2.dummyplanner;

import java.util.ArrayList;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IPlannerLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class PlannerLogicDummy_VarDecentralized extends PlannerLogicAbstractDummy implements IPlannerLogic {

	public PlannerLogicDummy_VarDecentralized() {
		// needed for Logic Loading mechanism
		super();
		threshold = 5;
		this.informationType = InformationType.Planning_SIMPLESAS;
	}
	
	public PlannerLogicDummy_VarDecentralized(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,informationType);
		threshold = 5;
		this.informationType = InformationType.Planning_SIMPLESAS;
	}

	private static final LogicType type = LogicType.PLANNER;
	private static final String id = "PlannerLogicDummy_VarDecentralized";
	
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
			//Format of input from analyzer: FALSE_AV_INPUT
			
			System.out.println(new PlanData().toString());
			System.out.println(new PlanData2().toString());
			
			String input = (String) data;
			ArrayList<Integer> values = extractMonitorValues(input);
			
			ArrayList<String> plan = new ArrayList<String>();
			
			for (Integer i : values) {
				if (isAboveTreshold(i)) plan.add("Increase variable " + i);  
			}
			
			String result = "";
			for (String p: plan) {
				result += p + "_";
			}
			
			if (result.endsWith("_")) {
				result = result.substring(0, result.lastIndexOf("_"));
			}
			
			this.sendData(result);
			
			return result;
		}
		return null;
	}

}
