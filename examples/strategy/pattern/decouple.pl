java_import(['strategy.pattern']).

declareSet(concreteStrategies, 
	   ['BubbleSort.sort__int[]', 
	    'LinearSort.sort__int[]']).
declareSet(context, ['StrategyDemo']).

hideSetFrom(concreteStrategies, context).
