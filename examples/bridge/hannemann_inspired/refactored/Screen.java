package bridge.refactored;


public class Screen {
	Print p;
	void draw(){}
}

class WelcomeScreen extends Screen{
	
	void welcome(){
		p.welcomePrint();
	}
	
}

class InfoScreen extends Screen{
	
	void info(){
		p.infoPrint();
	}
	
}

interface Print{
	void welcomePrint();
	void infoPrint();
}

class TextImp implements Print{
	public void welcomePrint(){}
	public void infoPrint(){}
}

class GraphicImpl implements Print{
	public void welcomePrint(){}
	public void infoPrint(){}
}