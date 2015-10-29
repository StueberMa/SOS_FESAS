package de.mannheim.wifo2.fesas.logicRepository.logicElements.planner.de.mannheim.sos.tunnelSAS.planner;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.planner.PlannerLogicAbstractDummy;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Environment;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Lamp;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Tunnel;
import java.util.ArrayList;

import com.google.gson.Gson;

import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.BrightnessDTO;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IPlannerLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class PlannerLogicDummy_Light extends PlannerLogicAbstractDummy
		implements IPlannerLogic {

	public PlannerLogicDummy_Light() {
		// needed for Logic Loading mechanism
		super();
		threshold = 5;
		this.informationType = InformationType.Planning_SIMPLESAS;
	}

	public PlannerLogicDummy_Light(IAdaptationLogic adaptationLogic,
			IInformationType informationType) {
		super(adaptationLogic, informationType);
		threshold = 5;
		this.informationType = InformationType.Planning_SIMPLESAS;
	}

	private static final LogicType type = LogicType.PLANNER;
	private static final String id = "PlannerLogicDummy_Light";

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
		// Loop über alle Lampen.
		// bis zur Mitte des Arrays:
		// lampBrightness = (envBrightness (oder Vorgänger mit neuer
		// Brightness)) / 1.25
		// nach der Mitte des Arrays:
		// lampBrightness = (envBrightness (oder Vorgänger mit neuer
		// Brightness)) * 1.25

		// Alle Lampen mit aktualisierter lampBrightness

		if (data instanceof String) {
			Gson gson = new Gson();
			System.out.println((String) data);
			BrightnessDTO brightnessValues = gson.fromJson((String) data,
					BrightnessDTO.class);
			ArrayList<Integer> changedBrightnessValues = new ArrayList<Integer>();

			if (brightnessValues.getLampBrightness() != null) {
				int totalBrightness = 0;
				int newBrightness = 0;

				int previousBrightness = brightnessValues.getEnvBrightness();

				int midpoint = brightnessValues.getLampBrightness().size() / 2;

				for (int i = 0; i < brightnessValues.getLampBrightness().size(); i++) {
					if (i <= midpoint) {
						newBrightness = (int) (previousBrightness / 1.4);
					} else {
						newBrightness = (int) (previousBrightness * 1.4);
					}
					totalBrightness = totalBrightness + newBrightness;
					changedBrightnessValues.add(newBrightness);
					previousBrightness = newBrightness;
				}

				BrightnessDTO outputValues = new BrightnessDTO();
				outputValues.setLampBrightness(changedBrightnessValues);
				outputValues.setEnvBrightness(brightnessValues
						.getEnvBrightness());

				System.out.println("Planner - Average Brightness: "
						+ totalBrightness
						/ brightnessValues.getLampBrightness().size());

				this.sendData(gson.toJson(outputValues));
				return gson.toJson(outputValues);
			}
		}
		return null;
	}

}
