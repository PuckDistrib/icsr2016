node(0, package, 'prototype', '').
node(1, package, 'pattern', '').
edge(contains,0,1).
node(18, class, 'Curly', '').
edge(contains,1,18).
node(4, method, 'clone', arrow('@primitive.void','prototype.pattern.Stooge')).
edge(contains,18,4).
edge(uses,4,18).
node(2, interface, 'Stooge', '').
edge(uses,4,2).
node(20, constructor, 'Curly', arrow('@primitive.void','prototype.pattern.Curly')).
edge(uses,4,20).
node(19, method, 'slap_stick', arrow('@primitive.void','@primitive.void')).
edge(contains,18,19).
edge(contains,18,20).
edge(isa,18,2).
edge(uses,18,2).
edge(contains,1,2).
node(11, method, 'slap_stick', arrow('@primitive.void','@primitive.void')).
edge(contains,2,11).
node(10, method, 'clone', arrow('@primitive.void','prototype.pattern.Stooge')).
edge(contains,2,10).
edge(uses,10,2).
node(12, class, 'Larry', '').
edge(contains,1,12).
node(8, method, 'clone', arrow('@primitive.void','prototype.pattern.Stooge')).
edge(contains,12,8).
edge(uses,8,2).
edge(uses,8,12).
node(14, constructor, 'Larry', arrow('@primitive.void','prototype.pattern.Larry')).
edge(uses,8,14).
edge(contains,12,14).
node(13, method, 'slap_stick', arrow('@primitive.void','@primitive.void')).
edge(contains,12,13).
edge(isa,12,2).
edge(uses,12,2).
node(15, class, 'Moe', '').
edge(contains,1,15).
node(17, constructor, 'Moe', arrow('@primitive.void','prototype.pattern.Moe')).
edge(contains,15,17).
node(16, method, 'slap_stick', arrow('@primitive.void','@primitive.void')).
edge(contains,15,16).
node(7, method, 'clone', arrow('@primitive.void','prototype.pattern.Stooge')).
edge(contains,15,7).
edge(uses,7,2).
edge(uses,7,17).
edge(uses,7,15).
edge(isa,15,2).
edge(uses,15,2).
node(23, class, 'PrototypeDemo', '').
edge(contains,1,23).
node(24, constructor, 'PrototypeDemo', arrow('@primitive.void','prototype.pattern.PrototypeDemo')).
edge(contains,23,24).
node(3, attribute, 'roles', 'java.util.List<prototype.pattern.Stooge>').
edge(contains,23,3).
edge(uses,3,2).
node(9, method, 'main', arrow('String[]','@primitive.void')).
edge(contains,23,9).
edge(uses,9,2).
edge(uses,9,3).
node(5, method, 'make_stooge', arrow('@primitive.int','prototype.pattern.Stooge')).
edge(uses,9,5).
edge(uses,9,11).
node(21, class, 'Factory', '').
edge(uses,9,21).
edge(contains,1,21).
node(22, constructor, 'Factory', arrow('@primitive.void','prototype.pattern.Factory')).
edge(contains,21,22).
node(6, attribute, 'prototypes', 'Stooge[]').
edge(contains,21,6).
edge(uses,6,18).
edge(uses,6,2).
edge(uses,6,12).
edge(uses,6,14).
edge(uses,6,17).
edge(uses,6,15).
edge(uses,6,20).
edge(contains,21,5).
edge(uses,5,2).
edge(uses,5,6).
edge(uses,5,10).
uses_dependency((9, 11), (9, 2)).
