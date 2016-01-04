package mediator.pattern;

import java.util.ArrayList;

class Node {
	private int m_val;
	Node(int v){m_val = v;}
	int get_val(){return m_val;}
}

class List {
	private ArrayList<Node> m_arr;
	public void add_node(Node n){
		m_arr.add(n);
	}
	public void traverse(){
		for(Node n: m_arr){
			System.out.print(n.get_val()+ " ");
		}
		System.out.println();
	}
	public void remove_node(int v){
		for(Node n: m_arr){
			if(n.get_val() == v){
				m_arr.remove(n);
				break;
			}				
		}
	}
	
}