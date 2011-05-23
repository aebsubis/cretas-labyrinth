package gui;

import game.GameHandler;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import utils.ArrayList;
import utils.Debugger;
import utils.ResourcesHandler;

public class GUIPresentation extends Canvas implements CommandListener{

	// Comando para saltar la presentación.
	private Command skip;
	
	// Comando para pasar a la siguiente diapositiva.
	private Command next;
	
	// Imagen de presentación.
	private String image;
	
	// Texto de la presentación.
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
		if (command == next) {
			
			// Reproducimos el sonido.
 			ResourcesHandler.getInstance().playSound("nextslide");
 			
			GameHandler.getInstance().nextSlide();
			
		} if (command == skip) {
		
			// Reproducimos el sonido.
 			ResourcesHandler.getInstance().playSound("skipslide");
 			
			GameHandler.getInstance().skipSlide();
		}
	}

	protected void paint(Graphics graphics) {
		Debugger.debug.print("GuiPresentation", "paint", "Starts");
		
		// Dibujamos la imagen de la diapositiva.
	   	graphics.drawImage(ResourcesHandler.getInstance().getImage(image), 0, 0, Graphics.LEFT | Graphics.TOP);	
	   	
		// Dibujamos el texto de la diapositiva.
	   	String completeText = ResourcesHandler.getInstance().getText(text);
	   	ArrayList lines = new ArrayList();
	   	String currentLine = "";
	   	for(int i=0; i<completeText.length(); i++) {
	   		if(completeText.charAt(i) == '\n') {
	   			lines.add(currentLine);
	   			currentLine = "";
	   		} else {
	   			currentLine += completeText.charAt(i);
	   		}
	   	}
	   	lines.add(currentLine);
	   	
	   	for(int i=0; i<lines.size(); i++) {
	   		graphics.drawString((String)lines.get(i), getWidth()/2, getHeight()-(20*(lines.size()-i)), Graphics.BASELINE | Graphics.HCENTER);
	   	}
	   	
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
