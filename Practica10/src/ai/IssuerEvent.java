package ai;

import utils.ArrayList;

public class IssuerEvent {

	// Mensajes aplazados.
	private ArrayList delayedMessages;
	
	// Manejador del emisor de eventos.
	private static IssuerEvent issuerEvent;
	
	// Constructor privado.
	private IssuerEvent() {
		delayedMessages = new ArrayList();
	}
	
	public static synchronized IssuerEvent getInstance() {
		if(issuerEvent == null) {
			issuerEvent = new IssuerEvent();
		}
		return issuerEvent;
	}
	
	// Descarga los mensajes aplazados.
	private void download(AIObject reciver, Message message) {
		if(reciver != null) {
			reciver.handleMessage(message);
		} else {
			System.err.println( "AIObject equals null.");
			System.exit(-1);
		}
	}
	
	// Envía un mensaje
	public void sendMessage(double delay, double senderId, double reciverId, int type){
		
		// Obtenemos el receptor.
		AIObject reciver = EventManager.getInstance().getAIObject(reciverId);
		
		// Comprobamos si existe.
		if(reciver == null) {
			System.err.println( "Reciver id " + reciverId + " not found.");
			System.exit(-1);
		}
		
		// Creamos el mensaje.
		Message m = new Message(senderId, reciverId, type, 0);
		
		if(delay <= 0) {
			// Enviamos el mensaje instantaneamente.
			download(reciver, m);
		} else {
			// Guardamos el mensaje en aplazados.
			m.deliveryTime = System.currentTimeMillis() + delay;
			delayedMessages.add(m);
		}
	}
	
	// Envía los mensajes aplazados.
	public void sendDelayedMessages() {
		double actualTime = System.currentTimeMillis();
		
		for (int i=0; i < delayedMessages.size(); i++) {
			Message m = (Message)delayedMessages.get(i);
			if(m.deliveryTime <= actualTime) {
				AIObject reciver = EventManager.getInstance().getAIObject(m.reciverId);
				
				// Comprobamos si existe.
				if(reciver != null) {
					System.err.println( "Reciver id " + m.reciverId + " not found.");
					System.exit(-1);
				}
				
				// Enviamos el mensaje.
				download(reciver, m);
				
				// Lo eliminamos de la lista.
				delayedMessages.remove(i);
				
				// Recuperamos el índice perdido.
				i--;
			}
		}
	}	
}
