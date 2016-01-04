package state.intermediate;

import java.io.*;

/*
 * Original Chain candidate
 */
/*
  class Chain {
 
	private int state;

	public Chain()     { state = 0; }
	public void pull() {
		if (state == 0) {
			state = 1;
			System.out.println( "   low speed" );
		} else if (state == 1) {
			state = 2;
			System.out.println( "   medium speed" );
		} else if (state == 2) {
			state = 3;
			System.out.println( "   high speed" );
		} else {
			state = 0;
			System.out.println( "   turning off" );
		}   
	}   
}
*/
/*
 * Chain candidate with method extracted
 */

interface LowI{
	public void pullLow(Chain c);
}
class Low implements LowI{
	public void pullLow(Chain c){
		c.setState(1);
		System.out.println( "   low speed" );
	}
}
interface MediumI{
	public void pullMedium(Chain c);
}
class Medium implements MediumI{
	public void pullMedium(Chain c){
		c.setState(2);
		System.out.println( "   medium speed" );
	}
}
interface HighI{
	public void pullHigh(Chain c);
}
class High implements HighI{
	public void pullHigh(Chain c){
		c.setState(3);
		System.out.println( "   high speed" );
	}
}

interface OffI{
	public void pullOff(Chain c);
}
class Off implements OffI{
	public void pullOff(Chain c){
		c.setState(4);
		System.out.println( "   turning off" );
		
	}
}

class Chain {
	
	private int state;

	public Chain(){ state = 0; }
	
	OffI off;
	LowI low;
	MediumI medium;
	HighI high;
	
	void setState(int s){
		state=s;
	}
	public void pull() {
		if (state == 0) {
			low.pullLow(this);
		} else if (state == 1) {
			medium.pullMedium(this);
		} else if (state == 2) {
			high.pullHigh(this);
		} else {
			off.pullOff(this);
		}   
	}   
}

public class StateDemo {
	public static void main( String[] args ) throws IOException {
		InputStreamReader is = new InputStreamReader( System.in );
		int ch;
		Chain chain = new Chain();
		chain.off = new Off();
		chain.low = new Low();
		chain.medium = new Medium();
		chain.high = new High();
		while (true) {
			System.out.print( "Press <Enter>" );
			ch = is.read();    ch = is.read();
			chain.pull();
		}   
	}   
}