package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.intermediate.IntermediateRecord;

/**
 * Final-form {@link Record} parser.
 *
 * @param <T> The {@link Record} type.
 */
public interface RecordParser <T extends Record> {

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  T parse(IntermediateRecord record);
}
