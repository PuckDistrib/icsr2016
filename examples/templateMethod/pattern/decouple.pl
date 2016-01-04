java_import(['templateMethod.pattern',
			 'templateMethod.pattern.FancyGenerator',
			 'templateMethod.pattern.SimpleGenerator']).

declareSet(concreteImplem,['prepare__String', 'filter__String', 'finalize__String']).

declareSet(template_method,['DecoratedStringGenerator.generate__String']).
hideSetFrom(concreteImplem, template_method).
