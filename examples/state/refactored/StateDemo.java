package state.refactored;

import java.io.*;

/*
 * code after merge but before replace conditionnal with polymorphism
 */
/*
interface Speed{
	public void pull(Chain c);
}
class Low implements Speed{
	public void pull(Chain c){
		c.setState(1);
		System.out.println( "   low speed" );
	}
}

class Medium implements Speed{
	public void pull(Chain c){
		c.setState(2);
		System.out.println( "   medium speed" );
	}
}

class High implements Speed{
	public void pull(Chain c){
		c.setState(3);
		System.out.println( "   high speed" );
	}
}


class Off implements Speed{
	public void pull(Chain c){
		c.setState(4);
		System.out.println( "   turning off" );
		
	}
}

class Chain {
	
	private int state;

	public Chain(){ state = 0; }
	
	Speed off;
	Speed low;
	Speed medium;
	Speed high;
	
	void setState(int s){
		state=s;
	}
	public void pull() {
		if (state == 0) {
			low.pull(this);
		} else if (state == 1) {
			medium.pull(this);
		} else if (state == 2) {
			high.pull(this);
		} else {
			off.pull(this);
		}   
	}   
}
*/

/*
 * code after replace conditionnal with polymorphism
 */

interface Speed{
	public void pull(Chain c);
}
class Low implements Speed{
	public void pull(Chain c){
		c.setState(new Medium());
		System.out.println( "   low speed" );
	}
}

class Medium implements Speed{
	public void pull(Chain c){
		c.setState(new High());
		System.out.println( "   medium speed" );
	}
}

class High implements Speed{
	public void pull(Chain c){
		c.setState(new Off());
		System.out.println( "   high speed" );
	}
}


class Off implements Speed{
	public void pull(Chain c){
		c.setState(new Low());
		System.out.println( "   turning off" );
		
	}
}

class Chain {
	
	private Speed state;

	public Chain(){ }
	
	void setState(Speed s){
		state=s;
	}
	public void pull() {
		state.pull(this);
	}   
}
public class StateDemo {
	public static void main( String[] args ) throws IOException {
		InputStreamReader is = new InputStreamReader( System.in );
		int ch;
		Chain chain = new Chain();
		chain.setState(new Low());
		while (true) {
			System.out.print( "Press <Enter>" );
			ch = is.read();ch = is.read();
			chain.pull();
		}   
	}   
}