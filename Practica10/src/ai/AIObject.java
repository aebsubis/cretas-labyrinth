package ai;

import game.Element;

public abstract class AIObject {

	public static final int PLAYER = 0;
	public static final int BUG = 1;
	public static final int SPIDER = 2;
	public static final int SNAKE = 3;
	public static final int MINOTAUR = 4;
	
	// Maquina de estados.
	protected StateMachine stateMachine;
	
	// Elemento al que representa.
	protected Element element;
	
	// Identificador del objeto.
	private double id;
	
	// Identificador del último objeto del que se recivió un mensaje.
	private double lastMessageRecivedEmisorId;	
	
	// Constructor sobrecargado.
	public AIObject(double id, Element element) {
		this.id = id;
		this.element = element;
		stateMachine = new StateMachine();
	}
	
	// Devuelve el id del objeto.
	public double getId() {
		return id;
	}
	
	// Establece el identificador del emisor del último mensaje recibido.
	void setLastMessageRecivedEmisorId(double lastMessageRecivedEmisorId) {
		this.lastMessageRecivedEmisorId = lastMessageRecivedEmisorId;
	}
	
	// Devuelve el identificador del emisor del último mensaje recibido.
	double getLastMessageRecivedEmisorId() {
		return lastMessageRecivedEmisorId;
	}
	
	// Actualiza el objeto.
	public void update() {
		stateMachine.update(this);
	}
	
	// Se indica al objeto que meneje el mensaje.
	public boolean handleMessage(Message m) {
		return stateMachine.handleMessage(this, m);
	}
}
