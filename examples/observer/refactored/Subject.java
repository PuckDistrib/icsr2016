package observer.refactored;

// when abstracting the second observer, a existing abstraction is already found
// no need to merge

interface Obs{
	void update(int v);
}

class DivObs implements Obs{
	private int div_;
	public DivObs(int d) {
		div_ = d;
	}
	public void update(int v){
		System.out.println(v + " div " + div_ +" is " + (v / div_));
	}
}

class ModObs implements Obs{
	private int div_;
	public ModObs(int d) {
		div_ = d;
	}
	public void update(int v){
		System.out.println(v + " mod " + div_ +" is " + (v % div_));
	}
}

/*
 * it remains to change the two Obs ref for a collection to obtain the Observer Pattern
 */
class Subject {
	private int val_;
	private Obs div_;
	private Obs mod_;
	
	public Subject(Obs div, Obs mod){
		div_ = div;
		mod_ = mod;
	}
	
	public void setVal(int v){
		val_ = v;
		div_.update(v);
		mod_.update(v);
	}
}

class Main{
	public static void main(String[] args){
		Subject subj = new Subject(new DivObs(4), new ModObs(3));
		subj.setVal(14);
	}
}