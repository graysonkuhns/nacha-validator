package edu.ucmo.nacha.record;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link AggregateRecordParser} test case.
 *
 * @author Grayson Kuhns
 */
public class AggregateRecordParserTest {

  // Constants
  private static final String RECORD_TOO_SHORT =
      "9873e78973dt";
  private static final String RECORD_TOO_SHORT_MSG =
      "Records are expected to be 94 characters long";

  private static final String RECORD_INVALID_TYPE =
      "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
  private static final String RECORD_UNSUPPORTED_TYPE =
      "9bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";

  private static final String RECORD_VALID =
      "632101000019123456789011121310000000002               MUSTARD MISTER M      DD0101000010000002";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private Record record;
  private SpecializedRecordParser specializedParser;
  private AggregateRecordParser aggregateParser;

  @Test
  public void parse__ThrowsException__WhenTheRecordIsNotTheCorrectLength__Test() {
    // Describe the exception to expect
    thrown.expect(InvalidRecordException.class);
    thrown.expectMessage(RECORD_TOO_SHORT_MSG);

    // Attempt to parse the record
    aggregateParser.parse(RECORD_TOO_SHORT);
  }

  @Test
  public void parse__ThrowsException__WhenTheRecordTypeIsInvalidTest__Test() {
    // Describe the exception to expect
    thrown.expect(InvalidRecordException.class);
    thrown.expectCause(Is.isA(InvalidRecordTypeException.class));

    // Attempt to parse the record
    aggregateParser.parse(RECORD_INVALID_TYPE);
  }

  @Test
  public void parse__ThrowsException__WhenTheRecordTypeIsUnsupported__Test() {
    // Describe the exception to expect
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("Parsing records of type \"FILE_CONTROL\" is not currently supported");

    // Attempt to parse the record
    aggregateParser.parse(RECORD_UNSUPPORTED_TYPE);
  }

  @Test
  public void parse__ParsesTheRecord__WhenTheRecordIsValidAndSupported__Test() {
    assertThat(aggregateParser
        .parse(RECORD_VALID))
        .isNotNull()
        .isEqualTo(record);
  }

  @Before
  public void setUp() {
    // Specialized record parser mocking
    record = mock(Record.class);
    specializedParser = mock(SpecializedRecordParser.class);
    doReturn(record)
        .when(specializedParser)
        .parse(eq(RECORD_VALID));
    doReturn(RecordType.ENTRY_DETAIL)
        .when(specializedParser)
        .getSupportedRecordType();

    // Create the aggregate parser
    aggregateParser = new AggregateRecordParser(ImmutableSet.of(specializedParser));
  }
}
