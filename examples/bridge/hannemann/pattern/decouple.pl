%declareSet
java_import(['bridge1.pattern']).
collectMethodsOfCoISet(concreteImplementations, 
				['CrossCapitalImplementation',
				 'StarImplementation']).


declareSet(refinedAbstractions, ['InformationScreen',
			   'GreetingScreen']).


declareSet(abstractImplementations, ['ScreenImplementation']).

declareSetUnion(implementations, [abstractImplementations, concreteImplementations]).

declareSet(plainAbstractions,['Screen']).

declareSetUnion(abstractions, [plainAbstractions,  refinedAbstractions]).

hideSetFrom(concreteImplementations, abstractions).
hideSetFrom(refinedAbstractions, implementations).
