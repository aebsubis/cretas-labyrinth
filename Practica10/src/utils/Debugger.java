package utils;

public class Debugger {

	// Indica si se debe mostrar el debug.
	private static final boolean DEBUGGING = false;
	
	// Acceso r�pido.
	public static Debugger debug = getInstance();
	
	// Instancia de la clase.
	private static Debugger debugger;
	
	// Constructor privado.
	private Debugger() {
		
	}
	
	// Funci�n est�tica para obtener la instancia de la clase.
	public static synchronized Debugger getInstance() {
		if(debugger == null) {
			debugger = new Debugger();
		}
		return debugger;
	}
	
	public void print(String author, String method, String message) {
		if(DEBUGGING) System.out.println(author + ":" + method + " - " + message);
	}
}
