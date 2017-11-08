package de.dfki.mlt.rudimant.common;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportInfo extends BasicInfo {
  private List<ErrorInfo> _errors = new ArrayList<>();
  private Path _path;

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

  public Path getPath() {
    return _path;
  }

  public void setPath(Path path) {
    this._path = path;
  }
}
