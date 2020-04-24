package edu.ucmo.nacha.file;

import edu.ucmo.nacha.record.finalform.Record;
import java.util.List;

/**
 * File parsing operation results.
 *
 * @author Grayson Kuhns
 */
public interface FileParseResults {

  /**
   * Gets the {@link Record}s.
   *
   * @return The {@link Record}s.
   */
  List<Record> getRecords();
}
