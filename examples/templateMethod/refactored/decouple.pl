java_import(['templateMethod.refactored',
				'templateMethod.refactored.FancyDecoratorImpl',
				'templateMethod.refactored.SimpleDecoratorImpl']).

declareSet(concreteImplem,['prepare__String', 'filter__String', 'finalize__String']).
			    
declareSet(template_method,['DecoratedStringGenerator.generate__String']).
hideSetFrom(concreteImplem, template_method).
