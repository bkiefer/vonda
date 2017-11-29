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

  /**
   * define the server URL plus port number
   */
  private static String SERVER_URL = "http://localhost:";
  //private static String SERVER_URL = "http://localhost:1408";

  private static Logger logger = LoggerFactory.getLogger(DebugServer.class);

  XmlRpcClient client;

  /**
   * call with, e.g., java -cp .:../lib/* de/dfki/lt/hfc/server/HfcClient
   * 'select distinct ?p where ?s ?p ?o' note the quotes (' ... ') to make the
   * query a single string alternatively, one might further supply the server
   * URL as a second argiment, e.g., java -cp .:./lib/*
   * de/dfki/lt/hfc/server/HfcClient 'select distinct ?p where ?s ?p ?o ?b ?e'
   * http://localhost:1408
   *
   * @throws MalformedURLException
   */
  public DebugClient(int port) {
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    try {
      config.setServerURL(new URL(DebugClient.SERVER_URL));
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
