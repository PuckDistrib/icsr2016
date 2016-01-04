node(0, package, 'command', '').
node(1, package, 'refactored', '').
edge(contains,0,1).
node(2, class, 'Button', '').
edge(contains,1,2).
node(4, constructor, 'Button', arrow(tuple(['java.lang.String', 'command.refactored.Action']),'command.refactored.Button')).
edge(contains,2,4).
node(5, interface, 'Action', '').
edge(uses,4,5).
node(14, class, 'Main', '').
edge(contains,1,14).
node(3, method, 'main', arrow('String[]','@primitive.void')).
edge(contains,14,3).
node(13, constructor, 'Action2', arrow('@primitive.void','command.refactored.Action2')).
edge(uses,3,13).
edge(uses,3,2).
node(10, constructor, 'Action1', arrow('@primitive.void','command.refactored.Action1')).
edge(uses,3,10).
edge(uses,3,4).
node(8, class, 'Action1', '').
edge(uses,3,8).
node(11, class, 'Action2', '').
edge(uses,3,11).
node(15, constructor, 'Main', arrow('@primitive.void','command.refactored.Main')).
edge(contains,14,15).
edge(contains,1,5).
node(6, method, 'clicked', arrow('@primitive.void','@primitive.void')).
edge(contains,5,6).
edge(contains,1,8).
edge(contains,8,10).
node(9, method, 'clicked', arrow('@primitive.void','@primitive.void')).
edge(contains,8,9).
node(46, method, 'println', arrow('java.lang.String','@primitive.void')).
edge(uses,9,46).
edge(isa,8,5).
edge(uses,8,5).
edge(contains,1,11).
edge(contains,11,13).
node(12, method, 'clicked', arrow('@primitive.void','@primitive.void')).
edge(contains,11,12).
edge(uses,12,46).
edge(isa,11,5).
edge(uses,11,5).
node(16, package, 'java', '').
node(17, package, 'io', '').
edge(contains,16,17).
node(18, class, 'PrintStream', '').
edge(contains,17,18).
edge(contains,18,46).
uses_dependency((7, 6), (4, 5)).
uses_dependency((9, 46), (23, 18)).
uses_dependency((12, 46), (23, 18)).
