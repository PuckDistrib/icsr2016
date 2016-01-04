%foreach concreteProducts family
%	hideScopeButFrom(concreteProducts, concreteFactory)
%hideScope(concreteFactories)

java_import(['abstractFactory.pattern']).

declareSet(motifProducts, ['MotifButton', 'MotifMenu']).
%isFriendOf('MotifFactory', motifProducts).
friendOf('MotifFactory', 'MotifButton').
friendOf('MotifFactory', 'MotifMenu').

declareSet(windowsProducts, ['WindowsButton', 'WindowsMenu']).
%isFriendOf('WindowsFactory', windowsProducts).
friendOf('WindowsFactory', 'WindowsButton').
friendOf('WindowsFactory', 'WindowsMenu').



declareSet(concreteFactories, ['MotifFactory', 'WindowsFactory']).

declareSetUnion(products_, [motifProducts, windowsProducts]).

hideScopeSet(products_).	
