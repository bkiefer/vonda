package de.dfki.mlt.agent.rdf;

// for the time-sensitive predicates, make a version that takes a boolean[]
// and sets its first value to true if the information is new.

// alternatively, we could do this actively: if a piece of information has
// been used, mark it as used, or clear the container!

// all methods that change values in this and other RDF proxy classes
// must signal that some data has changed!

/** Representation for the target of multi-value properties */
public class RdfSet<T> {

}
