package edu.ucmo.nacha.record;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link SpecializedRecordParser} test case.
 *
 * @author Grayson Kuhns
 */
public class SpecializedIntermediateRecordParserTest {

  // Constants
  private static final RecordType SUPPORTED_RECORD_TYPE = RecordType.ENTRY_DETAIL;
  private static final String RECORD_VALID =
      "6321010000191234567890111213100000000021300           MUSTARD MISTER M      DD0101000010000002";

  // Fixtures
  private SpecializedRecordParser recordParser;

  @Test
  public void parse__ParsesTheRecord__WhenTheRecordIsValidAndSupported__Test() {
    // Parse the record
    IntermediateRecord record = recordParser.parse(RECORD_VALID);
    assertThat(record).isNotNull();

    // Validate the record type
    assertThat(record
        .getType())
        .isNotNull()
        .isEqualTo(SUPPORTED_RECORD_TYPE);

    // Validate the record fields
    Map<RecordField, String> fields = record.getFields();
    assertThat(fields)
        .isNotNull()
        .isNotEmpty();

    assertThat(fields
        .get(RecordField.ED_RECEIVER_ROUTING_NUMBER_CHECK_DIGIT))
        .isNotNull()
        .isEqualTo("9");

    assertThat(fields
        .get(RecordField.ED_RECEIVER_ROUTING_NUMBER))
        .isNotNull()
        .isEqualTo("10100001");

    assertThat(fields
        .get(RecordField.ED_RECEIVER_NAME))
        .isNotNull()
        .isEqualTo("MUSTARD MISTER M      ");

    assertThat(fields
        .get(RecordField.ED_RECEIVER_ACCOUNT_NUMBER))
        .isNotNull()
        .isEqualTo("12345678901112131");

    assertThat(fields
        .get(RecordField.ED_DISCRETIONARY_DATA))
        .isNotNull()
        .isEqualTo("DD");

    assertThat(fields
        .get(RecordField.ED_TRANSACTION_AMOUNT))
        .isNotNull()
        .isEqualTo("0000000002");

    assertThat(fields
        .get(RecordField.ED_TRACE_NUMBER))
        .isNotNull()
        .isEqualTo("101000010000002");

    assertThat(fields
        .get(RecordField.ED_RECEIVER_ID_NUMBER))
        .isNotNull()
        .isEqualTo("1300           ");

    assertThat(fields
        .get(RecordField.ED_HAS_ADDENDA))
        .isNotNull()
        .isEqualTo("0");

    assertThat(fields
        .get(RecordField.ED_TRANSACTION_TYPE))
        .isNotNull()
        .isEqualTo("32");
  }

  @Test
  public void getSupportedRecordType__Test() {
    assertThat(recordParser
        .getSupportedRecordType())
        .isNotNull()
        .isEqualTo(SUPPORTED_RECORD_TYPE);
  }

  @Before
  public void setUp() {
    recordParser = new SpecializedRecordParser(SUPPORTED_RECORD_TYPE);
  }
}
