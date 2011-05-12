package ai;

import game.Element;
import game.GameHandler;
import gui.GUIHandler;
import utils.Location2D;
import utils.ResourcesHandler;

public class Minotaur_StateBreak extends State{
	
	// Instancia de la clase.
	private static Minotaur_StateBreak instance;
	
	// Constructor privado.
	private Minotaur_StateBreak() {
		this.name = "minotaur_stateBreak";
	}
	
	// Tiempo que dura el descanso.
	private double breakTime = 3000;
	
	// Instante de comienzo del descanso.
	private double startTime;
	
	// Obtiene la instancia
	public static Minotaur_StateBreak getInstance() {
		if(instance == null)
			instance = new Minotaur_StateBreak();
		return instance;
	}
	
	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		// Guardamos el instante de entrada.
		startTime = System.currentTimeMillis();
		
		// Establecemos la animación del descanso.
		GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_take_break"));
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		
		// Objeto jugador.
		Element player = GameHandler.getInstance().getCurrentPhase().getCurrentStage().getPlayer();

		// Posición del jugador.
		Location2D playerLocation = player.getLocation();
		
		// Posición del enemigo.
		Location2D pActLoc = o.element.getLocation();
		
		// Comprobamos si están en la misma posición.
		if(playerLocation.equals(pActLoc)) {
			
			// Dañamos al personaje.
			AIHandler.getInstance().sendMessage(0, o.getId(), player.getId(), Message.recivedImpact);
		}
		
		// Comprobamos si ha pasado el tiempo suficiente.
		if(System.currentTimeMillis()-startTime >= breakTime) {
			// Finalizamos el descanso.
			o.stateMachine.changeState(Minotaur_StateStand.getInstance(), o);	
		}
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}
}
