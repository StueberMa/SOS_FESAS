package de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.planner;

import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.planner.IPlanner;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.AdaptationLogicService;

public class PlannerService extends AdaptationLogicService implements IPlanner {
	
	public PlannerService(String name, String fesasID) {
		super(name, fesasID);
		category = InformationCategory.PLANNER;
	}

}
