package maps;

import utils.ArrayList;

public class MapTraining3 extends Map{

	// Constructor por defecto
	public MapTraining3() {
		
		// Inicializamos las dimensiones del mapa.
		this.setWidth(23);
		this.setHeight(36);
		
		// Inicializamos la posición inicial del mapa.
		this.setStartLocationX(9);
		this.setStartLocationY(12);
		
		// Inicializamos la posición final del mapa.
		this.setEndLocationX(17);
		this.setEndLocationY(30);
		
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
		
		sceneryElements[1][3] = new MapElement(1, 3, "none", "wall_rock", 0, false);
		sceneryElements[1][5] = new MapElement(1, 5, "none", "wall_rock", 0, false);
		
		sceneryElements[2][1] = new MapElement(2, 1, "none", "wall_rock", 0, false);
		sceneryElements[2][3] = new MapElement(2, 3, "none", "wall_rock", 0, false);
		sceneryElements[2][6] = new MapElement(2, 6, "none", "wall_rock", 0, false);
		sceneryElements[2][8] = new MapElement(2, 8, "none", "wall_rock", 0, false);
		sceneryElements[2][10] = new MapElement(2, 10, "none", "wall_rock", 0, false);
		sceneryElements[2][12] = new MapElement(2, 12, "none", "wall_rock", 0, false);
		sceneryElements[2][14] = new MapElement(2, 14, "none", "wall_rock", 0, false);
		sceneryElements[2][16] = new MapElement(2, 16, "none", "wall_rock", 0, false);
		sceneryElements[2][18] = new MapElement(2, 18, "none", "wall_rock", 0, false);
		sceneryElements[2][19] = new MapElement(2, 19, "none", "wall_rock", 0, false);
		sceneryElements[2][20] = new MapElement(2, 20, "none", "wall_rock", 0, false);
		sceneryElements[2][21] = new MapElement(2, 21, "none", "wall_rock", 0, false);
		sceneryElements[2][22] = new MapElement(2, 22, "none", "wall_rock", 0, false);
		sceneryElements[2][23] = new MapElement(2, 23, "none", "wall_rock", 0, false);
		sceneryElements[2][24] = new MapElement(2, 24, "none", "wall_rock", 0, false);
		sceneryElements[2][25] = new MapElement(2, 25, "none", "wall_rock", 0, false);
		sceneryElements[2][26] = new MapElement(2, 26, "none", "wall_rock", 0, false);
		sceneryElements[2][27] = new MapElement(2, 27, "none", "wall_rock", 0, false);
		sceneryElements[2][28] = new MapElement(2, 28, "none", "wall_rock", 0, false);
		sceneryElements[2][29] = new MapElement(2, 29, "none", "wall_rock", 0, false);
		sceneryElements[2][30] = new MapElement(2, 30, "none", "wall_rock", 0, false);
		sceneryElements[2][31] = new MapElement(2, 31, "none", "wall_rock", 0, false);
		sceneryElements[2][32] = new MapElement(2, 32, "none", "wall_rock", 0, false);
		sceneryElements[2][33] = new MapElement(2, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[3][3] = new MapElement(3, 3, "none", "wall_rock", 0, false);
		sceneryElements[3][4] = new MapElement(3, 4, "none", "wall_rock", 0, false);
		sceneryElements[3][8] = new MapElement(3, 8, "none", "wall_rock", 0, false);
		sceneryElements[3][10] = new MapElement(3, 10, "none", "wall_rock", 0, false);
		sceneryElements[3][12] = new MapElement(3, 12, "none", "wall_rock", 0, false);
		sceneryElements[3][14] = new MapElement(3, 14, "none", "wall_rock", 0, false);
		sceneryElements[3][16] = new MapElement(3, 16, "none", "wall_rock", 0, false);
		sceneryElements[3][18] = new MapElement(3, 18, "none", "wall_rock", 0, false);
		sceneryElements[3][33] = new MapElement(3, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[4][2] = new MapElement(4, 2, "none", "wall_rock", 0, false);
		sceneryElements[4][3] = new MapElement(4, 3, "none", "wall_rock", 0, false);
		sceneryElements[4][6] = new MapElement(4, 6, "none", "wall_rock", 0, false);
		sceneryElements[4][18] = new MapElement(4, 18, "none", "wall_rock", 0, false);
		sceneryElements[4][20] = new MapElement(4, 20, "none", "wall_rock", 0, false);
		sceneryElements[4][21] = new MapElement(4, 21, "none", "wall_rock", 0, false);
		sceneryElements[4][22] = new MapElement(4, 22, "none", "wall_rock", 0, false);
		sceneryElements[4][23] = new MapElement(4, 23, "none", "wall_rock", 0, false);
		sceneryElements[4][24] = new MapElement(4, 24, "none", "wall_rock", 0, false);
		sceneryElements[4][25] = new MapElement(4, 25, "none", "wall_rock", 0, false);
		sceneryElements[4][26] = new MapElement(4, 26, "none", "wall_rock", 0, false);
		sceneryElements[4][27] = new MapElement(4, 27, "none", "wall_rock", 0, false);
		sceneryElements[4][28] = new MapElement(4, 28, "none", "wall_rock", 0, false);
		sceneryElements[4][29] = new MapElement(4, 29, "none", "wall_rock", 0, false);
		sceneryElements[4][30] = new MapElement(4, 30, "none", "wall_rock", 0, false);
		sceneryElements[4][31] = new MapElement(4, 31, "none", "wall_rock", 0, false);
		sceneryElements[4][33] = new MapElement(4, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[5][3] = new MapElement(5, 3, "none", "wall_rock", 0, false);
		sceneryElements[5][4] = new MapElement(5, 4, "none", "wall_rock", 0, false);
		sceneryElements[5][5] = new MapElement(5, 5, "none", "wall_rock", 0, false);
		sceneryElements[5][6] = new MapElement(5, 6, "none", "wall_rock", 0, false);
		sceneryElements[5][7] = new MapElement(5, 7, "none", "wall_rock", 0, false);
		sceneryElements[5][8] = new MapElement(5, 8, "none", "wall_rock", 0, false);
		sceneryElements[5][9] = new MapElement(5, 9, "none", "wall_rock", 0, false);
		sceneryElements[5][10] = new MapElement(5, 10, "none", "wall_rock", 0, false);
		sceneryElements[5][11] = new MapElement(5, 11, "none", "wall_rock", 0, false);
		sceneryElements[5][12] = new MapElement(5, 12, "none", "wall_rock", 0, false);
		sceneryElements[5][13] = new MapElement(5, 13, "none", "wall_rock", 0, false);
		sceneryElements[5][14] = new MapElement(5, 14, "none", "wall_rock", 0, false);
		sceneryElements[5][15] = new MapElement(5, 15, "none", "wall_rock", 0, false);
		sceneryElements[5][16] = new MapElement(5, 16, "none", "wall_rock", 0, false);
		sceneryElements[5][17] = new MapElement(5, 17, "none", "wall_rock", 0, false);
		sceneryElements[5][18] = new MapElement(5, 18, "none", "wall_rock", 0, false);
		sceneryElements[5][20] = new MapElement(5, 20, "none", "wall_rock", 0, false);
		sceneryElements[5][31] = new MapElement(5, 31, "none", "wall_rock", 0, false);
		sceneryElements[5][33] = new MapElement(5, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[6][1] = new MapElement(6, 1, "none", "wall_rock", 0, false);
		sceneryElements[6][3] = new MapElement(6, 3, "none", "wall_rock", 0, false);
		sceneryElements[6][9] = new MapElement(6, 9, "none", "wall_rock", 0, false);
		sceneryElements[6][18] = new MapElement(6, 18, "none", "wall_rock", 0, false);
		sceneryElements[6][20] = new MapElement(6, 20, "none", "wall_rock", 0, false);
		sceneryElements[6][21] = new MapElement(6, 21, "none", "wall_rock", 0, false);
		sceneryElements[6][22] = new MapElement(6, 22, "none", "wall_rock", 0, false);
		sceneryElements[6][23] = new MapElement(6, 23, "none", "wall_rock", 0, false);
		sceneryElements[6][24] = new MapElement(6, 24, "none", "wall_rock", 0, false);
		sceneryElements[6][25] = new MapElement(6, 25, "none", "wall_rock", 0, false);
		sceneryElements[6][26] = new MapElement(6, 26, "none", "wall_rock", 0, false);
		sceneryElements[6][27] = new MapElement(6, 27, "none", "wall_rock", 0, false);
		sceneryElements[6][28] = new MapElement(6, 28, "none", "wall_rock", 0, false);
		sceneryElements[6][29] = new MapElement(6, 29, "none", "wall_rock", 0, false);
		sceneryElements[6][31] = new MapElement(6, 31, "none", "wall_rock", 0, false);
		sceneryElements[6][33] = new MapElement(6, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[7][3] = new MapElement(7, 3, "none", "wall_rock", 0, false);
		sceneryElements[7][4] = new MapElement(7, 4, "none", "wall_rock", 0, false);
		sceneryElements[7][5] = new MapElement(7, 5, "none", "wall_rock", 0, false);
		sceneryElements[7][7] = new MapElement(7, 7, "none", "wall_rock", 0, false);
		sceneryElements[7][11] = new MapElement(7, 11, "none", "wall_rock", 0, false);
		sceneryElements[7][13] = new MapElement(7, 13, "none", "wall_rock", 0, false);
		sceneryElements[7][19] = new MapElement(7, 19, "none", "wall_rock", 0, false);
		sceneryElements[7][31] = new MapElement(7, 31, "none", "wall_rock", 0, false);
		sceneryElements[7][33] = new MapElement(7, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[8][2] = new MapElement(8, 2, "none", "wall_rock", 0, false);
		sceneryElements[8][3] = new MapElement(8, 3, "none", "wall_rock", 0, false);
		sceneryElements[8][7] = new MapElement(8, 7, "none", "wall_rock", 0, false);
		sceneryElements[8][8] = new MapElement(8, 8, "none", "wall_rock", 0, false);
		sceneryElements[8][9] = new MapElement(8, 9, "none", "wall_rock", 0, false);
		sceneryElements[8][10] = new MapElement(8, 10, "none", "wall_rock", 0, false);
		sceneryElements[8][11] = new MapElement(8, 11, "none", "wall_rock", 0, false);
		sceneryElements[8][13] = new MapElement(8, 13, "none", "wall_rock", 0, false);
		sceneryElements[8][14] = new MapElement(8, 14, "none", "wall_rock", 0, false);
		sceneryElements[8][15] = new MapElement(8, 15, "none", "wall_rock", 0, false);
		sceneryElements[8][16] = new MapElement(8, 16, "none", "wall_rock", 0, false);
		sceneryElements[8][19] = new MapElement(8, 19, "none", "wall_rock", 0, false);
		sceneryElements[8][20] = new MapElement(8, 20, "none", "wall_rock", 0, false);
		sceneryElements[8][21] = new MapElement(8, 21, "none", "wall_rock", 0, false);
		sceneryElements[8][22] = new MapElement(8, 22, "none", "wall_rock", 0, false);
		sceneryElements[8][23] = new MapElement(8, 23, "none", "wall_rock", 0, false);
		sceneryElements[8][24] = new MapElement(8, 24, "none", "wall_rock", 0, false);
		sceneryElements[8][25] = new MapElement(8, 25, "none", "wall_rock", 0, false);
		sceneryElements[8][26] = new MapElement(8, 26, "none", "wall_rock", 0, false);
		sceneryElements[8][27] = new MapElement(8, 27, "none", "wall_rock", 0, false);
		sceneryElements[8][28] = new MapElement(8, 28, "none", "wall_rock", 0, false);
		sceneryElements[8][29] = new MapElement(8, 29, "none", "wall_rock", 0, false);
		sceneryElements[8][31] = new MapElement(8, 31, "none", "wall_rock", 0, false);
		sceneryElements[8][33] = new MapElement(8, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[9][3] = new MapElement(9, 3, "none", "wall_rock", 0, false);
		sceneryElements[9][31] = new MapElement(9, 31, "none", "wall_rock", 0, false);
		sceneryElements[9][33] = new MapElement(9, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[10][1] = new MapElement(10, 1, "none", "wall_rock", 0, false);
		sceneryElements[10][3] = new MapElement(10, 3, "none", "wall_rock", 0, false);
		sceneryElements[10][4] = new MapElement(10, 4, "none", "wall_rock", 0, false);
		sceneryElements[10][5] = new MapElement(10, 5, "none", "wall_rock", 0, false);
		sceneryElements[10][6] = new MapElement(10, 6, "none", "wall_rock", 0, false);
		sceneryElements[10][7] = new MapElement(10, 7, "none", "wall_rock", 0, false);
		sceneryElements[10][9] = new MapElement(10, 9, "none", "wall_rock", 0, false);
		sceneryElements[10][10] = new MapElement(10, 10, "none", "wall_rock", 0, false);
		sceneryElements[10][11] = new MapElement(10, 11, "none", "wall_rock", 0, false);
		sceneryElements[10][13] = new MapElement(10, 13, "none", "wall_rock", 0, false);
		sceneryElements[10][14] = new MapElement(10, 14, "none", "wall_rock", 0, false);
		sceneryElements[10][15] = new MapElement(10, 15, "none", "wall_rock", 0, false);
		sceneryElements[10][16] = new MapElement(10, 16, "none", "wall_rock", 0, false);
		sceneryElements[10][17] = new MapElement(10, 17, "none", "wall_rock", 0, false);
		sceneryElements[10][18] = new MapElement(10, 18, "none", "wall_rock", 0, false);
		sceneryElements[10][20] = new MapElement(10, 20, "none", "wall_rock", 0, false);
		sceneryElements[10][21] = new MapElement(10, 21, "none", "wall_rock", 0, false);
		sceneryElements[10][22] = new MapElement(10, 22, "none", "wall_rock", 0, false);
		sceneryElements[10][23] = new MapElement(10, 23, "none", "wall_rock", 0, false);
		sceneryElements[10][24] = new MapElement(10, 24, "none", "wall_rock", 0, false);
		sceneryElements[10][25] = new MapElement(10, 25, "none", "wall_rock", 0, false);
		sceneryElements[10][26] = new MapElement(10, 26, "none", "wall_rock", 0, false);
		sceneryElements[10][27] = new MapElement(10, 27, "none", "wall_rock", 0, false);
		sceneryElements[10][28] = new MapElement(10, 28, "none", "wall_rock", 0, true); // Atajo
		sceneryElements[10][29] = new MapElement(10, 29, "none", "wall_rock", 0, false);
		sceneryElements[10][30] = new MapElement(10, 30, "none", "wall_rock", 0, false);
		sceneryElements[10][31] = new MapElement(10, 31, "none", "wall_rock", 0, false);
		sceneryElements[10][33] = new MapElement(10, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[11][3] = new MapElement(11, 3, "none", "wall_rock", 0, false);
		sceneryElements[11][7] = new MapElement(11, 7, "none", "wall_rock", 0, false);
		sceneryElements[11][9] = new MapElement(11, 9, "none", "wall_rock", 0, false);
		sceneryElements[11][11] = new MapElement(11, 11, "none", "wall_rock", 0, false);
		sceneryElements[11][13] = new MapElement(11, 13, "none", "wall_rock", 0, false);
		sceneryElements[11][20] = new MapElement(11, 20, "none", "wall_rock", 0, false);
		sceneryElements[11][33] = new MapElement(11, 33, "none", "wall_rock", 0, false);
		
		sceneryElements[12][2] = new MapElement(12, 2, "none", "wall_rock", 0, false);
		sceneryElements[12][3] = new MapElement(12, 3, "none", "wall_rock", 0, false);
		sceneryElements[12][5] = new MapElement(12, 5, "none", "wall_rock", 0, false);
		sceneryElements[12][7] = new MapElement(12, 7, "none", "wall_rock", 0, false);
		sceneryElements[12][9] = new MapElement(12, 9, "none", "wall_rock", 0, false);
		sceneryElements[12][11] = new MapElement(12, 11, "none", "wall_rock", 0, false);
		sceneryElements[12][13] = new MapElement(12, 13, "none", "wall_rock", 0, false);
		sceneryElements[12][15] = new MapElement(12, 15, "none", "wall_rock", 0, false);
		sceneryElements[12][17] = new MapElement(12, 17, "none", "wall_rock", 0, false);
		sceneryElements[12][18] = new MapElement(12, 18, "none", "wall_rock", 0, false);
		sceneryElements[12][19] = new MapElement(12, 19, "none", "wall_rock", 0, false);
		sceneryElements[12][20] = new MapElement(12, 20, "none", "wall_rock", 0, false);
		sceneryElements[12][22] = new MapElement(12, 22, "none", "wall_rock", 0, false);
		sceneryElements[12][23] = new MapElement(12, 23, "none", "wall_rock", 0, false);
		sceneryElements[12][24] = new MapElement(12, 24, "none", "wall_rock", 0, false);
		sceneryElements[12][25] = new MapElement(12, 25, "none", "wall_rock", 0, false);
		sceneryElements[12][26] = new MapElement(12, 26, "none", "wall_rock", 0, false);
		sceneryElements[12][27] = new MapElement(12, 27, "none", "wall_rock", 0, false);
		sceneryElements[12][29] = new MapElement(12, 29, "none", "wall_rock", 0, false);
		sceneryElements[12][30] = new MapElement(12, 30, "none", "wall_rock", 0, false);
		sceneryElements[12][31] = new MapElement(12, 31, "none", "wall_rock", 0, false);
		sceneryElements[12][32] = new MapElement(12, 32, "none", "wall_rock", 0, false);
		sceneryElements[12][33] = new MapElement(12, 33, "none", "wall_rock", 0, false);
		
		
		
		// Guardamos el escenario del mapa.
		this.setSceneryElements(sceneryElements);
		
		
		// Inicializamos los elementos del mapa.
		ArrayList elements = new ArrayList();
		
		// Enemigos.
		elements.add(new MapElement(1, 1, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		elements.add(new MapElement(1, 4, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		elements.add(new MapElement(4, 21, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		elements.add(new MapElement(7, 15, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		elements.add(new MapElement(17, 6, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		elements.add(new MapElement(17, 25, "minotaur", "enemy_minotaur_stand_front", 1, 10, 2, 100, true, false, false, true, true));
		
		// Punto de salida.
		elements.add(new MapElement(this.getEndLocationX(), this.getEndLocationY(), "none", "end_level", 3, true));
		
		// Guardamos los elementos del mapa.
		this.setElements(elements);
	}
}
