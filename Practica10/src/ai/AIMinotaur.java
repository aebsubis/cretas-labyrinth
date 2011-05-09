package ai;

import game.Element;

public class AIMinotaur extends AIObject{
	
	public AIMinotaur(double id, Element element) {
		super(id, element);
		this.stateMachine.setCurrentState(Minotaur_StateGenerate.getInstance());
	}
}
