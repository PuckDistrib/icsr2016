package prototype.refactored.actors;

import prototype.refactored.Actor;


public class Extra implements Actor{

	public Extra create(){
		return new Extra();
	}
	public void act() {
		System.out.println("Something in the background");
	}
}