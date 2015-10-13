package de.mannheim.wifo2.fesas.logicRepository.logicElements.executor.de.mannheim.wifo2.dummyplannerexecutor;

import java.util.ArrayList;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IExecutorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class ExecutorLogicDummy_Var1 extends AbstractLogic  implements IExecutorLogic {

	public ExecutorLogicDummy_Var1() {
		// needed for Logic Loading mechanism
		super();
		this.informationType = InformationType.Executing_SIMPLESAS;
		supportedInformationTypes.add(InformationType.Planning_SIMPLESAS);
	}
	
	public ExecutorLogicDummy_Var1(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.EXECUTOR,informationType);
		threshold = 5;
		this.informationType = InformationType.Executing_SIMPLESAS;
		
		//TODO: create it based on meta data
				supportedInformationTypes.add(InformationType.Planning_SIMPLESAS);
	}

	private static final LogicType type = LogicType.EXECUTOR;
	private static final String id = "ExecutorLogicDummy_Var";
	
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
			String input = (String) data;
			
			//TODO: check, whether the plan is for the current system
			
			ArrayList<String> instructions = extractPlan(input);
			
			for (String i : instructions) {
				System.out.println("Instruction: " + i); 
			}
			
			return "No of performed instruction: " + instructions.size();
		}
		return null;
	}
	
	private ArrayList<String> extractPlan(String plan) {
		System.out.println("Executor 1"); 
		
		return values;
	}
	
}
