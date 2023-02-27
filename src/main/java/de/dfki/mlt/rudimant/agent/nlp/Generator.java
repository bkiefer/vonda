package de.dfki.mlt.rudimant.agent.nlp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;

public abstract class Generator extends NLProcessor {
  protected static Logger logger = LoggerFactory.getLogger(Generator.class);

  public abstract Pair<String, String> generate(DagNode dialAct);
}


