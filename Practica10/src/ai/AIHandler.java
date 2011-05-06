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
	
	public void registerObject(int type, double id, Element e) {
		AIObject o;
		
		/*switch(type) {
		case AIObject.PLAYER:
			o = new AIPlayer(id, e);
			break;
		case AIObject.BUG:
			o = new AIBug(id, e);
			break;
		case AIObject.SPIDER:
			o = new AISpider(id, e);
			break;
		case AIObject.SNAKE:
			o = new AISnake(id, e);
			break;
		case AIObject.MINOTAUR:
			o = new AIMinotaur(id, e);
			break;
		default:
			System.out.println("AI type " + type + " does not exists.");
			exit(-1);
			break;
		}*/
		
		if(type == AIObject.PLAYER)
			o = new AIPlayer(id, e);
		else
			o = new AIEnemy(id, e);
		
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
