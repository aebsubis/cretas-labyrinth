package game;

import ai.AIHandler;
import ai.AIObject;
import ai.Message;

import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;
import utils.Location2D;
import utils.Randomizer;

/**
 * Clase que contiene la información de una pantalla.
 * @author Daniel
 * 
 */
public class Stage {

	// Modo de movimiento de la cámara.
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
	private Scenery[][] visitedArea;
	
	// Cantidad de zonas visitadas.
	int exploredNodes;
	
	// Coordenada de inicio.
	private Location2D startLocation;
	
	// Coordenada de fin.
	private Location2D endLocation;
	
	// Indica si se ha completado la pantalla.
	private boolean completed;
	
	// Constructor por defecto.
	public Stage() {
		
	}

	// Inicializa la pantalla.
	public void init() {
		Debugger.debug.print("Stage", "Init", "Starts");
	
		// Pantalla no completada.
		completed = false;
		
		// Sin nodos explorados.
		exploredNodes = 0;
		
		// Datos de la pantalla (se debe hacer desde el gestor de recursos)
		width = 10;
		height = 10;
	
		// Nombre de la pantalla.
		name = "Nombre de prueba";
		
		// Start location
		startLocation = new Location2D(1, 1);

		// End location
		endLocation = new Location2D(width-2, height-2);
		
		// Inicializamos el escenario.
		scenery = new Scenery[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Obtenemos un identificador único para el nuevo objeto.
				double objectId = GameHandler.getInstance().getIdentifier();
				
				if( i == 0 || j == 0 || i == (width-1) || j == (height-1)) {
					// Creamos el objeto lógico.
					scenery[i][j] = new Scenery(objectId, new Location2D(i, j) ,false);
					
					// Creamos el objeto gráfico.
					GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "wall_rock", 1);
				}
				else {
					
					// Será pared con un 10% de probabilidades.
					if(Randomizer.getInstance().getRand(0, 100) > 30) {
						// Creamos el objeto lógico.
						scenery[i][j] = new Scenery(objectId,new Location2D(i, j), true);
					
						// Creamos el objeto gráfico.
						GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "floor_sand", 1);
					}
					else {
						// Creamos el objeto lógico.
						scenery[i][j] = new Scenery(objectId, new Location2D(i, j) ,false);
						
						// Creamos el objeto gráfico.
						GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "wall_rock", 1);
					}
				}
			}
			
			elements = new ArrayList();
		}
		
		// Añadimos la niebla.
		visitedArea = new Scenery [width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Obtenemos un identificador único para el nuevo objeto.
				double objectId = GameHandler.getInstance().getIdentifier();

				// Creamos el objeto lógico.
				visitedArea[i][j] = new Scenery(objectId, new Location2D(i, j) , true);
				
				// Creamos el objeto gráfico.
				GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "fog", 4);
			}
		}
		
		// Añadimos los disparadores.
		// Obtenemos un identificador para el final de nivel.
		double endLevelId = GameHandler.getInstance().getIdentifier();
		
		// Obtenemos la posición inicial del final de nivel.
		Location2D endLevelStartLocation = new Location2D(endLocation.getX(), endLocation.getY());
		
		// Creamos el objeto fin de nivel.
		Trigger endLevel = new Trigger(endLevelId, endLevelStartLocation);
		
		// Creamos el objeto gráfico fin de nivel.
		GUIHandler.getInstance().registerObject(endLevelId, endLevel, "end_level", 2);
		
		
		// Añadimos los elementos
		// Añadimos algunos enemigos
		int numEmemigos = 3;
		for(int i=0; i<numEmemigos;i++) {
			double enemyId = GameHandler.getInstance().getIdentifier();
			
			// Obtenemos la posición inicial del jugador.
			Location2D enemyStartLocation = new Location2D(endLocation.getX(), endLocation.getY());
			
			// Creamos el objeto jugador.
			Element enemy = new Element();
			enemy = new Element(enemyId, enemyStartLocation, 5, 2, 100, true, false, false, false, false);
			// Creamos el objeto gráfico.
			GUIHandler.getInstance().registerObject(enemyId, enemy, "enemy_minotaur_stand_front", 2);
			// Creamos el objeto IA.
			AIHandler.getInstance().registerObject(AIObject.MINOTAUR, enemyId, enemy);
		}
		
		// Obtenemos un identificador único para el jugador.
		double playerId = GameHandler.getInstance().getIdentifier();
		
		// Obtenemos la posición inicial del jugador.
		Location2D playerStartLocation = new Location2D(startLocation.getX(), startLocation.getY());
		
		// Creamos el objeto jugador.
		player = new Element(playerId, playerStartLocation, 5, 2, 100, true, false, false, false, false);
		// Creamos el objeto gráfico.
		GUIHandler.getInstance().registerObject(playerId, player, "player_icaro_stand_front", 2);
		// Creamos el objeto IA.
		AIHandler.getInstance().registerObject(AIObject.PLAYER, playerId, player);
		
		// Actualizamos el area visitada.
		updateVisitedArea();
		
		// Obtenemos la posición inicial de la cámara.
		Location2D cameraStartLocation = new Location2D(startLocation.getX(), startLocation.getY());
		
		// Establecemos la posición de la cámara.
		GUIHandler.getInstance().setCamera(cameraStartLocation,0);
		
		// Muestra la pantalla de juego.
		GUIHandler.getInstance().showScreen(GUIScreens.GAME);
		
		Debugger.debug.print("Stage", "Init", "Ends");
	}
	
	// Actualiza el área visitada.
	private void updateVisitedArea() {
		// Establecemos el área como visitada.
		for(int i = -2; i<3; i++) {
			for(int j=-2; j<3; j++) {
				if(player.getLocation().getX()+j>=0 
						&& player.getLocation().getX()+j<width
						&& player.getLocation().getY()+i>=0
						&& player.getLocation().getY()+i<height) {
					if(visitedArea[player.getLocation().getX()+j][player.getLocation().getY()+i] != null) {
						GUIHandler.getInstance().deleteObject(visitedArea[player.getLocation().getX()+j][player.getLocation().getY()+i].getId());
						visitedArea[player.getLocation().getX()+j][player.getLocation().getY()+i] = null;
						exploredNodes++;
					}
				}
			}
		}
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
	
	// Obtiene el objeto scenery de la localización.
	public Scenery getScenery(Location2D location) {
		return scenery[location.getX()][location.getY()];
	}
	
	// Establece el objeto scenery de la localización.
	public void setScenery(Location2D location, Scenery scn) {
		scenery[location.getX()][location.getY()] = scn;
	}
	
	// Obtiene la localización de inicio.
	public Location2D getStartLocation() {
		return startLocation;
	}
	
	// Establece la localización de inicio.
	public void setStartLocation(Location2D startLocation) {
		this.startLocation = startLocation;
	}
	
	// Obtiene la localización de fin.
	public Location2D getEndLocation() {
		return endLocation;
	}
	
	// Establece la localización de fin.
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
		
		// Intentamos mover al jugador en la dirección indicada.
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
			
		// Actualizamos el área visitada.
		updateVisitedArea();
					
		// Actualizamos la cámara.
		GUIHandler.getInstance().setCamera(player.getLocation(), cameraMovementMode);
	}
	
	// Mueve la cámara.
	public void moveCamera(int direction) {
		switch(direction) {
		case Direction.UP:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX(),GUIHandler.getInstance().getCamera().getY()-1), cameraMovementMode);
			break;
		case Direction.DOWN:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX(),GUIHandler.getInstance().getCamera().getY()+1), cameraMovementMode);
			break;
		case Direction.LEFT:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX()-1,GUIHandler.getInstance().getCamera().getY()), cameraMovementMode);
			break;
		case Direction.RIGHT:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX()+1, GUIHandler.getInstance().getCamera().getY()), cameraMovementMode);
			break;
		case Direction.UPLEFT:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX()-1, GUIHandler.getInstance().getCamera().getY()-1), cameraMovementMode);
			break;
		case Direction.UPRIGHT:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX()+1, GUIHandler.getInstance().getCamera().getY()-1), cameraMovementMode);
			break;
		case Direction.DOWNLEFT:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX()-1, GUIHandler.getInstance().getCamera().getY()+1), cameraMovementMode);
			break;
		case Direction.DOWNRIGHT:
			GUIHandler.getInstance().setCamera(new Location2D(GUIHandler.getInstance().getCamera().getX()+1, GUIHandler.getInstance().getCamera().getY()+1), cameraMovementMode);
			break;
		case Direction.CENTER:
			GUIHandler.getInstance().setCamera(new Location2D(player.getLocation().getX(), player.getLocation().getY()), cameraMovementMode);
			break;
		}
	}
	
	// Devuelve true si la coordenada se encuentra dentro de los límites del mapa.
	public boolean isOnMap(Location2D location) {
		return (location.getX() >= 0 && location.getX() < width && location.getY() >= 0 && location.getY() < height);
	}

	public void free() {
		// Elimina la información de la pantalla del motor gráfico.
		GUIHandler.getInstance().deleteAllObjects();
		
		// Elimina la información de la pantalla del motor de IA.
		AIHandler.getInstance().deleteAllObjects();
		
		// Desreferenciamos todos los elementos.
		/*this.elements.clear();
		this.endLocation = null;
		this.height = 0;
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
