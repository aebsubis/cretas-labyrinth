package ai;

import game.Direction;
import game.Element;
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
		
		// Obtenemos la pantalla.
		Stage stage = GameHandler.getInstance().getCurrentPhase().getCurrentStage();
		
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
		
		// Con una probabilidad del 5% se toma un descanso.
		if(Randomizer.getInstance().getRand(0, 100) < 5) {
			// Se toma un descanso.
			o.stateMachine.changeState(Minotaur_StateBreak.getInstance(), o);
		} else {
			// Toma una dirección aleatoria e intenta moverse.
			int r = Randomizer.getInstance().getRand(0,4);
			switch(r){
			case Direction.UP:			
				// Comprobamos si se puede realizar el movimiento.
				if(stage.isPassable(new Location2D(pActLoc.getX(), pActLoc.getY()-1))){
					// Establecemos la dirección del movimiento.
					o.element.setMovementDirection(Direction.UP);
					
					// Hacemos que el enemigo se desplace.
					o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
				}
				break;
			case Direction.DOWN:
				if(stage.isPassable(new Location2D(pActLoc.getX(), pActLoc.getY()+1))) {
					// Establecemos la dirección del movimiento.
					o.element.setMovementDirection(Direction.DOWN);
					
					// Hacemos que el enemigo se desplace.
					o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
				}
				break;
			case Direction.LEFT:
				if(stage.isPassable(new Location2D(pActLoc.getX()-1, pActLoc.getY()))) {
					// Establecemos la dirección del movimiento.
					o.element.setMovementDirection(Direction.LEFT);
					
					// Hacemos que el enemigo se desplace.
					o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
				}
				break;
			case Direction.RIGHT:
				if(stage.isPassable(new Location2D(pActLoc.getX()+1, pActLoc.getY()))) {
					// Establecemos la dirección del movimiento.
					o.element.setMovementDirection(Direction.RIGHT);
					
					// Hacemos que el enemigo se desplace.
					o.stateMachine.changeState(Minotaur_StateMove.getInstance(), o);
				}
				break;
			}
		}
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}

}
