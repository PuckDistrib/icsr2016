java_import(['strategy.intermediate']).

declareSet(concreteStrategies, 
	['BubbleSortImpl.bubbleSort__int[]', 
	 'LinearSortImpl.linearSort__int[]']).
declareSet(context, ['SortingContext']).

hideSetFrom(concreteStrategies, context).
