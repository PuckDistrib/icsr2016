java_import(['templateMethod.intermediate']).

declareSet(concreteImplem,['FancyDecoratorImpl.prepareFancy__String',
			    'FancyDecoratorImpl.filterFancy__String',
			    'FancyDecoratorImpl.finalizeFancy__String',
			    'SimpleDecoratorImpl.prepareSimple__String',
			    'SimpleDecoratorImpl.filterSimple__String',
			    'SimpleDecoratorImpl.finalizeSimple__String']).
			    
declareSet(template_method,['DecoratedStringGenerator.generate__String']).
hideSetFrom(concreteImplem, template_method).
