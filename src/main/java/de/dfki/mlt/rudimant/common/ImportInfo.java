package de.dfki.mlt.rudimant.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImportInfo extends BasicInfo {
  private List<ErrorInfo> _errors = new ArrayList<>();
  private String[] relativePath;

  public ImportInfo() { }

  public ImportInfo(String name, String[] pathSpec, int line, BasicInfo parent) {
    super(name, line, parent);
    setRelativePath(pathSpec);
  }

  public List<ErrorInfo> getErrors() {
    return _errors;
  }

  public void setErrors(List<ErrorInfo> errors) {
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
