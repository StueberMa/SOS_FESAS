package de.mannheim.wifo2.simpleSAS.adaptationLogic.supportingElements.communication.pubSub;

import info.pppc.base.system.SystemID;
import de.mannheim.wifo2.fesas.sasStructure.adaptationLogic.supportingElements.communication.pubSub.Event;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.simpleSAS.adaptationLogic.AdaptationLogicProxy;

/**
 * Support transmitting of data from PubSub to elements.
 * 
 * @author Krupitzer
 *
 */
public class ReceiverThread extends Thread {

	private AdaptationLogicProxy p;
	private Event event;
	
	
	public ReceiverThread (AdaptationLogicProxy p, Event event) {
		this.p = p;
		this.event = event;
	}
	
	
	@Override
	public void run() {
		if (Constants.DEBUG_COMMUNICATION) Logging.logIntoFile(getClass(), "Before receive data of subscriber!",
				SystemID.SYSTEM.toString(), "ReceiverThread", p.getID());
//		System.out.println("proxy: " + p.toString());
//		System.out.println("Event: " + event.toString());
//		System.out.println("EventType: " + event.getEventType().toString());
		Logging.logIntoFile(getClass(), "Publishing event to AL! ",
				SystemID.SYSTEM.toString(), "ReceiverThread", 
				"Event: " + event.getEventID() + " at " + p.getTargetID());
		p.receiveData("PUBSUB", event.getEventType().toString(), event);
//		p.receiveEvent(event);
		return;
		
	}
}
