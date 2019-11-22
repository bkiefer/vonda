package de.dfki.chatcat;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HandlerFactory;
import de.dfki.lt.hfc.db.server.HfcDbHandler;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import de.dfki.mlt.rudimant.agent.Agent;
import de.dfki.mlt.rudimant.agent.Behaviour;
import de.dfki.mlt.rudimant.agent.DialogueAct;

public abstract class ChatAgent extends Agent implements Constants {

  Rdf user;
  Rdf robot;
  String DEFNS = "cat";

  private HfcDbHandler handler;
  private HfcDbServer server;

  private RdfProxy startClient(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    String ontoFileName = (String) configs.get(CFG_ONTOLOGY_FILE);
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    if (configs.containsKey(CFG_SERVER_PORT)) {
      server = new HfcDbServer();
      server.readConfig(new File(configDir, ontoFileName));
      server.runServer((int) configs.get(CFG_SERVER_PORT));
      handler = server.getHandler();
    } else {
      handler = HandlerFactory.getHandler();
      handler.readConfig(new File(configDir, ontoFileName));
    }
    RdfProxy proxy = new RdfProxy(handler);
    handler.registerStreamingClient(proxy);
    return proxy;
    }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void init(File configDir, String language, Map configs)
          throws IOException, WrongFormatException {
    RdfProxy proxy = startClient(configDir, configs);
    super.init(configDir, language, proxy, configs);
    robot = proxy.getRdf("<chatcat:robot1>");
  }

  public void shutdown() {
    handler.shutdown();
    if (server != null) server.shutdown();
    super.shutdown();
  }

  @Override
  protected Behaviour createBehaviour(int delay, DialogueAct da) {
    System.out.println("Returned DA: " + da.toString());
    return super.createBehaviour(delay, da);
  }
}