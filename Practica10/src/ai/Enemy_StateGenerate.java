package ai;

public class Enemy_StateGenerate extends State{

	// Instancia de la clase.
	private static Enemy_StateGenerate instance;
	
	// Constructor privado.
	private Enemy_StateGenerate() {
		this.name = "player_stateStand";
	}
	
	// Obtiene la instancia
	public static Enemy_StateGenerate getInstance() {
		if(instance == null)
			instance = new Enemy_StateGenerate();
		return instance;
	}

	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		
		// Pasamos al estado parado.
		o.stateMachine.changeState(Enemy_StateStand.getInstance(), o);
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}
}
