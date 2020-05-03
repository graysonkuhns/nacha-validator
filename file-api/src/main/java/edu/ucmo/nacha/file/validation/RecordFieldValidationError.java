package edu.ucmo.nacha.file.validation;

import edu.ucmo.nacha.record.RecordField;

public class RecordFieldValidationError {

  private final int recordIndex;
  private final RecordField field;
  private final String errorMessage;

  public RecordFieldValidationError(
      final int recordIndex,
      final RecordField field,
      final String errorMessage) {

    this.recordIndex = recordIndex;
    this.field = field;
    this.errorMessage = errorMessage;
  }

  /**
   * Gets the record index.
   *
   * @return The record index.
   */
  public int getRecordIndex() {
    return recordIndex;
  }

  /**
   * Gets the {@link RecordField}.
   *
   * @return The {@link RecordField}.
   */
  public RecordField getField() {
    return field;
  }

  /**
   * Gets the error message.
   *
   * @return The error message.
   */
  public String getErrorMessage() {
    return errorMessage;
  }
}
