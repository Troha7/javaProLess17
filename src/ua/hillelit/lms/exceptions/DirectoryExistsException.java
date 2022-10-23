package ua.hillelit.lms.exceptions;

import java.io.IOException;

public class DirectoryExistsException extends IOException {

  public DirectoryExistsException(String message) {
    super(message);
  }

}
