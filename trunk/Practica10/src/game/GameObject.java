package game;

import gui.GUIHandler;
import utils.Location2D;
import utils.ResourcesHandler;

/**
 * Clase que define los atributos y operaciones básicas de un objeto del juego.
 * @author Daniel
 *
 */
public abstract class GameObject {

	// Identificador del objeto.
	private double id;
	
	// Localización del objeto.
	private Location2D location;
	
	// Construcor por defecto.
	public GameObject() {
		this.id = -1;
		this.location = new Location2D(-1,-1);
	}
	
	// Constructor sobrecargado.
	public GameObject(double id, Location2D location) {
		this.id = id;
		this.location = location;
	}
	
	// Obtiene el identificador del objeto.
	public double getId() {
		return id;
	}
	
	// Establece el identificador del objeto.
	public void setId(double id) {
		this.id = id;
	}
	
	// Obtiene la localización del objeto.
	public Location2D getLocation() {
		return this.location;
	}
	
	// Establece la localización del ojbeto.
	public void setLocation(Location2D location) {
		this.location = location;
	}
	
	// Desplaza el objeto.
	public Location2D move(int direction) {
		switch(direction) {
		case Direction.UP:
			location.setY(this.location.getY()-1);
			break;
		case Direction.LEFT:
			location.setX(this.location.getX()-1);
			break;
		case Direction.DOWN:
			location.setY(this.location.getY()+1);
			break;
		case Direction.RIGHT:
			location.setX(this.location.getX()+1);
			break;
		}
		return location;
	}
}
