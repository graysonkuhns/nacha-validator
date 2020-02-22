package edu.ucmo.nacha.file.entrydetail;

/**
 * {@link EntryDetail} parser.
 *
 * @author Grayson Kuhns
 */
public interface EntryDetailParser {

  /**
   * Parses an {@link EntryDetail}.
   *
   * @param record The record.
   * @return The {@link EntryDetail}.
   */
  EntryDetail parse(String record);
}
