package abstractFactory.refactored;

import abstractFactory.refactored.products.*;

interface Factory {
	Widget createButton();
	Widget createMenu();
}

class WindowsFactory implements Factory{

	static Widget windowsButton = new WindowsButton();
	static Widget windowsMenu = new WindowsMenu();

	public Widget createButton() {
		return windowsButton.create();
	}

	public Widget createMenu() {
		return windowsMenu.create();
	}
}

class MotifFactory implements Factory{

	static Widget motifButton = new MotifButton();
	static Widget motifMenu = new MotifMenu();
	
	public Widget createButton() {
		return motifButton.create();
	}

	public Widget createMenu() {
		return motifMenu.create();
	}
}

public class AbstractFactoryDemo {
	
	public static Factory factory;
	public static boolean motif = true;
	
	static void display_window_one(){
		Widget w[] = new Widget[2];
		w[0] = factory.createButton();
		w[1] = factory.createMenu(); 
		
		w[0].draw();
		w[1].draw();
	}
	
	static void display_window_two(){
		Widget w[] = new Widget[2];
		w[0] = factory.createMenu(); 
		w[1] = factory.createButton();

		w[0].draw();
		w[1].draw();
	}
	
	public static void main(String args[]){
		Widget w;
		if(motif)
			factory = new MotifFactory();
		else
			factory = new WindowsFactory();

		w = factory.createButton();
		
		w.draw();
		display_window_one();
		display_window_two();
	}
}
