package prototype.refactored.actors;

import prototype.refactored.Actor;

public class Comedian implements Actor{

	public Comedian create(){
		return new Comedian();
	}
	public void act() {
		System.out.println("Something funny");
	}
}