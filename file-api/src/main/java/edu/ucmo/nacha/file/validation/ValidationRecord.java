package edu.ucmo.nacha.file.validation;

import edu.ucmo.nacha.record.RecordField;
import java.util.ArrayList;
import java.util.List;

/**
 * Validation record.
 *
 * @author Grayson Kuhns
 */
public class ValidationRecord {

  // Properties
  private final List<RecordFieldValidationError> recordFieldValidationErrors = new ArrayList<>();

  /**
   * Record a validation error pertaining to a {@link RecordField}.
   *
   * @param recordIndex The record index.
   * @param field The {@link RecordField}.
   * @param errorMessage The error message.
   */
  public void addFieldValidationError(
      final int recordIndex,
      final RecordField field,
      final String errorMessage) {

    recordFieldValidationErrors.add(
        new RecordFieldValidationError(recordIndex, field, errorMessage));
  }

  /**
   * Gets the {@link RecordFieldValidationError}s.
   *
   * @return The {@link RecordFieldValidationError}s.
   */
  public List<RecordFieldValidationError> getRecordFieldValidationErrors() {
    return recordFieldValidationErrors;
  }
}
