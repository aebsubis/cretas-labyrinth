package ai;

import utils.Location2D;
import utils.ResourcesHandler;
import game.Direction;
import game.GameHandler;
import game.Stage;
import gui.GUIHandler;

public class Player_StateStand extends State{

	// Instancia de la clase.
	private static Player_StateStand instance;
	
	// Constructor privado.
	private Player_StateStand() {
		this.name = "player_stateStand";
	}
	
	// Obtiene la instancia
	public static Player_StateStand getInstance() {
		if(instance == null)
			instance = new Player_StateStand();
		return instance;
	}
	
	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_stand_back"));
			break;
		case Direction.DOWN:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_stand_front"));
			break;
		case Direction.LEFT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_stand_left"));
			break;
		case Direction.RIGHT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_stand_right"));
			break;
		}
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}
	
	public boolean onMessage(AIObject o, Message m) {
		// Indica si el mensaje ha podido ser manejado.
		boolean handled = true;

		// Obtenemos la posición del jugador.
		Location2D pActLoc = o.element.getLocation();
		
		// Obtenemos la escena.
		Stage stage = GameHandler.getInstance().getCurrentPhase().getCurrentStage();
		
		switch(m.type) {
		case Message.moveUp:
			// Comprobamos si se puede realizar el movimiento.			
			if(stage.isPassable(new Location2D(pActLoc.getX(), pActLoc.getY()-1))){
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.UP);
				
				// Hacemos que el jugador se desplace.
				o.stateMachine.changeState(Player_StateMove.getInstance(), o);
			}
			break;
		case Message.moveDown:
			// Comprobamos si se puede realizar el movimiento.			
			if(stage.isPassable(new Location2D(pActLoc.getX(), pActLoc.getY()+1))) {
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.DOWN);
				
				// Hacemos que el jugador se desplace.
				o.stateMachine.changeState(Player_StateMove.getInstance(), o);
			}
			break;
		case Message.moveLeft:
			// Comprobamos si se puede realizar el movimiento.			
			if(stage.isPassable(new Location2D(pActLoc.getX()-1, pActLoc.getY()))) {
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.LEFT);
				
				// Hacemos que el jugador se desplace.
				o.stateMachine.changeState(Player_StateMove.getInstance(), o);
			}
			break;
		case Message.moveRight:			
			// Comprobamos si se puede realizar el movimiento.
			if(stage.isPassable(new Location2D(pActLoc.getX()+1, pActLoc.getY()))) {
				// Establecemos la dirección del movimiento.
				o.element.setMovementDirection(Direction.RIGHT);
				
				// Hacemos que el jugador se desplace.
				o.stateMachine.changeState(Player_StateMove.getInstance(), o);
			}
			break;
		case Message.recivedImpact:

			if(System.currentTimeMillis() - o.element.getLastHitTime() > 1000) {
				o.element.setLastHitTime(System.currentTimeMillis());
				
				// Restamos una vida.
				o.element.setLives(o.element.getLives()-1);
				
				// Reproducimos el sonido.
	 			ResourcesHandler.getInstance().playSound("hit");
	 			
				// Desplazamos al personaje en la dirección del golpe si es posible.
				switch(AIHandler.getInstance().getObject(m.senderId).element.getMovementDirection()) {
				case Direction.UP:				
					// Comprobamos si se puede realizar el movimiento.
					if(stage.isPassable(new Location2D(pActLoc.getX(), pActLoc.getY()-1))){
						// Establecemos la dirección del movimiento.
						o.element.setMovementDirection(Direction.UP);
						
						// Hacemos que el jugador se desplace.
						o.stateMachine.changeState(Player_StateBoost.getInstance(), o);
					}
					break;
				case Direction.DOWN:
					// Comprobamos si se puede realizar el movimiento.
					if(stage.isPassable(new Location2D(pActLoc.getX(), pActLoc.getY()+1))){
						// Establecemos la dirección del movimiento.
						o.element.setMovementDirection(Direction.DOWN);
						
						// Hacemos que el jugador se desplace.
						o.stateMachine.changeState(Player_StateBoost.getInstance(), o);
					}
					break;
				case Direction.LEFT:
					// Comprobamos si se puede realizar el movimiento.
					if(stage.isPassable(new Location2D(pActLoc.getX()-1, pActLoc.getY()))){
						// Establecemos la dirección del movimiento.
						o.element.setMovementDirection(Direction.LEFT);
						
						// Hacemos que el jugador se desplace.
						o.stateMachine.changeState(Player_StateBoost.getInstance(), o);
					}
					break;
				case Direction.RIGHT:
					// Comprobamos si se puede realizar el movimiento.
					if(stage.isPassable(new Location2D(pActLoc.getX()+1, pActLoc.getY()))){
						// Establecemos la dirección del movimiento.
						o.element.setMovementDirection(Direction.RIGHT);
						
						// Hacemos que el jugador se desplace.
						o.stateMachine.changeState(Player_StateBoost.getInstance(), o);
					}
					break;
				}
			}			
			break;
		default:
			// No se pudo manejar ese tipo de mensaje.
			handled = false;
			break;
		}
		return handled;
	}	
}
