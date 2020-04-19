package edu.ucmo.nacha.record.finalform.field;

import edu.ucmo.nacha.record.RecordField;

/**
 * Field parse exception.
 *
 * @author Grayson Kuhns
 */
public class FieldParseException extends RuntimeException {

  // Properties
  private final RecordField field;

  /**
   * Constructor.
   *
   * @param field The {@link RecordField}.
   * @param reason The reason for the failure.
   */
  public FieldParseException(final RecordField field, final String reason) {
    super(reason);
    this.field = field;
  }

  /**
   * Constructor.
   *
   * @param field The {@link RecordField}.
   * @param reason The reason for the failure.
   */
  public FieldParseException(final RecordField field, final Throwable reason) {
    super(reason);
    this.field = field;
  }

  /**
   * Gets the {@link RecordField}.
   *
   * @return The {@link RecordField}.
   */
  public RecordField getField() {
    return field;
  }
}
