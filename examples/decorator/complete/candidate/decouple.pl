
java_import(['decorator.candidate']).

declareSet(concreteComponents, ['A']).
declareSet(decorators,['AwithX', 'AwithY', 'AwithZ', 'AwithXY', 'AwithXYZ']).

declareSetUnion(everybody, [concreteComponents, decorators]).

hideFromEachOther(everybody).
