package de.dfki.mlt.rudimant.common;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportInfo extends BasicInfo {
  protected List<ErrorWarningInfo> _errors = new ArrayList<>();
  protected List<ErrorWarningInfo> _warnings = new ArrayList<>();
  protected String[] relativePath;

  public ImportInfo() { }

  public ImportInfo(String name, String[] pathSpec, int line, BasicInfo parent) {
    super(name, line, parent);
    setRelativePath(pathSpec);
  }

  public List<ErrorWarningInfo> getWarnings() {
    return _warnings;
  }

  public void setWarnings(List<ErrorWarningInfo> warnings) {
    this._warnings = warnings;
  }

  public List<ErrorWarningInfo> getErrors() {
    return _errors;
  }

  public void setErrors(List<ErrorWarningInfo> errors) {
    this._errors = errors;
  }

  public Path getFilePath() {
    File f = new File(".");
    for (String sub : relativePath) f = new File(f, sub);
    f = new File(f, _label + ".rudi");
    return f.toPath();
  }

  public String[] getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String[] relativePath) {
    this.relativePath = relativePath;
  }

}
