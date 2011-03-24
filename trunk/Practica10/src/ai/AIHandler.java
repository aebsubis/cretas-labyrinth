package ai;
/**
 * 
 * @author Daniel
 * Manejador de la inteligencia artificial. La clase implementa el patr�n singleton para 
 * poder ser accedida desde cualquier parte del programa. Esta clase se encarga de las 
 * operaciones con almacenamiento y carga de informaci�n en memoria.
 */
public class AIHandler {

	// Manejador de la interfaz gr�fica.
	private static AIHandler aiHandler;
	
	// Constructor privado.
	private AIHandler() {
		
	}
	
	// Funci�n est�tica para obtener la instancia de la clase.
	public static synchronized AIHandler getInstance() {
		if(aiHandler == null) {
			aiHandler = new AIHandler();
		}
		return aiHandler;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
