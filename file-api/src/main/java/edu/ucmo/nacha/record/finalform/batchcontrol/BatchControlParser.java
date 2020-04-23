package edu.ucmo.nacha.record.finalform.batchcontrol;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link BatchControl} parser.
 *
 * @author Garrett Ewens
 */
@Singleton
public class BatchControlParser implements SpecializedRecordParser<BatchControl> {

  // Dependencies
  private final FieldParser fieldParser;
  private final BatchControlFactory batchControlFactory;

  /**
   * Constructor.
   *
   * @param fieldParser The {@link FieldParser}.
   * @param batchControlFactory The {@link BatchControlFactory}.
   */
  @Inject
  BatchControlParser(
      final FieldParser fieldParser,
      final BatchControlFactory batchControlFactory) {

    this.fieldParser = fieldParser;
    this.batchControlFactory = batchControlFactory;
  }

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  @Override
  public RecordType getSupportedRecordType() {
    return RecordType.BATCH_CONTROL;
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public BatchControl parse(final IntermediateRecord record) {
    // Parse fields
    final int serviceClassCode = fieldParser.getInt(record, RecordField.BC_SERVICE_CLASS_CODE);
    final long entryAndAddendaCount = fieldParser.getLong(record, RecordField.BC_ENTRY_AND_ADDENDA_COUNT);
    final long entryHash = fieldParser.getLong(record, RecordField.BC_ENTRY_HASH);
    final double debitAmount = fieldParser.getDouble(record, RecordField.BC_DEBIT_AMOUNT);
    final double creditAmount = fieldParser.getDouble(record, RecordField.BC_CREDIT_AMOUNT);
    final String companyIdentification = fieldParser.getString(record, RecordField.BC_COMPANY_IDENTIFICATION);
    final String messageAuthentication = fieldParser.getStringOrNull(record, RecordField.BC_MESSAGE_AUTHENTICATION_CODE);
    final String reserved = fieldParser.getStringOrNull(record, RecordField.BC_RESERVED);
    final String originatingDfiId = fieldParser.getString(record, RecordField.BC_ORIGINATING_DFI_ID);
    final long batchNumber = fieldParser.getLong(record, RecordField.BC_BATCH_NUMBER);

    // Create the record
    return batchControlFactory.create(
        serviceClassCode,
        entryAndAddendaCount,
        entryHash,
        debitAmount,
        creditAmount,
        companyIdentification,
        messageAuthentication,
        reserved,
        originatingDfiId,
        batchNumber);
  }
}
