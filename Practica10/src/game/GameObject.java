package game;

import utils.Location2D;

/**
 * Clase que define los atributos y operaciones básicas de un objeto del juego.
 * @author Daniel
 *
 */
public abstract class GameObject {

	// Identificador del objeto.
	private double id;
	
	// Tipo del objeto GFX.
	private String GFXType;
	
	// Tipo del objeto IA.
	private String AIType;
	
	// Profundidad a la que se encuentra el objeto.
	private int depth;
	
	// Localización del objeto.
	private Location2D location;
	
	// Delay de la posición.
	int xDelay;
	int yDelay;
	
	// Construcor por defecto.
	public GameObject() {
		this.id = -1;
		this.location = new Location2D(-1,-1);
	}
	
	// Constructor sobrecargado.
	public GameObject(double id, Location2D location, String AIType, String GFXType, int depth) {
		this.id = id;
		this.location = location;
		this.AIType = AIType;
		this.GFXType = GFXType;
		this.depth = depth;
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
	
	// Obtiene el tipo GFX.
	public String getGFXType() {
		return GFXType;
	}
	
	// Establece el tipo GFX.
	public void setGFXType(String GFXType) {
		this.GFXType = GFXType;
	}
	
	// Obtiene el tipo IA.
	public String getAIType() {
		return AIType;
	}
	
	// Establece el tipo IA.
	public void setAIType(String AIType) {
		this.AIType = AIType;
	}
	
	// Obtiene la profundidad.
	public int getDepth() {
		return depth;
	}
	
	// Establece la profundidad.
	public void setDepth(int depth) {
		this.depth = depth;
	}
}
