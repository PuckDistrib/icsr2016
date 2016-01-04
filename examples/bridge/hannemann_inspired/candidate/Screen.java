package bridge.candidate;


public class Screen {
	void draw(){}
}

class WelcomeScreenText extends Screen{
	
	void welcome(){
		printText();
	}
	void printText(){}
}

class WelcomeScreenGraphic extends Screen{
	
	void welcome(){
		printGraphic();
	}
	void printGraphic(){}
}


class InfoScreenText extends Screen{
	
	void info(){
		printText();
	}
	void printText(){}
}

class InfoScreenGraphic extends Screen{
	
	void info(){
		printGraphic();
	}
	void printGraphic(){}
}