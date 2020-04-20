package edu.ucmo.nacha.record.finalform.field;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Singleton;

/**
 * Default {@link FieldParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultFieldParser implements FieldParser {

  /**
   * Gets a {@link String} field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  @Override
  public String getString(final IntermediateRecord record, final RecordField field) {
    final String data = getStringOrNull(record, field);

    if (data == null) {
      throw new FieldParseException(field, "Expected field to contain data");
    }

    return data;
  }

  /**
   * Gets a {@link String} field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data or null if the field is empty.
   */
  @Override
  public String getStringOrNull(final IntermediateRecord record, final RecordField field) {
    String data = record
        .getFields()
        .get(field);

    if (data == null) {
      return null;
    }

    data = data.trim();
    if (data.isEmpty()) {
      return null;
    }

    return data;
  }

  /**
   * Gets a int field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  @Override
  public int getInt(final IntermediateRecord record, final RecordField field) {
    // Get the field data
    final String data = getString(record, field);

    // Convert to number
    try {
      return Integer.parseInt(data);
    } catch (NumberFormatException ex) {
      throw new FieldParseException(field, ex);
    }
  }

  /**
   * Gets a long field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  @Override
  public long getLong(final IntermediateRecord record, final RecordField field) {
    // Get the field data
    final String data = getString(record, field);

    // Convert to number
    try {
      return Long.parseLong(data);
    } catch (NumberFormatException ex) {
      throw new FieldParseException(field, ex);
    }
  }

  /**
   * Gets a double field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  @Override
  public double getDouble(final IntermediateRecord record, final RecordField field) {
    // Get the field data
    final String data = getString(record, field);

    // Convert to number
    try {
      return Double.parseDouble(data);
    } catch (NumberFormatException ex) {
      throw new FieldParseException(field, ex);
    }
  }

  /**
   * Gets a boolean field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  @Override
  public boolean getBoolean(final IntermediateRecord record, final RecordField field) {
    // Get the field data
    final int data = getInt(record, field);

    // Convert to boolean
    switch (data) {
      case 0:
        return false;
      case 1:
        return true;
      default:
        throw new FieldParseException(field, "Expected field to contain boolean value");
    }
  }
}
