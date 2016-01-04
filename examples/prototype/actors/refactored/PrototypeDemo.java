package prototype.refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import prototype.refactored.actors.Comedian;
import prototype.refactored.actors.Extra;
import prototype.refactored.actors.Tragedian;


public class PrototypeDemo{
	
	static List<Actor> roles = new ArrayList<Actor>();
	
	public static void makeMovie(Actor choice){
		roles.add(choice.create());
		
	}
	
	public static void main(String args[]){
		
		try {
			
			int choice;
			
			while(true){
				System.out.println("Comedian(1) Tragedian(2) Extra(3) Go(0): ");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				choice = Integer.parseInt(bufferRead.readLine());
				
				Actor larry  = new Comedian();
				Actor moe = new Tragedian();
				Actor curly = new Extra();
				Actor s;
				
				if(choice == 0)
					break;
				if(choice == 1)
					s = larry;
				else if(choice == 2)
					s = moe;
				else
					s = curly;

				makeMovie(s);

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