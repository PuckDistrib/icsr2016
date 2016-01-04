package prototype.pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

interface Stooge {
	Stooge clone();
	void slap_stick();
}

class Larry implements Stooge{

	public Stooge clone(){
		return new Larry();
	}
	public void slap_stick() {
		System.out.println("Larry: poke eyes");
	}
	
}

class Moe implements Stooge{
	public Stooge clone(){
		return new Moe();
	}
	public void slap_stick() {
		System.out.println("Moe: slap head");
	}
	
}

class Curly implements Stooge{
	public Stooge clone(){
		return new Curly();
	}
	public void slap_stick() {
		System.out.println("Curly: suffer abuse");
	}
}

class Factory{
	private static Stooge[] prototypes = new Stooge[]{new Larry(),
														new Moe(),
														new Curly()};
	public static Stooge make_stooge(int choice){
		return prototypes[choice].clone();
	}
}

public class PrototypeDemo{
	static List<Stooge> roles = new ArrayList<Stooge>();
	
	public static void main(String args[]){
		
		int choice;
		try {

			while(true){
				System.out.println("Larry(1) Moe(2) Curly(3) Go(0): ");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				choice = Integer.parseInt(bufferRead.readLine());


				if(choice == 0)
					break;
				else 
					roles.add(Factory.make_stooge(choice));
				
			}

			for(Stooge s: roles)
				s.slap_stick();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}