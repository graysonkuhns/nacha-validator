package edu.ucmo.nacha.file;

import edu.ucmo.nacha.record.Record;
import edu.ucmo.nacha.record.RecordParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  private final RecordParser recordParser;

  /**
   * Constructor.
   *
   * @param recordParser The {@link RecordParser}.
   */
  @Inject
  DefaultFileParser(final RecordParser recordParser) {
    this.recordParser = recordParser;
  }

  /**
   * Parses raw records from a NACHA file.
   *
   * @param rawRecords The raw records.
   * @return The {@link Record}s.
   */
  @Override
  public List<Record> parse(final List<String> rawRecords) {
    return rawRecords
        .stream()
        .map(recordParser::parse)
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
   * @return The {@link Record}s.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public List<Record> parse(final InputStream inputStream) throws IOException {
    return parse(new BufferedReader(new InputStreamReader(inputStream)));
  }

  /**
   * Parses a NACHA file.
   *
   * @param file The {@link File}.
   * @return The {@link Record}s.
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public List<Record> parse(final File file) throws IOException {
    return parse(new BufferedReader(new FileReader(file)));
  }

  private List<Record> parse(final BufferedReader reader) throws IOException {
    final List<String> rawRecords = new ArrayList<>();

    // Collect the raw records
    String record = reader.readLine();
    while (record != null) {
      rawRecords.add(record);
      record = reader.readLine();
    }

    return parse(rawRecords);
  }
}
