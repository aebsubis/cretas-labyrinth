package gui;

import utils.Location2D;

public class GUICamera {
	
	// Posición de la cámara.
	private Location2D location;
	
	// Destino de la cámara.
	private Location2D destination;
	
	// Modo de desplazamiento
	private int mode;
	
	// Valores de suavizado en el desplazamiento de la cámara.
	private int smoothX;
	private int smoothY;
	
	// Velocidad de la cámara.
	private int velX;
	private int velY;
	
	GUICamera() {
		mode = 0;
		location = new Location2D();
		destination= new Location2D();
		smoothX = 0;
		smoothY = 0;
		velX=0;
		velY=0;
	}
	
	// Devuelve la localización
	public Location2D getLocation() {
		return this.location;
	}
	
	// Establece la localización.
	public void setLocation(Location2D location) {
		this.location = location;
	}
	
	// Devuelve el destino.
	public Location2D getDestination() {
		return this.destination;
	}
	
	// Establece el destino.
	public void setDestination(Location2D destination, int mode) {
		this.destination = destination;
		setMode(mode);
	}
	
	// Devuelve el modo.
	public int getMode() {
		return this.mode;
	}
	
	// Establece el modo
	public void setMode(int mode) {
			this.mode = mode;
	}
	
	// Actualiza su localización.
	public void update() {
		switch(mode) {
		case 0:
			smoothX = 0;
			smoothY = 0;
			location = destination;
			break;
		case 1:
			int difX = Math.abs(destination.getX()-location.getX());
			if(velX > difX)
				velX--;
			else if(velX < difX)
				velX++;
			
			if (destination.getX() > location.getX()) {
				smoothX+=velX;
				if (smoothX >= GUIHandler.xBlockSize) {
					location.setX(location.getX()+1);
					smoothX -= GUIHandler.xBlockSize;
				}
			} else if (destination.getX() < location.getX()) {
				smoothX-=velX;
				if (smoothX <= - GUIHandler.xBlockSize) {
					location.setX(location.getX()-1);
					smoothX += GUIHandler.xBlockSize;
				}
			}
			
			int difY = Math.abs(destination.getY()-location.getY());
			if(velY > difY)
				velY--;
			else if(velY < difY)
				velY++;
			
			if (destination.getY() > location.getY()) {
				smoothY+=velY;
				if (smoothY >= GUIHandler.yBlockSize) {
					location.setY(location.getY()+1);
					smoothY -= GUIHandler.yBlockSize;
				}
			} else if (destination.getY() < location.getY()) {
				smoothY-=velY;
				if (smoothY <= -GUIHandler.yBlockSize) {
					location.setY(location.getY()-1);
					smoothY += GUIHandler.yBlockSize;
				}
			}
			break;
		}
	}
	
	// Devuelve el suavizado en x
	public int getSmoothX() {
		return this.smoothX;
	}
	
	// Devuelve el suavizado en y
	public int getSmoothY() {
		return this.smoothY;
	}
}