
java_import(['decorator.candidate']).

declareSet(decorators, ['AwithX', 'AwithY', 'AwithXY','AwithYX']).
declareSet(concrete_components, ['A']).
declareSetUnion(actors,[decorators, concrete_components]).
hideFromEachOther(actors).

%declareSet(decorators, ['AwithX', 'AwithY', 'AwithZ']).
%hideScopeFrom(concrete_components, decorators).
