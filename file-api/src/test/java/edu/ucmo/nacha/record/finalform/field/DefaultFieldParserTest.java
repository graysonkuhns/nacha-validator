package edu.ucmo.nacha.record.finalform.field;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link DefaultFieldParser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultFieldParserTest {

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private Map<RecordField, String> fields;
  private IntermediateRecord record;
  private DefaultFieldParser fieldParser;

  // #getStringOrNull()
  @Test
  public void getStringOrNull__ReturnsTheFieldData__WhenTheFieldIsNotEmpty__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "hello");
    assertThat(fieldParser
        .getStringOrNull(record, RecordField.ED_TRANSACTION_TYPE))
        .isNotNull()
        .isEqualTo("hello");
  }

  @Test
  public void getStringOrNull__ReturnsNull__WhenTheFieldIsNotSet__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, null);
    assertThat(fieldParser
        .getStringOrNull(record, RecordField.ED_TRANSACTION_TYPE))
        .isNull();
  }

  @Test
  public void getStringOrNull__ReturnsNull__WhenTheFieldDataIsEmpty__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "");
    assertThat(fieldParser
        .getStringOrNull(record, RecordField.ED_TRANSACTION_TYPE))
        .isNull();
  }

  // #getString()
  @Test
  public void getString__ReturnsTheFieldData__WhenTheFieldIsNotEmpty__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "hello");
    assertThat(fieldParser
        .getString(record, RecordField.ED_TRANSACTION_TYPE))
        .isNotNull()
        .isEqualTo("hello");
  }

  @Test
  public void getString__ThrowsException__WhenTheFieldIsNotSet__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, null);
    fieldParser.getString(record, RecordField.ED_TRANSACTION_TYPE);
  }

  @Test
  public void getString__ThrowsException__WhenTheFieldDataIsEmpty__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, "");
    fieldParser.getString(record, RecordField.ED_TRANSACTION_TYPE);
  }

  // #getInt()
  @Test
  public void getInt__ReturnsTheFieldData__WhenTheFieldContainsValidInt__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "15");
    assertThat(fieldParser
        .getInt(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(15);
  }

  @Test
  public void getInt__ThrowsException__WhenTheFieldContainsInvalidInt__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, "xy");
    fieldParser.getInt(record, RecordField.ED_TRANSACTION_TYPE);
  }

  @Test
  public void getInt__ThrowsException__WhenTheFieldIsNotSet__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, null);
    fieldParser.getInt(record, RecordField.ED_TRANSACTION_TYPE);
  }

  // #getLong()
  @Test
  public void getLong__ReturnsTheFieldData__WhenTheFieldContainsValidLong__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "9223372036854775807");
    assertThat(fieldParser
        .getLong(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(9223372036854775807L);
  }

  @Test
  public void getLong__ThrowsException__WhenTheFieldContainsInvalidLong__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, "xy");
    fieldParser.getLong(record, RecordField.ED_TRANSACTION_TYPE);
  }

  @Test
  public void getLong__ThrowsException__WhenTheFieldIsNotSet__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, null);
    fieldParser.getLong(record, RecordField.ED_TRANSACTION_TYPE);
  }

  // #getDouble()
  @Test
  public void getDouble__ReturnsTheFieldData__WhenTheFieldContainsValidDouble__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, ".987");
    assertThat(fieldParser
        .getDouble(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(0.987);
  }

  @Test
  public void getDouble__ThrowsException__WhenTheFieldContainsInvalidDouble__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, "xy");
    fieldParser.getDouble(record, RecordField.ED_TRANSACTION_TYPE);
  }

  @Test
  public void getDouble__ThrowsException__WhenTheFieldIsNotSet__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, null);
    fieldParser.getDouble(record, RecordField.ED_TRANSACTION_TYPE);
  }

  // #getBoolean()
  @Test
  public void getBoolean__ReturnsTrue__WhenTheFieldIsTrue__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "1");
    assertThat(fieldParser
        .getBoolean(record, RecordField.ED_TRANSACTION_TYPE))
        .isTrue();
  }

  @Test
  public void getBoolean__ReturnsFalse__WhenTheFieldIsFalse__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "0");
    assertThat(fieldParser
        .getBoolean(record, RecordField.ED_TRANSACTION_TYPE))
        .isFalse();
  }

  @Test
  public void getBoolean__ThrowsException__WhenTheFieldContainsInvalidBoolean__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, "6");
    fieldParser.getBoolean(record, RecordField.ED_TRANSACTION_TYPE);
  }

  @Test
  public void getBoolean__ThrowsException__WhenTheFieldIsNotSet__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, null);
    fieldParser.getBoolean(record, RecordField.ED_TRANSACTION_TYPE);
  }

  // #getCurrency()
  @Test
  public void getCurrency__ForZeroAmount__GetsTheCorrectAmount__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "  000000000  ");
    assertThat(fieldParser
        .getCurrency(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(0);
  }

  @Test
  public void getCurrency__ForCentsOnly__GetsTheCorrectAmount__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "  000000029  ");
    assertThat(fieldParser
        .getCurrency(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(0.29);
  }

  @Test
  public void getCurrency__ForDollarsOnly__GetsTheCorrectAmount__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "  000056700  ");
    assertThat(fieldParser
        .getCurrency(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(567);
  }

  @Test
  public void getCurrency__ForDollarsAndCents__GetsTheCorrectAmount__Test() {
    fields.put(RecordField.ED_TRANSACTION_TYPE, "  000056729  ");
    assertThat(fieldParser
        .getCurrency(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(567.29);
  }

  @Test
  public void getCurrency__ThrowsException__ForMalformedAmount__Test() {
    thrown.expect(FieldParseException.class);
    fields.put(RecordField.ED_TRANSACTION_TYPE, "  00005  6729  ");
    assertThat(fieldParser
        .getCurrency(record, RecordField.ED_TRANSACTION_TYPE))
        .isEqualTo(567.29);
  }

  @Before
  public void setUp() {
    // Record mocking
    fields = new HashMap<>();
    record = mock(IntermediateRecord.class);
    doReturn(fields)
        .when(record)
        .getFields();

    // Create field parser
    fieldParser = new DefaultFieldParser();
  }
}
