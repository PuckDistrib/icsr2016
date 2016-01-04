package decorator.refactored;

//delete XY YX IwithX IwithY

class Factory{
	static I makeA(){
		return new A();
	}
	static I makeAwithX(){
		return new AwithX();
	}
	static I makeAwithY(){
		return new AwithY();
	}
	
}
interface I {
	public void doIt();
}
public class A implements I{
	public void doIt() { System.out.print( 'A' ); }
}

class AwithX implements I {
	I a = Factory.makeA();
	public void doIt() { a.doIt();  doX(); }
	public void doX()  { System.out.print( 'X' ); }
}
class AwithY implements I {
	I a = Factory.makeA();
	public void doIt() { a.doIt();  doY(); }
	public void doY()  { System.out.print( 'Y' ); }
}
