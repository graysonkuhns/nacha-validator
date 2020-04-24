package edu.ucmo.nacha.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * NACHA File Parser.
 *
 * @author Grayson Kuhns
 */
public interface FileParser {

  /**
   * Parses raw records from a NACHA file.
   *
   * @param rawRecords The raw records.
   * @return The {@link FileParseResults}s.
   */
  FileParseResults parse(List<String> rawRecords);

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
  FileParseResults parse(InputStream inputStream) throws IOException;

  /**
   * Parses a NACHA file.
   *
   * @param file The {@link File}.
   * @return The {@link FileParseResults}s.
   * @throws IOException If an I/O error occurs.
   */
  FileParseResults parse(File file) throws IOException;
}
