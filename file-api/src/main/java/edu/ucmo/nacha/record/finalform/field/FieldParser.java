package edu.ucmo.nacha.record.finalform.field;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;

/**
 * Field parser.
 *
 * @author Grayson Kuhns
 */
public interface FieldParser {

  /**
   * Gets a {@link String} field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  String getString(IntermediateRecord record, RecordField field);

  /**
   * Gets a {@link String} field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data or null if the field is empty.
   */
  String getStringOrNull(IntermediateRecord record, RecordField field);

  /**
   * Gets a int field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  int getInt(IntermediateRecord record, RecordField field);

  /**
   * Gets a long field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  long getLong(IntermediateRecord record, RecordField field);

  /**
   * Gets a double field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  double getDouble(IntermediateRecord record, RecordField field);

  /**
   * Gets a boolean field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  boolean getBoolean(IntermediateRecord record, RecordField field);

  /**
   * Gets a currency field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  double getCurrency(IntermediateRecord record, RecordField field);
}
