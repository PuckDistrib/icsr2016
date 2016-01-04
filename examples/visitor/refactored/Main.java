package visitor.refactored;

abstract class Operation<T>{
	void doIt(BinaryTree bt){
		if(bt instanceof BinaryTreeLeaf)
			doIt((BinaryTreeLeaf) bt);
		if(bt instanceof BinaryTreeNode)
			doIt((BinaryTreeNode) bt);
		
	}
	
	abstract void doIt(BinaryTreeLeaf btl);
	
	abstract void doIt(BinaryTreeNode btn);
	
	abstract T getResult();
}

class Summer extends Operation<Integer>{

	Integer sum = 0;

	void doIt(BinaryTreeLeaf btl) {
		sum += btl.getValue();
	}

	void doIt(BinaryTreeNode btn) {
		doIt(btn.getRight());
		doIt(btn.getLeft());
	}

	Integer getResult() {
		return sum;
	}
}

class Traverser extends Operation<String>{
	
	StringBuilder traverse = new StringBuilder();

	void doIt(BinaryTreeLeaf btl) {
		traverse.append(btl.getValue());
	}

	void doIt(BinaryTreeNode btn) {
		traverse.append('{');
		doIt(btn.getLeft());
		traverse.append(", ");
		doIt(btn.getRight());
		traverse.append('}');
		
	}

	@Override
	String getResult() {
		return traverse.toString();
	}
}

interface BinaryTree {}

class BinaryTreeLeaf implements BinaryTree {  
    
	protected int value;
	
	public BinaryTreeLeaf(int value) { this.value = value; } 
	
	public int getValue() {return value; }

}

class BinaryTreeNode implements BinaryTree { 
    
	protected BinaryTree left;

	protected BinaryTree right;

	public BinaryTree getLeft() {
	    return left;
	}

	public BinaryTree getRight() {
	    return right;
	}

	public BinaryTreeNode(BinaryTree left, BinaryTree right) {
		this.left = left;
		this.right = right;
	}
}

public class Main { 
	
	public static void main(String[] args) { 
	    
	    System.out.println("Building the tree (1): leaves");
		
		BinaryTreeLeaf one   = new BinaryTreeLeaf(1);
		BinaryTreeLeaf two   = new BinaryTreeLeaf(2);
		BinaryTreeLeaf three = new BinaryTreeLeaf(3);
		
	    System.out.println("Building the tree (1): regular nodes");
		
		BinaryTreeNode regN = new BinaryTreeNode(one, two);
		BinaryTreeNode root = new BinaryTreeNode(regN, three);
		
        System.out.println("The tree now looks like this: ");
        System.out.println("         regN                 ");
        System.out.println("        /    \\               ");
        System.out.println("    regN      3               ");
        System.out.println("   /    \\                    ");
        System.out.println("  1      2                    ");
		            
        System.out.println("Visitor 1: SumVisitor, collects the sum of leaf");
        System.out.println("values. Result should be 6.");

        Operation<Integer> summer = new Summer();
        summer.doIt(root);
		System.out.println(summer.getResult());  
		
        System.out.println("Visitor 2: TraversalVisitor, collects a tree");
        System.out.println("representation. Result should be {{1,2},3}.");
		
        Operation<String> traverser = new Traverser();
        traverser.doIt(root);
		System.out.println(traverser.getResult());  
	}
}