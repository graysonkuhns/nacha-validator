package edu.ucmo.nacha.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Record fields.
 *
 * @author Grayson Kuhns
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RecordField {

  // File Header
  FH_PRIORITY_CODE(RecordType.FILE_HEADER, 2, 3, true),
  FH_IMMEDIATE_DESTINATION(RecordType.FILE_HEADER, 4, 13, true),
  FH_IMMEDIATE_ORIGIN(RecordType.FILE_HEADER, 14, 23, true),
  FH_FILE_CREATION_DATE(RecordType.FILE_HEADER, 24, 29, true),
  FH_FILE_CREATION_TIME(RecordType.FILE_HEADER, 30, 33, false),
  FH_FILE_ID_MODIFIER(RecordType.FILE_HEADER, 34, 34, true),
  FH_RECORD_SIZE(RecordType.FILE_HEADER, 35, 37, true),
  FH_BLOCKING_FACTOR(RecordType.FILE_HEADER, 38, 39, true),
  FH_FORMAT_CODE(RecordType.FILE_HEADER, 40, 40, true),
  FH_IMMEDIATE_DESTINATION_NAME(RecordType.FILE_HEADER, 41, 63, false),
  FH_IMMEDIATE_ORIGIN_NAME(RecordType.FILE_HEADER, 64, 86, false),
  FH_REFERENCE_CODE(RecordType.FILE_HEADER, 87, 94, false),
  
  // Batch Header
  BH_SERVICE_CLASS_CODE(RecordType.BATCH_HEADER, 2, 4, true),
  BH_COMPANY_NAME(RecordType.BATCH_HEADER, 5, 20, true),
  BH_DISCRETIONARY_DATA(RecordType.BATCH_HEADER, 21, 40, false),
  BH_COMPANY_IDENTIFICATION(RecordType.BATCH_HEADER, 41, 50, true),
  BH_STANDARD_ENTRY(RecordType.BATCH_HEADER, 51, 53, true),
  BH_ENTRY_DESCRIPTION(RecordType.BATCH_HEADER, 54, 63, true),
  BH_DESCRIPTIVE_DATE(RecordType.BATCH_HEADER, 64, 69, false),
  BH_EFFECTIVE_ENTRY_DATE(RecordType.BATCH_HEADER, 70, 75, true),
  BH_SETTLEMENT_DATE(RecordType.BATCH_HEADER, 76, 78, false),
  BH_ORIGINATOR_STATUS_CODE(RecordType.BATCH_HEADER, 79, 79, true),
  BH_ORIGINATING_DFI_ID(RecordType.BATCH_HEADER, 80, 87, true),
  BH_BATCH_NUMBER(RecordType.BATCH_HEADER, 88, 94, true),

  // Entry Detail
  ED_TRANSACTION_TYPE(RecordType.ENTRY_DETAIL, 2, 3, true),
  ED_RECEIVER_ROUTING_NUMBER(RecordType.ENTRY_DETAIL, 4, 11, true),
  ED_RECEIVER_ROUTING_NUMBER_CHECK_DIGIT(RecordType.ENTRY_DETAIL, 12, 12, true),
  ED_RECEIVER_ACCOUNT_NUMBER(RecordType.ENTRY_DETAIL, 13, 29, true),
  ED_TRANSACTION_AMOUNT(RecordType.ENTRY_DETAIL, 30, 39, true),
  ED_RECEIVER_ID_NUMBER(RecordType.ENTRY_DETAIL, 40, 54, false),
  ED_RECEIVER_NAME(RecordType.ENTRY_DETAIL, 55, 76, true),
  ED_DISCRETIONARY_DATA(RecordType.ENTRY_DETAIL, 77, 78, false),
  ED_HAS_ADDENDA(RecordType.ENTRY_DETAIL, 79, 79, true),
  ED_TRACE_NUMBER(RecordType.ENTRY_DETAIL, 80, 94, true),
  
  // Entry Detail Addenda
  EDA_TYPE_CODE(RecordType.ENTRY_DETAIL_ADDENDA, 2, 3, true),
  EDA_PAYMENT_INFORMATION(RecordType.ENTRY_DETAIL_ADDENDA, 4, 83, false),
  EDA_ADDENDA_SEQUENCE_NUMBER(RecordType.ENTRY_DETAIL_ADDENDA, 84, 87, true),
  EDA_ENTRY_SEQUENCE_NUMBER(RecordType.ENTRY_DETAIL_ADDENDA, 88, 94, true),

  // Batch Control
  BC_SERVICE_CLASS_CODE(RecordType.BATCH_CONTROL, 2, 4, true),
  BC_ENTRY_AND_ADDENDA_COUNT(RecordType.BATCH_CONTROL, 5, 10, true),
  BC_ENTRY_HASH(RecordType.BATCH_CONTROL, 11, 20, true),
  BC_DEBIT_AMOUNT(RecordType.BATCH_CONTROL, 21, 32, true),
  BC_CREDIT_AMOUNT(RecordType.BATCH_CONTROL, 33, 44, true),
  BC_COMPANY_IDENTIFICATION(RecordType.BATCH_CONTROL, 45, 54, true),
  BC_MESSAGE_AUTHENTICATION_CODE(RecordType.BATCH_CONTROL, 55, 73, false),
  BC_RESERVED(RecordType.BATCH_CONTROL, 74, 79, false),
  BC_ORIGINATING_DFI_ID(RecordType.BATCH_CONTROL, 80, 87, true),
  BC_BATCH_NUMBER(RecordType.BATCH_CONTROL, 88, 94, true),

  // File Control
  FC_BATCH_COUNT(RecordType.FILE_CONTROL, 2, 7, true),
  FC_BLOCK_COUNT(RecordType.FILE_CONTROL, 8, 13, true),
  FC_ENTRY_AND_ADDENDA_COUNT(RecordType.FILE_CONTROL, 14, 21, true),
  FC_ENTRY_HASH(RecordType.FILE_CONTROL, 22, 31, true),
  FC_DEBIT_AMOUNT(RecordType.FILE_CONTROL, 32, 43, true),
  FC_CREDIT_AMOUNT(RecordType.FILE_CONTROL, 44, 55, true),
  FC_RESERVED(RecordType.FILE_CONTROL, 56, 94, false);

  // Properties
  private final RecordType recordType;
  private final int startPosition;
  private final int endPosition;
  private final boolean required;

  /**
   * Constructor.
   *
   * @param recordType The {@link RecordType}.
   * @param startPosition The start position.
   * @param endPosition The end position.
   * @param required True if the field is required.
   */
  RecordField(
      final RecordType recordType,
      final int startPosition,
      final int endPosition,
      final boolean required) {

    this.recordType = recordType;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
    this.required = required;
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
   * Determines if the field is required.
   *
   * @return True if the field is either "Mandatory" or "Required".
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * Gets the name.
   *
   * @return The name.
   */
  public String getName() {
    final String[] nameParts = toString().split("_");
    final StringBuilder name = new StringBuilder();

    for (int i = 1; i < nameParts.length; i++) {
      char firstChar = nameParts[i].charAt(0);

      if (i == 1) {
        firstChar = Character.toLowerCase(firstChar);
      } else {
        firstChar = Character.toUpperCase(firstChar);
      }

      name.append(firstChar);
      name.append(
          nameParts[i].substring(1).toLowerCase());
    }

    return name.toString();
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
