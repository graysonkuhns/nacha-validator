package edu.ucmo.nacha.file.record;

import com.google.inject.Singleton;

/**
 * Default {@link RecordFieldParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultRecordFieldParser implements RecordFieldParser {

  /**
   * Gets a {@link String} field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The {@link String} field.
   */
  @Override
  public String getString(
      final String record,
      final String fieldName,
      int start,
      int end) {

    // Convert CommerceBank's position specification to comp. sci. specification (start = 0).
    // We can leave the end position alone because Java's
    // substring method uses an exclusive end position.
    start--;

    // Get the field data
    final String field;
    try {
      field = record.substring(start, end);
    } catch (IndexOutOfBoundsException ex) {
      throw new RecordFieldParseException(
          record, fieldName, RecordFieldType.STRING, start, end, ex);
    }

    // Remove leading and trailing whitespace from the field
    return field.trim();
  }

  /**
   * Gets a {@link String} field in a record or null if the {@link String} is empty.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The {@link String} field or null.
   */
  @Override
  public String getStringOrNull(
      final String record,
      final String fieldName,
      final int start,
      final int end) {

    final String field = getString(record, fieldName, start, end);
    return field.isEmpty() ? null : field;
  }

  /**
   * Gets an int field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The int field.
   */
  @Override
  public int getInt(
      final String record,
      final String fieldName,
      final int start,
      final int end) {

    // Get the field data
    final String field = getString(record, fieldName, start, end);

    // Convert to int
    try {
      return Integer.parseInt(field);
    } catch (NumberFormatException ex) {
      throw new RecordFieldParseException(
          record, fieldName, RecordFieldType.INT, start, end, ex);
    }
  }

  /**
   * Gets a long field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The long field.
   */
  @Override
  public long getLong(
      final String record,
      final String fieldName,
      final int start,
      final int end) {

    // Get the field data
    final String field = getString(record, fieldName, start, end);

    // Convert to long
    try {
      return Long.parseLong(field);
    } catch (NumberFormatException ex) {
      throw new RecordFieldParseException(
          record, fieldName, RecordFieldType.LONG, start, end, ex);
    }
  }

  /**
   * Gets a double field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The double field.
   */
  @Override
  public double getDouble(
      final String record,
      final String fieldName,
      final int start,
      final int end) {

    // Get the field data
    final String field = getString(record, fieldName, start, end);

    // Convert to double
    try {
      return Double.parseDouble(field);
    } catch (NumberFormatException ex) {
      throw new RecordFieldParseException(
          record, fieldName, RecordFieldType.DOUBLE, start, end, ex);
    }
  }

  /**
   * Gets a boolean field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The boolean field.
   */
  @Override
  public boolean getBoolean(
      final String record,
      final String fieldName,
      final int start,
      final int end) {

    // Get the field data
    final int field = getInt(record, fieldName, start, end);

    // Convert to boolean
    switch (field) {
      case 0:
        return false;
      case 1:
        return true;
      default:
        throw new RecordFieldParseException(
            record,
            fieldName,
            RecordFieldType.BOOLEAN,
            start,
            end,
            String.format("%d could not be converted to boolean", field));
    }
  }

  /**
   * Gets a dollar amount field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param fieldName The field name.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The dollar amount field.
   */
  @Override
  public double getDollarAmount(
      final String record,
      final String fieldName,
      final int start,
      final int end) {

    // Get the field data
    final String field = getString(record, fieldName, start, end);

    // There must be more than 2 characters
    if (field.length() < 2) {
      throw new RecordFieldParseException(
          record,
          fieldName,
          RecordFieldType.INT,
          start,
          end,
          "The dollar amount character length cannot be less than 2");
    }

    // Extract the currency component values
    final String dollarString;
    final String centsString;

    if (field.length() == 2) {
      dollarString = "0";
      centsString = field;
    } else {
      final int centStart = field.length() - 2;
      dollarString = field.substring(0, centStart);
      centsString = field.substring(centStart);
    }

    // Parse the current component values
    final double dollars = Double.parseDouble(dollarString);
    final double cents = Double.parseDouble(centsString) / 100;

    // Calculate the sum
    return (dollars + cents);
  }
}
