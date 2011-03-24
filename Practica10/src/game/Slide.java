package game;

/**
 * 
 * @author Daniel
 *
 */
public class Slide {

	// Identificador del recurso de imagen.
	private String image;
	
	// Texto de la diapositiva.
	private String text;
	
	// Constructor por defecto.
	public Slide() {
		this.image = "start_splash";
		this.text = "game_title";
	}
	
	// Obtiene la imagen.
	public String getImage() {
		return this.image;
	}
	
	// Establece la imagen.
	public void setImage(String image) {
		this.image = image;
	}
	
	// Obtiene el texto.
	public String getText() {
		return this.text;
	}
	
	// Establece el texto.
	public void setText(String text) {
		this.text = text;
	}
}
