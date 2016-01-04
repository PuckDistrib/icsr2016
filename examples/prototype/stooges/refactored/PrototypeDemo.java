package prototype.refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

interface Stooge {
	void slap_stick();
	Stooge create();
}

class Larry implements Stooge{

	public void slap_stick() {
		System.out.println("Larry: poke eyes");
	}
	public Stooge create(){
		return new Larry();
	}
}

class Moe implements Stooge{

	public void slap_stick() {
		System.out.println("Moe: slap head");
	}
	public Stooge create(){
		return new Moe();
	}
	
}

class Curly implements Stooge{

	public void slap_stick() {
		System.out.println("Curly: suffer abuse");
	}
	public Stooge create(){
		return new Curly();
	}
}

public class PrototypeDemo{
	
	static List<Stooge> roles = new ArrayList<Stooge>();
	
	//user_creator needs to create NEW instances 
	public static void user_creator(Stooge choice){
		roles.add(choice.create());
		
	}
	
	public static void main(String args[]){
		
		try {
			
			int choice;
			
			while(true){
				System.out.println("Larry(1) Moe(2) Curly(3) Go(0): ");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				choice = Integer.parseInt(bufferRead.readLine());
				
				Stooge larry  = new Larry();
				Stooge moe = new Moe();
				Stooge curly = new Curly();
				Stooge s;
				
				if(choice == 0)
					break;
				if(choice == 1)
					s = larry;
				else if(choice == 2)
					s = moe;
				else
					s = curly;

				user_creator(s);

			}
			
			for(Stooge s: roles)
				s.slap_stick();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}