package edu.ucmo.nacha.record.intermediate;

/**
 * Record parser.
 *
 * @author Grayson Kuhns
 */
public interface IntermediateRecordParser {

  /**
   * Parses a {@link IntermediateRecord}.
   *
   * @param input The input to parse.
   * @param indexTracker The {@link IndexTracker}.
   * @return The {@link IntermediateRecord}.
   */
  IntermediateRecord parse(String input, IndexTracker indexTracker);
}
