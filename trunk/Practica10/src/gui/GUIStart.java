package gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import utils.ResourcesHandler;

public class GUIStart extends Canvas implements CommandListener {

	// Comando para abandonar el juego.
	private Command exit;
	
	// Comando para iniciar el juego.
	private Command start;
	
	public GUIStart() {
		start = new Command(ResourcesHandler.getInstance().getText("start"), Command.OK, 0);
		exit = new Command(ResourcesHandler.getInstance().getText("exit"), Command.EXIT, 1);
		addCommand(start);
		addCommand(exit);
	    setCommandListener(this);
	}
	public void commandAction(Command command, Displayable displayable) {
		if (command == start)
			GUIHandler.getInstance().showScreen(GUIScreens.MAINMENU);
		if (command == exit)
			GUIHandler.getInstance().close();
	}

	protected void paint(Graphics graphics) {
		// Establecemos el color naranja claro.
	   	graphics.setColor(200,200,200);
	   	
	   	// Dibujamos el rectángulo superior.
	   	graphics.fillRect(0, 0, getWidth(), 18);
	   	
	   	// Establecemos el color gris oscuro.
	   	graphics.setColor(106,106,106);
	   	
	   	// Dibujamos el borde inferior.
	   	graphics.fillRect(0, 18, getWidth(), 1);
	   	
	   	// Establecemos el color blanco.
	   	graphics.setColor(255,255,255);
	   	
	   	// Dibujamos el brillo.
	   	graphics.fillRect(1, 17, getWidth()-1, 1);
	   	
	   	// Establecemos el color negro.
	   	graphics.setColor(0,0,0);

	   	// Mostramos la imagen de fondo.
	   	graphics.drawImage(ResourcesHandler.getInstance().getImage("start_splash"), 0, 19, Graphics.LEFT | Graphics.TOP);	
	   	
	   	// Mostramos el texto de bienvenida.
	   	graphics.drawString(ResourcesHandler.getInstance().getText("game_title"), getWidth()/2, 13, Graphics.BASELINE | Graphics.HCENTER);
	}

}
