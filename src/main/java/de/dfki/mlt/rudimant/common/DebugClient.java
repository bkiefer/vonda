package de.dfki.mlt.rudimant.common;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * an XML-RPC Client to connect to the debugger in Java
 */
public class DebugClient implements LogPrinter {

  /** define the server URL without port number
   */
  private static String SERVER_URL = "http://localhost:";

  private static Logger logger = LoggerFactory.getLogger(DebugServer.class);

  XmlRpcClient client;

  /** A client that connects to the server on localhost at the given port to
   *  send log information to the debugger.
   */
  public DebugClient(int port) {
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    try {
      config.setServerURL(new URL(DebugClient.SERVER_URL));
    } catch (MalformedURLException e) {
      logger.error("Bad URL: " + e);
    }
    client = new XmlRpcClient();
    client.setConfig(config);
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    Object[] params = {ruleId, result};
    try {
      client.execute("Rudibugger.printLog", params);
    } catch (XmlRpcException e) {
      logger.error(e.getMessage());
    }
  }

}
