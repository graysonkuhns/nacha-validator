package edu.ucmo.nacha.file.record;

/**
 * Record field parser.
 *
 * @author Grayson Kuhns
 */
public interface RecordFieldParser {

  /**
   * Gets a {@link String} field in a record.
   *
   * <note>
   *   1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The {@link String} field.
   */
  String getString(String record, int start, int end);

  /**
   * Gets an int field in a record.
   *
   * <note>
   *   1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The int field.
   */
  int getInt(String record, int start, int end);

  /**
   * Gets a long field in a record.
   *
   * <note>
   *   1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The long field.
   */
  long getLong(String record, int start, int end);

  /**
   * Gets a double field in a record.
   *
   * <note>
   *   1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The double field.
   */
  double getDouble(String record, int start, int end);

  /**
   * Gets a boolean field in a record.
   *
   * <note>
   *   1 is the first position in a record.
   * </note>
   *
   * @param record The full record.
   * @param start The start position of the field, inclusive.
   * @param end The end position of the field, inclusive.
   * @return The boolean field.
   */
  boolean getBoolean(String record, int start, int end);
}