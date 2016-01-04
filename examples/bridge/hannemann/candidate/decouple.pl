java_import(['bridge1.candidate']).
declareSet(concreteImplementations, ['Screen.printText__String',
			'Screen.printLine__void',
			'Screen.printDecor__void']).

declareSet(abstractImplementations, []).
declareSetUnion(implementations, [abstractImplementations, concreteImplementations]).

declareSet(plainAbstractions,['Screen']).

declareSet(refinedAbstractions, ['StarInformationScreen',
			   'StarGreetingScreen',
			   'CrossCapitalInformationScreen',
			   'CrossCapitalGreetingScreen']).

declareSetUnion(abstractions, [plainAbstractions,  refinedAbstractions]).

hideSetFrom(concreteImplementations, abstractions).
hideSetFrom(refinedAbstractions, implementations).



