java_import(['fileSystem', 
	'fileSystem.Directory', 'fileSystem.File']).

declareSet(uniformMethods, 
	['ls__void', 'display__String']).
hideSetFrom(uniformMethods,
	['FSClient','display__String']).
