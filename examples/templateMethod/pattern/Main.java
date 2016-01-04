package templateMethod.pattern;

abstract class DecoratedStringGenerator { 

	public String generate(String s) {
		s = prepare(s);
		s = filter(s);
		s = finalize(s);
		return s;
	}

	public abstract String prepare (String s);

	public abstract String filter (String s);

	public abstract String finalize (String s);
}

class FancyGenerator extends DecoratedStringGenerator {

	public String prepare  (String s) {
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

class SimpleGenerator extends DecoratedStringGenerator {

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
public class Main {

	/**
	 * Implements the driver for the Template Method design 
	 * pattern example.<p>
	 *
	 * @param args the command line parameters, unused
	 */

	public static void main(String[] args) {
		String original = "This Is The Original String To Be Processed";

		DecoratedStringGenerator c1 = new SimpleGenerator();
		DecoratedStringGenerator c2 = new FancyGenerator();

		System.out.println("<Original>");
		System.out.println(original);
		System.out.println("<SimpleGenerator>");
		System.out.println(c1.generate(original));
		System.out.println("<FancyGenerator>");
		System.out.println(c2.generate(original));
	}
}