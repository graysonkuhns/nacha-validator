package edu.ucmo.nacha.record;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import edu.ucmo.nacha.record.intermediate.IndexTracker;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import edu.ucmo.nacha.record.intermediate.SpecializedIntermediateRecordParser;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link SpecializedIntermediateRecordParser} test case.
 *
 * @author Grayson Kuhns
 */
public class SpecializedIntermediateRecordParserTest {

  // Constants
  private static final RecordType SUPPORTED_RECORD_TYPE = RecordType.ENTRY_DETAIL;
  private static final String RECORD_VALID =
      "6321010000191234567890111213100000000021300           MUSTARD MISTER M      DD0101000010000002";

  // Fixtures
  private IndexTracker indexTracker;
  private SpecializedIntermediateRecordParser recordParser;

  @Test
  public void parse__ParsesTheRecord__WhenTheRecordIsValidAndSupported__Test() {
    // Parse the record
    IntermediateRecord record = recordParser.parse(RECORD_VALID, indexTracker);
    assertThat(record).isNotNull();

    // Validate the record index
    assertThat(record
        .getIndex())
        .isEqualTo(1);

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
    indexTracker = mock(IndexTracker.class);
    doReturn(1)
        .when(indexTracker)
        .getNext();

    recordParser = new SpecializedIntermediateRecordParser(SUPPORTED_RECORD_TYPE);
  }
}
