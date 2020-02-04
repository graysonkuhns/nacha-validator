package edu.ucmo.nacha.file;

/**
 * Malformed NACHA file exception.
 *
 * @author Grayson Kuhns
 */
public class MalformedNachaFileException extends RuntimeException {

  /**
   * Constructor.
   */
  public MalformedNachaFileException() {
  }

  /**
   * Constructor.
   *
   * @param message The failure message.
   */
  public MalformedNachaFileException(final String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param cause The cause of the failure.
   */
  public MalformedNachaFileException(final Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   *
   * @param message The failure message.
   * @param cause The cause of the failure.
   */
  public MalformedNachaFileException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
