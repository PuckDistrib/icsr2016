package bridge1.pattern;

import java.util.Date;

// Hannemann example

class Screen {
    
    private ScreenImplementation implementor;  
    
    public Screen(ScreenImplementation implementor) {
        this.implementor = implementor;
    }
    
    public void drawText(String text) {
        implementor.printText(text);
        implementor.printLine();
    }
    
    public void drawTextBox(String text) {

        int length = text.length();

        for(int i=0; i<length+4; i++) {
            implementor.printDecor();
        }

        implementor.printLine();
        implementor.printDecor();
        implementor.printText(" "+text+" ");
        implementor.printDecor();
        implementor.printLine();
        
        for(int i=0; i<length+4; i++) {
            implementor.printDecor();
        }

        implementor.printLine(); 
    }
}

interface ScreenImplementation {
    
    void printLine();
    
    void printDecor();
    
    void printText(String text); 
}
 
class CrossCapitalImplementation implements ScreenImplementation {

    
    public void printLine() {
        System.out.println();
    }
    
    public void printDecor() {
        System.out.print("X");
    }
    
    public void printText(String text) {
        System.out.print(text.toUpperCase());
    }
}

class StarImplementation implements ScreenImplementation {
    
    public void printLine() {
        System.out.println();
    }
    
    public void printDecor() {
        System.out.print("*");
    }
    
    public void printText(String text) {
        System.out.print(text);
    }
}
 
class GreetingScreen extends Screen {
    
    public GreetingScreen(ScreenImplementation si) {
        super(si);
    }
    
    public void drawGreeting() {
        drawTextBox("Greetings!");
    }
}

class InformationScreen extends Screen { 
    
    public InformationScreen(ScreenImplementation si) {
        super(si);
    }

    
    public void drawInfo() {  
        Date date = new Date();
        drawTextBox("Current system time: "+date);
    }
}

public class BridgeDemo {

    public static void main(String[] args) { 
        
        System.out.println("Creating implementations...");
    
        ScreenImplementation i1 = new StarImplementation();
        ScreenImplementation i2 = new CrossCapitalImplementation();
        
        System.out.println("Creating abstraction (screens) / implementation combinations...");
        
        GreetingScreen gs1 = new GreetingScreen(i1);
        GreetingScreen gs2 = new GreetingScreen(i2);
        InformationScreen is1 = new InformationScreen(i1);
        InformationScreen is2 = new InformationScreen(i2);  
        
        System.out.println("Starting test:\n");
        
        gs1.drawText("\nScreen 1 (Refined Abstraction 1, Implementation 1):");
        gs1.drawGreeting();
        
        gs2.drawText("\nScreen 2 (Refined Abstraction 1, Implementation 2):");
        gs2.drawGreeting();
        
        is1.drawText("\nScreen 3 (Refined Abstraction 2, Implementation 1):");
        is1.drawInfo();

        is2.drawText("\nScreen 4 (Refined Abstraction 2, Implementation 2):");
        is2.drawInfo();
    }
}
