java_import(['decorator.refactored']).
declareSet(decorators, ['AwithX', 'AwithY', 'AwithZ']).
declareSet(concrete_components, ['A']).
declareSetUnion(actors,[decorators, concrete_components]).
hideFromEachOther(actors).