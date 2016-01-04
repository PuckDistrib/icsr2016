package prototype.refactored.actors;

import prototype.refactored.Actor;

public class Tragedian implements Actor{

	public Tragedian create(){
		return new Tragedian();
	}
	public void act() {
		System.out.println("Something sad");
	}
	
}