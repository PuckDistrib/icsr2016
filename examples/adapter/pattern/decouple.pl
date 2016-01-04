java_import(['adapter.pattern']).

declareSet(legacy_code, ['LegacyLine', 'LegacyRectangle']).
declareSet(adapter_, [r('Line.draw__int_int_int_int'),
		r('Rectangle.draw__int_int_int_int')]).

declareSet(client, ['AdapterDemo']).


hideSetFrom(legacy_code, client).
hideSetFrom(adapter_, client).
