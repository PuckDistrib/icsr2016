package adapter.intermediate;

//Refactoring restriction : LegacyLine and LegacyRectangle cannot be changed

interface LineI{
	public void drawLine(int x, int y, int x2, int y2);
}

class NewLine implements LineI{
	LegacyLine line = new LegacyLine();
	public void drawLine(int x1, int y1, int x2, int y2 ){
		line.draw( x1, y1, x2, y2 );
	}
}

interface RectangleI{
	public void drawRectangle(int x1, int y1, int x2, int y2 );
}

class NewRectangle implements RectangleI{
	LegacyRectangle rect = new LegacyRectangle();
	public void drawRectangle(int x1, int y1, int x2, int y2 ){
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
		Object[] shapes = { new NewLine(),
							new NewRectangle() };
		// A begin and end point from a graphical editor
		int x1 = 10, y1 = 20;
		int x2 = 30, y2 = 60;
		for (int i=0; i < shapes.length; ++i)
			if (shapes[i] instanceof NewLine)
				((LineI) shapes[i]).drawLine(x1, y1, x2, y2 );
			else if (shapes[i] instanceof NewRectangle)
				((RectangleI) shapes[i]).drawRectangle(x1, y1, x2, y2 );
	}  	
}
