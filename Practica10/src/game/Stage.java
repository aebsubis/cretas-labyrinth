package game;

import java.util.Random;

import ai.AIHandler;
import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;
import utils.Location2D;

/**
 * Clase que contiene la información de una pantalla.
 * @author Daniel
 * 
 */
public class Stage {

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
	
	/** Devuelve un número aleatorio
	 **/
	private Random randomizer = new Random();
	
	/**
	 * Devolver un número aleatorio entre min y max
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
		
		// Iniciamos el área visitada.
		visitedArea = new boolean [width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				visitedArea[i][j] = false;
		
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
					GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "wall_rock");
				}
				else {
					
					// Será pared con un 30% de probabilidades.
					if(getRand(0, 100) > 10) {
						// Creamos el objeto lógico.
						scenery[i][j] = new Scenery(objectId,new Location2D(i, j), true);
					
						// Creamos el objeto gráfico.
						GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "floor_sand");
					}
					else {
						// Creamos el objeto lógico.
						scenery[i][j] = new Scenery(objectId, new Location2D(i, j) ,false);
						
						// Creamos el objeto gráfico.
						GUIHandler.getInstance().registerObject(objectId, scenery[i][j], "wall_rock");
					}
				}
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
		GUIHandler.getInstance().registerObject(endLevelId, endLevel, "end_level");
		
		
		// Añadimos los elementos
		// Obtenemos un identificador único para el jugador.
		double playerId = GameHandler.getInstance().getIdentifier();
		
		// Obtenemos la posición inicial del jugador.
		Location2D playerStartLocation = new Location2D(startLocation.getX(), startLocation.getY());
		
		// Creamos el objeto jugador.
		player = new Element(playerId, playerStartLocation, 5, 10, 100, true, false, false, false, false);

		// Creamos el objeto gráfico.
		GUIHandler.getInstance().registerObject(playerId, player, "player_icaro");
		
		// Obtenemos la posición inicial de la cámara.
		Location2D cameraStartLocation = new Location2D(startLocation.getX(), startLocation.getY());
		
		// Establecemos la posición de la cámara.
		GUIHandler.getInstance().setCamera(cameraStartLocation);
		
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
	public boolean movePlayer(int direction) {
		// Indica si se ha podido mover al jugador.
		boolean success = false;
		
		// Obtenemos la posición del jugador.
		Location2D pActLoc = player.getLocation();
		
		// Posición final del jugador.
		Location2D pEndLoc = null;
		
		// Intentamos mover al jugador en la dirección indicada.
		switch(direction) {
		case Direction.UP:
			Location2D pFinLocUp = new Location2D(pActLoc.getX(), pActLoc.getY()-1);
			if(isOnMap(pFinLocUp) && getScenery(pFinLocUp).isPassable())
				pEndLoc = player.move(Direction.UP);
			break;
		case Direction.DOWN:
			Location2D pFinLocDown = new Location2D(pActLoc.getX(), pActLoc.getY()+1);
			if(isOnMap(pFinLocDown) && getScenery(pFinLocDown).isPassable())
				pEndLoc = player.move(Direction.DOWN);
			break;
		case Direction.LEFT:
			Location2D pFinLocDownLeft = new Location2D(pActLoc.getX()-1, pActLoc.getY());
			if(isOnMap(pFinLocDownLeft) && getScenery(pFinLocDownLeft).isPassable())
				pEndLoc = player.move(Direction.LEFT);
			break;
		case Direction.RIGHT:
			Location2D pFinLocDownRight = new Location2D(pActLoc.getX()+1, pActLoc.getY());
			if(isOnMap(pFinLocDownRight) && getScenery(pFinLocDownRight).isPassable())
				pEndLoc = player.move(Direction.RIGHT);
			break;
		}
		
		// Comprobamos si se ha podido mover.
		if(pEndLoc != null) {
			// Se ha podido mover.
			success = true;
			
			// Establecemos el área como visitada.
			for(int i = -2; i<3; i++)
				for(int j=-2; j<3; j++)
					if(player.getLocation().getX()+j>=0 
							&& player.getLocation().getX()+j<width
							&& player.getLocation().getY()+i>=0
							&& player.getLocation().getY()+i<height)
						visitedArea[player.getLocation().getX()+j][player.getLocation().getY()+i] = true;
			
			// Actualizamos la cámara.
			GUIHandler.getInstance().setCamera(pEndLoc);
		}
		
		// Devolvemos el resultado.
		return success;
	}

	// Devuelve true si la coordenada se encuentra dentro de los límites del mapa.
	private boolean isOnMap(Location2D location) {
		return (location.getX() >= 0 && location.getX() < width && location.getY() >= 0 && location.getY() < height);
	}
	
	// Muestra el area visitada por consola.
	public void showVisitedArea() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				if(visitedArea[j][i] == true)
					System.out.print("#");
				else
					System.out.print("·");
			}
			System.out.println("");
		}
		System.out.println("");System.out.println("");System.out.println("");
	}

	public void free() {
		// Elimina la información de la pantalla del motor gráfico.
		GUIHandler.getInstance().deleteAllObjects();
		
		// Elimina la información de la pantalla del motor de IA.
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
