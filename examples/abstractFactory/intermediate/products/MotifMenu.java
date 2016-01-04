package abstractFactory.intermediate.products;

import abstractFactory.intermediate.Widget;

public class MotifMenu implements Widget{
	public void draw() {
		System.out.println("MotifMenu");
	}

	public Widget create() {
		return new MotifMenu();
	}
}
