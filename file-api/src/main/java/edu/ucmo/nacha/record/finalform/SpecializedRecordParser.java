package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.RecordType;

/**
 * Specialized final-form {@link Record} parser.
 *
 * @param <T> The {@link Record} type.
 */
public interface SpecializedRecordParser<T extends Record> extends RecordParser<T> {

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  RecordType getSupportedRecordType();
}
