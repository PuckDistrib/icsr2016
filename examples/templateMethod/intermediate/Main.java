package templateMethod.intermediate;

interface SimpleDecorator {
	public String prepareSimple  (String s);
	public String filterSimple   (String s);
	public String finalizeSimple (String s);
}

class SimpleDecoratorImpl implements SimpleDecorator{
 	public String prepareSimple  (String s) {
		return s;
	}
	
  	public String filterSimple   (String s) {
		return s;
	}
	
 	public String finalizeSimple (String s) {
		return s+".";
	}
}

interface FancyDecorator{
	 public String prepareFancy (String s);
	 public String filterFancy   (String s);
	 public String finalizeFancy (String s);
}

class FancyDecoratorImpl implements FancyDecorator{
	public String prepareFancy (String s) {
		return s.toLowerCase();
	}
	
 	public String filterFancy   (String s) {
		s = s.replace('a', 'A'); 
		s = s.replace('e', 'E');
		s = s.replace('i', 'I'); 
		s = s.replace('o', 'O');
		s = s.replace('u', 'U');
		return s; 
	}
		
 	public String finalizeFancy (String s) {
		return (s+".\n(all consonants capitalized)");
	}
}

class DecoratedStringGenerator { 
    
	private int style;
	
	public DecoratedStringGenerator(int style){
		this.style = style;
	}
	
	public String generate(String s) {
		
		
		if(style==0){
			SimpleDecorator d = new SimpleDecoratorImpl();
			s = d.prepareSimple(s);
			s = d.filterSimple(s);
			s = d.finalizeSimple(s);
		}
		else{
			FancyDecorator d = new FancyDecoratorImpl();
			s = d.prepareFancy(s);
			s = d.filterFancy(s);
			s = d.finalizeFancy(s);
		}
		return s;
	}
	                        
}	

public class Main {

   
	public static void main(String[] args) {
		String original = "This Is The Original String To Be Processed";
		
		DecoratedStringGenerator c1 = new DecoratedStringGenerator(0);
		DecoratedStringGenerator c2 = new DecoratedStringGenerator(1);
		
		System.out.println("<Original>");
		System.out.println(original);
		System.out.println("<SimpleGenerator>");
		System.out.println(c1.generate(original));
		System.out.println("<FancyGenerator>");
		System.out.println(c2.generate(original));
	}
}