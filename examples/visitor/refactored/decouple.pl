java_import(['visitor.refactored',
	'visitor.refactored.Summer',
	'visitor.refactored.Traverser']).

declareSet(outside_operations, [r('Operation.doIt__BinaryTree'), r('doIt__BinaryTreeLeaf'), r('doIt__BinaryTreeNode')]).

declareSet(structure, ['BinaryTreeNode', 'BinaryTreeLeaf', 'BinaryTree']).

hideSetFrom(outside_operations, structure).
