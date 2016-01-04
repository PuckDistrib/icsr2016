
java_import(['decorator.pattern']).

declareSet(concreteComponents, ['A']).
declareSet(decorators,['X', 'Y', 'Z']).

declareSetUnion(everybody, [concreteComponents, decorators]).

hideFromEachOther(everybody).