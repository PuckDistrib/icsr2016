
java_import(['bridge.intermediate']).

declareSet(implementations, 
		['TextImpl', 
		 'GraphicImpl']).

declareSet(abstractions, ['Screen', 
						'WelcomeScreenText', 'WelcomeScreenGraphic',
						'InfoScreenText', 'InfoScreenGraphic']).

hideSetFrom(implementations, abstractions).
