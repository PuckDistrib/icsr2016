java_import(['mediator.refactored', 'mediator.refactored.Mediator']).

declareSet(mediator_set, ['add_node__Node_Node', 'traverse__Node', 'remove_node__Node_int']).

hideSetFrom(mediator_set, 'Node').
