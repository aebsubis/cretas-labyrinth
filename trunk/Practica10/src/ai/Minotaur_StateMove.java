package ai;

import game.Direction;
import game.Element;
import game.GameHandler;
import gui.GUIHandler;
import utils.Location2D;
import utils.ResourcesHandler;

public class Minotaur_StateMove extends State{

	// Instancia de la clase.
	private static Minotaur_StateMove instance;
	
	// Constructor privado.
	private Minotaur_StateMove() {
		this.name = "minotaur_stateMove";
	}
	
	// Obtiene la instancia
	public static Minotaur_StateMove getInstance() {
		if(instance == null)
			instance = new Minotaur_StateMove();
		return instance;
	}
	
	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_walk_back"));
			o.element.setYDelay(GUIHandler.yBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX(),o.element.getLocation().getY()-1));
			break;
		case Direction.DOWN:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_walk_front"));
			o.element.setYDelay(-GUIHandler.yBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX(),o.element.getLocation().getY()+1));
			break;
		case Direction.LEFT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_walk_left"));
			o.element.setXDelay(GUIHandler.xBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX()-1,o.element.getLocation().getY()));
			break;
		case Direction.RIGHT:
			GUIHandler.getInstance().getObject(o.getId()).setFrames(ResourcesHandler.getInstance().getFrames("enemy_minotaur_walk_right"));
			o.element.setXDelay(-GUIHandler.xBlockSize);
			o.element.setLocation(new Location2D(o.element.getLocation().getX()+1,o.element.getLocation().getY()));
			break;
		}
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		
		// Objeto jugador.
		Element player = GameHandler.getInstance().getCurrentPhase().getCurrentStage().getPlayer();

		// Posici�n del jugador.
		Location2D playerLocation = player.getLocation();
		
		// Posici�n del enemigo.
		Location2D pActLoc = o.element.getLocation();
		
		// Comprobamos si est�n en la misma posici�n.
		if(playerLocation.equals(pActLoc)) {
			
			// Da�amos al personaje.
			AIHandler.getInstance().sendMessage(0, o.getId(), player.getId(), Message.recivedImpact);
		}
		
		// Desplazar al enemigo seg�n su velocidad.
		switch (o.element.getMovementDirection()) {
		case Direction.UP:
			o.element.setYDelay(o.element.getYDelay()-o.element.getSpeed());
			if(o.element.getYDelay() <= 0) {
				o.element.setYDelay(0);
				o.stateMachine.changeState(Minotaur_StateStand.getInstance(), o);
			}
			break;
		case Direction.DOWN:
			o.element.setYDelay(o.element.getYDelay()+o.element.getSpeed());
			if(o.element.getYDelay() >= 0) {
				o.element.setYDelay(0);
				o.stateMachine.changeState(Minotaur_StateStand.getInstance(), o);
			}
			break;
		case Direction.LEFT:
			o.element.setXDelay(o.element.getXDelay()-o.element.getSpeed());
			if(o.element.getXDelay() <= 0) {
				o.element.setXDelay(0);
				o.stateMachine.changeState(Minotaur_StateStand.getInstance(), o);
			}
			break;
		case Direction.RIGHT:
			o.element.setXDelay(o.element.getXDelay()+o.element.getSpeed());
			if(o.element.getXDelay() >= 0) {
				o.element.setXDelay(0);
				o.stateMachine.changeState(Minotaur_StateStand.getInstance(), o);
			}
			break;
		}
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}
}
