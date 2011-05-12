package game;

import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;
import utils.ResourcesHandler;

/**
 * 
 * @author Daniel
 *
 */
public class Presentation {
	
	// Identificador del recurso sonoro.
	//private String music;
	
	// Identificador de la presentai�n.
	private String id;
	
	// ArrayList de diapositivas
	private ArrayList slides;
	
	// Diapositiva actual.
	private int currentSlide;
	
	// Indica si se ha completado la presentaci�n.
	private boolean completed;
	
	// Constructor por defecto.
	public Presentation(String id) {
		this.id = id;
	}

	public void init() {
		Debugger.debug.print("Presentation", "Init", "Starts");
		
		// Presentaci�n no completada.
		completed = false;
		
		// Cargamos la informaci�n de la presentaci�n.
		ResourcesHandler.getInstance().loadPresentation(this);
		
		// Arrancamos el sonido.
		//ResourcesHandler.getInstance().playSound("");
		
		// Obtenemos la primera diapositiva.
		Slide s = (Slide) slides.get(currentSlide);
		
		// Establecemos la diapositiva a mostrar.
		GUIHandler.getInstance().setSlide(s.getImage(),s.getText());
		
		// Mostramos la diapositiva.
		GUIHandler.getInstance().stopGame();
		GUIHandler.getInstance().showScreen(GUIScreens.PRESENTATION);

		Debugger.debug.print("Presentation", "Init", "Ends");
	}
	
	public boolean nextSlide() {
		boolean next = false;
		
		// Incrementar la diapositiva.
		currentSlide++;
		
		// Comprobamos si se han completado todas las diapositivas.
		if(currentSlide >= slides.size()) {
			currentSlide = 0;
			completed = true;
		}
		else {
			// Existe la siguiente diapositiva.
			next = true;

			// Obtenemos la siguiente diapositiva.
			Slide s = (Slide) slides.get(currentSlide);
			
			// Cargamos la siguiente diaposivia.
			GUIHandler.getInstance().setSlide(s.getImage(),s.getText());
			
			// Mostramos la diapositiva.
			GUIHandler.getInstance().showScreen(GUIScreens.PRESENTATION);
		}
		return next;
	}
	
	// Salta la presentaci�n.
	public void skip() {
		currentSlide = 0;
		completed = true;
	}
	
	// Indica si se ha completado la presentaci�n.
	public boolean isCompleted() {
		return completed;
	}

	// Devuelve el id de la presentaci�n.
	public String getId() {
		return id;
	}

	// Obtiene las diapositivas de la presentaci�n.
	public ArrayList getSlides() {
		return slides;
	}
	
	// Establece las diapositivas de la presentaci�n.
	public void setSlides(ArrayList slides) {
		this.slides = slides;
	}

	// Obtiene la diapositiva actual.
	public int getCurrentSlide(){
		return currentSlide;
	}
	
	// Establece la diapositiva actual.
	public void setCurrentSlide(int currentSlide) {
		this.currentSlide = currentSlide;		
	}
}

