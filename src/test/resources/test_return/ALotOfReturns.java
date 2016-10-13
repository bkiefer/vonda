import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ALotOfReturns extends NaoAgent {
  public static Logger logger = LoggerFactory.getLogger(ALotOfReturns.class);
  // add to this set the name of all rules you want to be logged
  private Set<String> rulesToLog = new HashSet<>();
  private Boolean wholeCondition = null;

  public void process() {
    this.init();
    LabelA();
    LabelZ();
  }
  // this is a file to test whether the return to label - functionality works as it is supposed to

  public void LabelA() {
    LabelA:
    if (true) {
      List<String> infoslots = new ArrayList<>();
      infoslots.add("Hobby");
      infoslots.add("Age");
      String x = infoslots[1];
      Object x = bla.schwafel.dingsbums;
      String d = "adf";
      //Rule LabelB
      boolean LabelB0 = "bla" != null;
      boolean LabelB1 = false;
      if (LabelB0) {
        LabelB1 = true;
      }
      boolean LabelB2 = LabelB0 && LabelB1;
      if (rulesToLog.contains("LabelB")) {
        HashMap<String, Boolean> LabelB = new HashMap<>();
        LabelB.put("\"bla\"  != null ", LabelB0);
        LabelB.put("true  ", LabelB1);
        LabelB.put("LabelB0&&LabelB1", LabelB2);
        LoggerFunction(LabelB, "LabelB", "ALotOfReturns");
      }
      LabelB:
      if ((LabelB0 && LabelB1)) { //kljljlj

      } else { //Rule LabelC
        boolean LabelC0 = 1 != 0;
        if (rulesToLog.contains("LabelC")) {
          HashMap<String, Boolean> LabelC = new HashMap<>();
          LabelC.put("1 != 0 ", LabelC0);
          LoggerFunction(LabelC, "LabelC", "ALotOfReturns");
        }
        LabelC:
        if (LabelC0) {
          break LabelA;
        }
      } //Rule LabelD
      boolean LabelD0 = d.equals("adf");
      if (rulesToLog.contains("LabelD")) {
        HashMap<String, Boolean> LabelD = new HashMap<>();
        LabelD.put("d.equals(\"adf\")  ", LabelD0);
        LoggerFunction(LabelD, "LabelD", "ALotOfReturns");
      }
      LabelD:
      if (LabelD0) {
        int s = 0;
      } else { //Rule LabelE
        LabelE:
        if (true) { // blablabla

          //Rule LabelF
          LabelF:
          if (false) {
            break LabelD;
          }
        }
      }
      return;
    }
  }

  public void LabelZ() {
    LabelZ:
    if (true) {
      String f = "this is a test";
    }
  }
}
