package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub;

import info.pppc.base.system.SystemID;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.Event;
import de.mannheim.wifo2.fesas.sasStructure.data.adaptationLogic.communication.event.IEvent;
import de.mannheim.wifo2.fesas.tools.logging.Logging;

/**
 * Supports the publishing of data.
 * 
 * @author Krupitzer
 *
 */
public class PublisherThread extends Thread {

	private PubSubProxy p;
	private IEvent event;
	@SuppressWarnings("unused")
	private String publishingSystem;
	
	
//	public PublisherThread (PubSubProxy p, IEvent event, String publishingSystem) {
//		this.p = p;
//		this.event = event;
//		this.publishingSystem = publishingSystem;
//	}
	
	public PublisherThread (PubSubProxy p, String category, String type, String publisher, 
			String system, String knowledgeID, String eventID, String publishingSystem) {
		this.p = p;
		this.publishingSystem = publishingSystem;
		this.event = new Event(category, type, publisher, system, knowledgeID, eventID);
	}
	
	
	@Override
	public void run() {
		Logging.logIntoFile(getClass(), "Event published to PubSub",
				SystemID.SYSTEM.toString(), "PublisherThread", 
				"Event: " + event.getEventID() + " - PubSub: " + p.getTargetID() +	" -- Type : " + event.getEventType());
		System.out.println(p.getSourceID());
		
		p.notify(event.toString(), SystemID.SYSTEM.toString(),
    			event.getPublisher(), event.getEventCategory().toString(),
    			event.getEventType().toString(), 
    			event.getKnowledgeID(), event.getEventID());
	}
}
