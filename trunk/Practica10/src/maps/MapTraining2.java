package maps;

import utils.ArrayList;

public class MapTraining2 extends Map{

	// Constructor por defecto
	public MapTraining2() {
		
		// Inicializamos las dimensiones del mapa.
		this.setWidth(10);
		this.setHeight(10);
		
		// Inicializamos la posición inicial del mapa.
		this.setStartLocationX(1);
		this.setStartLocationY(1);
		
		// Inicializamos la posición final del mapa.
		this.setEndLocationX(1);
		this.setEndLocationY(8);
		
		// Inicializamos la niebla.
		this.setFog(false);
		
		// Inicializamos el escenario del mapa.
		MapElement[][] sceneryElements = new MapElement[this.getWidth()][this.getHeight()];
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if(i==0 || j == 0 || i == this.getWidth()-1 || j == this.getHeight()-1)
					sceneryElements[i][j] = new MapElement(i, j, "none", "wall_rock", 0, false);
				else
					sceneryElements[i][j] = new MapElement(i, j, "none", "floor_sand", 0, true);
			}
		}
		
		sceneryElements[1][2] = new MapElement(1, 2, "none", "wall_rock", 0, false);
		sceneryElements[1][6] = new MapElement(1, 6, "none", "wall_rock", 0, false);
		
		sceneryElements[2][2] = new MapElement(2, 2, "none", "wall_rock", 0, false);
		sceneryElements[2][6] = new MapElement(2, 6, "none", "wall_rock", 0, false);
		
		sceneryElements[3][2] = new MapElement(3, 2, "none", "wall_rock", 0, false);
		sceneryElements[3][3] = new MapElement(3, 3, "none", "wall_rock", 0, false);
		sceneryElements[3][4] = new MapElement(3, 4, "none", "wall_rock", 0, false);
		sceneryElements[3][6] = new MapElement(3, 6, "none", "wall_rock", 0, false);
		
		sceneryElements[4][2] = new MapElement(4, 2, "none", "wall_rock", 0, false);
		sceneryElements[4][6] = new MapElement(4, 6, "none", "wall_rock", 0, false);
		
		sceneryElements[5][2] = new MapElement(5, 2, "none", "wall_rock", 0, false);
		sceneryElements[5][6] = new MapElement(5, 6, "none", "wall_rock", 0, false);
		
		sceneryElements[6][2] = new MapElement(6, 2, "none", "wall_rock", 0, false);
		sceneryElements[6][4] = new MapElement(6, 4, "none", "wall_rock", 0, false);
		sceneryElements[6][6] = new MapElement(6, 6, "none", "wall_rock", 0, false);
		
		sceneryElements[7][4] = new MapElement(7, 4, "none", "wall_rock", 0, false);
		
		sceneryElements[8][4] = new MapElement(8, 4, "none", "wall_rock", 0, false);
		
		// Guardamos el escenario del mapa.
		this.setSceneryElements(sceneryElements);
		
		
		// Inicializamos los elementos del mapa.
		ArrayList elements = new ArrayList();
		
		// Enemigo.
		elements.add(new MapElement(5, 5, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		
		// Punto de salida.
		elements.add(new MapElement(this.getEndLocationX(), this.getEndLocationY(), "none", "end_level", 3, true));
		
		// Guardamos los elementos del mapa.
		this.setElements(elements);
	}
}
