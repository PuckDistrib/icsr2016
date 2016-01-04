package abstractFactory.refactored.products;

import abstractFactory.refactored.Widget;

public class WindowsButton implements Widget{
	public void draw() {
		System.out.println("WindowsButton");
	}

	public Widget create() {
		return new WindowsButton();
	}
}