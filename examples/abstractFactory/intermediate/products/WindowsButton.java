package abstractFactory.intermediate.products;

import abstractFactory.intermediate.Widget;

public class WindowsButton implements Widget{
	public void draw() {
		System.out.println("WindowsButton");
	}

	public Widget create() {
		return new WindowsButton();
	}
}