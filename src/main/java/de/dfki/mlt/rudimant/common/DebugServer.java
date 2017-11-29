package de.dfki.mlt.rudimant.common;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.ForwardChainer;
import de.dfki.lt.hfc.Query;
import de.dfki.lt.hfc.WrongFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * establishes an XML-RPC server for querying information from the repository;
 * starting the server means to call the main method with exactly four arguments
 * @see main()
 *
 * @author Hans-Ulrich Krieger
 * @version Thu Jun 30 16:10:04 CEST 2011
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

	/**
	 * called by the main method and is given EXACTLY four arguments:
	 *   + port number
	 *   + path to namespace directory
	 *   + path to tuple directory
	 *   + path to rule directory
	 *
	 * NOTE: subdirectories, i.e., recursive embeddings of files are NOT allowed
	 * @throws IOException
	 * @throws WrongFormatException
	 * @throws FileNotFoundException
	 *
	 * @see main()
	 */
	private DebugServer(int port) throws IOException {
		this.port = port;
	}

	/**
	 * returns true iff the file does NOT start with a '.' and/or ends with a '~';
	 * otherwise false is returned
	 */
	private boolean isProperFile(String name) {
		if (name.charAt(0) == '.')
			return false;
		if (name.charAt(name.length() - 1) == '~')
			return false;
		return true;
	}

	/**
	 * starts the server and assigns instance fields hfc and query to the static
	 * class fields HFC and QUERY in class HfcServerApi
	 * @see HfcServerApi.stopServer()
	 */
	public synchronized void startServer() {
		try {
			this.webServer = new WebServer(this.port);
			XmlRpcServer xmlRpcServer = this.webServer.getXmlRpcServer();
			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			// HFC redirects the request to the static Query instance
			phm.addHandler("Agent", DebuggerAgentApi.class);
			xmlRpcServer.setHandlerMapping(phm);
			this.webServer.start();
			logger.info("\n Agent server started, waiting for input ...");
		}
		catch (XmlRpcException exception) {
			System.err.println("\n  HfcServer: XML-RPC Fault #" +
												 Integer.toString(exception.code) +
												 ": " + exception.toString());
		}
		catch (Exception exception) {
			System.err.println("\n  HfcServer: " + exception.toString());
		}
	}

	/**
	 * the main method requires EXACTLY four (4) arguments:
	 *   + 1st arg: port number (int)
	 *   + 2nd arg: namespace directory (String)
	 *   + 3rd arg: tuple directory (String)
	 *   + 4th arg: rule directory (String)
	 *
	 * call with, e.g.,
	 *   java -server -cp .:../lib/* -Xms800m -Xmx1200m de/dfki/lt/hfc/server/HfcServer 1408 ../resources/namespaces/ ../resources/tuples/ ../resources/rules/
	 * @throws IOException
	 * @throws WrongFormatException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, WrongFormatException, IOException {
		if (args.length != 4) {
			System.err.println("  wrong number of arguments; required (4): port-no namespace-dir tuple-dir rule-dir");
			System.exit(1);
		}
		System.out.println("\n  starting HFC Server ... ");
		DebugServer hs = new DebugServer(Integer.parseInt(args[0]));
		hs.startServer();
	}

}
