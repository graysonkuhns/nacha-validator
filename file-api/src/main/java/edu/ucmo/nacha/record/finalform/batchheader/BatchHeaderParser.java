package edu.ucmo.nacha.record.finalform.batchheader;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link BatchHeader} parser.
 *
 * @author King Butcher
 */
@Singleton
public class BatchHeaderParser implements SpecializedRecordParser<BatchHeader> {

  // Dependencies
  private final FieldParser fieldParser;
  private final BatchHeaderFactory batchHeaderFactory;

  /**
   * Constructor.
   *
   * @param fieldParser The {@link FieldParser}.
   * @param batchHeaderFactory The {@link BatchHeaderFactory}.
   */
  @Inject
  BatchHeaderParser(
      final FieldParser fieldParser,
      final BatchHeaderFactory batchHeaderFactory) {

    this.fieldParser = fieldParser;
    this.batchHeaderFactory = batchHeaderFactory;
  }

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  @Override
  public RecordType getSupportedRecordType() {
    return RecordType.BATCH_HEADER;
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public BatchHeader parse(final IntermediateRecord record) {
    // Parse fields
    final int serviceClassCode = fieldParser.getInt(record, RecordField.BH_SERVICE_CLASS_CODE);
    final String companyName = fieldParser.getString(record, RecordField.BH_COMPANY_NAME);
    final String discretionaryData = fieldParser.getStringOrNull(record, RecordField.BH_DISCRETIONARY_DATA);
    final String companyIdentification = fieldParser.getString(record, RecordField.BH_COMPANY_IDENTIFICATION);
    final String standardEntry = fieldParser.getString(record, RecordField.BH_STANDARD_ENTRY);
    final String entryDescription = fieldParser.getString(record, RecordField.BH_ENTRY_DESCRIPTION);
    final String descriptiveDate = fieldParser.getStringOrNull(record, RecordField.BH_DESCRIPTIVE_DATE);
    final String effectiveEntryDate = fieldParser.getString(record, RecordField.BH_EFFECTIVE_ENTRY_DATE);
    final String settlementDate = fieldParser.getStringOrNull(record, RecordField.BH_SETTLEMENT_DATE);
    final String originatorStatusCode = fieldParser.getString(record, RecordField.BH_ORIGINATOR_STATUS_CODE);
    final String originatingDfiId = fieldParser.getString(record, RecordField.BH_ORIGINATING_DFI_ID);
    final long batchNumber = fieldParser.getLong(record, RecordField.BH_BATCH_NUMBER);

    // Create the record
    return batchHeaderFactory.create(
        serviceClassCode,
        companyName,
        discretionaryData,
        companyIdentification,
        standardEntry,
        entryDescription,
        descriptiveDate,
        effectiveEntryDate,
        settlementDate,
        originatorStatusCode,
        originatingDfiId,
        batchNumber);
  }
}
