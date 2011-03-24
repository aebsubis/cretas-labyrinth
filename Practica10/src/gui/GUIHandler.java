package gui;

import game.GameHandler;
import game.GameObject;
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

	// Milisegundos por fotograma (40mspf = 15fps)
	public static final int MS_PER_FRAME = 40;
	
	// Manejador de la interfaz gráfica.
	private static GUIHandler guiHandler;	
	
	// Instancia del midlet
	private Practica10 theMidlet;
	
	// GUIObjects
	private ArrayList objects;
	
	// CameraLocation
	private Location2D camera;
	
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
		objects = new ArrayList();
		
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
		objects.add(o);

	}
	
	// Borra el objeto con el identificador indicado.
	public void deleteObject(double objectId) {
		boolean found = false;
		for (int i = 0; i < objects.size() && found == false; i++) {
			if (((GUIObject) objects.get(i)).getId() == objectId) {
				found = true;
				objects.remove(i);
			}
		}
	}
	
	// Eliminas todos los objetos del manejador gráfico.
	public void deleteAllObjects() {
		objects.clear();
	}
	
	// Obtenemos la posición de la cámara.
	public Location2D getCamera() {
		return this.camera;
	}
	
	// Establecemos la posición de la cámara.
	public void setCamera(Location2D camera) {
		this.camera = camera;
	}
	
	// Obtenemos los objetos gráficos.
	public ArrayList getObjects() {
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
