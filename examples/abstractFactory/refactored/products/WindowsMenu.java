package abstractFactory.refactored.products;

import abstractFactory.refactored.Widget;

public class WindowsMenu implements Widget{
	public void draw() {
		System.out.println("WindowsMenu");
	}

	public Widget create() {
		return new WindowsMenu();
	}
}