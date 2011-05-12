package game;

import ai.AIHandler;
import ai.Message;

import gui.GUIHandler;
import gui.GUIScreens;
import utils.ArrayList;
import utils.Debugger;
import utils.Location2D;
import utils.ResourcesHandler;

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
	
	// Indica si se ha perdido la pantalla.
	private boolean defeated;
	
	// Constructor por defecto.
	public Stage() {
		
	}

	// Inicializa la pantalla.
	public void init() {
		Debugger.debug.print("Stage", "Init", "Starts");
	
		// Pantalla no completada.
		completed = false;
		
		// Pantalla no perdida.
		defeated = false;
		
		// Sin nodos explorados.
		exploredNodes = 0;
		
		// Obtenemos la información de la pantalla.
		ResourcesHandler.getInstance().loadStage(this);
		
		// Creamos el escenario.
		if (scenery == null) {
			System.err.println("The scenery of stage " + id + " was not loaded correctly.");
			System.exit(-1);
		}
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Scenery s = scenery[i][j];
				GUIHandler.getInstance().registerObject(s.getId(), s, s.getGFXType(), s.getDepth());		
			}
		}
		
		// Cargamos la niebla.
		if (visitedArea == null) {
			System.err.println("The fog of stage " + id + " was not loaded correctly.");
			System.exit(-1);
		}
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Scenery s = visitedArea[i][j];
				if(s!=null)
					GUIHandler.getInstance().registerObject(s.getId(), s, s.getGFXType(), s.getDepth());
			}
		}
		
		// Creamos los elementos.
		if (elements == null) {
			System.err.println("The elements of stage " + id + " was not loaded correctly.");
			System.exit(-1);
		}
		
		for (int i = 0; i<elements.size(); i++) {
			Element e = (Element) elements.get(i);
			if(!e.getGFXType().equals("none"))
				GUIHandler.getInstance().registerObject(e.getId(), e, e.getGFXType(), e.getDepth());
			if(!e.getAIType().equals("none"))
				AIHandler.getInstance().registerObject(e.getId(), e.getAIType(), e);
		}
		
		// Creamos el jugador.
		if (player == null) {
			System.err.println("The player of stage " + id + " was not loaded correctly.");
			System.exit(-1);
		}
		
		GUIHandler.getInstance().registerObject(player.getId(), player, player.getGFXType(), player.getDepth());
		AIHandler.getInstance().registerObject(player.getId(), player.getAIType(), player);
		
		// Actualizamos el area visitada.
		updateVisitedArea();

		// Establecemos la posición de la cámara.
		GUIHandler.getInstance().setCamera(new Location2D(startLocation.getX(), startLocation.getY()), 0);
		
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
	
	// Intenta desplazar al jugador.
	public void movePlayer(int direction) {
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
	private boolean isOnMap(Location2D location) {
		return (location.getX() >= 0 && location.getX() < width && location.getY() >= 0 && location.getY() < height);
	}

	// Libera los recursos del juego.
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

	// Actualiza el juego.
	public void update() {
		
		// Activamos los disparadores.
		/*if(!completed && !defeated) {
			for(int i=0; i<triggers.size(); i++) {
				triggers.get(i).check();
			}
		}*/
		
		// Comprobamos si ha muerto el jugador.
		if(player.getLives() < 0) {
			
			// Pantalla perdida.
			defeated = true;
			
			// Mostramos la pantalla de derrota.
			GUIHandler.getInstance().stopGame();
			GUIHandler.getInstance().showScreen(GUIScreens.DEFEAT);
		}
			
		
		// Comprobamos si se ha completado la pantalla.
		if(player.getLocation().getX() == this.getEndLocation().getX() && player.getLocation().getY() == this.getEndLocation().getY()) {
			
			// Pantalla completada.
			completed = true;
			
			// Mostramos la pantalla de victoria.
			GUIHandler.getInstance().stopGame();
			GUIHandler.getInstance().showScreen(GUIScreens.VICTORY);
		}
	}

	// Indica si se puede pasar por la localización.
	public boolean isPassable(Location2D location) {
		boolean passable = true;
		
		if(!isOnMap(location))
			passable = false;
		if(passable == true && !getScenery(location).isPassable())
			passable = false;
		for(int i=0;passable == true && i<elements.size();i++) {
			Element e = (Element) elements.get(i);
			if(e.isPassable() == false)
				passable = false;
		}

		return passable;
	}
	
	/// Getters & Setters
	

	// Obtiene el area visitada.
	public Scenery[][] getVisitedArea() {
		return visitedArea;		
	}
	
	// Establece el area visitada.
	public void setVisitedArea(Scenery[][] visitedArea) {
		this.visitedArea = visitedArea;		
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
	public Scenery[][] getScenery() {
		return scenery;
	}
	
	// Establece el escenario.
	public void setScenery(Scenery[][] scenery) {
		this.scenery = scenery;
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
	
	// Obtiene si se ha perdido la pantalla.
	public boolean isDefeated() {
		return defeated;
	}
	
	// Establece si se ha perdido la pantalla.
	public void setDefeated(boolean defeated) {
		this.defeated = defeated;
	}
}
