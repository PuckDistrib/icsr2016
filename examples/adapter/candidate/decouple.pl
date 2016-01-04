java_import(['adapter.candidate']).

declareSet(legacy_code, ['LegacyLine', 'LegacyRectangle']).
declareSet(adapter_, [r('AdapterDemo.drawLine__LegacyLine_int_int_int_int'),
		r('AdapterDemo.drawRectangle__LegacyRectangle_int_int_int_int')]).

declareSet(client, ['AdapterDemo']).

hideSetFrom(legacy_code, client).
hideSetFrom(adapter_, client).
