package de.dfki.mlt.rudimant.common;

import java.util.ArrayList;
import java.util.List;

public class ImportInfo extends BasicInfo {
  private List<ErrorInfo> _errors = new ArrayList<>();

  public ImportInfo() { }

  public ImportInfo(String name, int line, BasicInfo parent) {
    super(name, line, parent);
  }

  public List<ErrorInfo> getErrors() {
    return _errors;
  }

  public void setErrors(List<ErrorInfo> errors) {
    this._errors = errors;
  }

  public String getFilename() {
    return _label + ".rudi";
  }

}
