package edu.ucmo.nacha.record;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link RecordField} test case.
 *
 * @author Grayson Kuhns
 */
public class RecordFieldTest {

  @Test
  public void getFields__ForEntryDetail__Test() {
    assertThat(RecordField
        .getFields(RecordType.ENTRY_DETAIL))
        .isNotNull()
        .hasSize(10)
        .contains(RecordField.TRANSACTION_TYPE)
        .contains(RecordField.RECEIVER_ROUTING_NUMBER)
        .contains(RecordField.RECEIVER_ROUTING_NUMBER_CHECK_DIGIT)
        .contains(RecordField.RECEIVER_ACCOUNT_NUMBER)
        .contains(RecordField.TRANSACTION_AMOUNT)
        .contains(RecordField.RECEIVER_ID_NUMBER)
        .contains(RecordField.RECEIVER_NAME)
        .contains(RecordField.DISCRETIONARY_DATA)
        .contains(RecordField.HAS_ADDENDA)
        .contains(RecordField.TRACE_NUMBER);
  }
}
