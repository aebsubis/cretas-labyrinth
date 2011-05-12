package ai;

import game.Element;

/**
 * 
 * @author Daniel
 * Manejador de la inteligencia artificial. La clase implementa el patrón singleton para 
 * poder ser accedida desde cualquier parte del programa. Esta clase se encarga de las 
 * operaciones con almacenamiento y carga de información en memoria.
 */
public class AIHandler {

	// Manejador de la interfaz gráfica.
	private static AIHandler aiHandler;
	
	// Constructor privado.
	private AIHandler() {
		
	}
	
	// Función estática para obtener la instancia de la clase.
	public static synchronized AIHandler getInstance() {
		if(aiHandler == null) {
			aiHandler = new AIHandler();
		}
		return aiHandler;
	}

	// Actualiza las uniades y envía los mensajes aplazados.
	public void update() {
		// Actualizamos las inteligencias artificiales.
		EventManager.getInstance().update();
		
		// Enviamos los mensajes aplazados.
		IssuerEvent.getInstance().sendDelayedMessages();
	}
	
	// Registra el objeto de la IA.
	public void registerObject(double id, String type, Element e) {
		AIObject o = null;
		
		if( type.equals(AIObject.PLAYER)) {
			o = new AIPlayer(id, e);
		}
		else if (type.equals(AIObject.BUG)) {
			// 
		}
		else if (type.equals(AIObject.SPIDER)) {
			// 
		}
		else if (type.equals(AIObject.SNAKE)) {
			// 
		}
		else if (type.equals(AIObject.MINOTAUR)) {
			o = new AIMinotaur(id, e);
		}
		
		if (o == null) {
			System.err.println("AIType " + type + " not found.");
			System.exit(-1);
		}
		
		// Guardamos el objeto IA.
		EventManager.getInstance().registerObject(o);
	}
	
	// Borra el objeto de la IA.
	public void deleteObject(double id) {
		EventManager.getInstance().deleteObject(id);
	}
	
	// Elimina todos los objetos de la IA.
	public void deleteAllObjects() {
		EventManager.getInstance().deleteAllObjects();
		IssuerEvent.getInstance().deleteAllMessages();
	}
	
	// Devuelve el objeto de la IA.
	public AIObject getObject(double id) {
		return EventManager.getInstance().getObject(id);
	}
	
	// Envía un mensaje.
	public void sendMessage(double delay, double senderId, double reciverId, int type) {
		IssuerEvent.getInstance().sendMessage(delay, senderId, reciverId, type);
	}
}
