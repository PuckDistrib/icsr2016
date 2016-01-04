java_import(['visitor.pattern',
	'visitor.pattern.SummationVisitor',
	'visitor.pattern.TraversalVisitor']).

declareSet(outside_operations, [r('visitNode__Visitable'), r('visitLeaf__Visitable')]).

declareSet(structure, ['BinaryTreeNode', 'BinaryTreeLeaf', 'BinaryTree']).

hideSetFrom(outside_operations, structure).
