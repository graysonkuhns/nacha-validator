package edu.ucmo.nacha.record.intermediate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultPaddingDetector} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultPaddingDetectorTest {

  // Fixtures
  private IntermediateRecord padding;
  private IntermediateRecord notPaddingOne;
  private IntermediateRecord notPaddingTwo;

  private DefaultPaddingDetector paddingDetector;

  @Test
  public void isPadding__ReturnsTrue__WhenFileControlRecordHasAllNines__Test() {
    assertThat(paddingDetector
        .isPadding(padding))
        .isTrue();
  }

  @Test
  public void isPadding__ReturnsFalse__WhenFileControlRecordHasNonNines__Test() {
    assertThat(paddingDetector
        .isPadding(notPaddingOne))
        .isFalse();
  }

  @Test
  public void isPadding__ReturnsFalse__WhenEntryDetailRecordHasNonNines__Test() {
    assertThat(paddingDetector
        .isPadding(notPaddingTwo))
        .isFalse();
  }

  @Before
  public void setUp() {
    // Padding record mocking
    Map<RecordField, String> fields = new HashMap<>();
    fields.put(RecordField.FC_BATCH_COUNT, "9999999999999999");
    fields.put(RecordField.FC_DEBIT_AMOUNT, "9999999999");

    padding = mock(IntermediateRecord.class);
    doReturn(fields)
        .when(padding)
        .getFields();
    doReturn(RecordType.FILE_CONTROL)
        .when(padding)
        .getType();

    // Non-padding record mocking
    fields = new HashMap<>();
    fields.put(RecordField.FC_BATCH_COUNT, "9999999999999999");
    fields.put(RecordField.FC_DEBIT_AMOUNT, "999897a6sd99999");

    notPaddingOne = mock(IntermediateRecord.class);
    doReturn(fields)
        .when(notPaddingOne)
        .getFields();
    doReturn(RecordType.FILE_CONTROL)
        .when(notPaddingOne)
        .getType();

    // Non-padding 2 record mocking
    fields = new HashMap<>();
    fields.put(RecordField.ED_TRANSACTION_TYPE, "9999999999999999");
    fields.put(RecordField.ED_DISCRETIONARY_DATA, "999897a6sd99999");

    notPaddingTwo = mock(IntermediateRecord.class);
    doReturn(fields)
        .when(notPaddingTwo)
        .getFields();
    doReturn(RecordType.ENTRY_DETAIL)
        .when(notPaddingTwo)
        .getType();

    // Create padding detector
    paddingDetector = new DefaultPaddingDetector();
  }
}
