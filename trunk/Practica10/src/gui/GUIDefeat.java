package gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import utils.Debugger;
import utils.ResourcesHandler;

public class GUIDefeat extends Canvas implements CommandListener{

	// Comando para continuar el juego.
	private Command retry;
	private Command exit;
	
	// Constructor por defecto.
	public GUIDefeat() {
		retry = new Command(ResourcesHandler.getInstance().getText("retry"), Command.OK, 0);
		exit = new Command(ResourcesHandler.getInstance().getText("exit"), Command.BACK, 0);
		addCommand(retry);
		addCommand(exit);
	    setCommandListener(this);
	}
	
	public void commandAction(Command command, Displayable displayable) {
		if (command == retry) {
			GUIHandler.getInstance().resumeGame();
		} else if(command == exit) {
			GUIHandler.getInstance().showScreen(GUIScreens.MAINMENU);
		}
	}

	protected void paint(Graphics graphics) {
		Debugger.debug.print("GuiDefeat", "paint", "Starts");
		
		// Dibujamos la imagen de la diapositiva.
	   	graphics.drawImage(ResourcesHandler.getInstance().getImage("defeat"), 0, 50, Graphics.LEFT | Graphics.TOP);	
	   	
	   	Debugger.debug.print("GuiDefeat", "paint", "Ends");
	}
}
