

% After applying the pattern, the constraints are respected apart from the temp field initialization in StackFIFO
% This remaining violation could be corrected by encapsulating the "new" in a factory
% Why the temp implementation become a List whereas it was an Array before is a question we don't have an answer for.
% But we can note that by hiding the implementation with the StackImpl interface, we can easily use any of the two implementations.

declareSet(concreteImplementations, ['bridge2.pattern.StackArray',
					'bridge2.pattern.StackList']).

declareSet(abstractImplementations, ['bridge2.pattern.StackImpl']).

declareSetUnion(implementations, [abstractImplementations, concreteImplementations]).

declareSet(plainAbstractions,['bridge2.pattern.Stack']).

declareSet(refinedAbstractions, ['bridge2.pattern.StackFIFO', 'bridge2.pattern.StackHanoi']).

declareSetUnion(abstractions, [plainAbstractions,  refinedAbstractions]).


hideSetFrom(concreteImplementations, abstractions).
hideSetFrom(refinedAbstractions, implementations).

