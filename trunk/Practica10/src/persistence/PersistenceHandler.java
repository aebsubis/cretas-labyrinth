package persistence;
/**
 * 
 * @author Daniel
 * Manejador de la persistencia. La clase implementa el patr�n singleton para poder ser
 * accedida desde cualquier parte del programa. Esta clase se encarga de las operaciones
 * con almacenamiento y carga de informaci�n en memoria.
 */
public class PersistenceHandler {

	// Manejador de la interfaz gr�fica.
	private static PersistenceHandler persistenceHandler;
	
	// Constructor privado.
	private PersistenceHandler() {
		
	}
	
	// Funci�n est�tica para obtener la instancia de la clase.
	public static synchronized PersistenceHandler getInstance() {
		if(persistenceHandler == null) {
			persistenceHandler = new PersistenceHandler();
		}
		return persistenceHandler;
	}
}
