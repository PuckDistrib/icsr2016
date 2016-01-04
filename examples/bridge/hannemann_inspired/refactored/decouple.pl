
java_import(['bridge.refactored']).

declareSet(implementations, ['TextImpl', 'GraphicImpl']).

declareSet(abstractions, ['Screen', 'WelcomeScreen', 'InfoScreen']).

hideSetFrom(implementations, abstractions).
