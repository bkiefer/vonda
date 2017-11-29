package de.dfki.mlt.rudimant.common;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * establishes an server in the agent to be able to modify which rules are
 * logged, and to see watched objects in the DB in the future
 */
public class DebugServer {

  private static Logger logger = LoggerFactory.getLogger(DebugServer.class);

  /**
   * the port number for the web server
   */
  private int port;

  /**
   * the web server that embodies the XML-RPC server
   */
  private WebServer webServer;

  /** Start a server that listens to the given port on the local machine
   *
   * @throws IOException
   */
  public DebugServer(int port) throws IOException {
    this.port = port;
  }

  /**
   * starts the server and assigns instance fields hfc and query to the static
   * class fields HFC and QUERY in class HfcServerApi
   *
   * @see HfcServerApi.stopServer()
   */
  public synchronized void startServer() {
    try {
      this.webServer = new WebServer(this.port);
      XmlRpcServer xmlRpcServer = this.webServer.getXmlRpcServer();
      PropertyHandlerMapping phm = new PropertyHandlerMapping();
      // HFC redirects the request to the static Query instance
      phm.addHandler("Agent", DebugAgentApi.class);
      xmlRpcServer.setHandlerMapping(phm);
      this.webServer.start();
      logger.info("Agent debug server started");
    } catch (XmlRpcException exception) {
      logger.error("Agent debug server: XML-RPC Fault #"
          + Integer.toString(exception.code)
          + ": " + exception.toString());
    } catch (Exception exception) {
      logger.error("Agent debug server: " + exception.toString());
    }
  }
}
