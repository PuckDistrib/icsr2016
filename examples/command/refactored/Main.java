package command.refactored;

/* -*- Mode: Java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This file is part of the design patterns project at UBC
 *
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * either http://www.mozilla.org/MPL/ or http://aspectj.org/MPL/.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is ca.ubc.cs.spl.aspectPatterns.
 * 
 * For more details and the latest version of this code, please see:
 * http://www.cs.ubc.ca/labs/spl/projects/aodps.html
 *
 * Contributor(s):   
 */
 
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Button extends JButton {

    /**
     * Creates a new button with the provided label
     *
     * @param label the label of the button
     */

	public Button(String label, Action act) {
		super(label);
		this.setActionCommand(label);
		
		final Action action = act;
		
		this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					action.clicked(); 
				}
			}); 
	}

}
interface Action{
	public void clicked();
}

class Action1 implements Action {
	
	public void clicked() {
		System.out.println("ButtonCommand executed");
	}  
}

class Action2 implements Action {

	public void clicked() {
		System.out.println("ButtonCommand number 2 executed");
	}  
}


public class Main {
	
    /**
     * This example creates a simple GUI with three buttons. Each button has a 
     * command associated with it that is executed when the button is pressed. 
     * Button1 and button3 have the same command, button2 has a different one.
     */
	
	public static void main(String[] args) {  
	    
    	Button button1 = new Button("Print Date", new Action1());
    	Button button2 = new Button("Command 1", new Action2());
    	Button button3 = new Button("Command 2", new Action1()); 
    	
		JPanel pane = new JPanel();
		pane.add(button1);  
		
		pane.add(button2);
		
		pane.add(button3);
		
		// Note: Can not have two commands.
		// That is within the pattern specification

        JFrame frame = new JFrame("Command Pattern Example");		
		
		frame.getContentPane().add(pane);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});  
		frame.pack();
		frame.setVisible(true);
	}
}
