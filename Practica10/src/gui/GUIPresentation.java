package gui;

import game.GameHandler;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import utils.Debugger;
import utils.ResourcesHandler;

public class GUIPresentation extends Canvas implements CommandListener{

	// Comando para saltar la presentaci�n.
	private Command skip;
	
	// Comando para pasar a la siguiente diapositiva.
	private Command next;
	
	// Imagen de presentaci�n.
	private String image;
	
	// Texto de la presentaci�n.
	private String text;
	
	// Constructor por defecto.
	public GUIPresentation() {
		next = new Command(ResourcesHandler.getInstance().getText("next"), Command.OK, 0);
		skip = new Command(ResourcesHandler.getInstance().getText("skip"), Command.EXIT, 1);
		addCommand(next);
		addCommand(skip);
	    setCommandListener(this);
	}
	
	public void commandAction(Command command, Displayable displayable) {
		if (command == next)
			GameHandler.getInstance().nextSlide();
		if (command == skip)
			GameHandler.getInstance().skipSlide();
	}

	protected void paint(Graphics graphics) {
		Debugger.debug.print("GuiPresentation", "paint", "Starts");
		
		// Dibujamos la imagen de la diapositiva.
	   	graphics.drawImage(ResourcesHandler.getInstance().getImage(image), 0, 0, Graphics.LEFT | Graphics.TOP);	
	   	
		// Dibujamos el texto de la diapositiva.
	   	graphics.drawString(ResourcesHandler.getInstance().getText(text), getWidth()/2, getHeight()/2, Graphics.BASELINE | Graphics.HCENTER);

	   	Debugger.debug.print("GuiPresentation", "paint", "Ends");
	}
	
	// Establece la imagen de fondo.
	public void setBackground(String image) {
		this.image = image;
	}
	
	// Establece el texto de la diapositiva.
	public void setText(String text) {
		this.text = text;
	}

}