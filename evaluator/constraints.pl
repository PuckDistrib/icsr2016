% Author Mikal Ziane

:- ensure_loaded('graphInvariants.pl').

% virtual scopes
node(Name,virtualScope,Name):-
	virtualScope(Name,_).
edge(virtualContains, VS, Node) :- 
	virtualScope(VS, Elements),
	gMember(Element, Elements),
	nodeOfElement(Element,Node).

% list names
declaredMember(Element, ListName):- collectSubtypesOf(SuperType,ListName),
	'isa+'(Element, SuperType).

%% declaredMember(Element, ListName):- collectConstructorsOf(Class,ListName),
%% 	contains(Class, Element), node(Element, constructor, _, _).

declaredMember(ElementName, ListName):- declareSet(ListName, List), member(ElementName, List).

declaredMember(Element, UnionName) :- declareSetUnion(UnionName, SetList),
				      member(NamedSet, SetList), declaredMember(Element, NamedSet).

%% declaredMember(Element,SetName):-collectMethodsOfCoI(HostClass,SetName),
%% 				 methodOf(Element,HostClass).

%% declaredMember(Element,SetName):-collectMethodsOfCoISet(ClassSet,SetName),
%% 				 gMember(HostClass,ClassSet), methodOf(Element,HostClass).

	
selectedMember(Element, ListName, OtherElements) :- 
	bagof(E,declaredMember(E, ListName), List), select(Element, List, OtherElements).

% shorcut notations for edges and nodes
uses(A,B) 			:- edge(uses, A, B).
isa(A,B) 				:- edge(isa, A, B).
contains(Owner,Node)			:- edge(contains, Owner, Node).
virtualContains(VScope,Node)		:- edge(virtualContains, VScope, Node).
vContains(A,B) 				:- contains(A,B) ; virtualContains(A,B).

%% human helpers predicates uneeded with machine generated graph

node(Node)						:- node(Node,_,_,_).
node(Id,Kind,_) 				        :- node(Id,Kind).
node(Node,method,Name,arrow(['void'],''))	:- node(Node,method,Name).
node(Node,constructor,Name,arrow(['void'],''))	:- node(Node,constructor,Name).
node(Node,Kind,Name,'')				:- node(Node,Kind,Name), Kind \= method, Kind \= constructor.

constructor(C):-
	node(C,constructor,_,_).

method(Method):- 
	node(Method, method, _, _).
methodOf(Method,CoI) :- classOrInterface(CoI), method(Method), contains(CoI, Method).

attribute(Attribute)			:- node(Attribute, attribute, _, _).
attributeOf(Attribute, CoI)	:- classOrInterface(CoI), attribute(Attribute), contains(CoI, Attribute).

methodsOf(Ms,CoI) 		:- classOrInterface(CoI), 
				   findall(M, (method(M), contains(CoI, M)), Ms).

class(Class)				:- node(Class, class, _, _).
classOrInterface(CoI)		:- node(CoI, class, _, _).
classOrInterface(CoI)		:- node(CoI, interface, _, _).
virtualScope(P) 			:- node(P, virtualScope, P).

root(A)	:- r(A).
% generalized contains
gContains(A,B) 				:- gMember(B,A), node(B).
gContains(A,B) 				:- node(A), 'vContains'(A,B).


gMember(A,B):- member(A,B).
gMember(A,B):- declaredMember(A,B).

gMember(A, universe) :- node(A).

gSelect(A,B,C) :- select(A,B,C).
gSelect(A,B,C) :- selectedMember(A,B,C).

violation(edge(K,A,B)):-
	edge(K, A, B), (K=uses ; K=contains), hiddenFrom(B,A), \+canSee(A,B).
	
%violation(edge(uses,A,B)):-
%	edge(uses,A,B), hiddenFrom(B,A), \+canSee(A,B).
%violation(edge(contains,A,B)):-
%	edge(contains,A,B), shouldntContain(B,A), \+canContain(A,B).


%gather all the violations
 checkConstraints(Violations)	:-
 	findall(edge(K,A,B), violation(edge(K,A,B)), Violations).


% canSee can be used to intoduce exceptions. Use it sparingly as it bypasses all the global constraints!
%hiddenFrom(B,A)	:- hideFrom(B,A), \+ canSee(A,B).
%cannotContain(A,B)	:- shouldntContain(A,B), \+ canContain(A,B).
%ruleApplication	:- rule , \+ exception
%cannotBe(A,B)		:- shouldntBe(A,B),	\+ canBe(A,B).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%% Higher predicates infering hideFrom %%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


% this is expensive when the graph is large
%% outside(S, Nodes):-
%% 	findall(Node, (node(Node), \+ 'gContains*'(S,Node)) , Nodes).
outside(S, Nodes):-
	findall(Node, (node(Node), \+ in_scope_of(Node, S)) , Nodes).


%% à la lecture des contrainte, si A a été reconnu comme un identifiant
%% il a été remplacé par l'id correspondant
%% friendOf(A,B):- isFriendOf(A1,B1), 
%% 	(integer(A1)->A1=A; gMember(A, A1)),
%% 	(integer(B1)->B1=B; gMember(B, B1)).

friend(A,B):- friendOf(A, B), integer(A), integer(B).
friend(root(A),B):- friendOf(root(A), B), integer(A), integer(B).
friend(A,root(B)):- friendOf(A, root(B)), integer(A), integer(B).
friend(root(A),root(B)):- friendOf(root(A), root(B)), integer(A), integer(B).

friend(A,B):- friendOf(As, B), gMember(A, As).

friends(S, OriginalFriends, Friends):- is_list(OriginalFriends),
    findall(F, friend(F, S), Friends, OriginalFriends).

friends(S, OriginalFriends, Friends):- declareSet(OriginalFriends, FList),
    findall(F, friend(F, S), Friends, FList).

/*friends(S, OriginalFriends, Friends):- \+is_list(OriginalFriends),
    findall(F, friend(F, S), Friends, [OriginalFriends]).
for previous def to work we need to add :
in_scope_of(A, Set) :- gMember(B, Set), in_scope_of(A, B).
*/


friends(S, Friends) :- friends(S, [], Friends).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%
%% CORE RULES
%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%hide(Set, Facades, Interlopers, Friends)

in_scope_of(A, root(A)) :- node(A).
in_scope_of(A, B) :- 'vContains*'(B, A).


in_scope_of_set(A, S) :- gMember(B, S), in_scope_of(A, B).


hide(S, Facades, Interlopers, AllFriends):- hideSet(ScopeSet, Facades, Interlopers, Friends), 
						    friends(ScopeSet,Friends,AllFriends), 
						    gMember(S, ScopeSet).

hiddenFrom(Element, Interloper) :- hide(S, Facades, Interlopers, Friends),
				 friends(S,Friends,AllFriends), 
				 in_scope_of(Element, S),
				 \+ in_scope_of_set(Element, Facades),
				 in_scope_of_set(Interloper, Interlopers),
				 \+ in_scope_of_set(Interloper, AllFriends),
				 \+ in_scope_of(Interloper, S).

				 %% 'vContains*'(S,Element),			% Element is in S
				 %% \+ 'gContains*'(Facades,Element),		% Element is not in one of the Facades
				 %% 'gContains*'(Interlopers,Interloper),	% Interloper is in one of the Interlopers
				 %% \+ 'gContains*'(AllFriends, Interloper),	% but not in one the Friends
				 %% \+ 'vContains*'(S,Interloper).		% Interloper is not in S




hide(S, [], universe, Friends)  :- hideButFrom(S, Friends).
hide(S, [], universe, [])  		:- hide(S).
hide(S, Facades, universe, []) 	:- hideBut(S, Facades).
hide(S, [], Interlopers, []) 	:- hideFrom(S, Interlopers).


hideSet(Set, [], universe, Friends) :- hideSetButFrom(Set, Friends).
hideSet(Set, [], universe, []) :- hideSet(Set).
hideSet(Set, Facades, universe, []) :- hideSetBut(Set, Facades).
hideSet(Set, [], Interlopers, []) :- hideSetFrom(Set, Interlopers).



/*hide(S, [], Universe, Friends)  :- hideButFrom(S, Friends), outside(S, Universe).
hide(S, [], Universe, [])  		:- hide(S), outside(S, Universe).
hide(S, Facades, Universe, []) 	:- hideBut(S, Facades), outside(S, Universe).
hide(S, [], Interlopers, []) 	:- hideFrom(S, Interlopers).


hide(S, [], Universe, AllFriends) :- hideSetButFrom(Set, Friends), friends(Set, Friends, AllFriends), gMember(S, Set), outside(S, Universe).
hide(S, [], Universe, Friends) :- hideSet(Set), friends(Set, Friends), gMember(S,Set), outside(S, Universe).
hide(S, Facades, Universe, Friends) :- hideSetBut(Set, Facades), friends(Set, Friends), gMember(S, Set), outside(S, Universe).
hide(S, [], Interlopers, Friends) :- hideSetFrom(Set, Interlopers), friends(Set, Friends), gMember(S, Set).

*/
/*
hide(S, [], Interlopers, Friends)  :- hideButFrom(S, Friends), outside(S, Interlopers).
hideButFrom(S, AllFriends)    :- hideSetButFrom(Set, Friends), 
					   friends(Set, Friends, AllFriends), gMember(S, Set).

hide(S, [], Interlopers, [])        :- hide(S), outside(S, Interlopers).
hideButFrom(S, Friends)		 :- hideSet(Set),
				       friends(Set, Friends), gMember(S,Set).

hide(S, Facades, Interlopers, []) 	  :- hideBut(S, Facades), outside(S, Interlopers).
hide(S, Facades,Interlopers, Friends) :- hideSetBut(Set, Facades), 
					   friends(Set, Friends), gMember(S,Set), outside(S,Interlopers).

% lightweight constraint   
hide(S, [], Interlopers, [])        :- hideFrom(S, Interlopers).
hideSet(Set, [], Interlopers, [])   :- hideSetFrom(Set, Interlopers).
*/
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% hideScopeFrom(FirstLayer,OtherLayers) :- layers([FirstLayer|OtherLayers]).
%% hideScopeButFrom(NextLayer, [Layer])  :- layers(Layers),
%% 		member(Layer, Layers),
%%  		nth0(I,Layers,Layer), 
%% 		J is I +1,
%% 		nth0(J,Layers,NextLayer).
 

%% hideScopeButFrom(Scopes,Scopes)	:- virtualPackage(Scopes).


hideFrom(Member, OtherActors) 		:- hideFromEachOther(Actors), %Actors is a list of scope and cannot be a virtualScope
	gSelect(Member, Actors, OtherActors).



%% hiddenFrom(S, Element) 			:- hideFromSelf(S), 'gContains*'(S, Element).
%% hideFromSelf(S) 				:- hideFromSelves(Ss), gMember(S,Ss).

%% hideFromOwnHierarchy(S) 		:- hideFromOwnHierarchies(Ss), gMmember(S, Ss).
%% hideHierarchyFrom(S, S) 			:- hideFromOwnHierarchy(S). 

%% hideHierarchyFrom(T, Scopes)		:- hideHierarchiesFrom(Types, Scopes), gMember(T, Types).

%% ce prédicat vise à interdire une classe d'avoir des champs ou paramètres de méthode d'un type appartenant à sa famille
%% c'est un type de use bien précis: c'est le type déclaré de l'élément      %%'
%% hiddenFrom(HMember, S) 				:- hideHierarchyFrom(T,Scopes),	haveSameHierarchy(T, HMember),
%% 						   'gContains*'(Scopes, S).
 
%%pmie peut manipuler une instance enfant
%useASubclassedClass(Client, _, Super) :- pmie(Client, _, Super), \+uses(Client, Super).
%useASubclassedClass(Client, Subs, Super) :- pmie(Client, Subs, Super), uses(Client, Super), 
%					member(S, Subs), 'isa+'(S, Super).
%
%mustHaveAUniqueInterface(Components, S1) :- haveAUniqueSuperClass(Components),
%				\+'gContains*'(Components, Client), 
%				useASubclassedClass(Client, Components, S1),
%				useASubclassedClass(Client, Components, S2),
%				S1=S2.
%


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	

% transitive closures
tclosure(Pred,A,B)	:- call(Pred,A,B).
tclosure(Pred,A,C)	:- call(Pred,A,B), tclosure(Pred,B,C).

'contains+'(A,B)	:- tclosure(contains,A,B).
'vContains+'(A,B) 	:- tclosure(vContains,A,B).
'gContains+'(A,B) 	:- tclosure(gContains,A,B).

'isa+'(A,B)			:- tclosure(isa,A,B).

% recursive transitve closures
rtclosure(_,A,A) :- node(A).
rtclosure(Pred,A,B) :- tclosure(Pred,A,B).

'contains*'(A,B)	:- rtclosure(contains,A,B).
'vContains*'(A,B) 	:- rtclosure(vContains,A,B).
'gContains*'(A,B) 	:- rtclosure(gContains,A,B).

'isa*'(A,B) :- rtclosure(isa,A,B).

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% EXTENSION to support string nodes
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%node(StringNode, stringLiteral, String):-
%	virtualScope(_,Elements), member(string(String), Elements), 
%	nodeOfElement(string(String), StringNode).

%% node(StringNode, stringLiteral, String):- string(String), 
%% 	nodeOfElement(string(String), StringNode).


%% nodeOfElement(string(Literal), StringNode):-!,( var(StringNode) ; atomic(StringNode)),
%% 	atom_concat('#SN#',Literal,StringNode).
%% nodeOfElement(Element,Element).

%hideScope(StringNode) 	:- hideString(String), node(StringNode, stringLiteral, String).

% %%%%%%%%%%%%%%%%%%%%%%%%%%%
% POSSIBLE EXTENSIONS
% %%%%%%%%%%%%%%%%%%%%%%%%%%%

%% hiddenFrom(Method,Client):- hideMethodsOfFrom(CoI,Client), methodOf(Method, CoI).
%% hideMethodsOfFrom(CoI,Node):- hideMethodsOf(CoI), node(Node).
%% hideMethodsOf(CoI):- hideMethodsOfCoIList(CoIL), member(CoI, CoIL).

%% shouldntContain(Nosy,Attribute):- forbidTypeInRefsFrom(Types, Nosies), 
%% 		gMember(Type, Types),
%% 		gMember(Nosy, Nosies),
%% 		attributeOf(Attribute, Nosy), 
%% 		uses(Attribute, Type).			% should only forbid if Type is the declaration type of Attribute
	
 

% manipulates(M,E) :- 'vContains*'(M,M1), 'vContains*'(E,E1), uses(M1,E1).
canSee(F, S) 					:- canManipulate(Friends, Scopes), 
						   'gContains*'(Scopes, S), 'gContains*'(Friends, F).

% root(Root) :- node(Root,Kind), Kind \= virtualScope, \+ contains(_,Root).



%% existPath(_, A, A).
%% existPath(Kinds, A, B)	:- existPath(Kinds, A, B), member(K, Kinds),
%% 							edge(K, A, C), existPath(Kinds, C, B).

%hiddenFrom(Element, Interloper) :- hideScope(S, Facades, Interlopers, Friends),
%	existPath([contains, virtualContains],S,Element),			% Element is in S
%	\+ 'gContains*'(Facades,Element),		% Element is not in one of the Facades
%	'gContains*'(Interlopers,Interloper),	% Interloper is in one of the Interlopers
%	\+ 'gContains*'(Friends, Interloper),	% but not in one the Friends
%	\+ 'gContains*'(S,Interloper).		% Interloper is not in S
