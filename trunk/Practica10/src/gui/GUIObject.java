package gui;

import utils.ArrayList;
import utils.Location2D;
import utils.ResourcesHandler;
import game.GameObject;

public class GUIObject {

	// Identificador del objeto.
	private double id;
	
	// Objeto que representa.
	private GameObject object;
	
	// Identificador de la animación.
	private String animation;
	
	// Frame actual.
	private int frame;
	
	// Identificadores de los frames del objeto.
	private ArrayList frames;
	
	// Constructor por defecto.
	public GUIObject() {
		this.id = -1;
		this.object = null;
		this.frame = 0;
		this.animation = "";
	}
	
	// Constructor sobrecargado
	public GUIObject(double id, GameObject object, String animation) {
		this.id = id;
		this.object = object;
		this.frame = 0;
		this.animation = animation;
		loadFrames();
	}
	
	// Obtiene el identificador.
	public double getId() {
		return this.id;
	}
	
	// Establece el identificador.
	public void setId(double id) {
		this.id = id;
	}
	
	// Obtiene el objeto.
	public GameObject getObject() {
		return this.object;
	}
	
	// Establece el objeto.
	public void setObject(GameObject object) {
		this.object = object;
	}
	
	// Obtiene el id de la imagen.
	public String getImage() {
		return (String) this.frames.get(frame);
	}
	
	// Establece el id de la imagen.
	public void setFrames(ArrayList frames) {
		this.frames = frames;
	}

	public Location2D getLocation() {
		return object.getLocation();
	}

	public void animate() {
		frame ++;
		if(frame>=frames.size())
			frame = 0;
	}
	
	public void loadFrames() {
		// Obtenemos los frames del objeto.
		frames = ResourcesHandler.getInstance().getFrames(animation);
	}
}
