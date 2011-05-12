package gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import utils.Debugger;
import utils.ResourcesHandler;


public class GUIVictory extends Canvas implements CommandListener{
	// Comando para continuar el juego.
	private Command next;
	
	// Constructor por defecto.
	public GUIVictory() {
		next = new Command(ResourcesHandler.getInstance().getText("next"), Command.OK, 0);
		addCommand(next);
	    setCommandListener(this);
	}
	
	public void commandAction(Command command, Displayable displayable) {
		if (command == next) {
			GUIHandler.getInstance().resumeGame();
		}
	}

	protected void paint(Graphics graphics) {
		Debugger.debug.print("GuiVictory", "paint", "Starts");
		
		// Dibujamos la imagen de la diapositiva.
	   	graphics.drawImage(ResourcesHandler.getInstance().getImage("victory"), 0, 50, Graphics.LEFT | Graphics.TOP);	
	   	
	   	Debugger.debug.print("GuiVictory", "paint", "Ends");
	}
}
