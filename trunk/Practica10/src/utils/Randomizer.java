package utils;

import java.util.Random;

public class Randomizer {

	// Instancia de la clase.
	private static Randomizer instance;
	
	// Generador de aleatorios.
	private Random randomizer;
	
	// Constructor privado.
	private Randomizer() {
		randomizer = new Random();
	}
	
	// Obtiene la instancia
	public static Randomizer getInstance() {
		if(instance == null)
			instance = new Randomizer();
		return instance;
	}
	
	public int getRand(int min, int max) {
		int r = Math.abs(randomizer.nextInt());
		return (r % (max - min)) + min;
	}
}
