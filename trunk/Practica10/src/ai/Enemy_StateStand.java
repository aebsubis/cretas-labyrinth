package ai;

import game.Direction;
import gui.GUIHandler;
import utils.ResourcesHandler;

public class Enemy_StateStand extends State{

	// Instancia de la clase.
	private static Enemy_StateStand instance;
	
	// Constructor privado.
	private Enemy_StateStand() {
		this.name = "enemy_stateStand";
	}
	
	// Obtiene la instancia
	public static Enemy_StateStand getInstance() {
		if(instance == null)
			instance = new Enemy_StateStand();
		return instance;
	}

	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_bug_stand_back"));
			break;
		case Direction.DOWN:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_bug_stand_front"));
			break;
		case Direction.LEFT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_bug_stand_left"));
			break;
		case Direction.RIGHT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_bug_stand_right"));
			break;
		}
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		// Examinar alrededores.
		// Tomarse un descanso
		// Moverse
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}

}
