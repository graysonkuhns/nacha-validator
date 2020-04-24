package edu.ucmo.nacha.record.finalform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.google.common.collect.ImmutableList;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultRecordsParser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultRecordsParserTest {

  // Fixtures
  private IntermediateRecord intermediateRecordOne;
  private IntermediateRecord intermediateRecordTwo;
  private IntermediateRecord intermediateRecordThree;

  private Record recordOne;
  private Record recordTwo;
  private RecordParser recordParser;

  private DefaultRecordsParser recordsParser;

  @Test
  public void parse__ParsesAndCollectsNonNullRecords__Test() {
    assertThat(recordsParser
        .parse(ImmutableList.of(
            intermediateRecordOne,
            intermediateRecordTwo,
            intermediateRecordThree)))
        .isNotNull()
        .hasSize(2)
        .contains(recordOne)
        .contains(recordTwo);
  }

  @Before
  public void setUp() {
    // Intermediate records mocking
    intermediateRecordOne = mock(IntermediateRecord.class);
    intermediateRecordTwo = mock(IntermediateRecord.class);
    intermediateRecordThree = mock(IntermediateRecord.class);

    // Record parser mocking
    recordOne = mock(Record.class);
    recordTwo = mock(Record.class);
    recordParser = mock(RecordParser.class);
    doReturn(recordOne)
        .when(recordParser)
        .parse(eq(intermediateRecordOne));
    doReturn(recordTwo)
        .when(recordParser)
        .parse(eq(intermediateRecordTwo));
    doReturn(null)
        .when(recordParser)
        .parse(eq(intermediateRecordThree));

    // Create the records parser
    recordsParser = new DefaultRecordsParser(recordParser);
  }
}
