package prototype.candidate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

interface Actor {
	void act();
}

class Comedian implements Actor{

	public Comedian(){}

	public void act() {
		System.out.println("Something funny");
	}
}

class Tragedian implements Actor{

	public void act() {
		System.out.println("Something sad");
	}
	
}

class Extra implements Actor{

	public void act() {
		System.out.println("Something in the background");
	}
}

public class PrototypeDemo{
	
	static List<Actor> roles = new ArrayList<Actor>();
	
	public static void makeMovie(int choice){
		
		if(choice == 1)
			roles.add(new Comedian());
		else if(choice == 2)
			roles.add(new Tragedian());
		else
			roles.add(new Extra());
	}
	
	public static void main(String args[]){
		
		try {
			
			int choice;
			
			while(true){
				System.out.println("Comedian(1) Tragedian(2) Extra(3) Go(0): ");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				choice = Integer.parseInt(bufferRead.readLine());
				
				if(choice == 0)
					break;
				makeMovie(choice);
			}
			
			for(Actor s: roles)
				s.act();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}