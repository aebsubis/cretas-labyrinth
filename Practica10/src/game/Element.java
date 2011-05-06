package game;

import utils.Location2D;

/**
 * 
 * @author Daniel
 * 
 */
public class Element extends GameObject{
	
	// Vidas del elemento.
	private int lives;
	
	// Velocidad del elemento.
	private int speed;
	
	// Stamina del elemento.
	private int stamina;
	
	// Indica si se puede mover.
	private boolean isMovable;
	
	// Dirección en la que se mueve.
	private int movementDirection;
	
	// Indica si se puede llevar.
	private boolean isPortable;
	
	// Indica si se puede usar.
	private boolean isUsable;
	
	// Indica si se puede atravesar.
	private boolean isPassable;
	
	// Indica si el elemento es enemigo.
	private boolean isEnemy;
	
	// Constructor por defecto.
	public Element() {
		
		// Llamamos al constructor de la clase padre.
		super();
		
		// Establecemos los atributos.
		this.lives = 0;
		this.speed = 0;
		this.stamina = 0;
		this.isMovable = false;
		this.movementDirection = Direction.DOWN;
		this.isPortable = false;
		this.isUsable = false;
		this.isPassable = false;
		this.isEnemy = false;
	}
	
	// Constructor sobrecargado.
	public Element(double id, Location2D location, int lives, int speed, int stamina, boolean movable, boolean portable, boolean usable, boolean passable, boolean enemy) {

		// Llamamos al constructor de la clase padre.
		super(id, location);
		
		// Establecemos los atributos.
		this.lives = lives;
		this.speed = speed;
		this.stamina = stamina;
		this.isMovable = movable;
		this.movementDirection = Direction.DOWN;
		this.isPortable = portable;
		this.isUsable = usable;
		this.isPassable = passable;
		this.isEnemy = enemy;
	}

	// Obtiene las vidas restantes.
	public int getLives() {
		return lives;
	}

	// Establece el número de vidas.
	public void setLives(int lives) {
		this.lives = lives;
	}
		
	// Obtiene la velocidad.
	public int getSpeed() {
		return speed;
	}
	
	// Establece la velocidad.
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// Obtiene la stamina.
	public int getStamina() {
		return stamina;
	}
	
	// Establece la stamina.
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	// Indica si se puede mover.
	public boolean isMovable() {
		return isMovable;
	}
	
	// Establece si el objeto puede moverse.
	public void setMovable(boolean movable) {
		isMovable = movable;
	}
	
	// Devuelve la dirección de movimiento.
	public int getMovementDirection() {
		return movementDirection;
	}
	
	// Establece la dirección del movimiento.
	public void setMovementDirection(int direction) {
		movementDirection = direction;
	}
	
	// Indica si se puede llevar.
	public boolean isPortable() {
		return isPortable;
	}
	
	// Establece si el objeto se puede llevar.
	public void setPortable(boolean portable) {
		isPortable = portable;
	}
	
	// Indica si se puede usar.
	public boolean isUsable() {
		return isUsable;
	}
	
	// Establece si el objeto puede usarse.
	public void setUsable(boolean usable) {
		isUsable = usable;
	}
	
	// Indica si se puede atravesar.
	public boolean isPassable() {
		return isPassable;
	}
	
	// Establece si el objeto puede atravesarse.
	public void setPassable(boolean passable) {
		isPassable = passable;
	}
	
	// Indica si el elemento es enemigo.
	public boolean isEnemy() { 
		return isEnemy;
	}
	
	// Establece si el objeto es enemigo.
	public void setEnemy(boolean enemy) {
		isEnemy = enemy;
	}
}
