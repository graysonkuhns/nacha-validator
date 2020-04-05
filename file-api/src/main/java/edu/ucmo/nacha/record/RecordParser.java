package edu.ucmo.nacha.record;

/**
 * Record parser.
 *
 * @author Grayson Kuhns
 */
public interface RecordParser {

  /**
   * Parses a {@link IntermediateRecord}.
   *
   * @param input The input to parse.
   * @return The {@link IntermediateRecord}.
   */
  IntermediateRecord parse(String input);
}
