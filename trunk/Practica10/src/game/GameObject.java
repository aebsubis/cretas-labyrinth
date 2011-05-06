package game;

import utils.Location2D;

/**
 * Clase que define los atributos y operaciones b�sicas de un objeto del juego.
 * @author Daniel
 *
 */
public abstract class GameObject {

	// Identificador del objeto.
	private double id;
	
	// Localizaci�n del objeto.
	private Location2D location;
	
	// Delay de la posici�n.
	int xDelay;
	int yDelay;
	
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
	
	// Obtiene la localizaci�n del objeto.
	public Location2D getLocation() {
		return this.location;
	}
	
	// Establece la localizaci�n del ojbeto.
	public void setLocation(Location2D location) {
		this.location = location;
	}
	
	// Obtiene el xDelay.
	public int getXDelay() {
		return xDelay;
	}
	
	// Establece el xDelay.
	public void setXDelay(int x) {
		this.xDelay = x;
	}
	
	// Obtiene el yDelay.
	public int getYDelay() {
		return yDelay;
	}
	
	// Establece el yDelay.
	public void setYDelay(int y) {
		this.yDelay = y;
	}
}
