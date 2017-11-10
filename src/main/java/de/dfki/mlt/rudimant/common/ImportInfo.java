package de.dfki.mlt.rudimant.common;

import java.nio.file.Path;
import java.nio.file.Paths;
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

  public Path getFilePath() {
    return Paths.get(_label + ".rudi");
  }

}
