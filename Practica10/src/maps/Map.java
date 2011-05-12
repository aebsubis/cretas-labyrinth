package maps;

import utils.ArrayList;

public class Map {

	// Ancho del mapa a cargar.
	private int width;
	
	// Alto del mapa a cargar.
	private int height;
	
	// Elementos del escenario.
	private MapElement[][] sceneryElements;	
	
	// Indica si hay niebla en el mapa.
	private boolean fog;
	
	// Elementos del mapa.
	private ArrayList elements;

	// Ordenada X inicial del mapa.
	private int startLocationX;
	
	// Ordenada Y inicial del mapa.
	private int startLocationY;
	
	// Ordenada X final del mapa.
	private int endLocationX;
	
	// Ordenada Y final del mapa.
	private int endLocationY;
	
	// Constructor por defecto.
	public Map() {
		
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MapElement[][] getSceneryElements() {
		return sceneryElements;
	}

	public MapElement getSceneryElement(int i, int j) {
		return sceneryElements[i][j];
	}
	
	public void setSceneryElements(MapElement[][] sceneryElements) {
		this.sceneryElements = sceneryElements;
	}
	
	public void setSceneryElement(int i, int j, MapElement element) {
		this.sceneryElements[i][j] = element;
	}

	public boolean isFog() {
		return fog;
	}

	public void setFog(boolean fog) {
		this.fog = fog;
	}
	
	public ArrayList getElements() {
		return elements;
	}

	public void setElements(ArrayList elements) {
		this.elements = elements;
	}
	
	public int getStartLocationX() {
		return startLocationX;
	}

	public void setStartLocationX(int startLocationX) {
		this.startLocationX = startLocationX;
	}

	public int getStartLocationY() {
		return startLocationY;
	}

	public void setStartLocationY(int startLocationY) {
		this.startLocationY = startLocationY;
	}

	public int getEndLocationX() {
		return endLocationX;
	}

	public void setEndLocationX(int endLocationX) {
		this.endLocationX = endLocationX;
	}

	public int getEndLocationY() {
		return endLocationY;
	}

	public void setEndLocationY(int endLocationY) {
		this.endLocationY = endLocationY;
	}
}
