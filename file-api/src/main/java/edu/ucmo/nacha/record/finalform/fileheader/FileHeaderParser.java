package edu.ucmo.nacha.record.finalform.fileheader;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link FileHeader} parser.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class FileHeaderParser implements SpecializedRecordParser<FileHeader> {

  // Dependencies
  private final FileHeaderFactory fileHeaderFactory;
  private final FieldParser fieldParser;

  /**
   * Constructor.
   *
   * @param fileHeaderFactory The {@link FileHeaderFactory}.
   * @param fieldParser The {@link FieldParser}.
   */
  @Inject
  FileHeaderParser(
      final FileHeaderFactory fileHeaderFactory,
      final FieldParser fieldParser) {

    this.fileHeaderFactory = fileHeaderFactory;
    this.fieldParser = fieldParser;
  }

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  @Override
  public RecordType getSupportedRecordType() {
    return RecordType.FILE_HEADER;
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public FileHeader parse(final IntermediateRecord record) {
    // Parse the fields
    final int priorityCode = fieldParser.getInt(record, RecordField.FH_PRIORITY_CODE);
    final long immediateDestination = fieldParser.getLong(record, RecordField.FH_IMMEDIATE_DESTINATION);
    final long immediateOrigin = fieldParser.getLong(record, RecordField.FH_IMMEDIATE_ORIGIN);
    final String fileCreationDate = fieldParser.getString(record, RecordField.FH_FILE_CREATION_DATE);
    final String fileCreationTime = fieldParser.getStringOrNull(record, RecordField.FH_FILE_CREATION_TIME);
    final String fileIdModifier = fieldParser.getString(record, RecordField.FH_FILE_ID_MODIFIER);
    final int recordSize = fieldParser.getInt(record, RecordField.FH_RECORD_SIZE);
    final int blockingFactor = fieldParser.getInt(record, RecordField.FH_BLOCKING_FACTOR);
    final int formatCode = fieldParser.getInt(record, RecordField.FH_FORMAT_CODE);
    final String immediateDestinationName = fieldParser.getStringOrNull(record, RecordField.FH_IMMEDIATE_DESTINATION_NAME);
    final String immediateOriginName = fieldParser.getStringOrNull(record, RecordField.FH_IMMEDIATE_ORIGIN_NAME);
    final String referenceCode = fieldParser.getStringOrNull(record, RecordField.FH_REFERENCE_CODE);

    // Create the record
    return fileHeaderFactory.create(
        record.getIndex(),
        priorityCode,
        immediateDestination,
        immediateOrigin,
        fileCreationDate,
        fileCreationTime,
        fileIdModifier,
        recordSize,
        blockingFactor,
        formatCode,
        immediateDestinationName,
        immediateOriginName,
        referenceCode);
  }
}
