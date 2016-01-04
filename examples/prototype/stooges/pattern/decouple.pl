java_import(['prototype.pattern']).

declareSet(concretePrototypes, ['Curly', 'Moe', 'Larry' ]).

declareSet(initial_creator,['Factory']).

hideSetButFrom(concretePrototypes, initial_creator).

