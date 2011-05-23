package gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import com.sun.perseus.model.Stop;

import utils.ResourcesHandler;

public class GUIMainMenu extends Canvas implements Runnable, CommandListener{

	// Comando para abandonar el menu.
	private Command back;
	
	// Comando para seleccionar una opción.
	private Command select;
	
	// Número de opciones.
	private int numOptions;
	
	// Opción activa.
	private int optionFocus;
	
	// indica si se ha seleccionado una opción.
	private boolean optionSelected;
	
	// Hilo para la ejecución.
	Thread t;
	
	// Constructor por defecto.
	public GUIMainMenu() {	  
		
		// No se ha seleccionado ninguna opción.
		optionSelected = false;
		
		// No se ha inicializado el hilo.
		t = null;
		
		select = new Command(ResourcesHandler.getInstance().getText("select"), Command.OK, 0);
		back = new Command(ResourcesHandler.getInstance().getText("back"), Command.BACK, 1);
		addCommand(select);
		addCommand(back);
	    setCommandListener(this);
	  	
	    // Número de opciones.
	    numOptions = 4;
	    
	  	// Establecemos la primera opción como activa.
	  	optionFocus = 0;
	}

	public void commandAction(Command command, Displayable diplayable) {
		if (command == select) {
			
			// Detenemos el dibujado.
			stop();
			
			// Reproducimos el sonido.
			ResourcesHandler.getInstance().playSound("selectoption");
			
			switch(optionFocus) {
			case 0:
				// Nuevo juego.
				optionSelected = true;
				GUIHandler.getInstance().newGame();
				break;
			case 1:
				// Continuar juego.
				optionSelected = true;
				GUIHandler.getInstance().resumeGame2();
				break;
			case 2:
				// Puntuaciones.
				//GUIHandler.getInstance().showScreen(GUIScreens.SCORES);
				break;
			case 3:
				// Opciones.
				GUIHandler.getInstance().showScreen(GUIScreens.SETTINGS);
				break;
			}
		}
		
		if (command == back)
			GUIHandler.getInstance().showScreen(GUIScreens.STARTSCREEN);
	}

	protected void paint(Graphics graphics) {
		// Establecemos el color gris claro.
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
	   	graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_splash"), 0, 19, Graphics.LEFT | Graphics.TOP);	
	   	
	   	// Mostramos el texto de bienvenida.
	   	graphics.drawString(ResourcesHandler.getInstance().getText("select_option"), getWidth()/2, 13, Graphics.BASELINE | Graphics.HCENTER);
	   	
	   	// Mostramos las opciones.
	   	if( optionFocus == 0)
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_newgame_focus"), 0, 30, Graphics.LEFT | Graphics.TOP);
	   	else
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_newgame"), 0, 30, Graphics.LEFT | Graphics.TOP);
	   	if( optionFocus == 1)
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_continue_focus"), 0, 65, Graphics.LEFT | Graphics.TOP);
	   	else
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_continue"), 0, 65, Graphics.LEFT | Graphics.TOP);
	   	if( optionFocus == 2)
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_scores_focus"), 0, 100, Graphics.LEFT | Graphics.TOP);
	   	else
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_scores"), 0, 100, Graphics.LEFT | Graphics.TOP);
	   	if( optionFocus == 3)
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_settings_focus"), 0, 135, Graphics.LEFT | Graphics.TOP);
	   	else
	   		graphics.drawImage(ResourcesHandler.getInstance().getImage("menu_settings"), 0, 135, Graphics.LEFT | Graphics.TOP);
	}
	
	protected void keyPressed(int keyCode)
   {
      switch (getGameAction(keyCode))
      {
         case UP:
        	// Reproducimos el sonido.
 			ResourcesHandler.getInstance().playSound("changeoption");
 			
            optionFocus--;
            if(optionFocus<0)
            	optionFocus = numOptions-1;
            break;
         case DOWN:
        	// Reproducimos el sonido.
  			ResourcesHandler.getInstance().playSound("changeoption");
  			
            optionFocus++;
            if(optionFocus>=numOptions)
            	optionFocus=0;
            break;
         case FIRE:
        	 commandAction(select, this);
        	 break;
      }
   }

	// Inicia el render.
	public void start() {
		if(t!=null)
			if(t.isAlive())
				t.interrupt();
		
		// Creamos un nuevo hilo de ejecución.
		t = new Thread(this);
	    t.start();
	}
	
	// Detiene el render.
	public void stop() {
		optionSelected = true;
		if(t!=null)
			if(t.isAlive())
				t.interrupt();
	}
	
	// Dibuja el menú hasta que se selecciona una opción.
	public void run() {
		optionSelected = false;
		while(!optionSelected) {
			// Obtenemos el instante de inicio del fotograma.
			long cycleStartTime = System.currentTimeMillis();
			
			// Redibujamos la pantalla.
			repaint();
			
			// Calculamos el tiempo transcurrido.
			long timeSinceStart = (cycleStartTime - System.currentTimeMillis());
	        if (timeSinceStart < GUIHandler.MS_PER_FRAME)
	        {
	           try
	           {
	              Thread.sleep(GUIHandler.MS_PER_FRAME - timeSinceStart);
	           }
	           catch(java.lang.InterruptedException e)
	           { }
	        }
		}
	}
}
