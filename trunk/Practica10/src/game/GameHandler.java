package game;

import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;
import utils.ResourcesHandler;
import ai.AIHandler;

/**
 * 
 * @author Daniel
 * Manejador del juego. La clase implementa el patrón singleton para poder ser accedida
 * desde cualquier parte del programa. Esta clase se encarga de las operaciones con la
 * información del juego.
 */
public class GameHandler{

	// Objeto manejador
	private static GameHandler gameHandler;

	// Contador de identificadores.
	private static double identifierCounter = 0;
	
	// Fases.
	private ArrayList phases;
	
	// Fase actual.
	private int currentPhase;
	
	// Cola de eventos de teclado.
	private ArrayList pressEvents;
	private ArrayList releaseEvents;
	
	// Indica si se ha completado el juego.
	private boolean completed;
	
	// Constructor privado.
	private GameHandler() {
	}
	
	// Función estática para obtener la instancia de la clase.
	public static synchronized GameHandler getInstance() {
		if(gameHandler == null) {
			gameHandler = new GameHandler();
		}
		return gameHandler;
	}

	public void init() {	
		Debugger.debug.print("GameHandler", "Init", "Starts");
		
		// Juego no completado.
		completed = false;
		
		// Inicializamos el vector de eventos.
		pressEvents = new ArrayList();
		releaseEvents = new ArrayList();
		
		// Inicializamos el ArrayList de fases.
		phases = new ArrayList();
		
		// Añadimos las fases.
		phases.add(ResourcesHandler.getInstance().getPhase("icaro_dedalos"));
		phases.add(ResourcesHandler.getInstance().getPhase("teseo"));
		
		// Establecemos la fase 0 como actual.
		currentPhase = 0;

		// Inicializamos la fase.
		Phase phase = (Phase) phases.get(currentPhase);
		phase.init();
		
		Debugger.debug.print("GameHandler", "Init", "Ends");
	}

	// Actualiza el estado del juego.
	public void update() {
		Debugger.debug.print("GameHandler", "update", "Starts");
		if(completed) {
			Debugger.debug.print("GameHandler", "update", "El juego está completado.");
		} else {
			Debugger.debug.print("GameHandler", "update", "El juego no está completado.");
			// Update events.
			events();
			Debugger.debug.print("GameHandler", "update", "events ended");
			// Update AI.		
			AIHandler.getInstance().update();
			Debugger.debug.print("GameHandler", "update", "ai ended");
			// Update Game.
			Phase phase = (Phase) phases.get(currentPhase);
			phase.update();
			Debugger.debug.print("GameHandler", "update", "phase updated");
			// Comprobar si se ha completado la fase.
			if(phase.isCompleted()) {
				Debugger.debug.print("GameHandler", "update", "nextphase");
				nextPhase();
				Debugger.debug.print("GameHandler", "update", "nextphased");
			}
		}
		Debugger.debug.print("GameHandler", "update", "Ends");
	}
	
	// Genera un identificador único.
	public double getIdentifier() {
		return identifierCounter++;
	}
	
	public void events()
	{
		// Fase actual.
		Phase p = (Phase) phases.get(currentPhase);
		
		while(pressEvents.size()>0) {
			Integer i = (Integer)pressEvents.remove(0);
			int event = i.intValue();
			switch(event) {
			case -1:
	            p.movePlayer(Direction.UP);
	            break;
	         case -3:
		         p.movePlayer(Direction.LEFT);
		        break;
	         case -2:
		         p.movePlayer(Direction.DOWN);
	            break;
	         case -4:
		         p.movePlayer(Direction.RIGHT);
		        break;
	         case -5:
	        	 System.out.println("FIRE");
	        	 break;
	         case 48:
	        	 System.out.println("0");
	        	 break;
	         case 49:
	        	 System.out.println("1");
	        	 break;
	         case 50:
	        	 System.out.println("2");
	        	 break;
	         case 51:
	        	 System.out.println("3");
	        	 break;
	         case 52:
	        	 System.out.println("4");
	        	 break;
	         case 53:
	        	 System.out.println("5");
	        	 break;
	         case 54:
	        	 System.out.println("6");
	        	 break;
	         case 55:
	        	 System.out.println("7");
	        	 break;
	         case 56:
	        	 System.out.println("8");
	        	 break;
	         case 57:
	        	 System.out.println("9");
	        	 break;
	         case 35:
	        	 System.out.println("POUND");
	        	 break;
	         case 42:
	        	 System.out.println("STAR");
	        	 break;
			}
		}
		
		while(releaseEvents.size()>0) {
			Integer i = (Integer)releaseEvents.remove(0);
			int event = i.intValue();
			switch(event) {
			case -1:
	            p.stopPlayer(Direction.UP);
	            break;
	         case -3:
		         p.stopPlayer(Direction.LEFT);
		        break;
	         case -2:
		         p.stopPlayer(Direction.DOWN);
	            break;
	         case -4:
		         p.stopPlayer(Direction.RIGHT);
		        break;
	         case -5:
	        	 System.out.println("FIRE");
	        	 break;
	         case 48:
	        	 System.out.println("0");
	        	 break;
	         case 49:
	        	 System.out.println("1");
	        	 break;
	         case 50:
	        	 System.out.println("2");
	        	 break;
	         case 51:
	        	 System.out.println("3");
	        	 break;
	         case 52:
	        	 System.out.println("4");
	        	 break;
	         case 53:
	        	 System.out.println("5");
	        	 break;
	         case 54:
	        	 System.out.println("6");
	        	 break;
	         case 55:
	        	 System.out.println("7");
	        	 break;
	         case 56:
	        	 System.out.println("8");
	        	 break;
	         case 57:
	        	 System.out.println("9");
	        	 break;
	         case 35:
	        	 System.out.println("POUND");
	        	 break;
	         case 42:
	        	 System.out.println("STAR");
	        	 break;
			}
		}
	}
	
	// Añade un evento a la cola de eventos.
	public void addPressEvent(int event) {
		pressEvents.add(new Integer(event));
	}
	
	// Añade un evento a la cola de eventos.
	public void addReleaseEvent(int event) {
		releaseEvents.add(new Integer(event));
	}

	// Pasa a la siguiente diapositiva.
	public void nextSlide() {
		Phase p = (Phase) phases.get(currentPhase);
		if(!p.nextSlide()) {
			p.start();
		}
	}

	// Salta las diapositivas.
	public void skipSlide() {
		Phase p = (Phase) phases.get(currentPhase);
		p.start();
	}
	
	// Avanza a la siguiente fase.
	public boolean nextPhase() {
		boolean next = false;
		Phase p;
		
		// Libera la fase actual.
		p = (Phase) phases.get(currentPhase);
		p.free();
		
		// Incrementa la pantalla.
		currentPhase++;
		
		// Comprobamos si se han completado todas las pantallas.
		if (currentPhase >= phases.size()) {
			currentPhase = 0;
			setCompleted(true);
			
			// Mostrar los créditos y volver al menú.
			GUIHandler.getInstance().showScreen(GUIScreens.MAINMENU);
		}
		else {
			// Existe la siguiente fase.
			next = true;
			
			// Cargamos la siguiente pantalla.
			p = (Phase) phases.get(currentPhase);
			p.init();
		}
		return next;
	}

	// Indica si el juego se ha completado
	public boolean isCompleted() {
		return completed;
	}
	
	// Establece si el juego se ha completado.
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
