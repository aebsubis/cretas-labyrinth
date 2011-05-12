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
		
		// Fase no completada.
		completed = false;
		
		// Cargamos la información de la fase.
		ResourcesHandler.getInstance().loadPhase(this);
		
		// Iniciamos la presentación.
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

		// Libera la pantalla actual.
		Stage s = (Stage) stages.get(currentStage);
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

	// Mueve la cámara.
	public void moveCamera(int direction) {
		Stage s = (Stage) stages.get(currentStage);
		s.moveCamera(direction);
	}
	
	// Pasa a la siguiente diapositiva.
	public boolean nextSlide() {
		return presentation.nextSlide();
	}
	
	// Añade una pantalla a la fase.
	public void addStage(Stage stage) {
		this.stages.add(stage);
	}

	// Actualiza el estado de la fase.
	public void update() {
		
		// Obtenemos la pantalla actual.
		Stage s = (Stage) stages.get(currentStage);
		
		// Comprobamos si se ha perdido la pantalla.
		if(s.isDefeated()) {
			
			// Repetimos la pantalla.
			s.free();
			s.init();
			
		} else {
			
			// Comprobar si se ha completado la pantalla.
			if(s.isCompleted()) {
				
				// Avanzamos a la siguiente pantalla.
				nextStage();
			
			} else {

				// Actualiza la pantalla.
				s.update();
			}	
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

	// Establece la pantalla actual.
	public void setCurrentStage(int currentStage) {
		this.currentStage = currentStage;
	}

	// Devuelve las pantallas de la fase.
	public ArrayList getStages() {
		return this.stages;
	}
	// Establece las pantallas de la fase.
	public void setStages(ArrayList stages) {
		this.stages = stages;
	}

	// Devuelve la presentación.
	public Presentation getPresentation() {
		return presentation;
	}
	
	// Establece la presentación.
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}
	
	// Clona el objeto.
	public Phase clone() {
		Phase p = new Phase();
		p.setId(this.id);
		p.setName(this.name);
		return p;
	}

	// Reinicia la pantalla.
	public void retryStage() {
		// Inicializar la pantalla actual.
		Stage s = (Stage) stages.get(currentStage);
		s.init();	
	}
}
