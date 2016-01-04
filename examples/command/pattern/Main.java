package command.pattern;

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

interface Command {
    
    public void executeCommand();
}

class Button extends JButton {

	protected Command command;
	
	public Button(String label) {
		super(label);
		this.setActionCommand(label);
		this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clicked(); 
				}
			}); 
	}

	public void clicked() {
		if (command != null) {
			command.executeCommand();
		}
	}
	
	public void setCommand(Command command)	{
		this.command = command;
	}  
}

class ButtonCommand implements Command {
	
	public void executeCommand() {
		System.out.println("ButtonCommand executed");
	}  
}

class ButtonCommand2 implements Command {

	public void executeCommand() {
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
	    
    	Button button1 = new Button("Print Date");
    	Button button2 = new Button("Command 1");
    	Button button3 = new Button("Command 2"); 
    	
    	Command com1 = new ButtonCommand();
    	Command com2 = new ButtonCommand2();

		JPanel pane = new JPanel();
		pane.add(button1);  
		button1.setCommand(com1);
		
		pane.add(button2);
		button2.setCommand(com2);
		
		pane.add(button3);
		button3.setCommand(com1);
		
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
