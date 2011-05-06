package ai;

public abstract class State {

	// Nombre del estado.
	protected String name;
	
	// Constructor por defecto.
	public State() {
		
	}
	
	// Acción a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		
	}
	
	// Acción a ejecutar al permanecer en el estado.
	public void execute(AIObject o) {
		
	}
	
	// Acción a ejecutar al salir del estado.
	public void exit(AIObject o) {
		
	}
	
	// Acción a ejecutar al recibir un mensaje
	public boolean onMessage(AIObject o, Message m) {
		return false;
	}
	
	// Devuelve el nombre del estado.
	public String getName() {
		return name;
	}
}
