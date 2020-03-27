package edu.ucmo.nacha.file;

import edu.ucmo.nacha.record.Record;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * NACHA file parser.
 *
 * @author Grayson Kuhns
 */
public interface FileParser {

  /**
   * Parses raw records from a NACHA file.
   *
   * @param rawRecords The raw records.
   * @return The {@link Record}s.
   */
  List<Record> parse(List<String> rawRecords);

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
  List<Record> parse(InputStream inputStream) throws IOException;

  /**
   * Parses a NACHA file.
   *
   * @param file The {@link File}.
   * @return The {@link Record}s.
   * @throws IOException If an I/O error occurs.
   */
  List<Record> parse(File file) throws IOException;
}
