java_import(['state.refactored']).

declareSet(context, ['Chain']).
declareSet(state_related_behavior, 
					['Low.pull__Chain', 
					 'Medium.pull__Chain', 
					 'High.pull__Chain', 
					 'Off.pull__Chain']).

hideSetFrom(state_related_behavior, context).
