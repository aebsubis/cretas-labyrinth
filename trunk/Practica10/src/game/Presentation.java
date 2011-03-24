package game;

import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;

/**
 * 
 * @author Daniel
 *
 */
public class Presentation {
	
	// Identificador del recurso sonoro.
	//private String music;
	
	// ArrayList de diapositivas
	private ArrayList slides;
	
	// Diapositiva actual.
	private int currentSlide;
	
	// Indica si se ha completado la presentación.
	private boolean completed;
	
	// Constructor por defecto.
	public Presentation() {
		
	}

	public void init() {
		Debugger.debug.print("Presentation", "Init", "Starts");
		// Presentación no completada.
		completed = false;
		
		// Identificamos el recurso sonoro.
		//music = "soundtest";
		
		// Inicializamos las diapositivas.
		slides = new ArrayList();
		
		// Añadimos dos diapositivas (debe hacerse desde resources handler).
		Slide slide1 = new Slide();
		slide1.setImage("slide_1");
		slide1.setText("slide_1");
		slides.add(slide1);
		
		Slide slide2 = new Slide();
		slide2.setImage("slide_2");
		slide2.setText("slide_2");
		slides.add(slide2);
		
		// Establecemos como actual la primera diapositiva.
		currentSlide = 0;
		
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
	
	// Salta la presentación.
	public void skip() {
		currentSlide = 0;
		completed = true;
	}
	
	// Indica si se ha completado la presentación.
	public boolean isCompleted() {
		return completed;
	}
}

