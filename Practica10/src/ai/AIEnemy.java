package ai;

import game.Element;

public class AIEnemy extends AIObject{
	
	public AIEnemy(double id, Element element) {
		super(id, element);
		this.stateMachine.setCurrentState(Enemy_StateGenerate.getInstance());
	}
}
