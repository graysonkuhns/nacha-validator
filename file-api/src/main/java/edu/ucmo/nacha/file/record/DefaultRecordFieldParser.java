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
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The {@link String} field.
   */
  @Override
  public String getString(final String record, int start, int end) {
    // Convert CommerceBank's position specification to comp. sci. specification (start = 0).
    // We can leave the end position alone because Java's
    // substring method uses an exclusive end position.
    start--;

    // Get the field data
    final String field;
    try {
      field = record.substring(start, end);
    } catch (IndexOutOfBoundsException ex) {
      throw new RecordFieldParseException(ex);
    }

    // Remove leading and trailing whitespace from the field
    return field.trim();
  }

  /**
   * Gets an int field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The int field.
   */
  @Override
  public int getInt(final String record, final int start, final int end) {
    // Get the field data
    final String field = getString(record, start, end);

    // Convert to int
    return Integer.parseInt(field);
  }

  /**
   * Gets a long field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The long field.
   */
  @Override
  public long getLong(final String record, final int start, final int end) {
    // Get the field data
    final String field = getString(record, start, end);

    // Convert to long
    return Long.parseLong(field);
  }

  /**
   * Gets a double field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The double field.
   */
  @Override
  public double getDouble(final String record, final int start, final int end) {
    // Get the field data
    final String field = getString(record, start, end);

    // Convert to double
    return Double.parseDouble(field);
  }

  /**
   * Gets a boolean field in a record.
   *
   * <note>
   * 1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The boolean field.
   */
  @Override
  public boolean getBoolean(final String record, final int start, final int end) {
    // Get the field data
    final int field = getInt(record, start, end);

    // Convert to boolean
    switch (field) {
      case 0:
        return false;
      case 1:
        return true;
      default:
        throw new RuntimeException();
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
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The dollar amount field.
   */
  @Override
  public double getDollarAmount(final String record, final int start, final int end) {
    // Get the field data
    final String field = getString(record, start, end);

    if (field.length() < 2) {
      throw new RuntimeException("Less than 2 length");
    }

    String dollarString;
    String centsString;

    if (field.length() == 2) {
      dollarString = "0";
      centsString = field;
    } else {
      int centStart = field.length() - 2;
      dollarString = field.substring(0, centStart);
      centsString = field.substring(centStart);
    }

    centsString = "0.".concat(centsString);

    double total = Double.parseDouble(dollarString);
    total += Double.parseDouble(centsString);

    return total;
  }
}
