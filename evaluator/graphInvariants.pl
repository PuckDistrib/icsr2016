% Author Mikal Ziane
% Low level predicates to check access graph properties
% and related invariants.


% supported relations
supportedRelation(uses).
supportedRelation(contains).
supportedRelation(virtualContains).
supportedRelation(isa).

% An edge is well defined if it binds nodes that exist and if its relation is supported
edgeIsWellDefined(Relation,Source,Target):-
		supportedRelation(Relation),
		node(Source),
		node(Target).

% Name of Node
name_sig(Name, tuple(Params), Sep, NameSig):-
    !, maplist(full_name_to_local_name, Params, Ps0),
    atomic_list_concat(Ps0,'_', Ps),
    atomic_list_concat([Name, Sep, Ps], NameSig).

name_sig(Name, Param, Sep, NameSig):-
    full_name_to_local_name(Param, Param0),
    atomic_list_concat([Name, Sep, Param0], NameSig).

	
nameOfNode(Id,NameSig):- node(Id,constructor, Name, arrow(Params, _)), 
			 name_sig(Name, Params, '#_', NameSig),!.

nameOfNode(Id,NameSig):- node(Id,method, Name, arrow(Params, _)),
			 name_sig(Name, Params, '__', NameSig),!.

nameOfNode(Id,Name):- node(Id,_,Name,_).

%nameOfNode(Id,NameSig):- node(Id, Kind, Name, Type),
%		(Kind=constructor *-> Type=arrow(Params,_), name_sig(Name,Params, '#_', NameSig);
%		(Kind=method *-> Type=arrow(Params,_), name_sig(Name,Params, '__', NameSig);
%			Name=NameSig)).

% full name : names on the path to node thru scopes
fullName(Node,FullName):- 
		pathToNode(Node,Path),
		namesOnPath(Path, Names),
		removeDefaultPackage(Names,NamesWithoutDefaultPackage),
		atomic_list_concat(NamesWithoutDefaultPackage,'.',FullName).


full_name_to_local_name(FullName, LocalName):-
    atomic_list_concat(Names,'.',FullName),
    last(Names, LocalName).



removeDefaultPackage(['<default package>'|Names], Names):-!.
removeDefaultPackage(Names,Names).

namesOnPath([],[]).
namesOnPath([Node|Nodes],[Name|Names]):- 
		nameOfNode(Node,Name),
		namesOnPath(Nodes,Names).
		
% edge(contains, Root, VP) :- root(Root), node(VP,virtualScope,_).
					   
% toplevel : no owner					   
toplevel(Node):- node(Node), \+ contains(_,Node).					   

% Path to Node through scopes 
pathToNode(Node,Path):- 
		path(Node,[],Path).
		
path(Top, Path, [Top|Path]):-
		toplevel(Top).
path(Node,Path,Result):-
		contains(Owner,Node),
		path(Owner,[Node|Path],Result).

% Node has these Owners with that Kind and that Name
owners(Node, Owners, Kind, Name) :- 
		node(Node),
		findall(Owner, ( node(Owner, Kind, Name),
				     edge(contains, Owner, Node) ),
				Owners).
				
% Single Node
isaSingleNode(Node):-
		node(Node),
		findall(Node, node(Node), Nodes),
		length(Nodes,1).

%hasOneKind(Node):-
%		node(Node),
%		findall(Kind, node(Node, Kind,_,_), Kinds),
%		length(Kinds,1).

% Node has one owner of that Kind
hasOneOwnerOfKind(Node, Kind):-
		node(Node),
		owners(Node, Owners, Kind, _),
		length(Owners,1).

% Node has at most one owner
hasAtMostOneOwner(Node):-
		node(Node),
		owners(Node,Owners,_,_),
		length(Owners,L),
		L =< 1.
	
% Node has one full name
hasOneFullName(Node):-
		node(Node),
		findall(FullName, fullName(Node, FullName), FullNames),
		length(FullNames,1).
	
% Full name is that of one node
isFullNameOfOneNode(FullName):-
		findall(Node, fullName(Node, FullName), Nodes),
		length(Nodes,1).


