**The Federated Ontology of the PAL Project**

Overall, the PAL ontology currently consists of _eight_
sub-ontologies, _seven_ of which are truly independent and do not have
knowledge of one another.
_One_ further ontology brings them together through the use of
hand-written interface axioms, employing axiom constructors such as
_rdfs:subClassOf_ and _owl:equivalentProperty_, or by posing
domain and range restrictions on certain underspecified properties.
The TBox and RBox of the PAL domain stays constant, i.e., will
_not_ change over time.  Only relation instances from the ABox
might undergo a temporal change, e.g., the weight of a child at
certain times, but, e.g., _not_ the birthdate.


Upper
=====
PAL makes use of a minimal and stripped-down upper ontology that
we have originally developed for the EU projects Musing, Monnet,
and TrendMiner (Krieger &amp; Declerck 2014), showing a tri-partite
division of the most general class _upp:Entity_, viz., _upp:Abstract_,
_upp:Happening_, and _upp:Physical_.
Most notable for PAL is the _upp:Happening_ representation which
distinguishes between atomic _upp:Situation_s and decomposable
_upp:Event_s, using properties such as _upp:startsWith_,
_upp:continuesWith_, and _upp:endsWith_.
This allows us to encode PDL-like processes and makes it also possible
to define pre- and post-conditions.
_upp:Happening_s are _upp:basedOn_ _upp:Entity_s, _upp:leadsTo_ other
_upp:Entity_s, and _upp:involves_ other _upp:Agent_s.

DIT++
=====
The DIT++ ontology is based on the taxonomy of dialogue acts, defined
by Harry Bunt and colleagues (Bunt et al. 2012).
The DIT++ taxonomy is translated into a subclass hierarchy, led by the
most general class _dial:DialogueAct_.
We have taken over the _general-purpose communicative functions_ and
parts  of the _dimension-specific communicative functions_.
The former dimension involves dialogue acts, such as _dial:Request_,
_dial:Instruct_, or _dial:AcceptSuggestion_.
The latter contains communicative acts which help to maintain a
dialogue, by indicating, e.g., _dial:AlloFeedback_ or _dial:Pausing_.
_dial:DialogueAct_s are equipped with several important properties,
such as _dial:sender_ and _dial:addressee_.
A dialogue act furthermore incorporates the (shallow) semantics of a
natural language utterance through property _dial:frame_.
Property _dial:follows_ records the temporal succession of dialogue
acts,  whereas _dial:refersTo_ allows to refer back to previously
introduced  dialogue acts (e.g., as used in indirect speech).


Time
====
The time ontology basically defines the classes
_time:DiachronicProperty_ and _time:Synchronic-Property_, making it
possible to characterize OWL  properties (via _rdf:type_) as being
able to undergo a temporal change or not, for instance
  _dom:birthdate rdf:type time:SynchronicProperty_
  _dom:weight rdf:type time:DiachronicProperty_
We have furthermore defined the property _time:assign_ to implement
the concept of an imperative, programming language variable that can
change  over time and whose time series needs to be recorded.

Logic
=====
The representation of transaction time in PAL needs to talk about the
_truth_ and _falsity_ of statements.  For this, we make use of a logic
ontology which includes even  more general _polarity_ values, such as
_don't know_ and _error_, arranged in a class subsumption hierarchy.

Domain
======
The domain ontology defines concepts and relation which are relevant
to the PAL domain, e.g., _dom:Activity_ (playing a game, cooking,
making a diary entry), _dom:Actor_ (child, family members, health
professionals), emotional _dom:Mood_, or (learning) _dom:Goal_s which
progress  over time (see below).
As the child (and its diabetes' history) is at the heart of the PAL
project, _dom:Child_ is consequently equipped with a large number of
properties, dealing with family relationships, serious issues
(hypoglycemia symptoms), hobbies, activities, or lab values.
_dom:LabValue_ bundles datatype properties relevant for the initial
anamnesis and the diabetes use case, such as _dom:bmi_ (body mass
index), _dom:height_, or _dom:bsl_ (blood sugar level).
It is worth noting that such datatype properties usually map to custom
XSD  datatypes, designed for PAL (see below).

Semantics
=========
The shallow semantic representation in PAL is loosely build on
_thematic_ relations or roles (Fillmore 1977), leading to general
_verb_ frames and including named arguments such as _sem:agent_,
_sem:patient_, _sem:theme_, or _sem:manner_ which can be found in
frameworks, such as VerbNet, Verb-Ocean, or FrameNet.
These properties are defined on the very general class _sem:Frame_ and
are domain-restricted by very general classes; for instance,
_sem:agent_ and _sem:patient_ map to the underspecified class
_sem:Actor_.
These general _docking_ classes will later be interfaced with more
specific classes from other sub-ontologies by means of interface axioms
(see below).
Even though the semantic representation is almost flat, additional
roles  such as _sem:purpose_ (typed to _sem:Frame_) allow us to build
up nested structures, say for a sentence like _OK, you will be asking_
(frame: _sem:AssigningRole_) in a natural language quiz scenario
between robot and child.

Goal
====
The goal ontology formalizes diabetes self-management progression and
is based on the Dutch _Diabetes "weet \amp; doe" doelen_ (know \& do
goals) as formulated by the EADV (http://www.eadv.nl/).
These recommendations structure knowledge and skills supposed to be
obtained by the child from onset to adolescence in order to gradually
increase autonomy. Thus, goals are attuned to age ranges and are
divided into important topics, such as _nutrition_ and _insulin_.
These goals are translated into subclasses of _goal:KnowledgeGoal_ and
_goal:SkillGoal_, led by the superclass _goal:T1DMGoal_.
One aim of the PAL system is to support self-management progression,
by offering educational content and activities. The PAL system
objectives that contribute to diabetes learning goals are defined as
subclasses of _goal:SupportingObjectives_.
Multilingual labels for Dutch, Italian, and English have been added to
the goal classes as they were used in the dialogue.
Properties, such as _goal:hasLevel_ (the suggested age range) and
_goal:hasProgress_ (capturing percentage of completion) are defined
on the general goal class _goal:Goal_.
Dependencies between goals are captured via property
_goal:requiresAsClass_ which directly operates on class objects.

Pal
===
The PAL ontology first of all imports the previously introduced
sub-ontologies, but also defines interface axioms in order to properly
integrate the distributed information.
This includes, e.g., restricting the domain and range of (possibly
underspecified) properties or identifying (subsuming) classes and
properties across ontologies.
For example:
  _dom:Actor = upp:Agent = dial:Agent = sem:Actor_
  _dom:Goal = goal:Goal &lt; upp:Event_
  _goal:contributesTo &lt; upp:leadsTo_
  _forall dial:frame.sem:Frame_
The _first_ axiom identifies the important actor/agent classes that
can be found in the various sub-ontologies.
The _second_ statement makes _goal:Goal_ (and _dom:Goal_) a subclass
of the very general class _upp:Event_ from the upper ontology (see
above). As a consequence, properties, such as _upp:startsWith_ or
_upp:continuesWith_, defined on _upp:Event_ become available in
instances of _goal:Goal_ (_goals behave like events, occupying
time_).
The _third_ declaration defines _goal:contributesTo_ as a subproperty
of the general property _upp:leadsTo_ and constraints  the relation
signature from _(upp:Happening, upp:Entity)_ to
_(goal:SupportingObjective, goal:T1DMLearningGoal)_.
The _fourth_ restriction links the underspecified dialogue act
property _dial:frame_ to shallow semantic frames (see above).

XSD Datatypes
=============
Some of the datatype properties from the domain ontology utilize
custom XSD types. For instance:
  * _body mass index dom:bmi_, measured in _xsd:kg\_m2_
  * _blood sugar level dom:bsl_, either measured in _xsd:mmol\_L_
    or _xsd:mg\_dL_
  * _diastolic blood pressure dom:dbp_, measured in _xsd:mmHg_

Changes from v1.0 to new version
================================
Identical:

* domain.owl
* pal.owl
* dmgoals.owl
* time.owl
* upper.owl

Enhanced:

* dialogue.owl
* logic.owl
* semantics.owl