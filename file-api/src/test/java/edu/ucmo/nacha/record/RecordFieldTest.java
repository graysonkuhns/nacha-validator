package edu.ucmo.nacha.record;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * {@link RecordField} test case.
 *
 * @author Grayson Kuhns
 */
public class RecordFieldTest {

  @Test
  public void getFields__ForFileHeader__Test() {
    assertThat(RecordField
        .getFields(RecordType.FILE_HEADER))
        .isNotNull()
        .hasSize(12)
        .contains(RecordField.FH_PRIORITY_CODE)
        .contains(RecordField.FH_IMMEDIATE_DESTINATION)
        .contains(RecordField.FH_IMMEDIATE_ORIGIN)
        .contains(RecordField.FH_FILE_CREATION_DATE)
        .contains(RecordField.FH_FILE_CREATION_TIME)
        .contains(RecordField.FH_FILE_ID_MODIFIER)
        .contains(RecordField.FH_RECORD_SIZE)
        .contains(RecordField.FH_BLOCKING_FACTOR)
        .contains(RecordField.FH_FORMAT_CODE)
        .contains(RecordField.FH_IMMEDIATE_DESTINATION_NAME)
        .contains(RecordField.FH_IMMEDIATE_ORIGIN_NAME)
        .contains(RecordField.FH_REFERENCE_CODE);
  }

  @Test
  public void getFields__ForEntryDetail__Test() {
    assertThat(RecordField
        .getFields(RecordType.ENTRY_DETAIL))
        .isNotNull()
        .hasSize(10)
        .contains(RecordField.ED_TRANSACTION_TYPE)
        .contains(RecordField.ED_RECEIVER_ROUTING_NUMBER)
        .contains(RecordField.ED_RECEIVER_ROUTING_NUMBER_CHECK_DIGIT)
        .contains(RecordField.ED_RECEIVER_ACCOUNT_NUMBER)
        .contains(RecordField.ED_TRANSACTION_AMOUNT)
        .contains(RecordField.ED_RECEIVER_ID_NUMBER)
        .contains(RecordField.ED_RECEIVER_NAME)
        .contains(RecordField.ED_DISCRETIONARY_DATA)
        .contains(RecordField.ED_HAS_ADDENDA)
        .contains(RecordField.ED_TRACE_NUMBER);
  }

  @Test
  public void getFields__ForBatchControl__Test() {
    assertThat(RecordField
        .getFields(RecordType.BATCH_CONTROL))
        .isNotNull()
        .hasSize(10)
        .contains(RecordField.BC_SERVICE_CLASS_CODE)
        .contains(RecordField.BC_ENTRY_COUNT)
        .contains(RecordField.BC_ENTRY_HASH)
        .contains(RecordField.BC_DEBIT_AMOUNT)
        .contains(RecordField.BC_CREDIT_AMOUNT)
        .contains(RecordField.BC_COMPANY_IDENTIFICATION)
        .contains(RecordField.BC_MESSAGE_AUTHENTICATION_CODE)
        .contains(RecordField.BC_RESERVED)
        .contains(RecordField.BC_ORIGINATING_DFI_ID)
        .contains(RecordField.BC_BATCH_NUMBER);
  }

  @Test
  public void getFields__ForBatchHeader__Test() {
    assertThat(RecordField
        .getFields(RecordType.BATCH_HEADER))
        .isNotNull()
        .hasSize(12)
        .contains(RecordField.BH_SERVICE_CLASS_CODE)
        .contains(RecordField.BH_COMPANY_NAME)
        .contains(RecordField.BH_DISCRETIONARY_DATA)
        .contains(RecordField.BH_COMPANY_IDENTIFICATION)
        .contains(RecordField.BH_STANDARD_ENTRY)
        .contains(RecordField.BH_ENTRY_DESCRIPTION)
        .contains(RecordField.BH_DESCRIPTIVE_DATE)
        .contains(RecordField.BH_EFFECTIVE_ENTRY_DATE)
        .contains(RecordField.BH_SETTLEMENT_DATE)
        .contains(RecordField.BH_ORIGINATOR_STATUS_CODE)
        .contains(RecordField.BH_ORIGINATING_DFI_ID)
        .contains(RecordField.BH_BATCH_NUMBER);
  }
}
