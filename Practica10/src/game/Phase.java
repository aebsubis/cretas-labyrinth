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
	
	// Presentaci�n de la fase.
	private Presentation presentation;
	
	// Nombre de la fase.
	private String name;
	
	// Indica si la fase est� completada.
	boolean completed;
	
	// Constructor por defecto.
	public Phase() {
	}
	
	public void init() {		
		Debugger.debug.print("Phase", "Init", "Starts");
		
		// Establecemos la primera pantalla como actual.
		currentStage = 0;
		
		// Obtenemos las pantallas.
		stages = ResourcesHandler.getInstance().getStages(id);
		
		// Obtenemos la presentaci�n.
		presentation = new Presentation(id);
		
		// Iniciamos la presentaci�n.
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

	// Mueve la c�mara.
	public void moveCamera(int direction) {
		Stage s = (Stage) stages.get(currentStage);
		s.moveCamera(direction);
	}
	
	// Pasa a la siguiente diapositiva.
	public boolean nextSlide() {
		return presentation.nextSlide();
	}
	
	// A�ade una pantalla a la fase.
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

	// Devuelve la pantalla actual.
	public Stage getCurrentStage() {
		return (Stage) stages.get(currentStage);
	}

	public Phase clone() {
		Phase p = new Phase();
		p.setId(this.id);
		p.setName(this.name);
		return p;
	}
}
