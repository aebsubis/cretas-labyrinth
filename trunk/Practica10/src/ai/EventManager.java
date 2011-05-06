package ai;

import java.util.Enumeration;
import java.util.Hashtable;

public class EventManager {

	// Manejador del manejador de eventos.
	private static EventManager eventManager;
	
	// Hashtable de objetos.
	private Hashtable objects;
	
	// Constructor privado.
	private EventManager() {
		objects = new Hashtable();
	}
	
	public static synchronized EventManager getInstance() {
		if(eventManager == null) {
			eventManager = new EventManager();
		}
		return eventManager;
	}
	
	// Registra un objeto IA
	public void registerObject(AIObject object) {
		objects.put(String.valueOf(object.getId()), object);
	}
	
	// Obtiene el objeto IA del id que recibe como parámetro.
	public AIObject getObject(double objectId) {
		
		AIObject o = (AIObject) objects.get(String.valueOf(objectId));
		
		if(o==null) {
			System.err.println( "AIObject \"" + objectId + "\" not found.");
			System.exit(-1);
		}
		
		return o;
	}
	
	// Elimina el objeto IA del id que recibe como parámetro.
	public void deleteObject(double objectId) {
		AIObject o = (AIObject) objects.get(String.valueOf(objectId));
		
		if(o==null) {
			System.err.println( "AIObject \"" + objectId + "\" not found.");
			System.exit(-1);
		}
					
		objects.remove(String.valueOf(objectId));
	}
	
	// Actualiza los objetos IA.
	public void update() {
		Enumeration e = objects.elements();
		while(e.hasMoreElements()){
			AIObject o = (AIObject) e.nextElement();
			o.update();
		}
	}

	// Elimina todos los objetos IA.
	public void deleteAllObjects() {
		objects.clear();
	}
}
