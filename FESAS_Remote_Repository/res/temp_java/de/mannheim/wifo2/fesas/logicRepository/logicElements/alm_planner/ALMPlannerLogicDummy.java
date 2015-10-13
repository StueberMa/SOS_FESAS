package de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_planner.de.mannheim.wifo2.fesas.logicRepository.logicElements.alm_planner;


import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.In;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.StdIn;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.StdOut;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.Bag;
import java.util.HashMap;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.wifo2.graphLibrary.Graph;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IALMMonitorLogic;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.IAdaptationLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.IInformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationType;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeRecord;
import de.mannheim.wifo2.fesas.tools.alm.ConvertToString;

public class ALMPlannerLogicDummy extends AbstractLogic implements IALMMonitorLogic {
	
	public ALMPlannerLogicDummy() {
		super();
		threshold = 5;
		
		//TODO: create it based on meta data
		this.informationType = InformationType.Planning_ALM;
		supportedInformationTypes.add(InformationType.Analyzing_ALM);
	}
	
	public ALMPlannerLogicDummy(IAdaptationLogic adaptationLogic, IInformationType informationType) {
		super(adaptationLogic,InformationCategory.ALM_PLANNER,informationType);
		threshold = 5;
		//TODO: create it based on meta data
		this.informationType = InformationType.Planning_ALM;
		supportedInformationTypes.add(InformationType.Analyzing_ALM);
	}

	private static final LogicType type = LogicType.ALM_PLANNER;
	private static final String id = "ALMPlannerLogicDummy";
	
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
			try {
				this.handleReceivedData(dataAsString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}
	
	/**
	 * This method handles the received Data. it is converted back into graph
	 * and associated hashmap, then actions on the graph are being planned.
	 * 
	 * @param dat
	 *            incoming data
	 * @throws Exception
	 *             is thrown when the incoming data does not fit the desired
	 *             format.
	 */
	private void handleReceivedData(String dat) throws Exception {
		// graph and associated Hashmap are now being converted back to then
		// plan a change
		String sep2 = "_._._"; // the data seperator
		String[] splittedData = new String[dat.split(sep2).length];
		splittedData = dat.split(sep2);

		if (splittedData.length != 2) {
			// Something in Sending went wrong.
			System.out.println("LENGTH NOT IN RANGE " + splittedData.length);
			throw new Exception();

		}
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		map = ConvertToString.stringToStringHashMap(splittedData[1]);

		// Graph needs to be recreated.

		String graphedString = splittedData[0];

		Graph oldGraph = this.recreateGraphfromString(graphedString);

		System.out.println("RECREATED" + "\n" + oldGraph.toString());

		// HashMap and Graph have been recreated and are available to the
		// Planner now

		// Start Planning.

		@SuppressWarnings("unused")
		// Will be useful for further work
		int numberOfMAPE = oldGraph.V() - 5;

		// Calculate how many Monitors, Analyzers, Planners, and Executors there
		// are

		int numberOfMonitors = oldGraph.degree(1) - 1;
		int numberOfAnalyzers = oldGraph.degree(2) - 1;
		int numberOfPlanners = oldGraph.degree(3) - 1;
		int numberOfExecutors = oldGraph.degree(4) - 1;

		System.out.println("THERE ARE: " + "\n" + numberOfMonitors
				+ " MONITORS " + numberOfAnalyzers + " ANALYZERS "
				+ numberOfPlanners + " PLANNERS " + numberOfExecutors
				+ " EXECUTORS ");

		// LETS PLAN

		Graph plannedGraph = this.planAction(oldGraph);

		// CALL EXECUTOR?

		// Sending should be a String containing old HashMap, old Graph, and new
		// Graph

		String toSend = oldGraph.toString() + sep2 + plannedGraph.toString()
				+ sep2 + ConvertToString.stringHashMapToString(map);

		this.sendData(toSend);

	}

	/**
	 * The method where the actual planning is happening. For now, a random MAPE
	 * Instance, and therefore one vertice is added to the graph
	 * 
	 * @param g
	 *            a graph as an input on which the planning happens
	 * @return the modified graph
	 */
	private Graph planAction(Graph g) {
		// TODO Auto-generated method stub

		// FIRST SAMPLE:
		// ADD ONE RANDOM MAPE
		int newNumberVertices = g.V() + 1;
		Graph modifiedGraph = new Graph(newNumberVertices);
		// Only Option to add a Vertice is to recreate a new Graph
		// reUse of RebuildGraph

		String[] splitupGraph = g.toString().split("\\n");
		// The old Graph will be transformed now into a new graph with one more
		// vertice.
		int range = 0;
		boolean mac = false;
		String[] underArray = new String[splitupGraph.length];

		for (int i = 1; i < splitupGraph.length; i++) {
			int index = 0;
			index = splitupGraph[i].indexOf(":");
			underArray[i] = splitupGraph[i].substring(index + 2);
			char u = (char) 32;

			String[] edgesToCreate = new String[underArray[i].split(String
					.valueOf(u)).length];

			edgesToCreate = underArray[i].split(String.valueOf(u));		

			if (i == 1) {
				if (edgesToCreate[edgesToCreate.length - 1].equals("1")) {
					mac = true;
								}
			}

			if (mac) {
				range = edgesToCreate.length;
			} else {
				range = edgesToCreate.length - 1;
			}

			String[] edgesShort = new String[range];

			for (int iter = 0; iter < edgesShort.length; iter++) {
				edgesShort[iter] = edgesToCreate[iter];
			}

			String[] reversed = new String[edgesShort.length];

			for (int iter = 0; iter < reversed.length; iter++) {
				reversed[iter] = edgesShort[reversed.length - 1 - iter];
			}

			for (int iter = 0; iter < reversed.length; iter++) {

				if (Integer.parseInt(reversed[iter]) > i - 1) {

					modifiedGraph.addEdge(i - 1, Integer.parseInt(reversed[iter]));
				}
			}
		}

		// ModifiedGraph now has all Vertices and Edges of the old Graph + 1 new
		// Vertice

		int randomizer = 0;
		randomizer = (int) (Math.random() * 4);

		if (randomizer == 0) {
			// CREATE MONITOR
			modifiedGraph.addEdge(1, modifiedGraph.V() - 1);

		}
		if (randomizer == 1) {
			// CREATE ANALYZER
			modifiedGraph.addEdge(2, modifiedGraph.V() - 1);

		}
		if (randomizer == 2) {
			// CREATE PLANNER
			modifiedGraph.addEdge(3, modifiedGraph.V() - 1);
		}
		if (randomizer == 3) {
			// CREATE EXECUTOR

			modifiedGraph.addEdge(4, modifiedGraph.V() - 1);
		}

		// NEW GRAPH UP

		int numberOfMonitors = modifiedGraph.degree(1) - 1;
		int numberOfAnalyzers = modifiedGraph.degree(2) - 1;
		int numberOfPlanners = modifiedGraph.degree(3) - 1;
		int numberOfExecutors = modifiedGraph.degree(4) - 1;

		System.out.println("MODIFIED GRAPH NOW THERE ARE: " + "\n"
				+ numberOfMonitors + " MONITORS " + numberOfAnalyzers
				+ " ANALYZERS " + numberOfPlanners + " PLANNERS "
				+ numberOfExecutors + " EXECUTORS ");
		System.out.println(modifiedGraph.toString());

		return modifiedGraph;

	}

	/**
	 * Method to recreate the graph out of the .toString() representation
	 * 
	 * @param graphString
	 *            the input string
	 * @return the recreated graph
	 */
	public Graph recreateGraphfromString(String graphString) {
		// String[] splitupGraph = graphString.split("\\n");
		int range = 0;
		boolean mac = false;
		String[] splitupGraph = new String[graphString.split("\\n").length];
		String[] underArray = new String[splitupGraph.length];
		splitupGraph = graphString.split("\\n");
		Graph g = new Graph(splitupGraph.length - 1);
		for (int i = 1; i < splitupGraph.length; i++) {
			int index = 0;
			index = splitupGraph[i].indexOf(":");
			underArray[i] = splitupGraph[i].substring(index + 2);
			char u = (char) 32;

			String[] edgesToCreate = new String[underArray[i].split(String
					.valueOf(u)).length];

			edgesToCreate = underArray[i].split(String.valueOf(u));		

			if (i == 1) {
				if (edgesToCreate[edgesToCreate.length - 1].equals("1")) {
					mac = true;
								}
			}

			if (mac) {
				range = edgesToCreate.length;
			} else {
				range = edgesToCreate.length - 1;
			}

			String[] edgesShort = new String[range];

			for (int iter = 0; iter < edgesShort.length; iter++) {
				edgesShort[iter] = edgesToCreate[iter];
			}

			String[] reversed = new String[edgesShort.length];

			for (int iter = 0; iter < reversed.length; iter++) {
				reversed[iter] = edgesShort[reversed.length - 1 - iter];
			}

			for (int iter = 0; iter < reversed.length; iter++) {

				if (Integer.parseInt(reversed[iter]) > i - 1) {

					g.addEdge(i - 1, Integer.parseInt(reversed[iter]));
				}
			}
		}
		return g;
	}

}
