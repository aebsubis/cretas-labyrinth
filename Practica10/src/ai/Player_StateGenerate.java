package ai;

public class Player_StateGenerate extends State{
	// Instancia de la clase.
	private static Player_StateGenerate instance;
	
	// Constructor privado.
	private Player_StateGenerate() {
		this.name = "player_stateGenerate";
	}
	
	// Obtiene la instancia
	public static Player_StateGenerate getInstance() {
		if(instance == null)
			instance = new Player_StateGenerate();
		return instance;
	}
	
	// Acciones a ejecutar al entrar al estado.
	public void enter(AIObject o) {
	}
	
	// Acciones a ejecutar mientras se permanece en el estado.
	public void execute(AIObject o) {
		
		// Pasamos al estado parado.
		o.stateMachine.changeState(Player_StateStand.getInstance(), o);
	}
	
	// Acciones a ejecutar al salir del estado.
	public void exit(AIObject o){
		
	}
}
