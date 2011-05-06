package ai;

import game.Element;

public class AIPlayer extends AIObject{

	public AIPlayer(double id, Element element) {
		super(id, element);
		this.stateMachine.setCurrentState(Player_StateGenerate.getInstance());
	}
}
