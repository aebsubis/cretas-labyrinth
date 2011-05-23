package ai;

import game.Direction;
import game.GameHandler;
import game.Stage;
import gui.GUIHandler;
import utils.Location2D;
import utils.ResourcesHandler;

public class Player_StateMove extends State{
	// Instancia de la clase.
	private static Player_StateMove instance;
	
	// Constructor privado.
	private Player_StateMove() {
		this.name = "player_stateMove";
	}
	
	// Obtiene la instancia
	public static Player_StateMove getInstance() {
		if(instance == null)
			instance = new Player_StateMove();
		return instance;
	}
	
	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_walk_back"));
			o.element.setYDelay(GUIHandler.yBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX(),o.element.getLocation().getY()-1));
			break;
		case Direction.DOWN:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_walk_front"));
			o.element.setYDelay(-GUIHandler.yBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX(),o.element.getLocation().getY()+1));
			break;
		case Direction.LEFT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_walk_left"));
			o.element.setXDelay(GUIHandler.xBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX()-1,o.element.getLocation().getY()));
			break;
		case Direction.RIGHT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("player_icaro_walk_right"));
			o.element.setXDelay(-GUIHandler.xBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX()+1,o.element.getLocation().getY()));
			break;
		}
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		// Desplazar al jugador según su velocidad.
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			o.element.setYDelay(o.element.getYDelay()-o.element.getSpeed());
			if(o.element.getYDelay() <= 0) {
				o.element.setYDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		case Direction.DOWN:
			o.element.setYDelay(o.element.getYDelay()+o.element.getSpeed());
			if(o.element.getYDelay() >= 0) {
				o.element.setYDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		case Direction.LEFT:
			o.element.setXDelay(o.element.getXDelay()-o.element.getSpeed());
			if(o.element.getXDelay() <= 0) {
				o.element.setXDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		case Direction.RIGHT:
			o.element.setXDelay(o.element.getXDelay()+o.element.getSpeed());
			if(o.element.getXDelay() >= 0) {
				o.element.setXDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		}
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
