package edu.ucmo.nacha.file;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.finalform.Record;
import java.util.List;
import javax.inject.Inject;

/**
 * Default {@link FileParseResults} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultFileParseResults implements FileParseResults {

  // Properties
  private final List<Record> records;

  /**
   * Constructor.
   *
   * @param records The {@link Record}s.
   */
  @Inject
  DefaultFileParseResults(@Assisted final List<Record> records) {
    this.records = records;
  }

  /**
   * Gets the {@link Record}s.
   *
   * @return The {@link Record}s.
   */
  @Override
  public List<Record> getRecords() {
    return records;
  }
}
