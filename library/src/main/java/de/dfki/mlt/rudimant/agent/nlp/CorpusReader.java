package de.dfki.mlt.rudimant.agent.nlp;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import de.dfki.mlt.rudimant.agent.nlp.EvalLangServices.Example;

public interface CorpusReader {

  default String getPrefix() { return ""; }

  Iterable<String> getCorpora();

  Iterable<Example> readCorpus(File corpusFile) throws IOException;

  void init(Map<String, Object> config);
}
