java_import(['fileSystem','fileSystem.fileKinds']).
declareSet(file_kinds, [r('File'), r('Directory')]).

hideSet(file_kinds).
friendOf([r(fileKinds)], file_kinds).
