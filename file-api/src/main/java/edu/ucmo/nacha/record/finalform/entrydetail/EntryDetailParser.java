package edu.ucmo.nacha.record.finalform.entrydetail;

import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.RecordParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;

/**
 * {@link EntryDetail} parser.
 *
 * @author Grayson Kuhns
 */
public class EntryDetailParser implements RecordParser<EntryDetail> {

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  @Override
  public RecordType getSupportedRecordType() {
    return RecordType.ENTRY_DETAIL;
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param intermediateRecord The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public EntryDetail parse(final IntermediateRecord intermediateRecord) {
    return null;
  }
}
