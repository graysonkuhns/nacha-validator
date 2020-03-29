package edu.ucmo.nacha.record;

import java.util.Map;

/**
 * Default {@link Record} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultRecord implements Record {

  // Properties
  private final RecordType type;
  private final Map<RecordField, String> fields;

  /**
   * Constructor.
   *
   * @param type The {@link RecordType}.
   * @param fields The fields.
   */
  DefaultRecord(
      final RecordType type,
      final Map<RecordField, String> fields) {

    this.type = type;
    this.fields = fields;
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  @Override
  public RecordType getType() {
    return type;
  }

  /**
   * Gets the fields.
   *
   * @return The fields.
   */
  @Override
  public Map<RecordField, String> getFields() {
    return fields;
  }
}
