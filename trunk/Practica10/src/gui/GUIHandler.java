package gui;

import java.util.Hashtable;

import game.GameHandler;
import game.GameObject;
import game.Phase;
import Practica10;
import javax.microedition.lcdui.Display;

import utils.ArrayList;
import utils.Debugger;
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
	
	// Manejador de la interfaz gráfica.
	private static GUIHandler guiHandler;	
	
	// Instancia del midlet
	private Practica10 theMidlet;
	
	// GUIObjects
	private Hashtable objects;
	// CameraLocation
	
	// Pantallas
	private GUIMainMenu mainmenuScreen;
	private GUILoadGame loadgameScreen;
	private GUISaveGame savegameScreen;
	private GUIGame gameScreen;
	private GUIGameMenu gamemenuScreen;
	private GUIPresentation presentationScreen;
	private GUIMap mapScreen;
	private GUIStart startScreen;
	private GUIScores scoresScreen;
	private GUISettings settingsScreen;
	private GUIInventory inventoryScreen;
	
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
		case GUIScreens.GAMEMENU:
			//Display.getDisplay(theMidlet).setCurrent(gamemenuScreen);
			break;
		case GUIScreens.PRESENTATION:
			Display.getDisplay(theMidlet).setCurrent(presentationScreen);
			break;
		case GUIScreens.MAP:
			//Display.getDisplay(theMidlet).setCurrent(mapeScreen);
			break;
		case GUIScreens.STARTSCREEN:
			Display.getDisplay(theMidlet).setCurrent(startScreen);
			break;
		case GUIScreens.SCORES:
			// Display.getDisplay(theMidlet).setCurrent(scoreScreen);
			break;
		case GUIScreens.SETTINGS:
			// Display.getDisplay(theMidlet).setCurrent(settingsScreen);
			break;
		case GUIScreens.INVENTORY:
			// Display.getDisplay(theMidlet).setCurrent(inventoryScreen);
			break;
		}
	}
	
	public void init(Practica10 practica9) {
		// Guardamos la referencia al midlet.
		theMidlet = practica9;
		
		// Inicializamos el array de objetos.
		objects = new Hashtable();
		
		// Inicializamos las pantallas.
		mainmenuScreen = new GUIMainMenu();
		loadgameScreen = new GUILoadGame();
		savegameScreen = new GUISaveGame();
		gameScreen = new GUIGame();
		gamemenuScreen = new GUIGameMenu();
		presentationScreen = new GUIPresentation();
		mapScreen = new GUIMap();
		startScreen = new GUIStart();
		scoresScreen = new GUIScores();
		settingsScreen = new GUISettings();
		inventoryScreen	= new GUIInventory();
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
	public void continueGame() {
		// Cargamos los datos del juego.
		//GameHandler.getInstance().continue();
	}

	// Redibuja la pantalla del juego.
	public void renderGame() {
		gameScreen.repaint();
	}

	// Registra un objeto gráfico.
	public void registerObject(double id, GameObject object, String typeId) {
		
		// Creamos el objeto.
		GUIObject o = new GUIObject(id, object, typeId);

		// Lo añadimos a la lista de objetos.
		objects.put(new Double(id), o);

	}
	
	// Borra el objeto con el identificador indicado.
	public void deleteObject(double objectId) {
		GUIObject o = (GUIObject) objects.get(new Double(objectId));
		
		if(o==null) {
			System.err.println( "GUIObject \"" + objectId + "\" not found.");
			//System.exit(-1);
		}
		
		objects.remove(new Double(objectId));
	}
	
	// Borra el objeto con el identificador indicado.
	public GUIObject getObject(double objectId) {
		GUIObject o = (GUIObject) objects.get(new Double(objectId));
		
		if(o==null) {
			System.err.println( "GUIObject \"" + objectId + "\" not found.");
			System.exit(-1);
		}
		
		return o;
	}
	
	// Eliminas todos los objetos del manejador gráfico.
	public void deleteAllObjects() {
		objects.clear();
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
	public Hashtable getObjects() {
		return this.objects;
	}

	// Establece la presentación a mostrarse.
	public void setSlide(String image, String text) {
		presentationScreen.setBackground(image);
		presentationScreen.setText(text);
		presentationScreen.repaint();
	}

	public void stopGame() {
		gameScreen.stop();
	}
}