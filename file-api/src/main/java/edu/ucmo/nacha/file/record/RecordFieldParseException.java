package edu.ucmo.nacha.file.record;

import edu.ucmo.nacha.file.MalformedNachaFileException;

/**
 * Record field parse exception.
 *
 * @author Grayson Kuhns
 */
public class RecordFieldParseException extends MalformedNachaFileException {

  // Properties
  private final String record;
  private final String fieldName;
  private final RecordFieldType fieldType;
  private final int fieldStartPosition;
  private final int fieldEndPosition;

  /**
   * Legacy constructor.
   *
   * @param cause The cause.
   */
  public RecordFieldParseException(final Throwable cause) {
    super(cause);
    record = null;
    fieldName = null;
    fieldType = null;
    fieldStartPosition = 0;
    fieldEndPosition = 0;
  }

  /**
   * Constructor.
   *
   * @param record The record containing the field.
   * @param fieldName The field name.
   * @param fieldType The {@link RecordFieldType}.
   * @param fieldStartPosition The field start position.
   * @param fieldEndPosition The field end position.
   */
  public RecordFieldParseException(
      final String record,
      final String fieldName,
      final RecordFieldType fieldType,
      final int fieldStartPosition,
      final int fieldEndPosition) {

    this.record = record;
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.fieldStartPosition = fieldStartPosition;
    this.fieldEndPosition = fieldEndPosition;
  }

  /**
   * Constructor.
   *
   * @param record The record containing the field.
   * @param fieldName The field name.
   * @param fieldType The {@link RecordFieldType}.
   * @param fieldStartPosition The field start position.
   * @param fieldEndPosition The field end position.
   * @param message The failure message.
   */
  public RecordFieldParseException(
      final String record,
      final String fieldName,
      final RecordFieldType fieldType,
      final int fieldStartPosition,
      final int fieldEndPosition,
      final String message) {

    super(message);
    this.record = record;
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.fieldStartPosition = fieldStartPosition;
    this.fieldEndPosition = fieldEndPosition;
  }

  /**
   * Constructor.
   *
   * @param record The record containing the field.
   * @param fieldName The field name.
   * @param fieldType The {@link RecordFieldType}.
   * @param fieldStartPosition The field start position.
   * @param fieldEndPosition The field end position.
   * @param cause The failure cause.
   */
  public RecordFieldParseException(
      final String record,
      final String fieldName,
      final RecordFieldType fieldType,
      final int fieldStartPosition,
      final int fieldEndPosition,
      final Throwable cause) {

    super(cause);
    this.record = record;
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.fieldStartPosition = fieldStartPosition;
    this.fieldEndPosition = fieldEndPosition;
  }

  /**
   * Constructor.
   *
   * @param record The record containing the field.
   * @param fieldName The field name.
   * @param fieldType The {@link RecordFieldType}.
   * @param fieldStartPosition The field start position.
   * @param fieldEndPosition The field end position.
   */
  public RecordFieldParseException(
      final String record,
      final String fieldName,
      final RecordFieldType fieldType,
      final int fieldStartPosition,
      final int fieldEndPosition,
      final String message,
      final Throwable cause) {

    super(message, cause);
    this.record = record;
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.fieldStartPosition = fieldStartPosition;
    this.fieldEndPosition = fieldEndPosition;
  }

  /**
   * Gets the record containing the field.
   *
   * @return The record containing the field.
   */
  public String getRecord() {
    return record;
  }

  /**
   * Gets the field name.
   *
   * @return The field name.
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * Gets the {@link RecordFieldType}.
   *
   * @return The {@link RecordFieldType}.
   */
  public RecordFieldType getFieldType() {
    return fieldType;
  }

  /**
   * Gets the field start position.
   *
   * @return The field start position.
   */
  public int getFieldStartPosition() {
    return fieldStartPosition;
  }

  /**
   * Gets the field end position.
   *
   * @return The field end position.
   */
  public int getFieldEndPosition() {
    return fieldEndPosition;
  }
}
