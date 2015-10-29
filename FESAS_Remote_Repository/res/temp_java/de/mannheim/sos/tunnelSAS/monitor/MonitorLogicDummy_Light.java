package de.mannheim.wifo2.fesas.logicRepository.logicElements.monitor.de.mannheim.sos.tunnelSAS.monitor;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.monitor.MonitorLogicAbstractDummy;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Environment;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Lamp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model.Tunnel;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IMonitorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;

public class MonitorLogicDummy_Light extends MonitorLogicAbstractDummy implements IMonitorLogic {
	
	public MonitorLogicDummy_Light() {
		// needed for Logic Loading mechanism
		super();
		this.informationType = InformationType.Monitoring_SIMPLESAS;
		expectedMonitorValues = 1;
	}
	
	public MonitorLogicDummy_Light(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,informationType);
		this.informationType = InformationType.Monitoring_SIMPLESAS;
		
		expectedMonitorValues = 1;
	
	}

	private static final LogicType type = LogicType.MONITOR;
	private static final String id = "MonitorLogicDummy_Light";
	
	@Override
	public LogicType getLogicType() {
		return type;
	}

	@Override
	public String getID() {
		return id;
	}
	
//	public static void main(String args[]) {
//		try {
//			HttpClient httpClient = HttpClientBuilder.create().build();
//			HttpGet getRequest = new HttpGet("http://localhost:8080/TunnelController/services/tunnel/status");
//			getRequest.addHeader("accept", "application/json");
//
//			HttpResponse response = httpClient.execute(getRequest);
//
//			if (response.getStatusLine().getStatusCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//				   + response.getStatusLine().getStatusCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//
//			
//			System.out.println("Output from Server .... \n");
//
//            
//            String output;
//            String finOutput = "";
//			while ((output = br.readLine()) != null) {
//				finOutput = finOutput + output;
//			}
//			System.out.println(finOutput);
//			Gson gson = new com.google.gson.Gson();
//			
//            Tunnel tunnelResponse = gson.fromJson(finOutput, Tunnel.class);
//			System.out.println(tunnelResponse.getLamps().get(1).getBrightness());
//		  } catch (ClientProtocolException e) {
//			e.printStackTrace();
//		  } catch (IOException e) {
//			e.printStackTrace();
//		  }
//			
//	}
	@Override
	public String callLogic(Object data) {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet("http://localhost:8080/TunnelController/services/tunnel/status");
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			
			System.out.println("Output from Server .... \n");

            
            String output;
            String finOutput = "";
			while ((output = br.readLine()) != null) {
				finOutput = finOutput + output;
			}
			System.out.println(finOutput);
			Gson gson = new Gson();
			
            Tunnel tunnelResponse = gson.fromJson(finOutput, Tunnel.class);
			this.sendData(gson.toJson(tunnelResponse));
			return gson.toJson(tunnelResponse);
		  } catch (ClientProtocolException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		return null;
	}
}
