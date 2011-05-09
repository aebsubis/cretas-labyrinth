package ai;

import utils.Location2D;
import game.Direction;
import gui.GUIHandler;

public class Player_StateBoost extends State{

	// Instancia de la clase.
	private static Player_StateBoost instance;
	
	// Constructor privado.
	private Player_StateBoost() {
		this.name = "player_stateBoost";
	}
	
	// Obtiene la instancia
	public static Player_StateBoost getInstance() {
		if(instance == null)
			instance = new Player_StateBoost();
		return instance;
	}
	
	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			o.element.setYDelay(GUIHandler.yBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX(),o.element.getLocation().getY()-1));
			break;
		case Direction.DOWN:
			o.element.setYDelay(-GUIHandler.yBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX(),o.element.getLocation().getY()+1));
			break;
		case Direction.LEFT:
			o.element.setXDelay(GUIHandler.xBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX()-1,o.element.getLocation().getY()));
			break;
		case Direction.RIGHT:
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
			o.element.setYDelay(o.element.getYDelay()-5);
			if(o.element.getYDelay() <= 0) {
				o.element.setYDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		case Direction.DOWN:
			o.element.setYDelay(o.element.getYDelay()+5);
			if(o.element.getYDelay() >= 0) {
				o.element.setYDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		case Direction.LEFT:
			o.element.setXDelay(o.element.getXDelay()-5);
			if(o.element.getXDelay() <= 0) {
				o.element.setXDelay(0);
				o.stateMachine.changeState(Player_StateStand.getInstance(), o);
			}
			break;
		case Direction.RIGHT:
			o.element.setXDelay(o.element.getXDelay()+5);
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
}
