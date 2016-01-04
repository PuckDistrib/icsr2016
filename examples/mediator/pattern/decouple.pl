java_import(['mediator.pattern', 'mediator.pattern.List']).

declareSet(mediator_set, ['add_node__Node', 'traverse__void', 'remove_node__int']).

hideSetFrom(mediator_set, ['Node']).
