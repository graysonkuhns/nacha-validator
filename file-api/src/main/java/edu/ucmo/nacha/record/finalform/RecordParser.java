package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;

/**
 * Final-form {@link Record} parser.
 *
 * @param <T> The {@link Record} type.
 */
public interface RecordParser<T extends Record> {

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  RecordType getSupportedRecordType();

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param intermediateRecord The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  T parse(IntermediateRecord intermediateRecord);
}
