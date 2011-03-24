import gui.GUIHandler;
import gui.GUIScreens;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import utils.ResourcesHandler;

/**
 * 
 * @author Daniel
 * Clase de entrada de la aplicaci�n.
 */
public class Practica10 extends MIDlet {
	
	// Constructor privado.
	public Practica10() {
		
	}
	
	// Detiene la aplicaci�n.
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {

	}

	// Pausa la aplicaci�n.
	protected void pauseApp() {
	}

	// Inicia la aplicaci�n.
	protected void startApp() throws MIDletStateChangeException {
		
		try {
		// Pedir a la capa de persistencia el �ltimo idioma preferido seleccionado.
		//
		
		// Inicializamos los recursos.
		ResourcesHandler.getInstance().init();
		
		// Inicializamos la interfaz gr�fica.
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
