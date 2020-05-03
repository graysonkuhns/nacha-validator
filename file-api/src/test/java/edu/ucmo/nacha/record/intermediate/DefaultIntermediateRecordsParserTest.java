package edu.ucmo.nacha.record.intermediate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * {@link DefaultIntermediateRecordsParser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultIntermediateRecordsParserTest {

  // Constants
  private static final String RECORD_A =
      "101 10100001986753075211909150700A094101Testing1 Bank          THE FAB FOUR CORP              ";
  private static final String RECORD_B =
      "622101000019111111           00000000010200           JONES DESMOND           0101000010000001";
  private static final String RECORD_C =
      "6321010000191234567890111213100000000021300           MUSTARD MISTER M        0101000010000002";
  private static final String RECORD_PADDING =
      "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
  private static final String RECORDS_WITHOUT_END_LINE =
      RECORD_A
          .concat("\n")
          .concat(RECORD_B)
          .concat("\n")
          .concat(RECORD_C)
          .concat("\n")
          .concat(RECORD_PADDING);
  private static final String RECORDS_WITH_END_LINE = RECORDS_WITHOUT_END_LINE.concat("\n");

  // Rules
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  // Fixtures
  private IntermediateRecord recordA;
  private IntermediateRecord recordB;
  private IntermediateRecord recordC;
  private IntermediateRecordParser recordParser;

  private List<String> rawRecords;
  private List<IntermediateRecord> records;

  private InputStream recordsWithoutEndLineStream;
  private InputStream recordsWithEndLineStream;

  private File recordsWithoutEndLineFile;
  private File recordsWithEndLineFile;

  private IndexTracker indexTracker;
  private IntermediateRecordsParser intermediateRecordsParser;

  @Test
  public void recordsAreParsedCorrectly__FromList__Test() {
    validateRecords(intermediateRecordsParser.parse(rawRecords));
  }

  @Test
  public void recordsAreParsedCorrectly__WithoutEndLine__FromInputStream__Test() throws Exception {
    validateRecords(intermediateRecordsParser.parse(recordsWithoutEndLineStream));
  }

  @Test
  public void recordsAreParsedCorrectly__WithEndLine__FromInputStream__Test() throws Exception {
    validateRecords(intermediateRecordsParser.parse(recordsWithEndLineStream));
  }

  @Test
  public void recordsAreParsedCorrectly__WithoutEndLine__FromFile__Test() throws Exception {
    validateRecords(intermediateRecordsParser.parse(recordsWithoutEndLineFile));
  }

  @Test
  public void recordsAreParsedCorrectly__WithEndLine__FromFile__Test() throws Exception {
    validateRecords(intermediateRecordsParser.parse(recordsWithEndLineFile));
  }

  @Before
  public void setUp() throws Exception {
    // Record mocking
    recordA = mock(IntermediateRecord.class);
    recordB = mock(IntermediateRecord.class);
    recordC = mock(IntermediateRecord.class);

    recordParser = mock(IntermediateRecordParser.class);
    doReturn(recordA)
        .when(recordParser)
        .parse(eq(RECORD_A), any());
    doReturn(recordB)
        .when(recordParser)
        .parse(eq(RECORD_B), any());
    doReturn(recordC)
        .when(recordParser)
        .parse(eq(RECORD_C), any());
    doReturn(null)
        .when(recordParser)
        .parse(eq(RECORD_PADDING), any());

    // Aggregate records
    rawRecords = new ArrayList<>();
    rawRecords.add(RECORD_A);
    rawRecords.add(RECORD_B);
    rawRecords.add(RECORD_C);

    records = new ArrayList<>();
    records.add(recordA);
    records.add(recordB);
    records.add(recordC);

    // Create input streams
    recordsWithoutEndLineStream = new ByteArrayInputStream(RECORDS_WITHOUT_END_LINE.getBytes());
    recordsWithEndLineStream = new ByteArrayInputStream(RECORDS_WITH_END_LINE.getBytes());

    // Prepare NACHA files
    recordsWithoutEndLineFile = folder.newFile("recordsWithoutLineEnding.txt");
    write(recordsWithoutEndLineFile, RECORDS_WITHOUT_END_LINE);

    recordsWithEndLineFile = folder.newFile("recordsWithLineEnding.txt");
    write(recordsWithEndLineFile, RECORDS_WITH_END_LINE);

    // Index tracking mocking
    indexTracker = mock(IndexTracker.class);
    Provider<IndexTracker> indexTrackerProvider = mock(Provider.class);
    doReturn(indexTracker)
        .when(indexTrackerProvider)
        .get();

    // Create the file parser
    intermediateRecordsParser = new DefaultIntermediateRecordsParser(recordParser, indexTrackerProvider);
  }

  private void validateRecords(final List<IntermediateRecord> records) {
    assertThat(records)
        .isNotNull()
        .hasSize(3)
        .contains(recordA)
        .contains(recordB)
        .contains(recordC);
  }

  private void write(final File file, final String content) throws IOException {
    final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(content);
    writer.close();
  }
}
