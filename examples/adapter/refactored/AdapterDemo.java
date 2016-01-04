package adapter.refactored;

//Refactoring restriction : LegacyLine and LegacyRectangle cannot be changed

interface Shape{
	public void draw(int x, int y, int x2, int y2);
}

class NewLine implements Shape{
	LegacyLine line = new LegacyLine();
	public void draw(int x1, int y1, int x2, int y2 ){
		line.draw( x1, y1, x2, y2 );
	}
}

class NewRectangle implements Shape{
	LegacyRectangle rect = new LegacyRectangle();
	public void draw(int x1, int y1, int x2, int y2 ){
		rect.draw(Math.min(x1,x2), Math.min(y1,y2),
					Math.abs(x2-x1), Math.abs(y2-y1));
	}

}

class LegacyLine {
	public void draw(int x1, int y1, int x2, int y2 ) {
		System.out.println( "line from (" + x1 + ',' + y1
				+ ") to (" + x2 + ',' + y2 + ')' );
	}  
}

class LegacyRectangle {
	public void draw(int x, int y, int w, int h ) {
		System.out.println( "rectangle at (" + x + ',' + y
				+ ") with width " + w + " and height " + h );
	}  
}

public class AdapterDemo {
	
	public static void main( String[] args ) {
		Shape[] shapes = { new NewLine(),
							new NewRectangle() };
		// A begin and end point from a graphical editor
		int x1 = 10, y1 = 20;
		int x2 = 30, y2 = 60;
		for (int i=0; i < shapes.length; ++i)
			shapes[i].draw(x1, y1, x2, y2 );
	}  	
}
