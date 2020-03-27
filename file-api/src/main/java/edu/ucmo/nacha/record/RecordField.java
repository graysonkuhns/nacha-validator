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
  ED_TRACE_NUMBER(RecordType.ENTRY_DETAIL, 80, 94);

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
