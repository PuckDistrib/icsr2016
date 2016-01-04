java_import(['state.candidate']).

declareSet(context, ['Chain']).
declareSet(state_related_behavior, ['Chain.pullLow__void', 
		 'Chain.pullMedium__void', 
		 'Chain.pullHigh__void', 
		 'Chain.pullOff__void']).

hideSetFrom(state_related_behavior, context).
