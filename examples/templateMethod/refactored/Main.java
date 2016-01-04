package templateMethod.refactored;

interface Decorator {
	public String prepare  (String s);
	public String filter   (String s);
	public String finalize (String s);
}

class SimpleDecoratorImpl implements Decorator{
 	public String prepare  (String s) {
		return s;
	}
	
  	public String filter   (String s) {
		return s;
	}
	
 	public String finalize (String s) {
		return s+".";
	}
}

class FancyDecoratorImpl implements Decorator{
	public String prepare (String s) {
		return s.toLowerCase();
	}
	
 	public String filter   (String s) {
		s = s.replace('a', 'A'); 
		s = s.replace('e', 'E');
		s = s.replace('i', 'I'); 
		s = s.replace('o', 'O');
		s = s.replace('u', 'U');
		return s; 
	}
		
 	public String finalize (String s) {
		return (s+".\n(all consonants capitalized)");
	}
}

class DecoratedStringGenerator { 
    
	private Decorator style;
	
	public DecoratedStringGenerator(Decorator style){
		this.style = style;
	}
	
	public String generate(String s) {
		
		s = style.prepare(s);
		s = style.filter(s);
		s = style.finalize(s);
		
		return s;
	}
	                        
}	

public class Main {

   
	public static void main(String[] args) {
		String original = "This Is The Original String To Be Processed";
		
		DecoratedStringGenerator c1 = new DecoratedStringGenerator(new SimpleDecoratorImpl());
		DecoratedStringGenerator c2 = new DecoratedStringGenerator(new FancyDecoratorImpl());
		
		System.out.println("<Original>");
		System.out.println(original);
		System.out.println("<SimpleGenerator>");
		System.out.println(c1.generate(original));
		System.out.println("<FancyGenerator>");
		System.out.println(c2.generate(original));
	}
}