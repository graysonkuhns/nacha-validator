package edu.ucmo.nacha.file;

import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.RecordParser;
import edu.ucmo.nacha.record.finalform.RecordsParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.PaddingDetector;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link FileParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultFileParser implements FileParser {

  // Dependencies
  private final FileParseResultsFactory fileParseResultsFactory;
  private final IntermediateRecordsParser intermediateRecordsParser;
  private final PaddingDetector paddingDetector;
  private final RecordParser recordParser;

  /**
   * Constructor.
   *
   * @param fileParseResultsFactory The {@link FileParseResultsFactory}.
   * @param intermediateRecordsParser The {@link IntermediateRecordsParser}.
   * @param paddingDetector The {@link PaddingDetector}.
   * @param recordParser The {@link RecordParser}.
   */
  @Inject
  DefaultFileParser(
      final FileParseResultsFactory fileParseResultsFactory,
      final IntermediateRecordsParser intermediateRecordsParser,
      final PaddingDetector paddingDetector,
      final RecordParser recordParser) {

    this.fileParseResultsFactory = fileParseResultsFactory;
    this.intermediateRecordsParser = intermediateRecordsParser;
    this.paddingDetector = paddingDetector;
    this.recordParser = recordParser;
  }

  private FileParseResults parseAndValidate(final List<IntermediateRecord> intermediateRecords) {
    // Filter out padding records
    final List<Record> finalRecords = intermediateRecords
        .stream()
        .filter(record -> !paddingDetector.isPadding(record))
        .map(recordParser::parse)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    return fileParseResultsFactory.create(finalRecords);
  }

  /**
   * Parses raw records from a NACHA file.
   *
   * @param rawRecords The raw records.
   * @return The {@link FileParseResults}s.
   */
  @Override
  public FileParseResults parse(final List<String> rawRecords) {
    return parseAndValidate(intermediateRecordsParser.parse(rawRecords));
  }

  /**
   * Parses a NACHA file from an {@link InputStream}.
   *
   * <p>
   * Useful for parsing a NACHA file transmitted over a network connection.
   * </p>
   *
   * @param inputStream The {@link InputStream}.
   * @return The {@link FileParseResults}s.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public FileParseResults parse(final InputStream inputStream) throws IOException {
    return parseAndValidate(intermediateRecordsParser.parse(inputStream));
  }

  /**
   * Parses a NACHA file.
   *
   * @param file The {@link File}.
   * @return The {@link FileParseResults}s.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public FileParseResults parse(final File file) throws IOException {
    return parseAndValidate(intermediateRecordsParser.parse(file));
  }
}
