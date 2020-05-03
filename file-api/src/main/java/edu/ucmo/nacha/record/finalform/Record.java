package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.RecordType;

/**
 * Record model.
 *
 * @author Grayson Kuhns
 */
public interface Record {

  /**
   * Gets the index.
   *
   * @return The index.
   */
  int getIndex();

  /**
   * Gets the type.
   *
   * @return The type.
   */
  RecordType getType();
}
