
% Intent : decouple abstractions from implementations 
% We have two implementations of a Stack, one using an array, the other using a List
%
% The abstractions are StackFIFO and StackHanoi
% the first one use a second stack to be able to access the last pushed elements
% the second one permits to push only elements which are smaller than the actual top
%
% They both use a Stack/LIFO object but don't need to know weither it is implemented with an array or a list

declareSet(concreteImplementations, ['bridge2.refactored.StackArray',
					'bridge2.refactored.StackList']).

declareSet(abstractImplementations, []).

declareSetUnion(implementations, [abstractImplementations, concreteImplementations]).

declareSet(refinedAbstractions, ['bridge2.refactored.StackFIFO', 'bridge2.refactored.StackHanoi']).

declareSet(plainAbstractions,['bridge2.refactored.Stack']).

declareSetUnion(abstractions, [plainAbstractions,  refinedAbstractions]).

declareSet(clients, ['bridge2.refactored.BridgeDisc']).

hideSetFrom(concreteImplementations, abstractions).
hideSetFrom(refinedAbstractions, implementations).






