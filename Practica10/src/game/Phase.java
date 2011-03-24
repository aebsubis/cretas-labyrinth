package game;

import utils.ArrayList;
import utils.Debugger;
import utils.ResourcesHandler;

/**
 * 
 * @author Daniel
 *
 */
public class Phase {

	// Identificador de la fase.
	private String id;
	
	// Pantallas de la fase.
	private ArrayList stages;
	
	// Pantalla actual.
	private int currentStage;
	
	// Presentación de la fase.
	private Presentation presentation;
	
	// Nombre de la fase.
	private String name;
	
	// Indica si la fase está completada.
	boolean completed;
	
	// Constructor por defecto.
	public Phase() {
	}
	
	public void init() {		
		Debugger.debug.print("Phase", "Init", "Starts");
		
		// Inicializamos el array de las pantallas.
		stages = ResourcesHandler.getInstance().getStages(id);
		
		// Establecemos la primera pantalla como actual.
		currentStage = 0;
		
		// Obtener datos de la presentación desde el gestor de recursos.
		
		// Inicializamos la presentación.
		presentation = new Presentation();
		presentation.init();
		
		Debugger.debug.print("Phase", "Init", "Ends");
	}
	
	// Obtiene el identificador.
	public String getId() {
		return id;
	}
	
	// Establece el identificador.
	public void setId(String id) {
		this.id = id;
	}
	
	// Obtiene el nombre de la fase.
	public String getName() {
		return this.name;
	}
	
	// Establece el nombre de la fase.
	public void setName(String name) {
		this.name = name;
	}
	
	// Inicia la fase.
	public void start()  {		
		Debugger.debug.print("Phase", "Start", "Starts");
		
		// Inicializar la primera pantalla.
		Stage s = (Stage) stages.get(currentStage);
		s.init();		
		
		Debugger.debug.print("Phase", "Start", "Ends");
	}
	
	// Avanza a la siguiente pantalla.
	public boolean nextStage() {
		boolean next = false;
		Stage s;
		
		// Libera la pantalla actual.
		s = (Stage) stages.get(currentStage);
		s.free();
		
		// Incrementa la pantalla.
		currentStage++;
		
		// Comprobamos si se han completado todas las pantallas.
		if (currentStage >= stages.size()) {
			currentStage = 0;
			completed = true;
		}
		else {
			// Existe la siguiente pantalla.
			next = true;
			
			// Cargamos la siguiente pantalla.
			s = (Stage) stages.get(currentStage);
			s.init();
		}
		return next;
	}

	// Mueve al personaje.
	public void movePlayer(int direction) {
		Stage s = (Stage) stages.get(currentStage);
		s.movePlayer(direction);
	}

	// Pasa a la siguiente diapositiva.
	public boolean nextSlide() {
		return presentation.nextSlide();
	}
	
	// Añade una pantalla a la fase.
	public void addStage(Stage stage) {
		this.stages.add(stage);
	}

	public void update() {
		// Actualiza la pantalla.
		Stage s = (Stage) stages.get(currentStage);
		s.update();
		
		// Comprobar si se ha completado la pantalla.
		if(s.isCompleted()) {
			nextStage();
		}
	}

	public boolean isCompleted() {
		return completed;
	}

	public void free() {
		// TODO Auto-generated method stub
		
	}
}
