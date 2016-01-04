java_import(['adapter.intermediate']).

declareSet(legacy_code, ['LegacyLine', 'LegacyRectangle']).
declareSet(adapter_, [r('NewLine.drawLine__int_int_int_int'),
		r('NewRectangle.drawRectangle__int_int_int_int')]).

declareSet(client, ['AdapterDemo']).

hideSetFrom(legacy_code, client).
hideSetFrom(adapter_, client).
