java_import(['observer.pattern']).

declareSet(concreteObservers, ['DivObs', 'ModObs']).
declareSet(subject, ['Subject']).

hideSetFrom(concreteObservers, subject).
