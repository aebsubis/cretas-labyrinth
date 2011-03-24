import gui.GUIHandler;
import gui.GUIScreens;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import utils.ResourcesHandler;

/**
 * 
 * @author Daniel
 * Clase de entrada de la aplicación.
 */
public class Practica10 extends MIDlet {
	
	// Constructor privado.
	public Practica10() {
		
	}
	
	// Detiene la aplicación.
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	// Pausa la aplicación.
	protected void pauseApp() {
	}

	// Inicia la aplicación.
	protected void startApp() throws MIDletStateChangeException {
		
		try {
		// Pedir a la capa de persistencia el último idioma preferido seleccionado.
		//
		
		// Inicializamos los recursos.
		ResourcesHandler.getInstance().init();
		
		// Inicializamos la interfaz gráfica.
		GUIHandler.getInstance().init(this);
		
		// Mostramos la pantalla inicial.
		GUIHandler.getInstance().showScreen(GUIScreens.STARTSCREEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try
	      {
	         destroyApp(true);
	         notifyDestroyed();
	      }

	      catch (MIDletStateChangeException e)
	      { }
	}

}
