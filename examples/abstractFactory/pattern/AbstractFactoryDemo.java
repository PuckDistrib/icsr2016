package abstractFactory.pattern;

interface Widget{
	void draw();
}

class MotifButton implements Widget{
	public void draw() {
		System.out.println("MotifButton");
	}
}

class MotifMenu implements Widget{
	public void draw() {
		System.out.println("MotifMenu");
	}
}


class WindowsButton implements Widget{
	public void draw() {
		System.out.println("WindowsButton");
	}
}

class WindowsMenu implements Widget{
	public void draw() {
		System.out.println("WindowsMenu");
	}
}

interface Factory{
	Widget create_button();
	Widget create_menu();
}

class MotifFactory implements Factory{

	public Widget create_button() {
		return new MotifButton();
	}

	public Widget create_menu() {
		return new MotifMenu();
	}

}

class WindowsFactory implements Factory{

	public Widget create_button() {
		return new WindowsButton();
	}

	public Widget create_menu() {
		return new WindowsMenu();
	}

}

public class AbstractFactoryDemo {

	public static boolean motif = true;
	static Factory factory;

	static void display_window_one(){
		Widget w[] = new Widget[]{
				factory.create_button(),
				factory.create_menu()
		};

		w[0].draw();
		w[1].draw();
	}

	static void display_window_two(){
		Widget w[] = new Widget[]{
				factory.create_menu(),
				factory.create_button()
		};

		w[0].draw();
		w[1].draw();
	}

	public static void main(String args[]){
		if(motif)
			factory = new MotifFactory();
		else
			factory = new WindowsFactory();
		
		Widget w = factory.create_button();
		
		w.draw();
		display_window_one();
		display_window_two();
	}
}
