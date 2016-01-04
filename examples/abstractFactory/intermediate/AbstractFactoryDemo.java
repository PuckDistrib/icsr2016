package abstractFactory.intermediate;

import abstractFactory.intermediate.products.*;

public class AbstractFactoryDemo {
	
	static Widget motifButton = new MotifButton();
	static Widget motifMenu = new MotifMenu();
	
	static Widget windowsButton = new WindowsButton();
	static Widget windowsMenu = new WindowsMenu();
	
	public static boolean motif = true;
	
	static void display_window_one(){
		Widget w[] = new Widget[2];
		if(motif){
			w[0] = motifButton.create();
			w[1] = motifMenu.create();		
		}
		else{
			w[0] = windowsButton.create();
			w[1] = windowsMenu.create();	
		}
		w[0].draw();
		w[1].draw();
	}
	
	static void display_window_two(){
		Widget w[] = new Widget[2];
		if(motif){
			w[0] = motifMenu.create();
			w[1] = motifButton.create();
		}
		else{
			w[0] = windowsMenu.create();	
			w[1] = windowsButton.create();
		}
		w[0].draw();
		w[1].draw();
	}
	
	public static void main(String args[]){
		Widget w;
		if(motif)
			w = motifButton.create();
		else
			w = windowsButton.create();
		
		w.draw();
		display_window_one();
		display_window_two();
	}
}
