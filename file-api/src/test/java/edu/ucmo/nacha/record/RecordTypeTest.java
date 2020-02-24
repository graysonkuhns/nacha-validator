package edu.ucmo.nacha.record;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link RecordType} test case.
 *
 * @author Grayson Kuhns
 */
public class RecordTypeTest {

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void getForId__FileHeader__ByInteger__Test() {
    assertThat(RecordType
        .getForId(1))
        .isNotNull()
        .isEqualTo(RecordType.FILE_HEADER);
  }

  @Test
  public void getForId__FileHeader__ByChar__Test() {
    assertThat(RecordType
        .getForId('1'))
        .isNotNull()
        .isEqualTo(RecordType.FILE_HEADER);
  }

  @Test
  public void getForId__FileHeader__ByString__Test() {
    assertThat(RecordType
        .getForId("1"))
        .isNotNull()
        .isEqualTo(RecordType.FILE_HEADER);
  }

  @Test
  public void getForId__BatchHeader__ByInteger__Test() {
    assertThat(RecordType
        .getForId(5))
        .isNotNull()
        .isEqualTo(RecordType.BATCH_HEADER);
  }

  @Test
  public void getForId__BatchHeader__ByChar__Test() {
    assertThat(RecordType
        .getForId('5'))
        .isNotNull()
        .isEqualTo(RecordType.BATCH_HEADER);
  }

  @Test
  public void getForId__BatchHeader__ByString__Test() {
    assertThat(RecordType
        .getForId("5"))
        .isNotNull()
        .isEqualTo(RecordType.BATCH_HEADER);
  }

  @Test
  public void getForId__EntryDetail__ByInteger__Test() {
    assertThat(RecordType
        .getForId(6))
        .isNotNull()
        .isEqualTo(RecordType.ENTRY_DETAIL);
  }

  @Test
  public void getForId__EntryDetail__ByChar__Test() {
    assertThat(RecordType
        .getForId('6'))
        .isNotNull()
        .isEqualTo(RecordType.ENTRY_DETAIL);
  }

  @Test
  public void getForId__EntryDetail__ByString__Test() {
    assertThat(RecordType
        .getForId("6"))
        .isNotNull()
        .isEqualTo(RecordType.ENTRY_DETAIL);
  }

  @Test
  public void getForId__EntryDetailAddenda__ByInteger__Test() {
    assertThat(RecordType
        .getForId(7))
        .isNotNull()
        .isEqualTo(RecordType.ENTRY_DETAIL_ADDENDA);
  }

  @Test
  public void getForId__EntryDetailAddenda__ByChar__Test() {
    assertThat(RecordType
        .getForId('7'))
        .isNotNull()
        .isEqualTo(RecordType.ENTRY_DETAIL_ADDENDA);
  }

  @Test
  public void getForId__EntryDetailAddenda__ByString__Test() {
    assertThat(RecordType
        .getForId("7"))
        .isNotNull()
        .isEqualTo(RecordType.ENTRY_DETAIL_ADDENDA);
  }

  @Test
  public void getForId__BatchControl__ByInteger__Test() {
    assertThat(RecordType
        .getForId(8))
        .isNotNull()
        .isEqualTo(RecordType.BATCH_CONTROL);
  }

  @Test
  public void getForId__BatchControl__ByChar__Test() {
    assertThat(RecordType
        .getForId('8'))
        .isNotNull()
        .isEqualTo(RecordType.BATCH_CONTROL);
  }

  @Test
  public void getForId__BatchControl__ByString__Test() {
    assertThat(RecordType
        .getForId("8"))
        .isNotNull()
        .isEqualTo(RecordType.BATCH_CONTROL);
  }

  @Test
  public void getForId__FileControl__ByInteger__Test() {
    assertThat(RecordType
        .getForId(9))
        .isNotNull()
        .isEqualTo(RecordType.FILE_CONTROL);
  }

  @Test
  public void getForId__FileControl__ByChar__Test() {
    assertThat(RecordType
        .getForId('9'))
        .isNotNull()
        .isEqualTo(RecordType.FILE_CONTROL);
  }

  @Test
  public void getForId__FileControl__ByString__Test() {
    assertThat(RecordType
        .getForId("9"))
        .isNotNull()
        .isEqualTo(RecordType.FILE_CONTROL);
  }

  @Test
  public void getForId__ThrowsException__ForInvalidTypeCode__ByInteger__Test() {
    thrown.expect(InvalidRecordTypeException.class);
    thrown.expectMessage("Invalid type code: \"22\"");

    RecordType.getForId(22);
  }

  @Test
  public void getForId__ThrowsException__ForInvalidTypeCode__ByChar__Test() {
    thrown.expect(InvalidRecordTypeException.class);
    thrown.expectMessage("Invalid type code: \"0\"");

    RecordType.getForId('0');
  }

  @Test
  public void getForId__ThrowsException__ForInvalidTypeCode__ByString__Test() {
    thrown.expect(InvalidRecordTypeException.class);
    thrown.expectMessage("Invalid type code: \"22\"");

    RecordType.getForId("22");
  }

  @Test
  public void getForId__ThrowsException__ForNonNumericInput__Test() {
    thrown.expect(InvalidRecordTypeException.class);
    thrown.expectMessage("Invalid type code: \"abc\"");

    RecordType.getForId("abc");
  }
}
