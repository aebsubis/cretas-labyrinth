package persistence;
/**
 * 
 * @author Daniel
 * Manejador de la persistencia. La clase implementa el patrón singleton para poder ser
 * accedida desde cualquier parte del programa. Esta clase se encarga de las operaciones
 * con almacenamiento y carga de información en memoria.
 */
public class PersistenceHandler {

	// Manejador de la interfaz gráfica.
	private static PersistenceHandler persistenceHandler;
	
	// Constructor privado.
	private PersistenceHandler() {
		
	}
	
	// Función estática para obtener la instancia de la clase.
	public static synchronized PersistenceHandler getInstance() {
		if(persistenceHandler == null) {
			persistenceHandler = new PersistenceHandler();
		}
		return persistenceHandler;
	}
}
