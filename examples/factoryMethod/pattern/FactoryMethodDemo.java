package factoryMethod.pattern;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * From hannemann
 *
 */

abstract class GUIComponentCreator {
    
    public abstract JComponent createComponent(); 
    
    public abstract String getTitle(); 

    private static Point lastFrameLocation = new Point(0, 0);

    public final void showFrame() {
        JFrame frame = new JFrame(getTitle());
        
   		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		    
		JPanel panel = new JPanel();
	
		panel.add(createComponent());
		
		frame.getContentPane().add(panel);
		frame.pack();    
		frame.setLocation(lastFrameLocation);
		lastFrameLocation.translate(75, 75);
		frame.setVisible(true);  
    }
}		

class LabelCreator extends GUIComponentCreator {

    public JComponent createComponent() {
        JLabel label = new JLabel("This is a JLabel.");
        return label;            
    }
    
    public String getTitle() {
        return "Example 2: A JLabel";
    }
}

class ButtonCreator extends GUIComponentCreator {

    public JComponent createComponent() {
        final JButton button = new JButton("Click me!");
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setText("Thank you!");
			}
        });
        return button;            
    }
    
    public String getTitle() {
        return "Example 1: A JButton";
    }
}	

public class FactoryMethodDemo {

	static void client(GUIComponentCreator creator1, GUIComponentCreator creator2){
        creator1.showFrame();
        creator2.showFrame();
	}
	
    public static void main(String[] args) {
        
        GUIComponentCreator creator1 = new ButtonCreator();
        GUIComponentCreator creator2 = new LabelCreator();
        
        client(creator1, creator2);
    }
}