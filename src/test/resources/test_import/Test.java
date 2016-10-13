import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test extends NaoAgent {
  public static Logger logger = LoggerFactory.getLogger(Test.class);
  // add to this set the name of all rules you want to be logged
  private Set<String> rulesToLog = new HashSet<>();
  private Boolean wholeCondition = null;

  String activity = "j";

  public void process() {
    this.init();
    Blabla = new Bla();
    bla.process();
    rule();
  }

  public void rule() {
    boolean rule0 = 1 != 0;
    if (rulesToLog.contains("rule")) {
      HashMap<String, Boolean> rule = new HashMap<>();
      rule.put("1 != 0 ", rule0);
      LoggerFunction(rule, "rule", "Test");
    }
    rule:
    if (rule0) {
      activity = "blabla";
    }
  }
}
