package de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.executor;

import java.util.ArrayList;
import java.util.TimerTask;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.SupportedLanguage;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.ContractProperty;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.executor.IExecutor;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.AdaptationLogicService;

public class ExecutorService extends AdaptationLogicService implements IExecutor {

	public ExecutorService(String name, String fesasID) {
		super(name, fesasID);
		category = InformationCategory.EXECUTOR;

//		Timer timer = new Timer();
//		timer.schedule(new Task(this), 30000, 30000);
	}
	
	class Task extends TimerTask
	{
		private ExecutorService ex;
		
		private Contract c1;
		private Contract c2;
		
		private int last = 1;

		public Task(ExecutorService ex) {
			this.ex = ex;
			
			ArrayList<ContractProperty> prop = new ArrayList<ContractProperty>();
			prop.add(new ContractProperty("SHORT_NAME", "ExecutorLogicDummy_Var1", 0.75));
			c1 = new Contract("ExecutorLogicDummy1_Var", 
					SupportedLanguage.JAVA,	LogicType.EXECUTOR, prop);
			prop.clear();
			prop.add(new ContractProperty("SHORT_NAME", "ExecutorLogicDummy_Var2", 0.75));
			c2 = new Contract("ExecutorLogicDummy2_Var", 
					SupportedLanguage.JAVA,	LogicType.EXECUTOR, prop);
		}
		
		@Override 
		public void run()
		{
			if (last == 1) {
				ex.implementLogic(c2, InformationCategory.EXECUTOR);
				last = 2;
			} else {
				ex.implementLogic(c1, InformationCategory.EXECUTOR);
				last = 1;
			}
		}
	}
}
