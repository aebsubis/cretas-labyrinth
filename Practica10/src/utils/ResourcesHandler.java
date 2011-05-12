package utils;

import game.Element;
import game.GameHandler;
import game.Phase;
import game.Presentation;
import game.Scenery;
import game.Slide;
import game.Stage;

import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import maps.Map;
import maps.MapElement;
import maps.MapTraining1;
import maps.MapTraining2;
import maps.MapTraining3;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

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
	
	// Indica si se debe realizar el entrenamiento.
	private boolean training;
	
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
	public void init() {
		
		// Comprobamos que no haya sido inicializado todavía.
		if (initalizated == false) {
			
			// Establecemos el manejador como inicializado.
			initalizated = true;
			
			// Lenguaje por defecto.
			languaje = CASTELLANO;
			
			// Entrenamiento por defecto.
			training = true;
			
			// Textos genéricos
			textos = new Hashtable();
			texts = new Hashtable();
			
			textos.put("start", "Empezar");
			texts.put("start", "Start");
			
			textos.put("exit", "Salir");
			texts.put("exit", "Exit");
			
			textos.put("retry", "Reintentar");
			texts.put("retry", "Retry");
			
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
			
			// Nombres de fases.
			textos.put("phase_training1_name", "Entrenamiento 1");
			texts.put("phase_training1_name", "Training 1");
			
			textos.put("phase_training2_name", "Entrenamiento 2");
			texts.put("phase_training2_name", "Training 2");
			
			textos.put("phase_training3_name", "Entrenamiento 3");
			texts.put("phase_training3_name", "Training 3");
			
			textos.put("phase_icaro_dedalos_name", "La leyenda de Ícaro y Dédalos");
			texts.put("phase_icaro_dedalos_name", "The legend of Icarus and Daedalus");
			
			textos.put("phase_teseo_name", "La leyenda de Teseo");
			texts.put("phase_teseo_name", "The legend of Theseus");

			// Nombres de pantallas.
			textos.put("stage_training1_name", "Entrenamiento 1");
			texts.put("stage_training1_name", "Training 1");
			
			textos.put("stage_training2_name", "Entrenamiento 2");
			texts.put("stage_training2_name", "Training 2");
			
			textos.put("stage_training3_name", "Entrenamiento 3");
			texts.put("stage_training3_name", "Training 3");
			
			textos.put("stage_icaro_dedalos1_name", "Encerrados");
			texts.put("stage_icaro_dedalos1_name", "Locked");
			
			textos.put("stage_icaro_dedalos2_name", "Encuentra a tu padre");
			texts.put("stage_icaro_dedalos2_name", "Find your father");
			
			textos.put("stage_icaro_dedalos3_name", "Construye tu libertad");
			texts.put("stage_icaro_dedalos3_name", "Build your freedom");
			
			textos.put("stage_icaro_dedalos4_name", "Busqueda de materiales I");
			texts.put("stage_icaro_dedalos4_name", "Search for materials I");
			
			textos.put("stage_icaro_dedalos5_name", "Busqueda de materiales II");
			texts.put("stage_icaro_dedalos5_name", "Search for materials II");
			
			textos.put("stage_icaro_dedalos6_name", "Busqueda de materiales III");
			texts.put("stage_icaro_dedalos6_name", "Search for materials III");
			
			textos.put("stage_icaro_dedalos7_name", "Busqueda de materiales IV");
			texts.put("stage_icaro_dedalos7_name", "Search for materials IV");
			
			textos.put("stage_icaro_dedalos8_name", "Cuidado con el Minotauro");
			texts.put("stage_icaro_dedalos8_name", "Beware of Minotaur");
			
			textos.put("stage_icaro_dedalos9_name", "Vuela lejos");
			texts.put("stage_icaro_dedalos9_name", "Fly away");
			
			textos.put("stage_teseo1_name", "Proximamente");
			texts.put("stage_teseo1_name", "Comming soon");
			
			// Textos de diapositivas.
			textos.put("slide_1", "Diapositiva 1");
			texts.put("slide_1", "Slide 1");
			textos.put("slide_2", "Diapositiva 2");
			texts.put("slide_2", "Slide 2");
			
			textos.put("training1_text1", "Este es el personaje principal.");
			texts.put("training1_text1", "This is the main character.");
			textos.put("training1_text2", "Para desplazarte pulsa los botones\narriba, abajo, derecha e izquierda.");
			texts.put("training1_text2", "Press the buttons to scroll up, down,\nleft and right.");
			textos.put("training1_text3", "Encuentra la salida de este nivel\npara pasar al siguiente.");
			texts.put("training1_text3", "Find out at this level to the next.");
			
			textos.put("training2_text1", "Estos corazones indican tu nivel\nde salud actual.");
			texts.put("training2_text1", "These cores indicate your current\nhealth level.");
			textos.put("training2_text2", "Cada vez que recibes un impacto de\nun enemigo pierdes puntos de salud.");
			texts.put("training2_text2", "Every time you get an impact of an\nenemy lose health points.");
			textos.put("training2_text3", "Si te quedas sin salud\npierdes la partida.");
			texts.put("training2_text3", "If you lose your health\nyou lose the game.");
			
			textos.put("training3_text1", "En ocasiones puede que necesites\nexplorar el mapa para orientarte.\nPara ello utiliza el teclado numérico.");
			texts.put("training3_text1", "Sometimes you may need to explore\nthe map for orientation.\nIt uses the numeric keypad.");
			textos.put("training3_text2", "Para centrar la cámara de nuevo en\nel personaje pulsa el botón 5.");
			texts.put("training3_text2", "To focus the camera back into character\npress the 5 button.");
			
			textos.put("icaro_dedalos_text1", "Minos, hijo de Zeus y de Europa\npidió a Poseidón apoyo para\nsuceder al rey Asterión de Creta.");
			texts.put("icaro_dedalos_text1", "Minos, son of Zeus and Europe\ncalled to Poseidon support to\nsucceed the king of Crete Asterion.");
			textos.put("icaro_dedalos_text2", "Poseidón lo escuchó e hizo salir de\nlos mares un hermoso toro blanco\nal cual Minos prometió sacrificar.");
			texts.put("icaro_dedalos_text2", "Poseidon heard him and made out of\nthe sea a beautiful white bull\nwhich Minos promised to sacrifice.");
			textos.put("icaro_dedalos_text3", "Sin embargo, maravillado por sus\ncualidades, lo ocultó entre su rebaño\ny sacrificó a otro en su lugar.");
			texts.put("icaro_dedalos_text3", "However, amazed by his qualities,\nhe hid from his herd and sacrificed\nanother in his place.");
			textos.put("icaro_dedalos_text4", "Al saberlo Poseidón inspiró en su\nesposa un deseo incontenible por el\ntoro y nació el Minotauro.");
			texts.put("icaro_dedalos_text4", "Upon hearing, Poseidon inspired in\nhis wife an overwhelming desire for\nthe bull and the Minotaur was born.");
			textos.put("icaro_dedalos_text5", "El Minotauro era una bestia feroz\nque sólo comía carne humana y\nsegún crecía se volvía más salvaje.");
			texts.put("icaro_dedalos_text5", "The Minotaur was a beast that ate\nhuman flesh and only grew as it\nbecame more savage.");
			textos.put("icaro_dedalos_text6", "Para que nadie conociera el secreto\ndel laberinto, mandó encerrar a\nDédalo junto a su hijo Ícaro en él.");
			texts.put("icaro_dedalos_text6", "So that no one knew the secret of\nthe labyrinth, lock ordered Daedalus\nwith his son Icarus in it.");
			
			textos.put("teseo_text1", "Androgeno, hijo de Minos, fue\nasesinado en Atenas después de\nuna competición olímpica donde\nquedó campeón.");
			texts.put("teseo_text1", ".");
			textos.put("teseo_text2", "Minos lanzó su flota contra las\ncostas de Grecia, conquistando\nMegara y asedió Atenas.");
			texts.put("teseo_text2", ".");
			textos.put("teseo_text3", "Los atenienses consultaron el\noráculo y aceptaron las condiciones\nque Minosles impuso.");
			texts.put("teseo_text3", ".");
			textos.put("teseo_text4", "Cada nueve años siete doncellas y\nsiete donceles serían destinados\na ser devorados por el Minotauro.");
			texts.put("teseo_text4", ".");
			textos.put("teseo_text5", "El tributo se suspendería si\nalguno de ellos lograba escapar\ndel laberinto.");
			texts.put("teseo_text5", ".");
			textos.put("teseo_text6", "Teseo, con el consentimiento de su\npadre el rey Egeo, se hizo designar\ncomo uno de los siete jóvenes.");
			texts.put("teseo_text6", ".");
			textos.put("teseo_text7", "Ariadna, hija de Minos, se enamoró\nde Teseo y le ofreció un ovillo con el\nque guiarse para salir y una espada\nmágica para matar al Minotauro.");
			texts.put("teseo_text7", ".");
			
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
		    	images.put("victory", Image.createImage("/interface/victory.png"));
		    	images.put("defeat", Image.createImage("/interface/defeat.png"));

		    	images.put("live_off", Image.createImage("/interface/liveOff.png"));
		    	images.put("live_half", Image.createImage("/interface/liveHalf.png"));
		    	images.put("live_on", Image.createImage("/interface/liveOn.png"));
		    	
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
		    	images.put("enemy_minotaur_front1", Image.createImage("/element/enemy_minotaur_front1.png"));	
		    	images.put("enemy_minotaur_front2", Image.createImage("/element/enemy_minotaur_front2.png"));
		    	images.put("enemy_minotaur_front3", Image.createImage("/element/enemy_minotaur_front3.png"));
		    	images.put("enemy_minotaur_back1", Image.createImage("/element/enemy_minotaur_back1.png"));	
		    	images.put("enemy_minotaur_back2", Image.createImage("/element/enemy_minotaur_back2.png"));
		    	images.put("enemy_minotaur_back3", Image.createImage("/element/enemy_minotaur_back3.png"));
		    	images.put("enemy_minotaur_right1", Image.createImage("/element/enemy_minotaur_right1.png"));	
		    	images.put("enemy_minotaur_right2", Image.createImage("/element/enemy_minotaur_right2.png"));
		    	images.put("enemy_minotaur_right3", Image.createImage("/element/enemy_minotaur_right3.png"));
		    	images.put("enemy_minotaur_left1", Image.createImage("/element/enemy_minotaur_left1.png"));	
		    	images.put("enemy_minotaur_left2", Image.createImage("/element/enemy_minotaur_left2.png"));
		    	images.put("enemy_minotaur_left3", Image.createImage("/element/enemy_minotaur_left3.png"));
			
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
		    	
		    	/// Niebla
		    	images.put("fog", Image.createImage("/scenery/fog.png"));
		    	
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
		    	images.put("training1_slide1", Image.createImage("/phases/training1/presentation/training1_slide1.png"));
		    	images.put("training1_slide2", Image.createImage("/phases/training1/presentation/training1_slide2.png"));
		    	images.put("training1_slide3", Image.createImage("/phases/training1/presentation/training1_slide3.png"));
		    	
		    	images.put("training2_slide1", Image.createImage("/phases/training2/presentation/training2_slide1.png"));
		    	images.put("training2_slide2", Image.createImage("/phases/training2/presentation/training2_slide2.png"));
		    	images.put("training2_slide3", Image.createImage("/phases/training2/presentation/training2_slide3.png"));
		    	
		    	images.put("training3_slide1", Image.createImage("/phases/training3/presentation/training3_slide1.png"));
		    	images.put("training3_slide2", Image.createImage("/phases/training3/presentation/training3_slide2.png"));
		    	
		    	images.put("icaro_dedalos_slide1", Image.createImage("/phases/icaro_dedalos/presentation/icaro_dedalos_slide1.png"));
		    	images.put("icaro_dedalos_slide2", Image.createImage("/phases/icaro_dedalos/presentation/icaro_dedalos_slide2.png"));
		    	images.put("icaro_dedalos_slide3", Image.createImage("/phases/icaro_dedalos/presentation/icaro_dedalos_slide3.png"));
		    	images.put("icaro_dedalos_slide4", Image.createImage("/phases/icaro_dedalos/presentation/icaro_dedalos_slide4.png"));
		    	images.put("icaro_dedalos_slide5", Image.createImage("/phases/icaro_dedalos/presentation/icaro_dedalos_slide5.png"));
		    	images.put("icaro_dedalos_slide6", Image.createImage("/phases/icaro_dedalos/presentation/icaro_dedalos_slide6.png"));
				
		    	images.put("teseo_slide1", Image.createImage("/phases/teseo/presentation/teseo_slide1.png"));
		    	images.put("teseo_slide2", Image.createImage("/phases/teseo/presentation/teseo_slide2.png"));
		    	images.put("teseo_slide3", Image.createImage("/phases/teseo/presentation/teseo_slide3.png"));
		    	images.put("teseo_slide4", Image.createImage("/phases/teseo/presentation/teseo_slide4.png"));
		    	images.put("teseo_slide5", Image.createImage("/phases/teseo/presentation/teseo_slide5.png"));
		    	images.put("teseo_slide6", Image.createImage("/phases/teseo/presentation/teseo_slide6.png"));
		    	images.put("teseo_slide7", Image.createImage("/phases/teseo/presentation/teseo_slide7.png"));
		    	
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
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_front1");
	    	animations.put("enemy_minotaur_stand_front", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_back1");
	    	animations.put("enemy_minotaur_stand_back", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_right1");
	    	animations.put("enemy_minotaur_stand_right", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_left1");
	    	animations.put("enemy_minotaur_stand_left", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front2");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front3");
	    	animations.put("enemy_minotaur_walk_front", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back2");
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back3");
	    	animations.put("enemy_minotaur_walk_back", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right2");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right3");
	    	animations.put("enemy_minotaur_walk_right", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left2");
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left3");
	    	animations.put("enemy_minotaur_walk_left", f);
	    	
	    	f = new ArrayList();
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_left1");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_right1");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_front1");
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back1");
	    	f.add("enemy_minotaur_back1");
	    	animations.put("enemy_minotaur_take_break", f);
	    	
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
	    	
	    	/// Niebla
	    	f = new ArrayList();
	    	f.add("fog");
	    	animations.put("fog", f);
	    	
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
		    Phase p = null;
		    
		    p = new Phase();
			p.setId("training1");
			phases.put("training1", p);
			
			p = new Phase();
			p.setId("training2");
			phases.put("training2", p);
			
			p = new Phase();
			p.setId("training3");
			phases.put("training3", p);
			
			p = new Phase();
			p.setId("icaro_dedalos");
			phases.put("icaro_dedalos", p);
						
			p = new Phase();
			p.setId("teseo");
			phases.put("teseo", p);
		}
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
			System.err.println( "Phase \"" + id + "\" not found.");
			System.exit(-1);
		}

		return s.clone();
	}
	
	// Devuelve las pantallas de una fase.
	public ArrayList getStages(String id) {
		
		// Array list de pantallas.
		ArrayList stages = new ArrayList();
		Stage s = null;
		
		if(id.equals("training1")) {
			
			// Pantallas de la fase "training1"
			s = new Stage();
			s.setId("training1");
			stages.add(s);
			
		} else if(id.equals("training2")) {
			
			// Pantallas de la fase "training2"
			s = new Stage();
			s.setId("training2");
			stages.add(s);
			
		} else if(id.equals("training3")) {
			
			// Pantallas de la fase "training3"
			s = new Stage();
			s.setId("training3");
			stages.add(s);
			
		} else if(id.equals("icaro_dedalos")) {
			
			// Pantallas de la fase "icaro_dedalos"
			s = new Stage();
			s.setId("icaro_dedalos1");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos2");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos3");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos4");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos5");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos6");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos7");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos8");
			stages.add(s);
			
			s = new Stage();
			s.setId("icaro_dedalos9");
			stages.add(s);
			
		} else if(id.equals("teseo")) {
			
			// Pantallas de la fase "teseo"
			s = new Stage();
			s.setId("teseo1");
			stages.add(s);
			
		} else {
			System.err.println( "Stage \"" + id + "\" not found.");
			System.exit(-1);
		}
		return stages;
	}
	
	// Devuelve la presentación de la fase.
	public ArrayList getSlides(String id) {
		
		// Array list de diapositivas.
		ArrayList slides = new ArrayList();
		Slide s = null;
		
		if(id.equals("training1")) {
			
			// Diapositivas de la fase "training1"
			s = new Slide();
			s.setImage("training1_slide1");
			s.setText("training1_text1");
			slides.add(s);
			
			s = new Slide();
			s.setImage("training1_slide2");
			s.setText("training1_text2");
			slides.add(s);
			
			s = new Slide();
			s.setImage("training1_slide3");
			s.setText("training1_text3");
			slides.add(s);
			
		} else if(id.equals("training2")) {
			
			// Diapositivas de la fase "training2"
			s = new Slide();
			s.setImage("training2_slide1");
			s.setText("training2_text1");
			slides.add(s);
			
			s = new Slide();
			s.setImage("training2_slide2");
			s.setText("training2_text2");
			slides.add(s);
			
			s = new Slide();
			s.setImage("training2_slide3");
			s.setText("training2_text3");
			slides.add(s);
			
		} else if(id.equals("training3")) {
			
			// Diapositivas de la fase "training3"
			s = new Slide();
			s.setImage("training3_slide1");
			s.setText("training3_text1");
			slides.add(s);
			
			s = new Slide();
			s.setImage("training3_slide2");
			s.setText("training3_text2");
			slides.add(s);
			
		} else if(id.equals("icaro_dedalos")) {
			
			// Diapositivas de la fase "icaro_dedalos"
			s = new Slide();
			s.setImage("icaro_dedalos_slide1");
			s.setText("icaro_dedalos_text1");
			slides.add(s);
			
			s = new Slide();
			s.setImage("icaro_dedalos_slide2");
			s.setText("icaro_dedalos_text2");
			slides.add(s);
			
			s = new Slide();
			s.setImage("icaro_dedalos_slide3");
			s.setText("icaro_dedalos_text3");
			slides.add(s);
			
			s = new Slide();
			s.setImage("icaro_dedalos_slide4");
			s.setText("icaro_dedalos_text4");
			slides.add(s);
			
			s = new Slide();
			s.setImage("icaro_dedalos_slide5");
			s.setText("icaro_dedalos_text5");
			slides.add(s);
			
			s = new Slide();
			s.setImage("icaro_dedalos_slide6");
			s.setText("icaro_dedalos_text6");
			slides.add(s);
			
		} else if(id.equals("teseo")) {
			
			// Diapositivas de la fase "teseo"
			s = new Slide();
			s.setImage("teseo_slide1");
			s.setText("teseo_text1");
			slides.add(s);
			
			s = new Slide();
			s.setImage("teseo_slide2");
			s.setText("teseo_text2");
			slides.add(s);
			
			s = new Slide();
			s.setImage("teseo_slide3");
			s.setText("teseo_text3");
			slides.add(s);
			
			s = new Slide();
			s.setImage("teseo_slide4");
			s.setText("teseo_text4");
			slides.add(s);
			
			s = new Slide();
			s.setImage("teseo_slide5");
			s.setText("teseo_text5");
			slides.add(s);
			
			s = new Slide();
			s.setImage("teseo_slide6");
			s.setText("teseo_text6");
			slides.add(s);
			
			s = new Slide();
			s.setImage("teseo_slide7");
			s.setText("teseo_text7");
			slides.add(s);
			
		} else {
			System.err.println( "Slides \"" + id + "\" not found.");
			System.exit(-1);
		}
		return slides;
	}

	// Carga la información de una fase.
	public void loadPhase(Phase phase) {
		
		// Obtenemos el nombre de la fase.
		phase.setName(getText("phase_" + phase.getId() + "_name"));
		
		// Establecemos la primera pantalla como actual.
		phase.setCurrentStage(0);
		
		// Obtenemos las pantallas.
		phase.setStages(ResourcesHandler.getInstance().getStages(phase.getId()));
		
		// Obtenemos la presentación.
		phase.setPresentation(new Presentation(phase.getId()));
	}

	// Carga la información de una presentación.
	public void loadPresentation(Presentation presentation) {
		
		// Identificamos el recurso sonoro.
		//presentation.setMusic(ResourcesHandler.getInstance().getMusic(presentation.getId()));
		
		//Obtenemos las diapositivas.
		presentation.setSlides(ResourcesHandler.getInstance().getSlides(presentation.getId()));
				
		// Establecemos como actual la primera diapositiva.
		presentation.setCurrentSlide(0);
	}

	// Carga la información de una pantalla.
	public void loadStage(Stage stage){
		
		/*
		InputStreamReader reader =  null;
		KXmlParser parser = null;
		XmlParser gParser = null;
		XmlNode xml = null;
		
		reader = new InputStreamReader(getClass().getResourceAsStream("/phases/training1/stages/training1.xml"));

		parser = new KXmlParser();
		gParser = new XmlParser();
		
		try {
			parser.setInput(reader);
		} catch (Exception e) {
			System.err.println("Error al establecer el input.");
			e.printStackTrace();
		}

		try {
			xml = gParser.parseXML(parser, true);
		} catch (Exception e) {
			System.err.println("Error al obtener el XML.");
			e.printStackTrace();
		}
		*/
		Map loadingMap = null;
		if(stage.getId().equals("training1")) {
			loadingMap = new MapTraining1();
		} else if(stage.getId().equals("training2")) {
			loadingMap = new MapTraining2();
		} else if(stage.getId().equals("training3")) {
			loadingMap = new MapTraining3();
		} else {
			System.err.println( "Stage \"" + stage.getId() + "\" not found.");
			System.exit(-1);
		}
		
		
		// Nombre de la pantalla.
		stage.setName(getText("stage_" + stage.getId() + "_name"));
		
		// Leemos el tamaño de la pantalla.
		int width = loadingMap.getWidth();
		int height = loadingMap.getHeight();
		
		// Guardamos el tamaño de la pantalla.
		stage.setWidth(width);
		stage.setHeight(height);
		
		// Inicializamos el escenario.
		Scenery[][] scenery = new Scenery[stage.getWidth()][stage.getHeight()];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				// Obtenemos el identificador del objeto.
				double objectId = GameHandler.getInstance().getIdentifier();
				
				// Obtenemos el elemento a ser cargado.
				MapElement mapElement = loadingMap.getSceneryElement(i, j);
				
				// Leemos los parámetros.
				int elementXLocation = mapElement.getElementXLocation();
				int elementYLocation = mapElement.getElementYLocation();
				String elementAIType = mapElement.getElementAIType();
				String elementGFXType = mapElement.getElementGFXType();
				int elementDepth = mapElement.getElementDepth();
				boolean elementPasable = mapElement.isElementPasable();
				
				// Creamos el objeto lógico.
				scenery[i][j] = new Scenery(objectId, new Location2D(elementXLocation, elementYLocation) , elementAIType, elementGFXType, elementDepth, elementPasable);
			}
		}
		
		// Guardamos el escenario en la pantalla.
		stage.setScenery(scenery);
		
		
		// Inicializamos la niebla.
		Scenery[][] visitedArea = new Scenery [stage.getWidth()][stage.getHeight()];

		boolean activateFog = loadingMap.isFog();
		
		for (int i = 0; i < stage.getWidth(); i++) {
			for (int j = 0; j < stage.getHeight(); j++) {
				if(activateFog == true) {
				
					// Obtenemos un identificador único para el nuevo objeto.
					double objectId = GameHandler.getInstance().getIdentifier();
	
					// Creamos el objeto lógico.
					visitedArea[i][j] = new Scenery(objectId, new Location2D(i, j) , "none", "fog", 4, true);
				
				} else {
					
					visitedArea[i][j] = null;
					
				}
			}
		}
		
		// Guardamos la niebla en la pantalla.
		stage.setVisitedArea(visitedArea);
		
		
		// Inicializamos los elemetos.
		ArrayList elements = new ArrayList();
		
		// Para cada enemigo en la pantalla.
		for(int i = 0; i < loadingMap.getElements().size(); i++) {
			
			// Obtenemos un identificador único para el nuevo objeto.
			double elementId = GameHandler.getInstance().getIdentifier();
			
			// Obtenemos el elemento a ser cargado.
			MapElement mapElement = (MapElement)loadingMap.getElements().get(i);
			
			// Leemos los parámetros.
			int elementXLocation = mapElement.getElementXLocation();
			int elementYLocation = mapElement.getElementYLocation();
			String elementAIType = mapElement.getElementAIType();
			String elementGFXType = mapElement.getElementGFXType();
			int elementDepth = mapElement.getElementDepth();
			int elementLives = mapElement.getElementLives();
			int elementSpeed = mapElement.getElementSpeed();
			int elementStamina = mapElement.getElementStamina();
			boolean elementMovable = mapElement.isElementMovable();
			boolean elementPortable = mapElement.isElementPortable();
			boolean elementUsable = mapElement.isElementUsable();
			boolean elementPasable = mapElement.isElementPasable();
			boolean elementEnemy = mapElement.isElementEnemy();
			
			// Creamos el elemento.
			elements.add(new Element(elementId, new Location2D(elementXLocation, elementYLocation), elementAIType, elementGFXType, elementDepth, elementLives, elementSpeed, elementStamina, elementMovable, elementPortable, elementUsable, elementPasable, elementEnemy));
		}
			
		// Guardamos los elementos en la pantalla.
		stage.setElements(elements);

		// Obtenemos la posición inicial del nivel.
		int startLevelLocationX = loadingMap.getStartLocationX();
		int startLevelLocationY = loadingMap.getStartLocationY();
		
		// Start location
		stage.setStartLocation(new Location2D(startLevelLocationX, startLevelLocationY));
		
		// Obtenemos la posición final del nivel.
		int endLevelLocationX = 0;
		int endLevelLocationY = 0;
		
		// End location
		stage.setEndLocation(new Location2D(endLevelLocationX, endLevelLocationY));
		
		// Obtenemos un identificador único para el jugador.
		double playerId = GameHandler.getInstance().getIdentifier();
		
		// Cargamos el jugador.
		Element player = new Element(playerId, new Location2D(stage.getStartLocation().getX(), stage.getStartLocation().getY()), "player", "player_icaro_stand_front", 2, 10, 2, 100, true, false, false, false, false);

		// Guardamos el jugador.
		stage.setPlayer(player);
	}
	
	// Establece el idioma.
	public void setLanguaje(int languaje) {
		this.languaje = languaje;
	}
	
	// Establece si se debe reealizar el entrenamiento.
	public void setTraining(boolean training) {
		this.training = training;
	}
	
	// Indica si se debe realizar el entrenamiento.
	public boolean getTraining() {
		return training;
	}
}
