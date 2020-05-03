package edu.ucmo.nacha.record.intermediate;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.Map;

/**
 * Record model.
 *
 * @author Grayson Kuhns
 */
public interface IntermediateRecord {

  /**
   * Gets the record index.
   *
   * @return The record index.
   */
  int getIndex();

  /**
   * Gets the type.
   *
   * @return The type.
   */
  RecordType getType();

  /**
   * Gets the fields.
   *
   * @return The fields.
   */
  Map<RecordField, String> getFields();
}
