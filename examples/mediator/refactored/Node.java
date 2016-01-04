package mediator.refactored;

class Mediator{
	void add_node(Node n0, Node n){
		if(n0.get_next() != null)
			add_node(n0, n);
		else
			n0.set_next(n);
	}
	
	void traverse(Node n){
		System.out.print(n.get_val()+" ");
		if(n.get_next() != null)
			traverse(n.get_next());
		else
			System.out.println();
	}
	
	void remove_node(Node n, int v){
		if(n.get_next() != null){
			if(n.get_next().get_val() == v)
				n.set_next(n.get_next().get_next());
			else
				remove_node(n.get_next(), v);
		}
	}
}

class Node {
	private int m_val;
	private Node m_next;
	
	public Node(int v){
		m_val = v;
		m_next = null;
	}
	int get_val(){
		return m_val;
	}
	Node get_next(){
		return m_next;
	}
	void set_next(Node n){
		m_next = n;
	}
}
