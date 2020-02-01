package edu.ucmo.nacha.file.record;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultRecordFieldParser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultRecordFieldParserTest {

  // Fixtures
  private DefaultRecordFieldParser parser;

  // #getString()
  @Test
  public void getString__IsSuccessful__ForValidRequest__Test() {
    assertThat(parser
        .getString("  567  ", 2, 6))
        .isNotNull()
        .isEqualTo("567");
  }

  // #getInt()
  @Test
  public void getInt__IsSuccessful__ForValidRequest__Test() {
    assertThat(parser
        .getInt("  567  ", 2, 6))
        .isNotNull()
        .isEqualTo(567);
  }

  // #getLong()
  @Test
  public void getLong__IsSuccessful__ForValidRequest__Test() {
    assertThat(parser
        .getLong("  567  ", 2, 6))
        .isNotNull()
        .isEqualTo(567);
  }

  // #getDouble()
  @Test
  public void getDouble__IsSuccessful__ForValidRequest__Test() {
    assertThat(parser
        .getDouble("  56.7  ", 2, 6))
        .isNotNull()
        .isEqualTo(56.7);
  }

  // #getBoolean()
  @Test
  public void getBoolean__ReturnsFalse__WhenFieldIsZero__Test() {
    assertThat(parser
        .getBoolean("  0  ", 2, 4))
        .isFalse();
  }

  @Test
  public void getBoolean__ReturnsTrue__WhenFieldIsOne__Test() {
    assertThat(parser
        .getBoolean("  1  ", 2, 4))
        .isTrue();
  }

  // #getDollarAmount()
  @Test
  public void getDollarAmount__ForZeroAmount__Test() {
    assertThat(parser
        .getDollarAmount("  000000000  ", 2, 12))
        .isEqualTo(0);
  }

  @Test
  public void getDollarAmount__ForCentsOnly__Test() {
    assertThat(parser
        .getDollarAmount("  000000029  ", 2, 12))
        .isEqualTo(0.29);
  }

  @Test
  public void getDollarAmount__ForDollarsOnly__Test() {
    assertThat(parser
        .getDollarAmount("  000056700  ", 2, 12))
        .isEqualTo(567);
  }

  @Test
  public void getDollarAmount__ForDollarsAndCents__Test() {
    assertThat(parser
        .getDollarAmount("  000056729  ", 2, 12))
        .isEqualTo(567.29);
  }

  @Before
  public void setUp() {
    parser = new DefaultRecordFieldParser();
  }
}
