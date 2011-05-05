package utils;

import game.GameHandler;
import game.Phase;
import game.Scenery;
import game.Stage;
import gui.GUIHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.microedition.lcdui.Image;

/**
 * El Manejador de recursos es el encargado de gestionar todos los recursos externos
 * del juego como las imágenes y textos.
 * @author Daniel
 *
 */
public class ResourcesHandler{

	// Lenguajes.
	static final int CASTELLANO = 0;
	static final int ENGLISH = 1;
	
	// Variable de la clase.
	private static  ResourcesHandler resourceshandler;
	
	// Indica si los recursos han sido inicializados.
	private boolean initalizated = false;
	
	// Indica el lenguaje seleccionado.
	private int languaje;
	
	// Textos.
	private Hashtable textos;
	private Hashtable texts;
	
	// Imágenes estáticas.
	private Hashtable images;
	
	// Animaciones
	private Hashtable animations;
	
	// Sonidos.
	private Hashtable sounds;
	
	// Fases
	private Hashtable phases;
	
	// Constructor privado.
	private ResourcesHandler() {
		
	}
	
	// Devuelve la única instancia de la clase.
	public static synchronized ResourcesHandler getInstance() {
		if(resourceshandler == null) {
			resourceshandler = new ResourcesHandler();
		}
		return resourceshandler;	
	}
	
	// Carga todos los recursos.
	public boolean init() {
		
		// Indica si se ha podido inicializar.
		boolean result = false;
		
		// Comprobamos que no haya sido inicializado todavía.
		if (initalizated == false) {
			
			// Se ha podido inicializar.
			result = true;
			
			// Establecemos el manejador como inicializado.
			initalizated = true;
			
			// Lenguaje por defecto.
			languaje = CASTELLANO;
			
			// Textos
			textos = new Hashtable();
			texts = new Hashtable();
			
			textos.put("start", "Empezar");
			texts.put("start", "Start");
			
			textos.put("exit", "Salir");
			texts.put("exit", "Exit");
			
			textos.put("select", "Seleccionar");
			texts.put("select", "select");
			
			textos.put("back", "Volver");
			texts.put("back", "Back");
			
			textos.put("next", "Siguiente");
			texts.put("next", "Next");
			
			textos.put("skip", "Saltar");
			texts.put("skip", "Skip");
			
			textos.put("map", "Mapa");
			texts.put("map", "Map");
			
			textos.put("inventory", "Inventario");
			texts.put("inventory", "Inventory");
			
			textos.put("save", "Guardar");
			texts.put("save", "Save");
			
			textos.put("select_option", "Selecciona una opción");
			texts.put("select_option", "Select an option");
			
			textos.put("game_title", "El laberinto de Creta");
			texts.put("game_title", "Creta's labyrinth");
			
			textos.put("slide_1", "Diapositiva 1");
			texts.put("slide_1", "Slide 1");
			textos.put("slide_2", "Diapositiva 2");
			texts.put("slide_2", "Slide 2");
			
			// Imágenes estáticas.
			images = new Hashtable();
			
			try {
				// Interfaz gráfica.
		    	images.put("start_splash", Image.createImage("/interface/start_splash.png"));
		    	images.put("menu_splash", Image.createImage("/interface/menu_splash.png"));
		    	images.put("menu_newgame",Image.createImage("/interface/newgame.png"));
		    	images.put("menu_newgame_focus",Image.createImage("/interface/newgameFocus.png"));
		    	images.put("menu_continue",Image.createImage("/interface/continue.png"));
		    	images.put("menu_continue_focus",Image.createImage("/interface/continueFocus.png"));
		    	images.put("menu_scores", Image.createImage("/interface/scores.png"));
		    	images.put("menu_scores_focus", Image.createImage("/interface/scoresFocus.png"));
		    	images.put("menu_settings", Image.createImage("/interface/settings.png"));
		    	images.put("menu_settings_focus", Image.createImage("/interface/settingsFocus.png"));

		    	// Elementos
		    	images.put("end_level0",Image.createImage("/element/end0.png"));
		    	images.put("end_level1",Image.createImage("/element/end1.png"));
		    	images.put("end_level2",Image.createImage("/element/end2.png"));
		    	images.put("end_level3",Image.createImage("/element/end3.png"));
		    	
		    	/// Personajes
		    	images.put("player_icaro_front1", Image.createImage("/element/player_icaro_front1.png"));	
		    	images.put("player_icaro_front2", Image.createImage("/element/player_icaro_front2.png"));
		    	images.put("player_icaro_front3", Image.createImage("/element/player_icaro_front3.png"));
		    	images.put("player_icaro_back1", Image.createImage("/element/player_icaro_back1.png"));	
		    	images.put("player_icaro_back2", Image.createImage("/element/player_icaro_back2.png"));
		    	images.put("player_icaro_back3", Image.createImage("/element/player_icaro_back3.png"));
		    	images.put("player_icaro_right1", Image.createImage("/element/player_icaro_right1.png"));	
		    	images.put("player_icaro_right2", Image.createImage("/element/player_icaro_right2.png"));
		    	images.put("player_icaro_right3", Image.createImage("/element/player_icaro_right3.png"));
		    	images.put("player_icaro_left1", Image.createImage("/element/player_icaro_left1.png"));	
		    	images.put("player_icaro_left2", Image.createImage("/element/player_icaro_left2.png"));
		    	images.put("player_icaro_left3", Image.createImage("/element/player_icaro_left3.png"));
		    	
		    	//images.put("player_dedalos", Image.createImage("/element/player_dedadlos.png"));
		    	//images.put("player_teseo", Image.createImage("/element/player_teseo.png"));
		    	//images.put("player_ariadna", Image.createImage("/element/player_ariadna.png"));
		    	
		    	// Enemigos
		    	//images.put("enemy_bug", Image.createImage("/element/enemy_bug.png"));
		    	//images.put("enemy_spider", Image.createImage("/element/enemy_spider.png"));
		    	//images.put("enemy_snake", Image.createImage("/element/enemy_snake.png"));
		    	//images.put("enemy_minotaur", Image.createImage("/element/enemy_minotaur.png"));
			
				// Miscelania
				
				// Objetos del mapa
		    	//images.put("map_box", Image.createImage("/element/map_box.png"));
		    	//images.put("map_key", Image.createImage("/element/map_key.png"));
		    	//images.put("map_door", Image.createImage("/element/map_door.png"));
		    	//images.put("map_button", Image.createImage("/element/map_button.png"));
		    	//images.put("map_wood", Image.createImage("/element/map_wood.png"));
		    	//images.put("map_feather", Image.createImage("/element/map_feather.png"));
		    	//images.put("map_thread", Image.createImage("/element/map_thread.png"));
		    	//images.put("map_wax", Image.createImage("/element/map_wax.png"));
				
				// Escenario
				/// Suelos
		    	images.put("floor_sand", Image.createImage("/scenery/floor_sand.png"));
		    	
		    	//images.put("floor_grass", Image.createImage("/scenery/floor_grass.png"));
		    	//images.put("floor_rock", Image.createImage("/scenery/floor_rock.png"));
		    	//images.put("floor_tiles", Image.createImage("/scenery/floor_tiles.png"));
		    	//images.put("floor_bridge", Image.createImage("/scenery/floor_bridge.png"));
				
				// Paredes
		    	//images.put("wall_metal", Image.createImage("/scenery/wall_metal.png"));
		    	//images.put("wall_grass", Image.createImage("/scenery/wall_grass.png"));
		    	images.put("wall_rock", Image.createImage("/scenery/wall_rock.png"));
				
				// Naturaleza
		    	//images.put("nature_tree", Image.createImage("/scenery/nature_tree.png"));
		    	//images.put("nature_water", Image.createImage("/scenery/nature_water.png"));
		    	//images.put("nature_waterfall", Image.createImage("/scenery/nature_waterfall.png"));
		    	
		    	// Presentaciones
		    	images.put("slide_1", Image.createImage("/phases/icaro_dedalos/presentation/slide1.png"));
		    	images.put("slide_2", Image.createImage("/phases/icaro_dedalos/presentation/slide2.png"));
		    	
			}
		    catch (Exception ioe) {
		    	System.err.println("Images couldn't be loaded.");
		    }
		    
		    // Animaciones
		    animations = new Hashtable();
		    ArrayList f;
		    
	    	// Elementos
	    	f = new ArrayList();
	    	f.add("end_level0");
	    	f.add("end_level1");
	    	f.add("end_level2");
	    	f.add("end_level3");
	    	animations.put("end_level", f);
	    	
	    	/// Personajes
	    	f = new ArrayList();
	    	f.add("player_icaro_front1");
	    	animations.put("player_icaro_stand_front", f);
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_back1");
	    	animations.put("player_icaro_stand_back", f);
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_right1");
	    	animations.put("player_icaro_stand_right", f);
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_left1");
	    	animations.put("player_icaro_stand_left", f);
	    	
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_front1");
	    	f.add("player_icaro_front2");
	    	f.add("player_icaro_front1");
	    	f.add("player_icaro_front3");
	    	animations.put("player_icaro_walk_front", f);
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_back1");
	    	f.add("player_icaro_back2");
	    	f.add("player_icaro_back1");
	    	f.add("player_icaro_back3");
	    	animations.put("player_icaro_walk_back", f);
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_right1");
	    	f.add("player_icaro_right2");
	    	f.add("player_icaro_right1");
	    	f.add("player_icaro_right3");
	    	animations.put("player_icaro_walk_right", f);
	    	
	    	f = new ArrayList();
	    	f.add("player_icaro_left1");
	    	f.add("player_icaro_left2");
	    	f.add("player_icaro_left1");
	    	f.add("player_icaro_left3");
	    	animations.put("player_icaro_walk_left", f);
	    	
	    	// f = new ArrayList();
	    	// f.add("player_dedalos");
	    	// animations.put("player_dedalos", f);
	    	
	    	// f = new ArrayList();
	    	// f.add("player_teseo");
	    	// animations.put("player_teseo", f);
	    	
	    	// f = new ArrayList();
	    	// f.add("player_ariadna");
	    	// animations.put("player_ariadna", f);
	
	    	// Enemigos
	    	//images.put("enemy_bug", Image.createImage("/element/enemy_bug.png"));
	    	//images.put("enemy_spider", Image.createImage("/element/enemy_spider.png"));
	    	//images.put("enemy_snake", Image.createImage("/element/enemy_snake.png"));
	    	//images.put("enemy_minotaur", Image.createImage("/element/enemy_minotaur.png"));
		
			// Miscelania
			
			// Objetos del mapa
	    	//images.put("map_box", Image.createImage("/element/map_box.png"));
	    	//images.put("map_key", Image.createImage("/element/map_key.png"));
	    	//images.put("map_door", Image.createImage("/element/map_door.png"));
	    	//images.put("map_button", Image.createImage("/element/map_button.png"));
	    	//images.put("map_wood", Image.createImage("/element/map_wood.png"));
	    	//images.put("map_feather", Image.createImage("/element/map_feather.png"));
	    	//images.put("map_thread", Image.createImage("/element/map_thread.png"));
	    	//images.put("map_wax", Image.createImage("/element/map_wax.png"));
			
			// Escenario
			/// Suelos
	    	f = new ArrayList();
	    	f.add("floor_sand");
	    	animations.put("floor_sand", f);
	    	
	    	//images.put("floor_grass", Image.createImage("/scenery/floor_grass.png"));
	    	//images.put("floor_rock", Image.createImage("/scenery/floor_rock.png"));
	    	//images.put("floor_tiles", Image.createImage("/scenery/floor_tiles.png"));
	    	//images.put("floor_bridge", Image.createImage("/scenery/floor_bridge.png"));
			
			// Paredes
	    	//images.put("wall_metal", Image.createImage("/scenery/wall_metal.png"));
	    	//images.put("wall_grass", Image.createImage("/scenery/wall_grass.png"));
	    	f = new ArrayList();
	    	f.add("wall_rock");
	    	animations.put("wall_rock", f);
	    	
			// Naturaleza
	    	//images.put("nature_tree", Image.createImage("/scenery/nature_tree.png"));
	    	//images.put("nature_water", Image.createImage("/scenery/nature_water.png"));
	    	//images.put("nature_waterfall", Image.createImage("/scenery/nature_waterfall.png"));
		    	
		    
			// Sonidos
		    sounds = new Hashtable();
		    
		    
		    // Fases
		    phases = new Hashtable();
		    
			Phase p1 = new Phase();
			p1.setId("icaro_dedalos");
			phases.put("icaro_dedalos", p1);
			
			Phase p2 = new Phase();
			p2.setId("teseo");
			phases.put("teseo", p2);
		}
		
		// Devolvemos el resultado.
		return result;
	}
	
	// Obtiene un texto.
	public String getText(String id) {
		String t = null;
		switch(languaje) {
		case CASTELLANO:
			t = (String) textos.get(id);
			break;
		case ENGLISH:
			t = (String) texts.get(id);
			break;
		}
		
		if(t==null) {
			System.err.println( "Text \"" + id + "\" not found.");
			System.exit(-1);
		}
		
		return t;
	}
	
	// Obtiene una imagen.
	public Image getImage(String id) {
		Image i = (Image) images.get(id);
		
		if(i==null) {
			System.err.println( "Image \"" + id + "\" not found.");
			System.exit(-1);
		}
		
		return i;
	}
	
	// Obtiene los identificadores de una animación.
	public ArrayList getFrames(String id) {
		ArrayList f = (ArrayList) animations.get(id);
		
		if(f==null) {
			System.err.println( "Frame \"" + id + "\" not found.");
			System.exit(-1);
		}
		
		return f;
	}
	
	// Reproduce un sonido.
	public void playSound(String id) {
		/*try 
		  { 
			InputStream is = getClass().getResourceAsStream("/whut.mp3");
		    Player player = Manager.createPlayer(is,"audio/mpeg");
		    player.realize();
		    // get volume control for player and set volume to max
		    VolumeControl vc = (VolumeControl) player.getControl("VolumeControl");
		    if(vc != null)
		    {
		      vc.setLevel(100);
		    }
		    player.prefetch();
		    player.start();
		  }
		  catch (Exception e) { }*/
	}
	
	// Obtiene una fase.
	public Phase getPhase(String id) {		
		Phase s = (Phase) phases.get(id);
		
		if(s==null) {
			System.err.println( "Stage \"" + id + "\" not found.");
			System.exit(-1);
		}
		
		return s;
	}
	
	// Establece el idioma.
	public void setLanguaje(int languaje) {
		this.languaje = languaje;
	}
	
	
	
	
	
	
	
	
	// Devuelve las pantallas de una fase.
	public ArrayList getStages(String id) {
		ArrayList stages = new ArrayList();
		
		if(id=="icaro_dedalos") {
			Stage p1s1 = new Stage();
			p1s1.setId("s1pantalla1");
			stages.add(p1s1);
			
			Stage p1s2 = new Stage();
			p1s2.setId("s1pantalla2");
			stages.add(p1s2);
			
		} else {
			Stage p2s1 = new Stage();
			p2s1.setId("s2pantalla1");
			stages.add(p2s1);
			
			Stage p2s2 = new Stage();
			p2s2.setId("s2pantalla2");
			stages.add(p2s2);
		}
		return stages;
	}
}
