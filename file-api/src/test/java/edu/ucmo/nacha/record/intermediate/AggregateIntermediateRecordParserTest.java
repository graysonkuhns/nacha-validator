package edu.ucmo.nacha.record.intermediate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.google.common.collect.ImmutableSet;
import edu.ucmo.nacha.record.InvalidRecordException;
import edu.ucmo.nacha.record.InvalidRecordTypeException;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.intermediate.AggregateIntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import edu.ucmo.nacha.record.intermediate.SpecializedIntermediateRecordParser;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link AggregateIntermediateRecordParser} test case.
 *
 * @author Grayson Kuhns
 */
public class AggregateIntermediateRecordParserTest {

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
  private IndexTracker indexTracker;
  private IntermediateRecord record;
  private SpecializedIntermediateRecordParser specializedParser;
  private AggregateIntermediateRecordParser aggregateParser;

  @Test
  public void parse__ThrowsException__WhenTheRecordIsNotTheCorrectLength__Test() {
    // Describe the exception to expect
    thrown.expect(InvalidRecordException.class);
    thrown.expectMessage(RECORD_TOO_SHORT_MSG);

    // Attempt to parse the record
    aggregateParser.parse(RECORD_TOO_SHORT, indexTracker);
  }

  @Test
  public void parse__ThrowsException__WhenTheRecordTypeIsInvalidTest__Test() {
    // Describe the exception to expect
    thrown.expect(InvalidRecordException.class);
    thrown.expectCause(Is.isA(InvalidRecordTypeException.class));

    // Attempt to parse the record
    aggregateParser.parse(RECORD_INVALID_TYPE, indexTracker);
  }

  @Test
  public void parse__ThrowsException__WhenTheRecordTypeIsUnsupported__Test() {
    // Describe the exception to expect
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("Parsing records of type \"FILE_CONTROL\" is not currently supported");

    // Attempt to parse the record
    aggregateParser.parse(RECORD_UNSUPPORTED_TYPE, indexTracker);
  }

  @Test
  public void parse__ParsesTheRecord__WhenTheRecordIsValidAndSupported__Test() {
    assertThat(aggregateParser
        .parse(RECORD_VALID, indexTracker))
        .isNotNull()
        .isEqualTo(record);
  }

  @Before
  public void setUp() {
    // Index tracker mocking
    indexTracker = mock(IndexTracker.class);
    doReturn(1)
        .when(indexTracker)
        .getNext();

    // Specialized record parser mocking
    record = mock(IntermediateRecord.class);
    specializedParser = mock(SpecializedIntermediateRecordParser.class);
    doReturn(record)
        .when(specializedParser)
        .parse(eq(RECORD_VALID), eq(indexTracker));
    doReturn(RecordType.ENTRY_DETAIL)
        .when(specializedParser)
        .getSupportedRecordType();

    // Create the aggregate parser
    aggregateParser = new AggregateIntermediateRecordParser(ImmutableSet.of(specializedParser));
  }
}
