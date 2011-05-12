package game;

import utils.Location2D;

public class Trigger extends GameObject{

	// Constructor por defecto.
	public Trigger() {
		
		// Llamamos al constructor de la clase padre.
		super();
	}
	
	// Constructor sobrecargado.
	public Trigger(double id, Location2D location, String IAType, String GFXType, int depth) {

		// Llamamos al constructor de la clase padre.
		super(id, location, IAType, GFXType, depth);
	}
}
