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
	
	// Comando para mostrar el inventario.
	private Command inventory;
	
	// Comando para guardar el progreso.
	private Command save;
	
	// Camara.
	private GUICamera camera;
	
	// Indica si el juego está ejecutándose.
	private boolean running;
	
	// Hilo para la ejecución.
	Thread t;
	
	// Constructor por defecto.
	public GUIGame() {
		// No se está ejecutando el juego.
		running = false;
		
		// No se ha inicializado el hilo.
		t = null;
		
		this.camera = new GUICamera();
		
	    inventory = new Command(ResourcesHandler.getInstance().getText("inventory"), Command.OK,1);
	    save = new Command(ResourcesHandler.getInstance().getText("save"), Command.OK,2);
	    back = new Command(ResourcesHandler.getInstance().getText("back"), Command.BACK, 3);
		
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
		if (command == inventory) {
			GUIHandler.getInstance().showScreen(GUIScreens.INVENTORY);
		} else if (command == back) {
			GUIHandler.getInstance().showScreen(GUIScreens.MAINMENU);
		}
	}

	// Dibuja toda la pantalla del juego.
	protected void paint(Graphics graphics) {
		Debugger.debug.print("GUIGame", "paint", "Starts");
		
		// Dibujamos el fondo negro.
	   	graphics.setColor(0,0,0);
		graphics.fillRect(0, 0, getWidth(), getHeight());  	
	   	
		// Dibujamos la pantalla.
		paintScreen(graphics);
		
		// Dibujamos la barra de estado.
		paintStatusBar(graphics);
		
		Debugger.debug.print("GUIGame", "paint", "Ends");
	}

	// Dibuja el escenario.
	private void paintScreen(Graphics graphics) {

	   	// Obenemos la posición de la camara.
	   	Location2D cameraLocation = camera.getLocation();

	   	// Calculamos el área de recorte.
	   	int xMin = cameraLocation.getX() - 4;
	   	int xMax = cameraLocation.getX() + 4;
	   	int yMin = cameraLocation.getY() - 3;
	   	int yMax = cameraLocation.getY() + 4;

	   	// Obtenemos los elementos del escenario.
	   	for(int i=0; i< GUIHandler.maxDept; i++) {
	   		Enumeration objects = GUIHandler.getInstance().getObjects(i).elements();
	   
	   		// Dibujamos el escenario.
		   	while(objects.hasMoreElements()){
		   		// Obtenemos el objeto i.
				GUIObject o = (GUIObject) objects.nextElement();
				
				// Obtenemos la localización del objeto.
				Location2D objectLocation = o.getLocation();
				
				// Comprobamos si se encuentra dentro del área de dibujado.
				if(objectLocation.getX() >= xMin - 1 && objectLocation.getX() <= xMax + 1 && objectLocation.getY() >= yMin - 1 && objectLocation.getY() <= yMax + 1) {
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
	   			   	
	   	// Establecemos el color negro.
	   	graphics.setColor(0,0,0);
	   	
	   	// Mostramos el nombre de la pantalla.
	   	graphics.drawString(GameHandler.getInstance().getCurrentPhase().getCurrentStage().getName(), getWidth()/2, 13, Graphics.BASELINE | Graphics.HCENTER);
	   	
	   	// Desfase en el eje x en pixels.
	   	int xDelay = 0;
	   	
	   	// Desfase en el eje y en pixels.
	   	//int yDelay = -1;
	   	int yDelay = 20;
	   	
	   	// Obtenemos las vidas del jugador.
	   	int restLives = GameHandler.getInstance().getCurrentPhase().getCurrentStage().getPlayer().getLives();

	   	for(int i=0; i<5; i++) {
	   		if(restLives >= 2) {
	   			graphics.drawImage(ResourcesHandler.getInstance().getImage("live_on"), xDelay+(i*17), yDelay, Graphics.LEFT | Graphics.TOP);
	   			restLives -= 2;
	   		} else if(restLives == 1) {
	   			graphics.drawImage(ResourcesHandler.getInstance().getImage("live_half"), xDelay+(i*17), yDelay, Graphics.LEFT | Graphics.TOP);
	   			restLives--;
	   		} else {
	   			graphics.drawImage(ResourcesHandler.getInstance().getImage("live_off"), xDelay+(i*17), yDelay, Graphics.LEFT | Graphics.TOP);
	   		}	
	   	}
	}

	// Inicia el render.
	public void start() {

		if(running == false) {
			// Creamos un nuevo hilo de ejecución.
			t = new Thread(this);
			t.start();
		} else {
			System.err.println("GUIGame::There is another thread in progress.");
			System.exit(-1);
		}
	}
	
	// Detiene el render.
	public void stop() {

		running = false;
		if(t!=null)
			if(t.isAlive())
				t.interrupt();
	}
	
	public void run() {

		running = true;
		try {
		while(running) {
			
			Debugger.debug.print("GUIGame", "run", "Starts");
			// Obtenemos el instante de inicio del fotograma.
			long cycleStartTime = System.currentTimeMillis();

			// Dibujamos el juego.
			repaint();
			
			// Actualizamos la posición de la cámara
			camera.update();
			
			// Acualizamos el juego.
			GameHandler.getInstance().update();

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

	protected void keyRepeated(int keyCode) {
		GameHandler.getInstance().addPressEvent(keyCode);
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
		return this.camera.getDestination();
	}
}
