package observer.pattern;

/*
 * huston pattern example
 */

public class Subject {
	private Obs[] obs_;
	private int num_, val_;

	Subject(){
		obs_ = new Obs[10];
		num_ = 0;
	}
	
	int getVal(){
		return val_;
	}
	
	void attach(Obs o){
		obs_[num_++]=o;
	}
	
	void _notify(){
		for(int i=0; i<num_; i++){
			obs_[i].update();
		}
	}
	
	void setVal(int v){
		val_ = v;
		_notify();
	}
}

abstract class Obs{
	int div_;
	Subject sub_;
	abstract void update();
}

class DivObs extends Obs{
	public DivObs(Subject s, int d) {
		sub_ = s;
		div_ = d;
	}
	void update(){
		int v = sub_.getVal();
		System.out.println(v + " div " + div_ +" is " + (v/div_));
	}
}

class ModObs extends Obs{
	public ModObs(Subject s, int d) {
		sub_ = s;
		div_ = d;
	}
	void update(){
		int v = sub_.getVal();
		System.out.println(v + " mod " + div_ +" is " + (v%div_));
	}
}

class Main{
	public static void main(String[] args){
		Subject subj = new Subject();
		new DivObs(subj, 4);
		new DivObs(subj, 3);
		new ModObs(subj, 4);
		
		subj.setVal(14);
	}
}