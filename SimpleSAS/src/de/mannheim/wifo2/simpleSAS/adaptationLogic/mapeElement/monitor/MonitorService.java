package de.mannheim.wifo2.simpleSAS.adaptationLogic.mapeElement.monitor;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.ILogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.mapeElement.monitor.IMonitor;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.AdaptationLogicService;

public class MonitorService extends AdaptationLogicService implements IMonitor {

	public MonitorService(String name, String fesasID) {
		super(name, fesasID);
		category = InformationCategory.MONITOR;
	}
	
	//TODO: delete this later on
	@Override
	public void perform(info.pppc.base.system.operation.IMonitor monitor) throws Exception {
		synchronized (monitor) {
			try {
				monitor.wait(initFactor * Constants.PERIOD_INIT);
				initFactor++;
			} catch (InterruptedException e) {
				Logging.errorIntoFile(getClass(), "Thread (" + category + ") got interrupted.", 
						e, objectID.toString(), fesasID, null);					
			}
			while (! monitor.isCanceled()) {
				
				try {
					monitor.wait(Constants.PERIOD_CYCLE);
					for (ILogic logic : implementedLogics) {
						callLogic(logic, "CALLED_FROM_LOOP");
					}
				} catch (InterruptedException e) {
					Logging.errorIntoFile(getClass(), "Thread (" + category + ") got interrupted.", 
							e, objectID.toString(), fesasID, null);					
				}
			}
		}
	}

}
