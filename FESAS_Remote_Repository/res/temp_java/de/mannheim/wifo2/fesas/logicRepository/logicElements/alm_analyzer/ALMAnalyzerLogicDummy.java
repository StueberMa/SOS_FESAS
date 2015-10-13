package de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_analyzer.de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_analyzer;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.In;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.StdIn;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.StdOut;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.Bag;
import java.util.HashMap;
import java.util.Map.Entry;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.Graph;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IALMAnalyzerLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeRecord;
import de.mannheim.wifo2.fesas.tools.alm.ConvertToString;

public class ALMAnalyzerLogicDummy extends AbstractLogic implements IALMAnalyzerLogic {
	
	public ALMAnalyzerLogicDummy() {
		super();
		threshold = 5;
		
		//TODO: create it based on meta data
		this.informationType = InformationType.Analyzing_ALM;
		supportedInformationTypes.add(InformationType.Monitoring_ALM);
	}
	
	public ALMAnalyzerLogicDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.ALM_ANALYZER,informationType);
		threshold = 5;
		
		//TODO: create it based on meta data
		this.informationType = InformationType.Analyzing_ALM;
		supportedInformationTypes.add(InformationType.Monitoring_ALM);
	}

	private static final LogicType type = LogicType.ALM_ANALYZER;
	private static final String id = "ALMAnalyzerLogicDummy";
	
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
		
		String dataAsString = null;
		
		if (data instanceof KnowledgeRecord) {
			KnowledgeRecord kRecord = (KnowledgeRecord) data;
			Object kData = kRecord.getData();
			if (kData instanceof String) {
				dataAsString = (String) kData;
			} else {
				dataAsString = "";
			}
		} else {
			dataAsString = "";
		}
		
		if (dataAsString != null && dataAsString != "") {
			this.handleReceivedData(dataAsString);
			return "blaBlubb";
		}
		
		return null;
		
	}
	
	/**
	 * This method uses the received data from the monitor and analyzes it
	 * Then, for each ALM Proxy a graph is created and saved
	 * Finally, the graph and the HashMap with its information
	 * gets sent to the Planner
	 * @param data incoming data from the monitor
	 */
		public void handleReceivedData(String data) {

			if (data != "") {
				String[] stringsToBeConverted = null;

				// Data is being converted into HashMaps, and then into Graphs.
				stringsToBeConverted = data.split("\\n");
				@SuppressWarnings("unchecked")
				HashMap<String, String[]>[] maps = new HashMap[stringsToBeConverted.length];
				for (int i = 0; i < stringsToBeConverted.length; i++) {

					maps[i] = ConvertToString.stringToStringHashMap(stringsToBeConverted[i]);
				}
				// lets test the converted HashMap(s) and print some values

				/*
				 * HashMap<String,String[]> testMap = maps[0];
				 * 
				 * for (Entry<String, String[]> entry : testMap.entrySet()) { String
				 * costring ="";
				 * 
				 * for (int i =0;i<entry.getValue().length;i++){ costring +=
				 * entry.getValue()[i]; } System.out.println("KEY_" + entry.getKey()
				 * + "-" + "VALUES_" + costring); }
				 */

				// map conversion
				Graph[] graphs = new Graph[maps.length];
				for (int i = 0; i < maps.length; i++) {

					graphs[i] = createGraph(maps[i]);

					System.out.println("GRAPH IS_" + graphs[i].toString());

					// Associate Graph to Hashmap now, in order for Planner to be
					// able
					// to use all Info

					@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
					HashMap<HashMap, Graph> associatedGraphs = new<HashMap, Graph> HashMap();
					associatedGraphs.put(maps[i], graphs[i]);
					String toSend = "";
					String sep2 = "_._._";

					toSend = graphs[i].toString() + sep2
							+ ConvertToString.stringHashMapToString(maps[i]);
					
					//Data is prepared and graph ready for sending. 
					//Sending to ALM Planner happens now.

					this.sendData(toSend);

				}
			}

		}
	/**
	 * This method is being used to create a graph out of a HashMap
	 * @param hashMap the incoming data
	 * @return
	 */
		private Graph createGraph(HashMap<String, String[]> hashMap) {

			int hashMapEntrys = hashMap.size();
			int numberVertices = hashMapEntrys + 1 + 4;
			// number of Vertices will be the number of MAPE classes,
			// which is equal to the hashmap size + ONE Root vertice,
			// and FOUR vertices representing the overall
			// MONITOR, ANALYZER, EXECUTER AND PLANNER
			int index = 4 + 1;
			@SuppressWarnings("unused")
			int numberMape = numberVertices - index;
			Graph t = new Graph(numberVertices);
			t.addEdge(0, 1);
			t.addEdge(0, 2);
			t.addEdge(0, 3);
			t.addEdge(0, 4);

			// Graph initialized and edges from root to
			// Monitor, Analyzer, Planner, Executer given
			// now Edges from those 4 MAPE categories to specific instances need to
			// be given.

			for (Entry<String, String[]> entry : hashMap.entrySet()) {
				for (int i = 0; i < entry.getValue().length; i++) {
					if (entry.getValue()[i].contains("Monitor")) {
						// add edge to the Monitor (1)
						t.addEdge(1, index);
						index = index + 1;
						break;
					}
					if (entry.getValue()[i].contains("Analyzer")) {
						t.addEdge(2, index);
						index = index + 1;
						break;
					}
					if (entry.getValue()[i].contains("Planner")) {
						t.addEdge(3, index);
						index = index + 1;
						break;
					}
					if (entry.getValue()[i].contains("Executor")) {
						t.addEdge(4, index);
						index = index + 1;
						break;
					}
				}
			}


		//	System.out.println("----- GRAPH CREATED -----");
			// Graph has been created with the number of vertices and the correct edges.
			return t;

		}

}
