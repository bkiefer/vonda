package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;

//import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.HfcDbHandler;
import de.dfki.lt.hfc.db.rdfProxy.DbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.mlt.rudimant.agent.Agent;
import de.dfki.mlt.rudimant.agent.Behaviour;
import de.dfki.mlt.rudimant.agent.CommunicationHub;

/** Example for how all things that should be shut down really are
 *  shut down, especially the StreamingService of the HfcDbHandler.
 *
 *  It can possibly be used as minimalistic Agent for other tests
 *
 * @author kiefer
 *
 */
public class ShutdownTest {

  private class TestAgent extends Agent {
    private DbClient handler;

    String ONTO_FILE_NAME = "../ontologies/inits/pal.inference.yml";

    private RdfProxy startClient(File configDir)
        throws IOException, WrongFormatException {
      handler = new HfcDbHandler(new File(configDir, ONTO_FILE_NAME).getPath());
      RdfProxy proxy = new RdfProxy(handler);
      return proxy;
    }

    public void init() throws WrongFormatException, IOException {
      Path confFile = Paths.get(RESOURCE_DIR, "miniproj", "config.yml");
      File confDir = confFile.toFile().getParentFile();
      Yaml yaml = new Yaml();
      @SuppressWarnings("unchecked")
      Map<String, Object> configs =
      (Map<String, Object>) yaml.load(new FileReader(confFile.toFile()));
      //configs.put("debugPort", 9777);
      RdfProxy proxy = startClient(confDir);
      // the last parameter sets the default namespace for creating instances to
      // `chatcat:`
      super.init(confDir, "de_DE", proxy, configs, "test");
    }

    @Override
    public int process() {
      return 0;
    }

    @Override
    public void shutdown() {
      ((HfcDbHandler)handler).shutdown();
      super.shutdown();
    }
  }

  private class StubClient implements CommunicationHub {
    private boolean isRunning = true;

    private TestAgent _agent;

    private Deque<Object> itemsToSend = new ArrayDeque<>();

    public void init() throws IOException, WrongFormatException {
      _agent = new TestAgent();
      _agent.init();
      _agent.setCommunicationHub(this);
    }

    private boolean isRunning() {
      return isRunning;
    }

    private void runReceiveSendCycle() {
      int ping = 0;
      while (isRunning()) {
        boolean emptyRun = true;
        System.out.print(++ping + " ");
        // if a proposal was executed, handle pending events now
        if (!_agent.waitForIntention()) {

          _agent.processRules();
        }
        synchronized (itemsToSend) {
          Object c = itemsToSend.peekFirst();
          if (c != null && (c instanceof Behaviour)
              && _agent.waitForBehaviours((Behaviour)c)) {
            c = null;
          }
          if (c != null) {
            itemsToSend.removeFirst();
            emptyRun = false;
          }
        }
        if (emptyRun) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException ex) {
            // shut down?
            System.out.println("Interrupt");
          }
        }
      }
      _agent.shutdown();
    }

    public void startListening() {
      // eventually connect to communication infrastructure
      // _communicationChannel.connect();
      Thread listenToClient = new Thread() {
        @Override
        public void run() { runReceiveSendCycle(); }
      };
      listenToClient.setName("ListenToEvents");
      //listenToClient.setDaemon(true);
      listenToClient.start();
    }

    public void shutdown() {
      // eventually disconnect from communication infrastructure
      // _communicationChannel.disconnect();
      isRunning = false;
    }

    @Override
    public void sendBehaviour(Behaviour b) { }

    @Override
    public void sendIntentions(Set<String> intentions) { }
  }

  public void test() throws WrongFormatException, IOException, InterruptedException {
    StubClient stub = new StubClient();
    stub.init();
    stub.startListening();
    Thread.sleep(3000);
    stub.shutdown();
    assertTrue(true);
  }

  public static void main(String[] args) throws WrongFormatException, IOException, InterruptedException {
    new ShutdownTest().test();
  }

}
