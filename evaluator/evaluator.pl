% author Mikal Ziane
% master file for puck's evaluator

:- dynamic node/4, edge/3, 
	canSee/2, canContain/2,
	uses_dependency/2,%% generated for the solver, ignored by this evaluator

	hideScopeButFrom/2,
	hideScope/4,
	hideScopeSet/4,
	hideScopeFrom/2.

% These predicates may lack clauses

:- multifile
		root/1,
		r/1,
		node/2,
		node/3,
		node/4,
		canSee/2,
		canManipulate/2, 
		collectSubtypesOf/2,
	  	collectConstructorsOf/2,
		collectMethodsOfCoISet/2,
		collectMethodsOfCoI/2,	
		declareSet/2,
		declareSetUnion/2,
		isFriendOf/2,
		friendOf/2,

		hide/1,
		hide/4,
		hideSet/4,
		hideBut/2,
		hideButFrom/2,
		hideFrom/2,

		hideSet/1,
		hideSet/4,
		hideSetBut/2,
		hideSetButFrom/2,
		hideSetFrom/2,
		
		hideFromEachOther/1,

		hiddenFrom/2,

		virtualScope/1,
		virtualScope/2,
		
	
		invisibleEdge/3,
		invisibleNode/1,
		invisibleScope/1,
		layers/1,
		methodsOf/2,
		declareSet/2,
		vContains/2,
		edge/3,
		java_import/1.
		
:- discontiguous
		root/1,
		r/1,
		node/2,
		node/3,
		node/4,
		canSee/2,
		canManipulate/2,
		collectSubtypesOf/2,
		collectConstructorsOf/2,
		collectMethodsOfCoISet/2,
		collectMethodsOfCoI/2,
		declareSet/2,
		declareSetUnion/2,
		friendOf/2,

		hide/1,
		hide/4,
		hideSet/4,
		hideBut/2,
		hideButFrom/2,
		hideFrom/2,

		hideSet/1,
		hideSet/4,
		hideSetBut/2,
		hideSetButFrom/2,
		hideSetFrom/2,
		
		hiddenFrom/2,
		
		invisibleEdge/3,
		invisibleNode/1,
		invisibleScope/1,
		layers/1,
		methodsOf/2,
		declareSet/2,
		vContains/2,
		edge/3.
		
		
:- ensure_loaded('graphInvariants.pl').
:- ensure_loaded('pl2dot.pl').
:- ensure_loaded('constraints.pl').
:- ensure_loaded('tests/wellFormednessTests.pl').


load_graph_element(_, end_of_file).
load_graph_element(Stream, T):-assertz(T), load_graph_aux(Stream).
load_graph_aux(Stream):-
	read_term(Stream, T, []), load_graph_element(Stream, T).

load_graph(FileName):-
	retractall(node(_,_,_,_)),
	retractall(edge(_,_,_)),
	retractall(uses_dependency(_,_)),
	open(FileName, read, Stream),
	load_graph_aux(Stream),
	close(Stream),!.

search_full_name(root(ElementId), ^(ElementName)) :- search_full_name0(ElementId, ElementName).
search_full_name(root(ElementId), r(ElementName)) :- search_full_name0(ElementId, ElementName).
search_full_name(root(ElementId), root(ElementName)) :- search_full_name0(ElementId, ElementName).
search_full_name(ElementId, ElementName) :- atom(ElementName), search_full_name0(ElementId, ElementName).

search_full_name0(ElementId, ElementName):-fullName(ElementId, ElementName).
search_full_name0(ElementId, ElementName):-java_import(PackageNames), 
		member(PN, PackageNames), atomic_list_concat([PN, '.', ElementName], FN), 
		fullName(ElementId, FN).


name_to_id(NL, IdL):-is_list(NL),!, 
	maplist(identify, NL, IdL0), flatten(IdL0, IdL).
name_to_id(N, Id):- atom(N), identify(N,IdL),(atom(IdL)-> Id=IdL; IdL=[Id]).

identify(Name,Ids):- bagof(Id, search_full_name(Id, Name), Ids), !.
identify(X,X).


term_name_to_id(NamedTerm, IdentifiedTerm):-
	NamedTerm=..[Term| NamedArgs],
	maplist(name_to_id, NamedArgs, IdentifiedArgs),
	IdentifiedTerm=..[Term| IdentifiedArgs].

load_constraint_term(_, end_of_file).
load_constraint_term(Stream, java_import(Is)):- 
	assertz(java_import(Is)), load_constraint_aux(Stream).
load_constraint_term(Stream, NamedTerm):- 
	term_name_to_id(NamedTerm, IdentifiedTerm),
		assertz(IdentifiedTerm), load_constraint_aux(Stream).

load_constraint_aux(Stream):-
	read_term(Stream, T, []), load_constraint_term(Stream, T).


load_constraint(FileName):-
	open(FileName, read, Stream),
	load_constraint_aux(Stream),
	close(Stream),!.

evaluate(GraphFile, ConstraintFile, DotFile):-
	load_graph(GraphFile), load_constraint(ConstraintFile),
	run_tests,
	pl2dot(DotFile).
