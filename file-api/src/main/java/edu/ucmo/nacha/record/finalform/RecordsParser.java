package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import java.util.List;

/**
 * {@link Record}s parser.
 *
 * @author Grayson Kuhns
 */
public interface RecordsParser {

  /**
   * Parses {@link IntermediateRecord}s into final-form {@link Record}s.
   *
   * @param intermediateRecords The {@link IntermediateRecord}s.
   * @return The final-form {@link Record}s.
   */
  List<Record> parse(List<IntermediateRecord> intermediateRecords);
}
