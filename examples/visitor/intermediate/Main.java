package visitor.intermediate;

class Summer{
	int sum(BinaryTree bt){
		if(bt instanceof BinaryTreeLeaf)
			return sum((BinaryTreeLeaf) bt);
		if(bt instanceof BinaryTreeNode)
			return sum((BinaryTreeNode) bt);
		
		return 0;
	}

	int sum(BinaryTreeLeaf btl){
		return btl.getValue();
	}
	
	int sum(BinaryTreeNode btn){
		return sum(btn.getLeft()) + sum(btn.getRight());
	}
}

class Traverser{
	String traverse(BinaryTree bt){
		if(bt instanceof BinaryTreeLeaf)
			return traverse((BinaryTreeLeaf) bt);
		if(bt instanceof BinaryTreeNode)
			return traverse((BinaryTreeNode) bt);
		
		return "";
	}
	
	String traverse(BinaryTreeLeaf btl){
		return ""+btl.getValue();
	}
	String traverse(BinaryTreeNode btn){
		return "{" + traverse(btn.getLeft()) + ", " +traverse(btn.getRight()) + "}"; 
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
				            
		System.out.println((new Summer()).sum(root));  
		
        System.out.println("Visitor 2: TraversalVisitor, collects a tree");
        System.out.println("representation. Result should be {{1,2},3}.");
		
		System.out.println((new Traverser()).traverse(root));  
	}
}