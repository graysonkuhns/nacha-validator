package edu.ucmo.nacha.record;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Record fields.
 *
 * @author Grayson Kuhns
 */
public enum RecordField {

  // File Header
  FH_PRIORITY_CODE(RecordType.FILE_HEADER, 2, 3),
  FH_IMMEDIATE_DESTINATION(RecordType.FILE_HEADER, 4, 13),
  FH_IMMEDIATE_ORIGIN(RecordType.FILE_HEADER, 14, 23),
  FH_FILE_CREATION_DATE(RecordType.FILE_HEADER, 24, 29),
  FH_FILE_CREATION_TIME(RecordType.FILE_HEADER, 30, 33),
  FH_FILE_ID_MODIFIER(RecordType.FILE_HEADER, 34, 34),
  FH_RECORD_SIZE(RecordType.FILE_HEADER, 35, 37),
  FH_BLOCKING_FACTOR(RecordType.FILE_HEADER, 38, 39),
  FH_FORMAT_CODE(RecordType.FILE_HEADER, 40, 40),
  FH_IMMEDIATE_DESTINATION_NAME(RecordType.FILE_HEADER, 41, 63),
  FH_IMMEDIATE_ORIGIN_NAME(RecordType.FILE_HEADER, 64, 86),
  FH_REFERENCE_CODE(RecordType.FILE_HEADER, 87, 94),

  // Entry Detail
  ED_TRANSACTION_TYPE(RecordType.ENTRY_DETAIL, 2, 3),
  ED_RECEIVER_ROUTING_NUMBER(RecordType.ENTRY_DETAIL, 4, 11),
  ED_RECEIVER_ROUTING_NUMBER_CHECK_DIGIT(RecordType.ENTRY_DETAIL, 12, 12),
  ED_RECEIVER_ACCOUNT_NUMBER(RecordType.ENTRY_DETAIL, 13, 29),
  ED_TRANSACTION_AMOUNT(RecordType.ENTRY_DETAIL, 30, 39),
  ED_RECEIVER_ID_NUMBER(RecordType.ENTRY_DETAIL, 40, 54),
  ED_RECEIVER_NAME(RecordType.ENTRY_DETAIL, 55, 76),
  ED_DISCRETIONARY_DATA(RecordType.ENTRY_DETAIL, 77, 78),
  ED_HAS_ADDENDA(RecordType.ENTRY_DETAIL, 79, 79),
  ED_TRACE_NUMBER(RecordType.ENTRY_DETAIL, 80, 94),

  // Entry Detail Addenda
  EDA_TYPE_CODE(RecordType.ENTRY_DETAIL_ADDENDA, 2, 3),
  EDA_PAYMENT_INFORMATION(RecordType.ENTRY_DETAIL_ADDENDA, 4, 83),
  EDA_ADDENDA_SEQUENCE_NUMBER(RecordType.ENTRY_DETAIL_ADDENDA, 84, 87),
  EDA_ENTRY_SEQUENCE_NUMBER(RecordType.ENTRY_DETAIL_ADDENDA, 88, 94),

  // Batch Header
  BH_SERVICE_CLASS_CODE(RecordType.BATCH_HEADER, 2, 4),
  BH_COMPANY_NAME(RecordType.BATCH_HEADER, 5, 20),
  BH_DISCRETIONARY_DATA(RecordType.BATCH_HEADER, 21, 40),
  BH_COMPANY_IDENTIFICATION(RecordType.BATCH_HEADER, 41, 50),
  BH_STANDARD_ENTRY(RecordType.BATCH_HEADER, 51, 53),
  BH_ENTRY_DESCRIPTION(RecordType.BATCH_HEADER, 54, 63),
  BH_DESCRIPTIVE_DATE(RecordType.BATCH_HEADER, 64, 69),
  BH_EFFECTIVE_ENTRY_DATE(RecordType.BATCH_HEADER, 70, 75),
  BH_SETTLEMENT_DATE(RecordType.BATCH_HEADER, 76, 78),
  BH_ORIGINATOR_STATUS_CODE(RecordType.BATCH_HEADER, 79, 79),
  BH_ORIGINATING_DFI_ID(RecordType.BATCH_HEADER, 80, 87),
  BH_BATCH_NUMBER(RecordType.BATCH_HEADER, 88, 94);

  // Properties
  private final RecordType recordType;
  private final int startPosition;
  private final int endPosition;

  /**
   * Constructor.
   *
   * @param recordType The {@link RecordType}.
   * @param startPosition The start position.
   * @param endPosition The end position.
   */
  RecordField(
      final RecordType recordType,
      final int startPosition,
      final int endPosition) {

    this.recordType = recordType;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  /**
   * Gets the {@link RecordType}.
   *
   * @return The {@link RecordType}.
   */
  public RecordType getRecordType() {
    return recordType;
  }

  /**
   * Gets the start position.
   *
   * @return The start position.
   */
  public int getStartPosition() {
    return startPosition;
  }

  /**
   * Gets the end position.
   *
   * @return The end position.
   */
  public int getEndPosition() {
    return endPosition;
  }

  /**
   * Gets the {@link RecordField}s for a {@link RecordType}.
   *
   * @param recordType The {@link RecordType}.
   * @return The {@link RecordField}s.
   */
  public static List<RecordField> getFields(final RecordType recordType) {
    return Arrays
        .stream(values())
        .filter(field -> field.getRecordType().equals(recordType))
        .collect(Collectors.toList());
  }
}
