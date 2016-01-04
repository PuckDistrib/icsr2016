package abstractFactory.refactored.products;

import abstractFactory.refactored.Widget;

public class MotifMenu implements Widget{
	public void draw() {
		System.out.println("MotifMenu");
	}

	public Widget create() {
		return new MotifMenu();
	}
}
