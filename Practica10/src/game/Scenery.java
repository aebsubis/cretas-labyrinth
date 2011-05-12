package game;

import utils.Location2D;

/**
 * 
 * @author Daniel
 * 
 */
public class Scenery extends GameObject{
	
	// Indica si se puede atravesar.
	private boolean isPassable;	
	
	// Constructor por defecto.
	public Scenery() {
		super();
		
		this.isPassable = false;
	}
	
	// Constructor sobrecargado.
	public Scenery(double id, Location2D location, String AIType, String GFXType, int depth, boolean passable) {
		super(id, location, AIType, GFXType, depth);
		
		this.isPassable = passable;
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
