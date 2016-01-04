java_import(['observer.refactored']).

declareSet(concreteObservers, ['DivObs', 'ModObs']).
declareSet(subject, ['Subject']).

hideSetFrom(concreteObservers, subject).
