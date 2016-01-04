node(0, package, 'strategy', '').
node(1, package, 'candidate', '').
edge(contains,0,1).
node(11, class, 'StrategyDemo', '').
edge(contains,1,11).
node(3, method, 'main', arrow('String[]','@primitive.void')).
edge(contains,11,3).
node(5, constructor, 'SortingContext', arrow(tuple(['@primitive.int', 'int[]']),'strategy.candidate.SortingContext')).
edge(uses,3,5).
node(2, class, 'SortingContext', '').
edge(uses,3,2).
node(12, constructor, 'StrategyDemo', arrow('@primitive.void','strategy.candidate.StrategyDemo')).
edge(contains,11,12).
edge(contains,1,2).
node(8, method, 'bubbleSort', arrow('int[]','@primitive.void')).
edge(contains,2,8).
node(7, method, 'exchange', arrow(tuple(['int[]', '@primitive.int', '@primitive.int']),'@primitive.void')).
edge(uses,8,7).
node(10, method, 'sort', arrow(tuple(['@primitive.int', 'int[]']),'@primitive.void')).
edge(contains,2,10).
edge(uses,10,8).
node(9, method, 'linearSort', arrow('int[]','@primitive.void')).
edge(uses,10,9).
edge(contains,2,5).
edge(uses,5,10).
node(6, method, 'show', arrow('int[]','java.lang.String')).
edge(uses,5,6).
edge(contains,2,9).
edge(uses,9,7).
edge(contains,2,7).
node(4, method, 'methodUnrelatedWithTheSorting', arrow('@primitive.void','@primitive.void')).
edge(contains,2,4).
edge(contains,2,6).
