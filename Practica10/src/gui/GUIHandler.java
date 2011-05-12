package gui;

import java.util.Hashtable;

import game.GameHandler;
import game.GameObject;
import Practica10;
import javax.microedition.lcdui.Display;

import utils.ArrayList;
import utils.Location2D;

/**
 * 
 * @author Daniel
 * Manejador de la interfaz gráfica. La clase implementa el patrón singleton para poder
 * ser accedida desde cualquier parte del programa. Esta clase se encarga de las
 * operaciones con los gráficos.
 */
public class GUIHandler{
	// Desfase vertical ocupado por la barra de estado.
   	public static final int yBarSize = 19;
   	
	// Tamaño de los bloques en pixels.
   	public static final int xBlockSize = 20;
   	public static final int yBlockSize = 20;
   	
	// Milisegundos por fotograma (40mspf = 15fps)
	public static final int MS_PER_FRAME = 40;
	
	// Profundidad máxima.
	public static final int maxDept = 5;
	
	// Manejador de la interfaz gráfica.
	private static GUIHandler guiHandler;	
	
	// Instancia del midlet
	private Practica10 theMidlet;
	
	// GUIObjects
	private ArrayList objects;
	
	// Pantallas
	private GUIMainMenu mainmenuScreen;
	private GUILoadGame loadgameScreen;
	private GUISaveGame savegameScreen;
	private GUIGame gameScreen;
	private GUIPresentation presentationScreen;
	private GUIStart startScreen;
	private GUISettings settingsScreen;
	private GUIInventory inventoryScreen;
	private GUIVictory victoryScreen;
	private GUIDefeat defeatScreen;
	
	// Constructor privado.
	private GUIHandler() {
	}
	
	// Función estática para obtener la instancia de la clase.
	public static synchronized GUIHandler getInstance() {
		if(guiHandler == null) {
			guiHandler = new GUIHandler();
		}
		
		return guiHandler;
	}

	public void showScreen(int screen) {
		switch(screen) {
		case GUIScreens.MAINMENU:
			Display.getDisplay(theMidlet).setCurrent(mainmenuScreen);
			mainmenuScreen.start();
			break;
		case GUIScreens.LOADGAME:
			//Display.getDisplay(theMidlet).setCurrent(loadgameScreen);
			break;
		case GUIScreens.SAVEGAME:
			//Display.getDisplay(theMidlet).setCurrent(savegameScreen);
			break;
		case GUIScreens.GAME:
			Display.getDisplay(theMidlet).setCurrent(gameScreen);
			gameScreen.start();
			break;
		case GUIScreens.PRESENTATION:
			Display.getDisplay(theMidlet).setCurrent(presentationScreen);
			break;
		case GUIScreens.STARTSCREEN:
			Display.getDisplay(theMidlet).setCurrent(startScreen);
			break;
		case GUIScreens.SETTINGS:
			// Display.getDisplay(theMidlet).setCurrent(settingsScreen);
			break;
		case GUIScreens.INVENTORY:
			// Display.getDisplay(theMidlet).setCurrent(inventoryScreen);
			break;
		case GUIScreens.VICTORY:
			Display.getDisplay(theMidlet).setCurrent(victoryScreen);
			break;
		case GUIScreens.DEFEAT:
			Display.getDisplay(theMidlet).setCurrent(defeatScreen);
			break;
		}
	}
	
	public void init(Practica10 practica9) {
		// Guardamos la referencia al midlet.
		theMidlet = practica9;
		
		// Inicializamos el array de objetos.
		objects = new ArrayList();
		for(int i=0; i<maxDept; i++) {
			objects.add(new Hashtable());
		}
		
		// Inicializamos las pantallas.
		mainmenuScreen = new GUIMainMenu();
		loadgameScreen = new GUILoadGame();
		savegameScreen = new GUISaveGame();
		gameScreen = new GUIGame();
		presentationScreen = new GUIPresentation();
		startScreen = new GUIStart();
		settingsScreen = new GUISettings();
		inventoryScreen	= new GUIInventory();
		victoryScreen = new GUIVictory();
		defeatScreen = new GUIDefeat();
	}

	// Cierra la aplicación.
	public void close() {
		theMidlet.close();
	}

	// Inicia un nuevo juego.
	public void newGame() {		
		// Inicializamos el juego.
		GameHandler.getInstance().init();
	}

	// Continua el último juego.
	public void resumeGame() {
		// Cargamos los datos del juego.
		GameHandler.getInstance().resume();
	}
	
	// Resume que hace trampa porque antes de reiniciar establece la pantalla como perdida.
	public void resumeGame2() {
		GameHandler.getInstance().getCurrentPhase().getCurrentStage().setDefeated(true);
		resumeGame();
	}

	// Redibuja la pantalla del juego.
	public void renderGame() {
		gameScreen.repaint();
	}

	// Registra un objeto gráfico.
	public void registerObject(double id, GameObject object, String typeId, int dept) {
		
		// Creamos el objeto.
		GUIObject o = new GUIObject(id, object, typeId);

		// Lo añadimos a la lista de objetos.
		if(dept>=0 && dept<maxDept) {
			Hashtable h = (Hashtable)objects.get(dept);
			h.put(new Double(id), o);
		} else {
			System.err.println( "Dept \"" + dept + "\" not suported.");
			System.exit(-1);
		}
	}
	
	// Borra el objeto con el identificador indicado.
	public void deleteObject(double objectId) {
		boolean found = false;
		for(int i=0; i < maxDept && found == false; i++) {
			Hashtable h = (Hashtable)objects.get(i);
			GUIObject o = (GUIObject) h.get(new Double(objectId));
			if(o!=null) {
				found = true;
				h.remove(new Double(objectId));
			}
		}
		
		if(found==false) {
				System.err.println( "GUIObject \"" + objectId + "\" not found.");
				System.exit(-1);
		}
	}
	
	// Borra el objeto con el identificador indicado.
	public GUIObject getObject(double objectId) {
		GUIObject r = null;
		
		for(int i=0; i < maxDept && r == null; i++) {
			Hashtable h = (Hashtable)objects.get(i);
			GUIObject o = (GUIObject) h.get(new Double(objectId));
			if(o!=null) {
				r = o;
			}
		}
		
		if(r==null) {
			System.err.println( "GUIObject \"" + objectId + "\" not found.");
			System.exit(-1);
		}
	
		return r;
	}
	
	// Eliminas todos los objetos del manejador gráfico.
	public void deleteAllObjects() {
		for(int i=0; i<maxDept; i++) {
			Hashtable h = (Hashtable)objects.get(i);
			h.clear();
		}
	}
	
	// Obtenemos la posición de la cámara.
	public Location2D getCamera() {
		return gameScreen.getCamera();
	}
	
	// Establecemos la posición de la cámara.
	public void setCamera(Location2D camera, int mode) {
		gameScreen.setCamera(camera, mode);
	}
	
	// Obtenemos los objetos gráficos.
	public Hashtable getObjects(int dept) {
		Hashtable r = null;
		
		if(dept>=0 && dept<maxDept) {
			r = (Hashtable)objects.get(dept);
		} else {
			System.err.println( "Dept \"" + dept + "\" not suported.");
			System.exit(-1);
		}
		return r;
	}

	// Establece la presentación a mostrarse.
	public void setSlide(String image, String text) {
		presentationScreen.setBackground(image);
		presentationScreen.setText(text);
		presentationScreen.repaint();
	}

	// Detiene el juego.
	public void stopGame() {
		gameScreen.stop();
	}
}