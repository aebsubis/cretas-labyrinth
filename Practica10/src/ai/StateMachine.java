package ai;

public class StateMachine {

	// Estado actual.
	private State current;
	
	// Estado anterior.
	private State previous;
	
	// Estado global.
	private State global;
	
	// Constructor.
	public StateMachine() {
		current = null;
		previous = null;
		global = null;
	}
	
	// Establece el estado actual.
	public void setCurrentState(State s) {
		current = s;
	}
	
	// Establece el estado anterior.
	public void setPreviousState(State s) {
		previous = s;
	}
	
	// Establece el estado global.
	public void setGlobalState(State s) {
		global = s;
	}

	// Actualiza la maquina de estados.
	public void update(AIObject o) {
		if(global!=null)
			global.execute(o);
		if(current!=null)
			current.execute(o);
	}
	
	// Cambia el estado.
	public void changeState(State s, AIObject o) {
		if(current != null) {
			// Actualizamos el estado anterior.
			previous = current;
			
			// Ejecutamos la salida del estado actual.
			current.exit(o);
			
			// Actualizamos el estado actual.
			current = s;
			
			// Ejecutamos la entrada al nuevo estado actual.
			current.enter(o);
		} else {
			setCurrentState(s);
		}
	}

	// Regresa al estado anterior.
	public void toPreviousState(AIObject o) {
		changeState(previous, o);
	}

	// Devuelve el estado actual.
	public State getCurrentState() {
		return current;
	}

	// Devuelve el estado anterior.
	public State getPreviousState() {
		return previous;
	}

	// Devuelve el estado global.
	public State getGlobalState() {
		return global;
	}

	// Comprueba si se encuentra en el mismo estado que recibe por parámetro.
	public boolean sameState(State s) {
		return current.getName().equals(s.getName());
	}
	
	// Maneja mensajes.
	public boolean handleMessage(AIObject o, Message m) {
		if(current != null && current.onMessage(o, m))
			return true;
		if(global != null && global.onMessage(o, m))
			return true;
		return false;
	}
}
