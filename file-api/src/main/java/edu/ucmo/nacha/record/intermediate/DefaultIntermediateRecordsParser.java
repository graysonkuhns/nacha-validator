package edu.ucmo.nacha.record.intermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Default {@link IntermediateRecordsParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultIntermediateRecordsParser implements IntermediateRecordsParser {

  // Dependencies
  private final IntermediateRecordParser recordParser;
  private final Provider<IndexTracker> indexTrackerProvider;

  /**
   * Constructor.
   *
   * @param recordParser The {@link IntermediateRecordParser}.
   * @param indexTrackerProvider The {@link IndexTracker} {@link Provider}.
   */
  @Inject
  DefaultIntermediateRecordsParser(
      final IntermediateRecordParser recordParser,
      final Provider<IndexTracker> indexTrackerProvider) {

    this.recordParser = recordParser;
    this.indexTrackerProvider = indexTrackerProvider;
  }

  /**
   * Parses raw records from a NACHA file.
   *
   * @param rawRecords The raw records.
   * @return The {@link IntermediateRecord}s.
   */
  @Override
  public List<IntermediateRecord> parse(final List<String> rawRecords) {
    final IndexTracker indexTracker = indexTrackerProvider.get();

    return rawRecords
        .stream()
        .map(record -> recordParser.parse(record, indexTracker))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  /**
   * Parses a NACHA file from an {@link InputStream}.
   *
   * <p>
   * Useful for parsing a NACHA file transmitted over a network connection.
   * </p>
   *
   * @param inputStream The {@link InputStream}.
   * @return The {@link IntermediateRecord}s.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public List<IntermediateRecord> parse(final InputStream inputStream) throws IOException {
    return parse(new BufferedReader(new InputStreamReader(inputStream)));
  }

  /**
   * Parses a NACHA file.
   *
   * @param file The {@link File}.
   * @return The {@link IntermediateRecord}s.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public List<IntermediateRecord> parse(final File file) throws IOException {
    return parse(new BufferedReader(new FileReader(file)));
  }

  private List<IntermediateRecord> parse(final BufferedReader reader) throws IOException {
    final List<String> rawRecords = new ArrayList<>();

    // Collect the raw records
    String record;
    while ((record = reader.readLine()) != null) {
      rawRecords.add(record);
    }

    return parse(rawRecords);
  }
}
