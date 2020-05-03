package edu.ucmo.nacha.file;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.file.validation.ValidationRecord;
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
  private final ValidationRecord validationRecord;

  /**
   * Constructor.
   *
   * @param records The {@link Record}s.
   * @param validationRecord The {@link ValidationRecord}.
   */
  @Inject
  DefaultFileParseResults(
      @Assisted final List<Record> records,
      @Assisted final ValidationRecord validationRecord) {

    this.records = records;
    this.validationRecord = validationRecord;
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

  /**
   * Gets the {@link ValidationRecord}.
   *
   * @return The {@link ValidationRecord}.
   */
  @Override
  public ValidationRecord getValidationRecord() {
    return validationRecord;
  }
}
