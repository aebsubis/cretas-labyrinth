package maps;

public class MapElement {
	
	// Localización en la ordenada X del elemento.
	private int elementXLocation;

	// Localización en la ordenada Y del elemento.
	private int elementYLocation;
	
	// Tipo AI del elemento.
	private String elementAIType;
	
	// Tipo gráfico del elemento.
	private String elementGFXType;
	
	// Profundiad del elemento.
	private int elementDepth;

	// Vidas del elemento.
	private int elementLives;
	
	// Velocidad del elemento.
	private int elementSpeed;
	
	// Stamina del elemento;
	private int elementStamina;
	
	// Indica si se puede mover el elemento.
	private boolean elementMovable;
	
	// Indica si se puede llevar el elemento.
	private boolean elementPortable;
	
	// Indica si se puede usar el elemento.
	private boolean elementUsable;
	
	// Indica si es pasable el elemento.
	private boolean elementPasable;

	// Indica si es enemigo el elemento.
	private boolean elementEnemy;
	
	// Constructor por defecto.
	MapElement(){
		elementXLocation = 0;
		elementYLocation = 0;
		elementAIType = "none";
		elementGFXType = "none";
		elementDepth = 0;
		elementLives = 0;
		elementSpeed = 0;
		elementStamina = 0;
		elementMovable = false;
		elementPortable = false;
		elementUsable = false;
		elementPasable = false;
		elementEnemy = false;
	}
	
	// Constructor sobrecargado.
	MapElement(int elementXLocation, int elementYLocation, String elementAIType, String elementGFXType, int elementDepth, boolean elementPasable){
		this.elementXLocation = elementXLocation;
		this.elementYLocation = elementYLocation;
		this.elementAIType = elementAIType;
		this.elementGFXType = elementGFXType;
		this.elementDepth = elementDepth;
		this.elementLives = 0;
		this.elementSpeed = 0;
		this.elementStamina = 0;
		this.elementMovable = false;
		this.elementPortable = false;
		this.elementUsable = false;
		this.elementPasable = elementPasable;
		this.elementEnemy = false;
	}
	
	// Constructor sobrecargado.
	MapElement(int elementXLocation, int elementYLocation, String elementAIType, String elementGFXType, int elementDepth, int elementLives, int elementSpeed, int elementStamina, boolean elementMovable, boolean elementPortable, boolean elementUsable, boolean elementPasable, boolean elementEnemy){
		this.elementXLocation = elementXLocation;
		this.elementYLocation = elementYLocation;
		this.elementAIType = elementAIType;
		this.elementGFXType = elementGFXType;
		this.elementDepth = elementDepth;
		this.elementLives = elementLives;
		this.elementSpeed = elementSpeed;
		this.elementStamina = elementStamina;
		this.elementMovable = elementMovable;
		this.elementPortable = elementPortable;
		this.elementUsable = elementUsable;
		this.elementPasable = elementPasable;
		this.elementEnemy = elementEnemy;
	}
	
	public int getElementXLocation() {
		return elementXLocation;
	}

	public void setElementXLocation(int elementXLocation) {
		this.elementXLocation = elementXLocation;
	}

	public int getElementYLocation() {
		return elementYLocation;
	}

	public void setElementYLocation(int elementYLocation) {
		this.elementYLocation = elementYLocation;
	}

	public String getElementAIType() {
		return elementAIType;
	}

	public void setElementAIType(String elementAIType) {
		this.elementAIType = elementAIType;
	}

	public String getElementGFXType() {
		return elementGFXType;
	}

	public void setElementGFXType(String elementGFXType) {
		this.elementGFXType = elementGFXType;
	}

	public int getElementDepth() {
		return elementDepth;
	}

	public void setElementDepth(int elementDepth) {
		this.elementDepth = elementDepth;
	}

	public int getElementLives() {
		return elementLives;
	}

	public void setElementLives(int elementLives) {
		this.elementLives = elementLives;
	}

	public int getElementSpeed() {
		return elementSpeed;
	}

	public void setElementSpeed(int elementSpeed) {
		this.elementSpeed = elementSpeed;
	}

	public int getElementStamina() {
		return elementStamina;
	}

	public void setElementStamina(int elementStamina) {
		this.elementStamina = elementStamina;
	}

	public boolean isElementMovable() {
		return elementMovable;
	}

	public void setElementMovable(boolean elementMovable) {
		this.elementMovable = elementMovable;
	}

	public boolean isElementPortable() {
		return elementPortable;
	}

	public void setElementPortable(boolean elementPortable) {
		this.elementPortable = elementPortable;
	}

	public boolean isElementUsable() {
		return elementUsable;
	}

	public void setElementUsable(boolean elementUsable) {
		this.elementUsable = elementUsable;
	}

	public boolean isElementPasable() {
		return elementPasable;
	}

	public void setElementPasable(boolean elementPasable) {
		this.elementPasable = elementPasable;
	}

	public boolean isElementEnemy() {
		return elementEnemy;
	}

	public void setElementEnemy(boolean elementEnemy) {
		this.elementEnemy = elementEnemy;
	}
}
