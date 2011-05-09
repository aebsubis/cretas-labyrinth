package ai;

import game.Direction;
import game.GameHandler;
import game.Stage;
import gui.GUIHandler;
import utils.Location2D;
import utils.Randomizer;
import utils.ResourcesHandler;

public class Minotaur_StateStand extends State{

	// Instancia de la clase.
	private static Minotaur_StateStand instance;
	
	// Constructor privado.
	private Minotaur_StateStand() {
		this.name = "minotaur_stateStand";
	}
	
	// Obtiene la instancia
	public static Minotaur_StateStand getInstance() {
		if(instance == null)
			instance = new Minotaur_StateStand();
		return instance;
	}

	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_stand_back"));
			break;
		case Direction.DOWN:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_stand_front"));
			break;
		case Direction.LEFT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_stand_left"));
			break;
		case Direction.RIGHT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_stand_right"));
			break;
		}
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		// Examinar alrededores.
		// Tomarse un descanso
		// Moverse
		
		// Toma una dirección aleatoria e intenta moverse.
		int r = Randomizer.getInstance().getRand(0,4);
		switch(r){
		case Direction.UP:

			// Obtenemos la posición del enemigo.
			Location2D pActLoc1 = o.element.getLocation();
			
			// Comprobamos si se puede realizar el movimiento.
			Location2D pFinLocUp = new Location2D(pActLoc1.getX(), pActLoc1.getY()-1);
			Stage s1 = GameHandler.getInstance().getCurrentPhase().getCurrentStage();
			if(s1.isOnMap(pFinLocUp) && s1.getScenery(pFinLocUp).isPassable()){
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.UP);
				
				// Hacemos que el enemigo se desplace.
				o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
			}
			break;
		case Direction.DOWN:

			// Obtenemos la posición del enemigo.
			Location2D pActLoc2 = o.element.getLocation();
			
			Location2D pFinLocDown = new Location2D(pActLoc2.getX(), pActLoc2.getY()+1);
			Stage s2 = GameHandler.getInstance().getCurrentPhase().getCurrentStage();
			if(s2.isOnMap(pFinLocDown) && s2.getScenery(pFinLocDown).isPassable()) {
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.DOWN);
				
				// Hacemos que el enemigo se desplace.
				o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
			}
			break;
		case Direction.LEFT:

			// Obtenemos la posición del enemigo.
			Location2D pActLoc3 = o.element.getLocation();
			
			Location2D pFinLocDownLeft = new Location2D(pActLoc3.getX()-1, pActLoc3.getY());
			Stage s3 = GameHandler.getInstance().getCurrentPhase().getCurrentStage();
			if(s3.isOnMap(pFinLocDownLeft) && s3.getScenery(pFinLocDownLeft).isPassable()) {
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.LEFT);
				
				// Hacemos que el enemigo se desplace.
				o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
			}
			break;
		case Direction.RIGHT:

			// Obtenemos la posición del enemigo.
			Location2D pActLoc4 = o.element.getLocation();
			
			Location2D pFinLocDownRight = new Location2D(pActLoc4.getX()+1, pActLoc4.getY());
			Stage s4 = GameHandler.getInstance().getCurrentPhase().getCurrentStage();
			if(s4.isOnMap(pFinLocDownRight) && s4.getScenery(pFinLocDownRight).isPassable()) {
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.RIGHT);
				
				// Hacemos que el enemigo se desplace.
				o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
			}
			break;
		}
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}

}
