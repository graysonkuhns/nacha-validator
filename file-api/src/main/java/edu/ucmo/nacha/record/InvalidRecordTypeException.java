package edu.ucmo.nacha.record;

/**
 * Invalid {@link RecordType} exception.
 *
 * @author Grayson Kuhns
 */
public class InvalidRecordTypeException extends IllegalArgumentException {

  // Messages
  private static final String MSG_TMPL = "Invalid type code: \"%s\"";

  /**
   * Constructor.
   *
   * @param typeCode The invalid type code.
   */
  public InvalidRecordTypeException(final int typeCode) {
    super(String.format(MSG_TMPL, typeCode));
  }

  /**
   * Constructor.
   *
   * @param typeCode The invalid type code.
   */
  public InvalidRecordTypeException(final String typeCode) {
    super(String.format(MSG_TMPL, typeCode));
  }
}
