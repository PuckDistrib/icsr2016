package decorator.intermediate;

class Factory{
	static I makeA(){
		return new A();
	}
	static IwithX makeAwithX(){
		return new AwithX();
	}
	static IwithY makeAwithY(){
		return new AwithY();
	}
	
}
interface I {
	public void doIt();
}
public class A implements I{
	public void doIt() { System.out.print( 'A' ); }
}
interface IwithX extends I{
	public void doX();
}
class AwithX implements IwithX {
	I a = Factory.makeA();
	public void doIt() { a.doIt();  doX(); }
	public void doX()  { System.out.print( 'X' ); }
}
interface IwithY extends I{
	public void doY();
}
class AwithY implements IwithY {
	I a = Factory.makeA();
	public void doIt() { a.doIt();  doY(); }
	public void doY()  { System.out.print( 'Y' ); }
}

class AwithXY implements IwithX {
	IwithX awithX = Factory.makeAwithX();
	private IwithY obj =  Factory.makeAwithY();
	
	public void doX(){
		awithX.doX();
	}
	
	public void doIt() {
		awithX.doIt();
		obj.doY();
	}  
}

class AwithYX implements IwithY {
	
	private IwithX obj = Factory.makeAwithX();
	IwithY awithY =  Factory.makeAwithY();
	
	public void doY(){
		awithY.doY();
	}
	
	public void doIt() {
		awithY.doIt();
		obj.doX();
	}  
}