package de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_executor.de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_executor;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IALMExecutorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class ALMExecutorLogicDummy extends AbstractLogic implements IALMExecutorLogic {
	
	public ALMExecutorLogicDummy() {
		super();
		threshold = 5;
		
		//TODO: create it based on meta data
		this.informationType = InformationType.Executing_ALM;
		supportedInformationTypes.add(InformationType.Planning_ALM);
	}
	
	public ALMExecutorLogicDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.ALM_EXECUTOR,informationType);
		threshold = 5;
		
		//TODO: create it based on meta data
		this.informationType = InformationType.Executing_ALM;
		supportedInformationTypes.add(InformationType.Planning_ALM);
	}

	private static final LogicType type = LogicType.ALM_EXECUTOR;
	private static final String id = "ALMExecutorLogicDummy";
	
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
		return null;
	}

}
