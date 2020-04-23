package edu.ucmo.nacha.record.finalform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.google.common.collect.ImmutableSet;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetail;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetailParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link AggregateRecordParser} test case.
 *
 * @author Grayson Kuhns
 */
public class AggregateRecordParserTest {

  // Fixtures
  private IntermediateRecord entryDetailIntermediateRecord;
  private IntermediateRecord batchControlIntermediateRecord;

  private EntryDetail entryDetail;
  private EntryDetailParser entryDetailParser;

  private AggregateRecordParser aggregateRecordParser;

  @Test
  public void parse__ReturnsTheParsedRecord__WhenParserIsRegistered__Test() {
    assertThat(aggregateRecordParser
        .parse(entryDetailIntermediateRecord))
        .isNotNull()
        .isEqualTo(entryDetail);
  }

  @Test
  public void parse__ReturnsNull__WhenParserIsNotRegistered__Test() {
    assertThat(aggregateRecordParser
        .parse(batchControlIntermediateRecord))
        .isNull();
  }

  @Before
  public void setUp() {
    // Intermediate records mocking
    entryDetailIntermediateRecord = mock(IntermediateRecord.class);
    doReturn(RecordType.ENTRY_DETAIL)
        .when(entryDetailIntermediateRecord)
        .getType();

    batchControlIntermediateRecord = mock(IntermediateRecord.class);
    doReturn(RecordType.BATCH_CONTROL)
        .when(batchControlIntermediateRecord)
        .getType();

    // Entry detail parser mocking
    entryDetail = mock(EntryDetail.class);
    entryDetailParser = mock(EntryDetailParser.class);
    doReturn(entryDetail)
        .when(entryDetailParser)
        .parse(any(IntermediateRecord.class));
    doReturn(RecordType.ENTRY_DETAIL)
        .when(entryDetailParser)
        .getSupportedRecordType();

    // Create the aggregate parser
    aggregateRecordParser = new AggregateRecordParser(ImmutableSet.of(entryDetailParser));
  }
}
