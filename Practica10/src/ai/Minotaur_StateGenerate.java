package ai;

public class Minotaur_StateGenerate extends State{

	// Instancia de la clase.
	private static Minotaur_StateGenerate instance;
	
	// Constructor privado.
	private Minotaur_StateGenerate() {
		this.name = "minotaur_stateGenerate";
	}
	
	// Obtiene la instancia
	public static Minotaur_StateGenerate getInstance() {
		if(instance == null)
			instance = new Minotaur_StateGenerate();
		return instance;
	}

	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		
		// Pasamos al estado parado.
		o.stateMachine.changeState(Minotaur_StateStand.getInstance(), o);
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}
}
