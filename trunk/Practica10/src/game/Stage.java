package game;

import java.util.Random;

import ai.AIHandler;
import ai.AIObject;
import ai.Message;

import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;
import utils.Location2D;

/**
 * Clase que contiene la informaci�n de una pantalla.
 * @author Daniel
 * 
 */
public class Stage {

	// Modo de movimiento de la c�mara.
	// 0 instantaneo
	// 1 suavizado
	private static final int cameraMovementMode = 1;
	
	// Identificador de la pantalla.
	private String id;
	
	// Ancho.
	private int width;

	// Alto
	private int height;
	
	// Nombre.
	private String name;

	// Elemento jugador.
	private Element player;
	
	// Elementos de la pantalla.
	private ArrayList elements;

	// Matriz del mapa.
	private Scenery[][] scenery;
	
	// Matriz de zonas visitadas.
	private boolean[][] visitedArea;
	
	// Coordenada de inicio.
	private Location2D startLocation;
	
	// Coordenada de fin.
	private Location2D endLocation;
	
	// Indica si se ha completado la pantalla.
	private boolean completed;
	
	/** Devuelve un n�mero aleatorio
	 **/
	private Random randomizer = new Random();
	
	/**
	 * Devolver un n�mero aleatorio entre min y max
	 */
	private int getRand(int min, int max) {
		int r = Math.abs(randomizer.nextInt());
		return (r % (max - min)) + min;
	}
	
	// Constructor por defecto.
	public Stage() {
		
	}

	// Inicializa la pantalla.
	public void init() {
		Debugger.debug.print("Stage", "Init", "Starts");
		
		// Pantalla no completada.
		completed = false;
		
		// Datos de la pantalla (se debe hacer desde el gestor de recursos)
		width = 10;
		height = 10;
		
		// Start location
		startLocation = new Location2D(1, 1);

		// End location
		endLocation = new Location2D(width-2, height-2);
		
		// Iniciamos el �rea visitada.
		visitedArea = new boolean [width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				visitedArea[i][j] = false;
		
		// Inicializamos el escenario.
		scenery = new Scenery[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Obtenemos un identificador �nico para el nuevo objeto.
				double objectId = GameHandler.getInstance().getIdentifier();
				
				if( i == 0 || j == 0 || i == (width-1) || j == (height-1)) {
					// Creamos el objeto l�gico.
					scenery[i][j] = new Scenery(objectId, new Location2D(i, j) ,false);
					
					// Creamos el objeto gr�fico.
					GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "wall_rock", 1);
				}
				else {
					
					// Ser� pared con un 30% de probabilidades.
					if(getRand(0, 100) > 10) {
						// Creamos el objeto l�gico.
						scenery[i][j] = new Scenery(objectId,new Location2D(i, j), true);
					
						// Creamos el objeto gr�fico.
						GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "floor_sand", 1);
					}
					else {
						// Creamos el objeto l�gico.
						scenery[i][j] = new Scenery(objectId, new Location2D(i, j) ,false);
						
						// Creamos el objeto gr�fico.
						GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "wall_rock", 1);
					}
				}
			}
		}
		
		// A�adimos los disparadores.
		// Obtenemos un identificador para el final de nivel.
		double endLevelId = GameHandler.getInstance().getIdentifier();
		
		// Obtenemos la posici�n inicial del final de nivel.
		Location2D endLevelStartLocation = new Location2D(endLocation.getX(), endLocation.getY());
		
		// Creamos el objeto fin de nivel.
		Trigger endLevel = new Trigger(endLevelId, endLevelStartLocation);
		
		// Creamos el objeto gr�fico fin de nivel.
		GUIHandler.getInstance().registerObject(endLevelId, endLevel, "end_level", 2);
		
		
		// A�adimos los elementos
		// Obtenemos un identificador �nico para el jugador.
		double playerId = GameHandler.getInstance().getIdentifier();
		
		// Obtenemos la posici�n inicial del jugador.
		Location2D playerStartLocation = new Location2D(startLocation.getX(), startLocation.getY());
		
		// Creamos el objeto jugador.
		player = new Element(playerId, playerStartLocation, 5, 2, 100, true, false, false, false, false);
		// Creamos el objeto gr�fico.
		GUIHandler.getInstance().registerObject(playerId, player, "player_icaro_stand_front", 2);
		// Creamos el objeto IA.
		AIHandler.getInstance().registerObject(AIObject.PLAYER, playerId, player);
		System.out.println("PlayerID: " + playerId);
		
		// Obtenemos la posici�n inicial de la c�mara.
		Location2D cameraStartLocation = new Location2D(startLocation.getX(), startLocation.getY());
		
		// Establecemos la posici�n de la c�mara.
		GUIHandler.getInstance().setCamera(cameraStartLocation,0);
		
		// Muestra la pantalla de juego.
		GUIHandler.getInstance().showScreen(GUIScreens.GAME);
		
		Debugger.debug.print("Stage", "Init", "Ends");
	}
	
	// Obtiene el identificador.
	public String getId() {
		return id;
	}
	
	// Establece el identificador.
	public void setId(String id) {
		this.id = id;
	}
	
	// Obtiene el ancho de la pantalla.
	public int getWidth() {
		return width;
	}
	
	// Establece el ancho de la pantalla.
	public void setWidth(int width) {
		this.width = width;
	}
	
	// Obtiene el alto de la pantalla.
	public int getHeight() {
		return height;
	}
	
	// Establece el alto de la pantalla.
	public void setHeight(int height) {
		this.height = height;
	}
	
	// Obtiene el nombre de la pantalla.
	public String getName() {
		return name;
	}
	
	// Establece el nombre de la pantalla.
	public void setName(String name) {
		this.name = name;
	}
	
	// Obtiene el elemento jugador.
	public Element getPlayer() {
		return player;
	}
	
	// Establece el elemento jugador.
	public void setPlayer(Element player) {
		this.player = player;
	}
	
	// Obtiene los elementos de la pantalla.
	public ArrayList getElements() {
		return elements;
	}
	
	// Establece los elementos de la pantalla.
	public void setElements(ArrayList elements) {
		this.elements = elements;
	}
	
	// Obtiene el escenario.
	public Scenery[][] getScenary() {
		return scenery;
	}
	
	// Establece el escenario.
	public void setScenary(Scenery[][] scenary) {
		this.scenery = scenary;
	}
	
	// Obtiene el objeto scenery de la localizaci�n.
	public Scenery getScenery(Location2D location) {
		return scenery[location.getX()][location.getY()];
	}
	
	// Establece el objeto scenery de la localizaci�n.
	public void setScenery(Location2D location, Scenery scn) {
		scenery[location.getX()][location.getY()] = scn;
	}
	
	// Obtiene la localizaci�n de inicio.
	public Location2D getStartLocation() {
		return startLocation;
	}
	
	// Establece la localizaci�n de inicio.
	public void setStartLocation(Location2D startLocation) {
		this.startLocation = startLocation;
	}
	
	// Obtiene la localizaci�n de fin.
	public Location2D getEndLocation() {
		return endLocation;
	}
	
	// Establece la localizaci�n de fin.
	public void setEndLocation(Location2D endLocation) {
		this.endLocation = endLocation;
	}
	
	// Obtiene si se ha completado la pantalla.
	public boolean isCompleted() {
		return completed;
	}
	
	// Establece si se ha completado la pantalla.
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	// Desplaza al jugador si es posible.
	public void movePlayer(int direction) {
		
		// Intentamos mover al jugador en la direcci�n indicada.
		switch(direction) {
		case Direction.UP:
			AIHandler.getInstance().sendMessage(0, -1, player.getId(), Message.moveUp);
			break;
		case Direction.DOWN:
			AIHandler.getInstance().sendMessage(0, -1, player.getId(), Message.moveDown);
			break;
		case Direction.LEFT:
			AIHandler.getInstance().sendMessage(0, -1, player.getId(), Message.moveLeft);
			break;
		case Direction.RIGHT:
			AIHandler.getInstance().sendMessage(0, -1, player.getId(), Message.moveRight);
			break;
		}
			
		// Establecemos el �rea como visitada.
		for(int i = -2; i<3; i++)
			for(int j=-2; j<3; j++)
				if(player.getLocation().getX()+j>=0 
						&& player.getLocation().getX()+j<width
						&& player.getLocation().getY()+i>=0
						&& player.getLocation().getY()+i<height)
					visitedArea[player.getLocation().getX()+j][player.getLocation().getY()+i] = true;
			
		// Actualizamos la c�mara.
		GUIHandler.getInstance().setCamera(player.getLocation(), cameraMovementMode);
	}
	
	// Devuelve true si la coordenada se encuentra dentro de los l�mites del mapa.
	public boolean isOnMap(Location2D location) {
		return (location.getX() >= 0 && location.getX() < width && location.getY() >= 0 && location.getY() < height);
	}
	
	// Muestra el area visitada por consola.
	public void showVisitedArea() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				if(visitedArea[j][i] == true)
					System.out.print("#");
				else
					System.out.print("�");
			}
			System.out.println("");
		}
		System.out.println("");System.out.println("");System.out.println("");
	}

	public void free() {
		// Elimina la informaci�n de la pantalla del motor gr�fico.
		GUIHandler.getInstance().deleteAllObjects();
		
		// Elimina la informaci�n de la pantalla del motor de IA.
		//AIHandler.getInstance().deleteAllObjects();
		
		// Desreferenciamos todos los elementos.
		/*this.completed = false;
		this.elements.clear();
		this.endLocation = null;
		this.height = 0;
		this.id = "";
		this.name = "";
		this.player = null;
		this.randomizer = null;
		this.scenery = null;
		this.startLocation = null;
		this.visitedArea = null;
		this.width = 0;
		
		// Invocar al recolector de basura.
		System.gc();*/
	}

	public void update() {
		// Comprobamos si se ha completado la pantalla.
		if(player.getLocation().getX() == width-2 && player.getLocation().getY() == height-2)
			completed = true;
	}	
}
