package de.dfki.mlt.rudimant.common;

import de.dfki.mlt.rudimant.compiler.Location;

public class ErrorInfo {
  private Location _location;

  private String _message;

  public ErrorInfo() {}

  public ErrorInfo(String m, Location l) {
    _message = m;
    _location = l;
  }

  public Location getLocation() {
    return _location;
  }

  public void setLocation(Location location) {
    this._location = location;
  }

  public String getMessage() {
    return _message;
  }

  public void setMessage(String message) {
    this._message = message;
  }


}
