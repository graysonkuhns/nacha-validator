package edu.ucmo.nacha.record.finalform.entrydetail;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link EntryDetail} parser.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class EntryDetailParser implements SpecializedRecordParser<EntryDetail> {

  // Dependencies
  private final FieldParser fieldParser;
  private final EntryDetailFactory entryDetailFactory;

  /**
   * Constructor.
   *
   * @param fieldParser The {@link FieldParser}.
   * @param entryDetailFactory The {@link EntryDetailFactory}.
   */
  @Inject
  EntryDetailParser(
      final FieldParser fieldParser,
      final EntryDetailFactory entryDetailFactory) {

    this.fieldParser = fieldParser;
    this.entryDetailFactory = entryDetailFactory;
  }

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
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public EntryDetail parse(final IntermediateRecord record) {

    int transactionType = 0;
    long receiverRoutingNumber = 0;
    int receiverRoutingNumberCheckDigit = 0;
    String receiverAccountNumber = fieldParser.getString(record, RecordField.ED_RECEIVER_ACCOUNT_NUMBER);
    double transactionAmount = 0;
    String receiverIdNumber = fieldParser.getStringOrNull(record, RecordField.ED_RECEIVER_ID_NUMBER);
    String receiverName = fieldParser.getString(record, RecordField.ED_RECEIVER_NAME);
    String discretionaryData = fieldParser.getStringOrNull(record, RecordField.ED_DISCRETIONARY_DATA);
    boolean hasAddenda = false;
    long traceNumber = 0;

    return entryDetailFactory.create(
        transactionType,
        receiverRoutingNumber,
        receiverRoutingNumberCheckDigit,
        receiverAccountNumber,
        transactionAmount,
        receiverIdNumber,
        receiverName,
        discretionaryData,
        hasAddenda,
        traceNumber);
  }
}