package edu.ucmo.nacha.record.intermediate;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.Map;

/**
 * Default {@link IntermediateRecord} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultIntermediateRecord implements IntermediateRecord {

  // Properties
  private final int index;
  private final RecordType type;
  private final Map<RecordField, String> fields;

  /**
   * Constructor.
   *
   * @param index The index.
   * @param type The {@link RecordType}.
   * @param fields The fields.
   */
  DefaultIntermediateRecord(
      final int index,
      final RecordType type,
      final Map<RecordField, String> fields) {

    this.index = index;
    this.type = type;
    this.fields = fields;
  }

  /**
   * Gets the record index.
   *
   * @return The record index.
   */
  @Override
  public int getIndex() {
    return index;
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
