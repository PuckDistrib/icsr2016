java_import(['command.intermediate', 
			'command.intermediate.Action1',
			'command.intermediate.Action2']).

hideSetFrom(action, invoker).

declareSet(action, ['clicked__void']).

declareSet(invoker, ['Button', 'ButtonAction', 'ButtonAction2']).
			
