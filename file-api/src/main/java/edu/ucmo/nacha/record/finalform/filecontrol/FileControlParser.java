package edu.ucmo.nacha.record.finalform.filecontrol;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link FileControl} parser.
 *
 * @author Garrett Ewens
 */
@Singleton
public class FileControlParser implements SpecializedRecordParser<FileControl> {

  // Dependencies
  private final FieldParser fieldParser;
  private final FileControlFactory fileControlFactory;

  /**
   * Constructor.
   *
   * @param fieldParser The {@link FieldParser}
   * @param fileControlFactory The {@link FileControlFactory}.
   */
  @Inject
  FileControlParser(
      final FieldParser fieldParser,
      final FileControlFactory fileControlFactory) {

    this.fieldParser = fieldParser;
    this.fileControlFactory = fileControlFactory;
  }

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  @Override
  public RecordType getSupportedRecordType() {
    return RecordType.FILE_CONTROL;
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public FileControl parse(IntermediateRecord record) {
    // Parse fields
    long batchCount = fieldParser.getLong(record, RecordField.FC_BATCH_COUNT);
    long blockCount = fieldParser.getLong(record, RecordField.FC_BLOCK_COUNT);
    long entryAndAddendaCount = fieldParser.getLong(record, RecordField.FC_ENTRY_AND_ADDENDA_COUNT);
    long entryHash = fieldParser.getLong(record, RecordField.FC_ENTRY_HASH);
    double debitAmount = fieldParser.getDouble(record, RecordField.FC_DEBIT_AMOUNT);
    double creditAmount = fieldParser.getDouble(record, RecordField.FC_CREDIT_AMOUNT);
    String reserved = fieldParser.getStringOrNull(record, RecordField.FC_RESERVED);

    // Create the record
    return fileControlFactory.create(
        batchCount,
        blockCount,
        entryAndAddendaCount,
        entryHash,
        debitAmount,
        creditAmount,
        reserved);
  }
}
