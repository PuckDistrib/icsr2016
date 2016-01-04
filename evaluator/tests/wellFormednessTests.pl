% Author Mikal Ziane

:- ensure_loaded('../evaluator.pl').

:- begin_tests(graphs).

% Nodes are defined only once
test(singleNodes):-
	forall(node(Node),
		   isaSingleNode(Node)).

%test(nodesAreWellDefined):-
%	forall(node(Node),
%		hasOneKind(Node)).

% Constraints are well defined
test(constraintsAreWellDefined):-
	forall(hiddenFrom(A,B), (node(A), node(B))).
		   
% Edges are well defined
test(edgesAreWellDefined):-
	forall(edge(Relation,Source,Target),
			edgeIsWellDefined(Relation,Source,Target)).

% each element has one name
test(oneName):-
	forall( node(Node),
			hasOneFullName(Node)).

% each full name is the full name of one node
test(oneNode):-
	forall(	fullName(_,FullName),
			isFullNameOfOneNode(FullName)).

% each element has one owner except for packages
test(oneOwner) :-
	forall( (node(Node,Kind,_), Kind \== package, Kind \== virtualScope),
			hasOneOwnerOfKind(Node, _)).

% packages have at most one owner
test(packagesHaveAtMostOneOwner) :-
	forall( node(Node,package,_),
			hasAtMostOneOwner(Node)).

% each method belongs to exactly one class or interface
test(methodsHaveOneOwner) :-
	forall( node(Node,method,_),
			(hasOneOwnerOfKind(Node, class) ;
			 hasOneOwnerOfKind(Node, interface))).

% each attribute belongs to exactly one class
test(attributesHaveOneOwner) :-
	forall( node(Node,attribute,_),
			hasOneOwnerOfKind(Node, class)).		

:- end_tests(graphs).
:- begin_tests(constraints).

test(contains_irreflexivity, [nondet]):-
	forall(node(Node), \+ contains(Node,Node)).
test(contains_antisymmetry):-
	forall( (node(Node),contains(Node,SubNode)),
			\+ contains(SubNode,Node)).
			
% reflexo transitive closure
test('contains*_reflexivity', [nondet]) :-
	forall(node(Node), 'contains*'(Node,Node)).
test('contains*_antisymmetry', [nondet]) :-
	forall( (node(Node), 'contains*'(Node,SubNode)),
			(\+ 'contains*'(SubNode,Node) ;
			 SubNode = Node)).
test('contains*_transitivity', [nondet]) :-
	forall( ('contains*'(N1,N2), 'contains*'(N2,N3)),
			'contains*'(N1,N3)).
% test('contains*_dp_is_the_root', [nondet]) :-
%	forall(node(Node), 'contains*'(dp,Node)).
	

:- end_tests(constraints).

