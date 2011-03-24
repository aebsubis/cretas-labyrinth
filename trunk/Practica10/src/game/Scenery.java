package game;

import utils.Location2D;

/**
 * 
 * @author Daniel
 * 
 */
public class Scenery extends GameObject{
	// Identificador del objeto.
	private double id;
	
	// Indica si se puede atravesar.
	private boolean isPassable;
	
	
	// Constructor por defecto.
	public Scenery() {
		super();
		
		this.isPassable = false;
	}
	
	// Constructor sobrecargado.
	public Scenery(double id, Location2D location, boolean passable) {
		super(id, location);
		
		this.isPassable = passable;
	}
	
	// Obtiene el identificador.
	public double getId() {
		return id;
	}
	
	// Establece el identificador.
	public void setId(double id) {
		this.id = id;
	}
	
	// Indica si se puede atravesar.
	public boolean isPassable() {
		return isPassable;
	}
	
	// Establece si se puede atravesar.
	public void setPassable(boolean passable) {
		isPassable = passable;
	}
}
