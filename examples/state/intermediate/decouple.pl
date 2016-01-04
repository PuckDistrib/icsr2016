java_import(['state.intermediate']).

declareSet(context, ['Chain']).
declareSet(state_related_behavior, 
					['Low.pullLow__Chain', 
					 'Medium.pullMedium__Chain', 
					 'High.pullHigh__Chain', 
					 'Off.pullOff__Chain']).

hideSetFrom(state_related_behavior, context).
