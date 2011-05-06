package gui;

import java.util.Enumeration;

import game.GameHandler;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import utils.Debugger;
import utils.Location2D;
import utils.ResourcesHandler;

public class GUIGame extends Canvas implements Runnable, CommandListener{
   	
	// Comando para abandonar el juego.
	private Command back;
	
	// Comando para mostrar el minimapa.
	private Command map;
	
	// Comando para mostrar el inventario.
	private Command inventory;
	
	// Comando para guardar el progreso.
	private Command save;
	
	// Camara.
	private GUICamera camera;
	
	// Indica si el juego está ejecutándose.
	private boolean running = false;
	
	// Constructor por defecto.
	public GUIGame() {
		this.camera = new GUICamera();
		
	    map = new Command(ResourcesHandler.getInstance().getText("map"), Command.OK, 0);
	    inventory = new Command(ResourcesHandler.getInstance().getText("inventory"), Command.OK,1);
	    save = new Command(ResourcesHandler.getInstance().getText("save"), Command.OK,2);
	    back = new Command(ResourcesHandler.getInstance().getText("back"), Command.BACK, 3);
		
		addCommand(map);
		addCommand(inventory);
		addCommand(save);
		addCommand(back);
	    setCommandListener(this);
	}

	// Ejecutamos los comandos.
	public void commandAction(Command command, Displayable displayable) {
		// Detenemos el dibujado.
		stop();
		
		// Cambiamos de pantalla.
		if(command == map) {
			GUIHandler.getInstance().showScreen(GUIScreens.MAP);
		} else if (command == inventory) {
			GUIHandler.getInstance().showScreen(GUIScreens.INVENTORY);
		} else if (command == back) {
			GUIHandler.getInstance().showScreen(GUIScreens.MAINMENU);
		}
	}

	protected void paint(Graphics graphics) {
		Debugger.debug.print("GUIGame", "paint", "Starts");
		// Dibujamos el fondo negro.
	   	graphics.setColor(0,0,0);
		graphics.fillRect(0, 0, getWidth(), getHeight());  	
	   	
		// Dibujamos la pantalla.
		paintScreen(graphics);
		Debugger.debug.print("GUIGame", "paint", "Ends");
		
		// Dibujamos la barra de estado.
		paintStatusBar(graphics);
	}

	private void paintScreen(Graphics graphics) {

	   	// Obenemos la posición de la camara.
	   	Location2D cameraLocation = camera.getLocation();

	   	// Calculamos el área de recorte.
	   	int xMin = cameraLocation.getX() - 5;
	   	int xMax = cameraLocation.getX() + 5;
	   	int yMin = cameraLocation.getY() - 4;
	   	int yMax = cameraLocation.getY() + 5;

	 // Obtenemos los elementos del escenario.
	   	Enumeration objects1 = GUIHandler.getInstance().getObjects(1).elements();

	   	// Dibujamos el escenario.
	   	while(objects1.hasMoreElements()){
	   		// Obtenemos el objeto i.
			GUIObject o = (GUIObject) objects1.nextElement();
			
			// Obtenemos la localización del objeto.
			Location2D objectLocation = o.getLocation();
			
			// Comprobamos si se encuentra dentro del área de dibujado.
			if(objectLocation.getX() >= xMin -2 && objectLocation.getX() <= xMax +2 && objectLocation.getY() >= yMin - 2 && objectLocation.getY() <= yMax + 2) {
				// Actualizamos la animación.
				o.animate();
				
				// Lo dibujamos en la zona correspondiente.
				graphics.drawImage(ResourcesHandler.getInstance().getImage(o.getImage()),
						GUIHandler.xBlockSize * (objectLocation.getX() - xMin) - camera.getSmoothX() + o.getObject().getXDelay(),
						GUIHandler.yBarSize + (GUIHandler.yBlockSize * (objectLocation.getY() - yMin)) - camera.getSmoothY() + o.getObject().getYDelay(),
						Graphics.LEFT | Graphics.TOP);
			}
		}
	   	
	   	// Obtenemos el resto de elementos
	   	Enumeration objects2 = GUIHandler.getInstance().getObjects(2).elements();

	   	// Dibujamos.
	   	while(objects2.hasMoreElements()){
	   		// Obtenemos el objeto i.
			GUIObject o = (GUIObject) objects2.nextElement();
			
			// Obtenemos la localización del objeto.
			Location2D objectLocation = o.getLocation();
			
			// Comprobamos si se encuentra dentro del área de dibujado.
			if(objectLocation.getX() >= xMin -2 && objectLocation.getX() +2 <= xMax && objectLocation.getY() >= yMin -2 && objectLocation.getY() <= yMax +2) {
				// Actualizamos la animación.
				o.animate();
				
				// Lo dibujamos en la zona correspondiente.
				graphics.drawImage(ResourcesHandler.getInstance().getImage(o.getImage()),
						GUIHandler.xBlockSize * (objectLocation.getX() - xMin) - camera.getSmoothX() + o.getObject().getXDelay(),
						GUIHandler.yBarSize + (GUIHandler.yBlockSize * (objectLocation.getY() - yMin)) - camera.getSmoothY() + o.getObject().getYDelay(),
						Graphics.LEFT | Graphics.TOP);
			}
		}
	}

	// Dibuja la barra de estado y sus componentes.
	private void paintStatusBar(Graphics graphics) {
		// Dibujamos el fondo de la barra de estado.
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
	   	/*		   	
		// Dibujamos los elementos de la barra de estado.
	   	// Desfase en el eje x en pixels.
	   	int xDelay = 0;
	   	
	   	// Desfase en el eje y en pixels.
	   	int yDelay = -1;
	   	
	   	// Obtenemos las vidas del jugador.
	   	int restLives = stage.getPlayer().getLives();

	   	for(int i=0; i<5; i++) {
	   		if(restLives >= 2) {
	   			graphics.drawImage(liveGraphics[0], xDelay+(i*17), yDelay, Graphics.LEFT | Graphics.TOP);
	   			restLives -= 2;
	   		} else if(restLives == 1) {
	   			graphics.drawImage(liveGraphics[1], xDelay+(i*17), yDelay, Graphics.LEFT | Graphics.TOP);
	   			restLives--;
	   		} else {
	   			graphics.drawImage(liveGraphics[2], xDelay+(i*17), yDelay, Graphics.LEFT | Graphics.TOP);
	   		}	
	   	}*/
	}

	// Inicia el render.
	public void start() {
		// Creamos un nuevo hilo de ejecución.
		Thread t = new Thread(this);
	    t.start();
	}
	
	// Detiene el render.
	public void stop() {
		running = false;
	}
	
	public void run() {
		running = true;
		try {
		while(running) {
			Debugger.debug.print("GUIGame", "run", "Starts");
			// Obtenemos el instante de inicio del fotograma.
			long cycleStartTime = System.currentTimeMillis();

			// Actualizamos la posición de la cámara
			camera.update();
			
			// Acualizamos el juego.
			GameHandler.getInstance().update();

			// Dibujamos el juego.
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
	        Debugger.debug.print("GUIGame", "run", "Ends");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Captura los eventos de teclado y se los pasa al manejador del juego.
	protected void keyPressed(int keyCode) {
		GameHandler.getInstance().addPressEvent(keyCode);
	}
	
	protected void keyReleased(int keyCode) {
		GameHandler.getInstance().addReleaseEvent(keyCode);
	}

	// Establece la localización de la cámara.
	// El modo 0 traslada la cámara al instante.
	// El modo 1 traslada la cámara con desplazamiento por bloque.
	// El modo 2 traslada la cámara con desplazamiento suavizado.
	public void setCamera(Location2D camera, int mode) {
		this.camera.setDestination(camera, mode);
	}

	// Devuelve la localización de la cámara.
	public Location2D getCamera() {
		return this.camera.getLocation();
	}
}
