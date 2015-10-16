package de.mannheim.wifo2.fesas.logicRepository.logicElements.knowledge.de.mannheim.wifo2.knowledge;

import java.util.HashMap;

import de.mannheim.wifo2.fesas.logicRepository.logicElements.AbstractLogic;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.logicInterfaces.IKnowledgeLogic;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.information.InformationCategory;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.IKnowledgeRecord;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeNotFoundException;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.knowledge.KnowledgeRecord;
import de.mannheim.wifo2.fesas.settings.Constants;

public class KnowledgeLogicWithHashMap extends AbstractLogic implements IKnowledgeLogic {

	private volatile HashMap<String,KnowledgeRecord> knowledgeBase;
	private static LogicType type;
	private static final String id = "Knowledge_123";
	
	public KnowledgeLogicWithHashMap() {
		super(null,InformationCategory.KNOWLEDGE,null);
		knowledgeBase = new HashMap<String,KnowledgeRecord>();
	}
	
	
	@Override
	public LogicType getLogicType() {
		return type;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public synchronized IKnowledgeRecord getKnowledge(String id) throws KnowledgeNotFoundException {
		if (knowledgeBase.containsKey(id)) {
			if(Constants.DEBUG_KNOWLEDGE) System.out.println("Knowledge found : " + id);
			return knowledgeBase.get(id);
		} else {
			if(Constants.DEBUG_KNOWLEDGE) System.out.println("Knowledge not found : " + id);
			throw new KnowledgeNotFoundException(id);
		}
	}

	@Override
	public String callLogic(Object data) {
		return null;
	}
	
	@Override
	public synchronized String saveKnowledge(IKnowledgeRecord knowledge, String id) {
		
		if(Constants.DEBUG_KNOWLEDGE) System.out.println("Knowledge received for saving : " + knowledge.toString());
		knowledgeBase.put(id, (KnowledgeRecord) knowledge);
		return "Knowledge saved : " + id;
	}
	
	@Override
	public void sendData(Object data) {
//		adaptationLogic.sendData(data);	
	}
	
	

}
