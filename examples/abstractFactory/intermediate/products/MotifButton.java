package abstractFactory.intermediate.products;

import abstractFactory.intermediate.Widget;

public class MotifButton implements Widget{
	public void draw() {
		System.out.println("MotifButton");
	}

	public Widget create() {
		return new MotifButton();
	}
}