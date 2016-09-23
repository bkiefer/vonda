package de.dfki.mlt.agent.rdf;

import static de.dfki.mlt.agent.rdf.Quiz.*;

import org.apache.thrift.TException;
import static de.dfki.mlt.agent.rdf.Hobby.*;
import static de.dfki.mlt.agent.rdf.Color.*;

import de.dfki.mlt.agent.Agent;
import de.dfki.lt.hfc.db.QueryException;
import de.dfki.lt.hfc.db.QueryResult;

public class Child extends RdfProxy{
  public static final String CHILD_NAME = "forename";
  public static final String CHILD_GENDER = "hasGender";
  private static final String CHILD_PREFERENCES = "hasPreference";
  public static final String CHILD_HOBBY = "Hobby";
  public static final String CHILD_COLOR = "Color";
  public static final String CHILD_BIRTHDATE = "birthdate";

  public static final String CHILD_SESSIONS = "hasSessions";
  public static final String CHILD_LOCATION = "inLocation";

  public Child(Agent agent) { super(agent); }

  public Child(Agent agent, String uri) { super(agent, uri); }

  public boolean hasPlayedQuiz() {
    try {
      QueryResult result = _agent._client.selectQuery(
          "select ?q where ?q <rdf:type> <dom:Quiz> ?t "
              + "& ?q <dom:" + QUIZ_PLAYER + "> " + get("id") + " ?t2");
      return (result.getTable().getRowsSize() != 0);
    }
    catch (QueryException ex) {
      _agent.logger.error("Wrong Query: {}", ex);
    } catch (TException e) {
      _agent.logger.error("Unknown Error: {}", e);
    }
    return false;
  }

  /** Get a liking string from the user
   *  TODO: A CRUDE HOAX, TO BE REPLACED BY SOMETHING SENSIBLE
   * @param theme
   * @return
   */
  public String getLiking(String theme) {
    Preference prefs = (Preference) get(CHILD_PREFERENCES);
    if (prefs == null) return null;
    RdfProxy o = prefs.get("hasLocal" + theme);
    if (o == null) return null;
    Object q = o.getLabel(_agent.getLanguage());
    return (String)((q != null) ? q : o.get("id"));
  }

  /** Get a liking string from the user
   *  TODO: A CRUDE HOAX, TO BE REPLACED BY SOMETHING SENSIBLE
   * @param theme
   * @return
   */
  public void setLiking(String theme, String value) {
    Preference prefs = (Preference) get(CHILD_PREFERENCES);
    if (prefs == null) {
      put(CHILD_PREFERENCES, prefs = new Preference(_agent));
    }
    RdfProxy r = null;
    switch (theme) {
    case "Color": r = new Color(_agent); r.put(COLOR_NAME, value); break;
    case "Hobby": r = new Hobby(_agent); r.put(HOBBY_NAME, value); break;
    }
    prefs.put("hasLocal" + theme, r);
  }

  static {
    // valid keys:
    String[][] valid = {
        // TODO: MATCH NAMES WITH ONTOLOGY
        { CHILD_NAME, "String" },
        { CHILD_GENDER, "String" },
        { CHILD_PREFERENCES, "Preference" },
        //{ CHILD_HOBBY, "String" },
        //{ CHILD_COLOR, "String" },
        { CHILD_BIRTHDATE, "Date" },
        { CHILD_SESSIONS, "RdfList<Session>" },
        { CHILD_LOCATION, "String" }
        // TODO: or just sessions, of which the last is the current one?
        // Here's another question: are the sessions embedded in the child, or
        // just listed, and the session contains the child to which it belongs
        // and can thus be retrieved?

        // we will assume the second version for the moment, because that means
        // the child data is changing less frequently.
        /*
        { "PreviousSessions", "SessionList" },
        { "CurrentSession", "Session" },
        { "PreviousQuizSessions", "QuizSessionList" },
        { "CurrentQuizSession", "QuizSession" }
        */
    };
    addValidKeys(Child.class, "<dom:Child>", valid);
  }
}
