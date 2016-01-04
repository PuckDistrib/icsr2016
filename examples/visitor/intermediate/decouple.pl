java_import(['visitor.intermediate',
	'visitor.intermediate.Summer',
	'visitor.intermediate.Traverser']).

declareSet(outside_operations, [r('sum__BinaryTree'), r('sum__BinaryTreeLeaf'), r('sum__BinaryTreeNode'), 
		        r('traverse__BinaryTree'), r('traverse__BinaryTreeLeaf'), r('traverse__BinaryTreeNode')]).

declareSet(structure, ['BinaryTreeNode', 'BinaryTreeLeaf', 'BinaryTree']).

hideSetFrom(outside_operations, structure).
