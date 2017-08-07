package de.dfki.mlt.rudimant.agent;

import java.util.Set;

import org.apache.thrift.TException;

import de.dfki.lt.hfc.db.QueryException;
import de.dfki.lt.hfc.db.QueryResult;
import de.dfki.lt.hfc.db.TupleException;
import de.dfki.lt.hfc.db.rdfProxy.DbClient;
import de.dfki.lt.hfc.db.server.StreamingClient;
import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.tecs.rpc.RPCFactory;

public class RdfProxyClient implements DbClient {

  private HfcDbService.Client _client;

  public RdfProxyClient(String host, int port) {
    _client = RPCFactory.createSyncClient(HfcDbService.Client.class,
        host, port);
  }

  @Override
  public void registerStreamingClient(StreamingClient sc) {
    throw new UnsupportedOperationException("Currently not available");
  }

  @Override
  public String getNewId(String namespace, String clazzUri)
      throws TupleException {
    try {
      return _client.getNewId(namespace, clazzUri);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public QueryResult selectQuery(String query) throws QueryException {
    try {
      return _client.selectQuery(query);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getValue(String uri, String predicate)
      throws QueryException, TupleException {
    try {
      return _client.getValue(uri, predicate);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int setValue(String uri, String fieldName, String value)
      throws TupleException {
    try {
      return _client.setValue(uri, fieldName, value);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Set<String> getMultiValue(String uri, String property)
      throws TupleException, QueryException {
    try {
      return _client.getMultiValue(uri, property);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int addToMultiValue(String uri, String property, String val)
      throws TupleException, QueryException {
    try {
      return _client.addToMultiValue(uri, property, val);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int removeFromMultiValue(String uri, String property, String val)
      throws TupleException, QueryException {
    try {
      return _client.removeFromMultiValue(uri, property, val);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int setMultiValue(String uri, String property, Set<String> values)
      throws TupleException, QueryException {
    try {
      return _client.setMultiValue(uri, property, values);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

}
