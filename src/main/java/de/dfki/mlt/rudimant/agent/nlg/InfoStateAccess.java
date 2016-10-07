package de.dfki.mlt.rudimant.agent.nlg;

import de.dfki.mlt.rudimant.agent.Agent;

public class InfoStateAccess implements BaseInfoStateAccess {

  private Agent _agent;

  private String ROBOT_NAME = "Nao";

  private String ROBOT_GENDER = "masc";

  public InfoStateAccess(Agent agent) {
    _agent = agent;
  }

  /**
   * This method gives mapping rules for proto LFs access to information state
   * variables coming from the user model or the game move generator
   *
   * @param name the name of the variable to retrieve
   * @return
   */
  public Object getInfoVar(String name) {
    return null;
  }
  /*
    String result = null;
    switch(name) {
    case "robotName": result = ROBOT_NAME; break;
    case "robotGender" :result = ROBOT_GENDER; break;
    case "currentGame": result = _agent.getActivityName(); break;
    case "Expressive": result = "yes"; break;
    case "Encounter":
      result = _agent.firstEncounter() ? "first" : "notfirst";
      break;
    case "Familiarity":
      result = _agent.isFamiliar() ? "yes" : "no";
      break;
    case "GivenName":
      // general information
      result = _agent.getUserName();
      break;
    case "Gender":
      // general information
      result = _agent.getUserGender();
      break;
    case "FavoriteColor":
      result = _agent.getUserColor();
      break;
    case "Hobby":
      result = _agent.getUserHobby();
      break;
    case "questionCount":
      result = Integer.toString(_agent.getNumberOfQuestionsCurrentAsker());
      break;
    case "hasPlayedGame":
      result = _agent.hasPlayedGame() ? "yes" : "no";
      break;
    case "Asker":
      result = _agent.getCurrentAsker();
      break;
    case "PlayerNumber":
      result = "sg";
      break;
    default:
      _agent.logger.warn("Unknown key requested from Information State: {}", name);
      break;
    }

    /*
    // TODO: What's this? Something which once worked and now does not because
    // the generation has not been checked / improved regularly
    if (name.equals("Expressive"))
      return "no";

    // FROM HERE ON: activity specific information
    SpecificData activityData = _activeUser.getActiveActivity();
    if (activityData == null) {
      // The name of the current game
      logger.error("No current activity for current user?");
      return null;
    }

    if (name.equals("currentGame"))
      return activityData.getActivityName().toLowerCase();

    return activityData.getValue(name);
    *
    // _agent.logger.debug("Asked for {}, returned {}", name, result);
    return result;
  }*/
}
