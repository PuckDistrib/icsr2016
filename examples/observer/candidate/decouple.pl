java_import(['observer.candidate']).

declareSet(concreteObservers, ['DivObs', 'ModObs']).
declareSet(subject, ['Subject']).

hideSetFrom(concreteObservers, subject).
