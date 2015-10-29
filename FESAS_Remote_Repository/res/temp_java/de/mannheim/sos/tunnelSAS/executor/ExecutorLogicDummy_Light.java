package de.mannheim.wifo2.fesas.logicRepository.logicElements.executor.de.mannheim.sos.tunnelSAS.executor;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Environment;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Lamp;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Tunnel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.BrightnessDTO;
import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IExecutorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class ExecutorLogicDummy_Light extends AbstractLogic implements
		IExecutorLogic {

	public ExecutorLogicDummy_Light() {
		// needed for Logic Loading mechanism
		super();
		this.informationType = InformationType.Executing_SIMPLESAS;
		supportedInformationTypes.add(InformationType.Planning_SIMPLESAS);
	}

	public ExecutorLogicDummy_Light(IAdaptationLogic adaptationLogic,
			IInformationType informationType) {
		super(adaptationLogic, InformationCategory.EXECUTOR, informationType);
		this.informationType = InformationType.Executing_SIMPLESAS;

		// TODO: create it based on meta data
		supportedInformationTypes.add(InformationType.Planning_SIMPLESAS);
	}

	private static final LogicType type = LogicType.EXECUTOR;
	private static final String id = "ExecutorLogicDummy_Light";

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
		// Senden von Lichteinstellungswerten
		// -> REST-Web Service (PUT). IP-Addresse ist fest hinterlegt.

		// Attribute des Service
		// - Array von Lampen
		// jede Lampe hat Attribute:
		// - lampBrightness

		if (data instanceof String) {
			try {
				Gson gson = new Gson();

				BrightnessDTO brightnessValues = gson.fromJson((String) data,
						BrightnessDTO.class);

				JsonArray jsonArray = new JsonArray();

				for (int value : brightnessValues.getLampBrightness()) {
					jsonArray.add(value);
				}
				
				System.out.println("Executor - Sending Values: " + gson.toJson(jsonArray));
				
				HttpClient httpClient = HttpClientBuilder.create().build();
				URIBuilder builder = new URIBuilder(
						"http://localhost:8080/TunnelController/services/tunnel/control")
						.addParameter("brightness", gson.toJson(jsonArray));

				HttpPut putRequest = new HttpPut(builder.build());

				putRequest.addHeader("accept", "application/json");

				HttpResponse response = httpClient.execute(putRequest);

				if (response.getStatusLine().getStatusCode() != 204) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatusLine().getStatusCode());
				}

				httpClient.getConnectionManager().shutdown();
			
				return "It ends here.";

			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
