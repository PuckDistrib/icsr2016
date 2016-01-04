% translates a graph described in prolog	 into a .dot file
% File is the name of the output .dot file
% The default file name is 'graph.dot'
% nodes are of the form node(Node, Kind, Name)
% where Node is the name of the node (it will also be used in the .dot file)
%		Kind is package, class, method, attribute ...
%		Name is the (partial) name of the element denoted by the node
% edges are of the form edge(Kind, Source, Target)
% where Kind is isa, contains, uses ...
%		Source is the name of the source node
%		Target is the name of the target node

% January 2014: new version closer to UML to make access graphs more compact and esier to read
% classes and interfaces are rectangles
% methods and attributes are considered as "hooked nodes", displayed inside the rectangle,
% and are thus encoded inside the label of the class but with  ports (using the <port> notation)
% so that edges can still start from or end to them.

:- ensure_loaded('constraints.pl').

displayedNodeOf(Node, Class):-
	hookedIn(Node, Class),!.
displayedNodeOf(Node, Node).


subgraph(NodeId):- node(NodeId,package,_,_).
hook(NodeId):- classOrInterface(NodeId).

pl2dot(File, Filter, ShowIds):- 
	checkConstraints(Violations),
	tell(File),
	writeln('digraph G {'), %linux dotty parser doesn't accept anonym graph %'
	writeln('rankdir=LR;'),				
	writeln('ranksep=equally;'),				
	writeln('compound=true;'),				
	nodes2dot(Violations, ShowIds), %	nodes2dot(Violations, Filter),
	edges2dot(Violations, Filter),
	writeln('}'),
	told.

ru2dot(File) :- pl2dot(File, redUses, no_id),!.
ru2dot	   :- ru2dot('ru.dot').

pl2dot(File, ShowIds)   :-pl2dot(File, all, ShowIds),!.
pl2dot(File)            :-pl2dot(File, no_id). %%show_ids).
pl2dot                  :-pl2dot('graph.dot').
%% invisibleNode(Node):- node(Node), 'vContains*'(Scope,Node),
%% 		      invisibleScope(Scope).
	

% writes all the nodes to the current output in the .dot format
%% nodes2dot :- forall( (node(Node, _, _, _), \+contains(_, Node) , \+ invisibleNode(Node)),
%% 		     writelnNode(Node)).

%% parameterize type and constructors of the std lib uses the carcater < and > that have to be replaced for the html like labels
safe_name(UnsafeName, SafeName):-
	atomic_list_concat(N, '<', UnsafeName), atomic_list_concat(N, '&lt;', N2),
	atomic_list_concat(N3, '>', N2), atomic_list_concat(N3, '&gt;', SafeName).


nodes2dot(Violations, ShowIds) :- forall( (node(Node, _, _, _), \+contains(_, Node)),
		     writelnNode(Violations, ShowIds, Node)).

writelnNode(Violations, ShowIds, Id) :- subgraph(Id), writeln_subgraph(Violations, ShowIds, Id),!.
writelnNode(Violations, ShowIds, Id) :- hook(Id), writeln_hook(Violations, ShowIds, Id),!.
writelnNode(_, _, _):-!.

id_string(no_id, _, '').
id_string(show_ids, Id, Str):-atomic_list_concat([' (', Id, ')'], Str).	

writeln_subgraph(Violations, ShowIds, Id):-
    node(Id,Kind,Name0,_),
    safe_name(Name0, Name),
    name_decoration(Violations, Id, Color, Pre, Post),
    id_string(ShowIds, Id, IdStr),
    atomic_list_concat(['subgraph cluster', Id,' {\n', 
			'label= <<FONT COLOR="',Color,'">', Pre, '&lt;&lt;', Kind, '&gt;&gt; ', Name, IdStr, Post, '</FONT>>\n'], Str),
    write(Str),	
    findall(N, contains(Id,N), Ns),
    (length(Ns,0)*-> write(Id), write(' [label="" shape=none ]');
    maplist(call(writelnNode(Violations, ShowIds)), Ns)),
    writeln('}').

		

sort_class_aux(method, N, (Attrs, Cts, Ms, Others), (Attrs, Cts, [N|Ms], Others)):-!.
sort_class_aux(constructor, N, (Attrs, Cts, Ms, Others), (Attrs, [N|Cts], Ms, Others)):-!.
sort_class_aux(attribute, N, (Attrs, Cts, Ms, Others), ([N|Attrs], Cts, Ms, Others)):-!.
sort_class_aux(_, N, (Attrs, Cts, Ms, Others), (Attrs, Cts, Ms, [N|Others])).

sort_class(Id, SortIn, SortOut):- node(Id,Kind,_,_), sort_class_aux(Kind, Id, SortIn, SortOut).

name_decoration(Violations, Id, C, Pre, Post):- 
    member(edge(contains, _, Id), Violations) -> C=red, Pre='<U>', Post='</U>'; 
    C=black, Pre='', Post=''.

write_tr(Violations, ShowIds, Id) :-
    node(Id,Kind,Name0,_),
    safe_name(Name0, Name),
    name_decoration(Violations, Id, Color, Pre, Post),
    (Kind=method -> Parentheses='()'; Parentheses=''),
    id_string(ShowIds, Id, IdStr),
    atomic_list_concat(['<TR><TD PORT="', Id,'" ALIGN="LEFT" BORDER="0"><FONT COLOR="',Color,'">', Pre, Name, Parentheses, IdStr, Post,'</FONT></TD></TR>\n'], Str),
    write(Str).


writeln_hook(Violations, ShowIds, Id) :- 
    node(Id,Kind,Name0,_),
    safe_name(Name0, Name),
    node_kind_to_fill_color(Kind, Color),
    (Kind=interface*-> atomic_list_concat(['&lt;&lt;interface&gt;&gt;<BR/>', Name],UmlName);
     UmlName=Name),
    name_decoration(Violations, Id, FColor, Pre, Post),
    id_string(ShowIds, Id, IdStr),
    atomic_list_concat([Id, ' [ label = <<TABLE BGCOLOR="', Color,'"> <TR> <TD PORT="', Id, '" BORDER="0"> <FONT COLOR="',FColor,'"><B>', Pre, UmlName, IdStr, Post,'</B></FONT></TD></TR>\n'], NodeStr),
    write(NodeStr), 
    findall(CeeId, contains(Id, CeeId), Cees),
    foldl(sort_class, Cees, ([],[],[],[]), (Attrs, Cts, Ms, Others)),
    length(Attrs, LA),
    length(Ms, LM0),
    length(Cts, LC),
    LM is LC + LM0,
    ((LA\=0 ; LM\=0) -> write('<HR/>'); true),
    maplist(call(write_tr(Violations, ShowIds)), Attrs),
    ((LA\=0, LM\=0) -> write('<HR/>'); true),
    maplist(call(write_tr(Violations, ShowIds)), Cts),
    maplist(call(write_tr(Violations, ShowIds)), Ms),
    write('</TABLE>>, shape = "none" ];\n'),
    maplist(call(writeln_hook(Violations, ShowIds)), Others).

% writes all the edges to the current output in the .dot format
edges2dot(Violations):-
    forall((edge(Kind, Source, Target), \+subgraph(Source)),
	     member(edge(Kind, Source, Target), Violations)
	     -> writelnEdge(Kind, Source, Target, incorrect)
	     ; writelnEdge(Kind, Source, Target, correct) ) .

edges2dot(Violations, all):-
	edges2dot(Violations).

edges2dot(V, redUses):-
    list_to_set(V, Violations),
	forall(  (edge(Kind, Source, Target), \+subgraph(Source),
              (edge(isa, Source, Target) ; %print isa even if no violation
                member(edge(Kind, Source, Target), Violations))),
		 writelnEdge(Kind, Source, Target, incorrect)).
		 

dot_id(Id, DotId):-
    (hook(Id) -> CerId=Id; contains(CerId, Id)),
    atomic_list_concat([CerId,':',Id], DotId).
dot_id(Id, Id):- subgraph(Id).
    

writelnEdge(contains, Source, Target, Status) :- 
    !, (hook(Source), hook(Target), writeln_edge_aux(contains, Source, Target, Status); true).

writelnEdge(Kind, Source, Target, Status) :- writeln_edge_aux(Kind, Source, Target, Status).


writeln_edge_aux(Kind, Source, Target, Status) :- 
    dot_id(Source, SId),
    dot_id(Target, TId),
    kind2style(Kind, Style),
    status2Color(Status, Color),
    status2thickness(Status, Thick),
    kind2headStyle(Kind, HeadStyle),
    subgraph_arc(Source, 'ltail', TailStr),
    subgraph_arc(Target, 'lhead', HeadStr),
    atomic_list_concat(
	    [SId, ' -> ', TId, ' [', TailStr, HeadStr, 'style = ', Style, 
	     ', color = ', Color, ', penwidth = ', Thick, ', arrowhead = ', HeadStyle, ' ];\n'], Str),
    write(Str).


subgraph_arc(Id, Position, Str):- 
    subgraph(Id)*-> atomic_list_concat([Position, '=cluster',Id,', '], Str); %because of the comma do not put Str as last attribute
    Str=''.

status2Color(correct, black).
status2Color(incorrect, red).

status2thickness(correct, 1).
status2thickness(incorrect, 5).


kind2style(isa, dashed).
kind2style(contains, bold).
kind2style(virtualContains, bold).
kind2style(uses, solid).

kind2headStyle(isa, empty).
kind2headStyle(contains, 'open').
kind2headStyle(virtualContains, 'open').
kind2headStyle(uses, normal).

node_kind_to_fill_color(virtualScope,'#33FF33').%Green
node_kind_to_fill_color(package,'#FF9933').%Orange
node_kind_to_fill_color(interface,'#FFFF99').%Light yellow
node_kind_to_fill_color(class,'#FFFF33').%Yellow
node_kind_to_fill_color(constructor,'#FFFF33').%yellow
node_kind_to_fill_color(method,'#FFFFFF').%White
node_kind_to_fill_color(attribute,'#FFFFFF').%White
node_kind_to_fill_color(stringLiteral,'#CCFFCC').%Very light green
