package edu.ucmo.nacha.record;

import java.util.Map;

/**
 * Record model.
 *
 * @author Grayson Kuhns
 */
public interface Record {

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
