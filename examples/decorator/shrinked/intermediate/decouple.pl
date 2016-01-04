java_import(['decorator.intermediate']).
declareSet(decorators, ['AwithX', 'AwithY', 'AwithZ', 'AwithXY','AwithYX', 'AwithXYZ']).
declareSet(concrete_components, ['A']).
declareSetUnion(actors,[decorators, concrete_components]).
hideFromEachOther(actors).