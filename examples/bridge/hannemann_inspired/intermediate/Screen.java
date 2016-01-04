package bridge.intermediate;


public class Screen {
	void draw(){}
}

class WelcomeScreenText extends Screen{
	
	Text t;
	
	void welcome(){
		t.welcomePrintText();
	}
	
}

class WelcomeScreenGraphic extends Screen{
	
	Graphic g;
	
	void welcome(){
		g.welcomePrintGraphic();
	}
	
}


class InfoScreenText extends Screen{
	
	Text t;
	
	
	void info(){
		t.infoPrintText();
	}
	
}

class InfoScreenGraphic extends Screen{
	Graphic g;

	void info(){
		g.infoPrintGraphic();
	}
}

interface Text{
	void welcomePrintText();
	void infoPrintText();
}
class TextImpl implements Text{
	public void welcomePrintText(){}
	public void infoPrintText(){}
}

interface Graphic{
	void welcomePrintGraphic();
	void infoPrintGraphic();
}

class GraphicImpl implements Graphic{
	public void welcomePrintGraphic(){}
	public void infoPrintGraphic(){}
}