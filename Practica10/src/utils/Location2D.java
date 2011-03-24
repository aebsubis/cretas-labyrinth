package utils;

/**
 * Clase que define una coordenada en 2 dimensiones.
 * @author Daniel
 * 
 */
public class Location2D {

	// Coordenada x.
	private int x;
	
	// Coordenada y.
	private int y;
	
	// Constructor por defecto.
	public Location2D() {
		x = 0;
		y = 0;
	}
	
	// Constructor por defecto.
	public Location2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Devuelve el valor de la coordenada x.
	public int getX() {
		return x;
	}
	
	// Establece el valor de la coordenada x.
	public void setX(int x) {
		this.x = x;
	}
	
	// Devuelve el valor de la coordenada y.
	public int getY() {
		return y;
	}
	
	// Establece el valor de la coordenada y.
	public void setY(int y) {
		this.y = y;
	}
	
	// Establece las coordenadas x,y.
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Obtiene las coordenadas x,y.
	public void get(int x, int y) {
		x = this.x;
		y = this.y;
	}
}
