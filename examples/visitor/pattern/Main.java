package visitor.pattern;

interface Visitable { 
    
   public void accept(BinaryTreeVisitor visitor);
}

class BinaryTreeLeaf implements Visitable {  
    
	protected int value;
	
	public void accept(BinaryTreeVisitor visitor) {
		visitor.visitLeaf(this);
	}
	
	public BinaryTreeLeaf(int value) { this.value = value; } 
	
	public int getValue() {return value; }
}

class BinaryTreeNode implements Visitable { 
    
	protected Visitable left;

	protected Visitable right;

	public void accept(BinaryTreeVisitor visitor) {
		visitor.visitNode(this);
	}  
	
	public Visitable getLeft() {
	    return left;
	}

	public Visitable getRight() {
	    return right;
	}

	public BinaryTreeNode(Visitable left, Visitable right) {
		this.left = left;
		this.right = right;
	}
}

interface BinaryTreeVisitor { 
    
	public void visitNode(Visitable node); 

	public void visitLeaf(Visitable node);
	
	public String report();
}

class SummationVisitor implements BinaryTreeVisitor {
    
   protected int sum = 0;
	 
   public void visitNode(Visitable node) {
		BinaryTreeNode rnode = (BinaryTreeNode) node; 
		rnode.left.accept(this);
		rnode.right.accept(this);
	}
		
   public void visitLeaf(Visitable node) {
		BinaryTreeLeaf leaf = (BinaryTreeLeaf) node;
		sum += leaf.getValue();
	}
	
	public String report() {
		return ">>> SummationVisitor collected a sum of: "+sum;
	}
}

class TraversalVisitor implements BinaryTreeVisitor {
    
	protected String result = "";
	 
	public void visitNode(Visitable node) {
		BinaryTreeNode rnode = (BinaryTreeNode) node;
		result += "{"; 
		rnode.getLeft().accept(this);
		result += ",";
		rnode.getRight().accept(this);
		result += "}";
	}
		
	public void visitLeaf(Visitable node) {
		BinaryTreeLeaf leaf = (BinaryTreeLeaf) node;
		result += leaf.getValue();
	}
	
	public String report() {
		return ">>> TraversalVisitor traversed the tree to: "+result;
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
				            
		SummationVisitor sumVisitor = new SummationVisitor();  
		root.accept(sumVisitor);
		System.out.println(sumVisitor.report());  
		
        System.out.println("Visitor 2: TraversalVisitor, collects a tree");
        System.out.println("representation. Result should be {{1,2},3}.");
		
		TraversalVisitor traversalVisitor = new TraversalVisitor();  
		root.accept(traversalVisitor);
		System.out.println(traversalVisitor.report());  
	}
}