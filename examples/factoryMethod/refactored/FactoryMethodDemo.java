package factoryMethod.refactored;

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

interface Creator{
	public JComponent create();
}

class ButtonCreatorImpl implements Creator{
	 public JButton create(){
	    	final JButton button = new JButton("Click me!");
	        button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button.setText("Thank you!");
				}
	        });
	        return button;
	    }
}

class LabelCreatorImpl implements Creator{
	public JLabel create(){
   	 JLabel label = new JLabel("This is a JLabel.");
     return label;
}
}

class GUIComponentCreator {
    
    
    private static Point lastFrameLocation = new Point(0, 0);

    public final void showFrame(String title, Creator creator) {
        JFrame frame = new JFrame(title);
        
   		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		    
		JPanel panel = new JPanel();
	
		panel.add(creator.create());
			
		frame.getContentPane().add(panel);
		frame.pack();    
		frame.setLocation(lastFrameLocation);
		lastFrameLocation.translate(75, 75);
		frame.setVisible(true);  
    }
}			

public class FactoryMethodDemo {

	public static void main(String[] args) {
        
        GUIComponentCreator creator1 = new GUIComponentCreator();
        GUIComponentCreator creator2 = new GUIComponentCreator();
        
        creator1.showFrame("Example 1: A JButton", new ButtonCreatorImpl());
        creator2.showFrame("Example 2: A JLabel", new LabelCreatorImpl());
	}
}