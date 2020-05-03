package edu.ucmo.nacha.record.finalform.entrydetailaddenda;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link EntryDetailAddenda} parser.
 *
 * @author King Butcher
 */
@Singleton
public class EntryDetailAddendaParser implements SpecializedRecordParser<EntryDetailAddenda> {

  // Dependencies
  private final FieldParser fieldParser;
  private final EntryDetailAddendaFactory entryDetailAddendaFactory;

  /**
   * Constructor.
   *
   * @param fieldParser The {@link FieldParser}.
   * @param entryDetailAddendaFactory The {@link EntryDetailAddendaFactory}.
   */
  @Inject
  EntryDetailAddendaParser(
      final FieldParser fieldParser,
      final EntryDetailAddendaFactory entryDetailAddendaFactory) {

    this.fieldParser = fieldParser;
    this.entryDetailAddendaFactory = entryDetailAddendaFactory;
  }

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  @Override
  public RecordType getSupportedRecordType() {
    return RecordType.ENTRY_DETAIL_ADDENDA;
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public EntryDetailAddenda parse(final IntermediateRecord record) {
    // Parse fields
    final int typeCode = fieldParser.getInt(record, RecordField.EDA_TYPE_CODE);
    final String paymentInformation = fieldParser.getStringOrNull(record, RecordField.EDA_PAYMENT_INFORMATION);
    final int addendaSequenceNumber = fieldParser.getInt(record, RecordField.EDA_ADDENDA_SEQUENCE_NUMBER);
    final int entrySequenceNumber = fieldParser.getInt(record, RecordField.EDA_ENTRY_SEQUENCE_NUMBER);

    // Create the record
    return entryDetailAddendaFactory.create(
        record.getIndex(),
        typeCode,
        paymentInformation,
        addendaSequenceNumber,
        entrySequenceNumber);
  }
}
