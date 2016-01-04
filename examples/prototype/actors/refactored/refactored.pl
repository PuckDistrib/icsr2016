node(0, package, 'prototype', '').
node(1, package, 'refactored', '').
edge(contains,0,1).
node(4, interface, 'Actor', '').
edge(contains,1,4).
node(12, method, 'act', arrow('@primitive.void','@primitive.void')).
edge(contains,4,12).
node(10, method, 'create', arrow('@primitive.void','prototype.refactored.Actor')).
edge(contains,4,10).
edge(uses,10,4).
node(21, class, 'PrototypeDemo', '').
edge(contains,1,21).
node(11, method, 'makeMovie', arrow('prototype.refactored.Actor','@primitive.void')).
edge(contains,21,11).
edge(uses,11,4).
node(9, attribute, 'roles', 'java.util.List<prototype.refactored.Actor>').
edge(uses,11,9).
edge(uses,11,10).
node(22, constructor, 'PrototypeDemo', arrow('@primitive.void','prototype.refactored.PrototypeDemo')).
edge(contains,21,22).
edge(contains,21,9).
edge(uses,9,4).
node(5, method, 'main', arrow('String[]','@primitive.void')).
edge(contains,21,5).
edge(uses,5,11).
edge(uses,5,4).
edge(uses,5,12).
node(3, class, 'Tragedian', '').
edge(uses,5,3).
edge(uses,5,9).
node(16, constructor, 'Extra', arrow('@primitive.void','prototype.refactored.actors.Extra')).
edge(uses,5,16).
node(8, constructor, 'Tragedian', arrow('@primitive.void','prototype.refactored.actors.Tragedian')).
edge(uses,5,8).
node(20, constructor, 'Comedian', arrow('@primitive.void','prototype.refactored.actors.Comedian')).
edge(uses,5,20).
node(17, class, 'Comedian', '').
edge(uses,5,17).
node(13, class, 'Extra', '').
edge(uses,5,13).
node(2, package, 'actors', '').
edge(contains,1,2).
edge(contains,2,3).
node(6, method, 'create', arrow('@primitive.void','prototype.refactored.actors.Tragedian')).
edge(contains,3,6).
edge(uses,6,3).
edge(uses,6,8).
edge(contains,3,8).
node(7, method, 'act', arrow('@primitive.void','@primitive.void')).
edge(contains,3,7).
edge(isa,3,4).
edge(uses,3,4).
edge(contains,2,17).
node(19, method, 'act', arrow('@primitive.void','@primitive.void')).
edge(contains,17,19).
node(18, method, 'create', arrow('@primitive.void','prototype.refactored.actors.Comedian')).
edge(contains,17,18).
edge(uses,18,20).
edge(uses,18,17).
edge(contains,17,20).
edge(isa,17,4).
edge(uses,17,4).
edge(contains,2,13).
edge(contains,13,16).
node(14, method, 'create', arrow('@primitive.void','prototype.refactored.actors.Extra')).
edge(contains,13,14).
edge(uses,14,16).
edge(uses,14,13).
node(15, method, 'act', arrow('@primitive.void','@primitive.void')).
edge(contains,13,15).
edge(isa,13,4).
edge(uses,13,4).
uses_dependency((5, 12), (5, 4)).
uses_dependency((11, 10), (11, 4)).
