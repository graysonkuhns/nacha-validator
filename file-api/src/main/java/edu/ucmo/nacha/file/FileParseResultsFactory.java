package edu.ucmo.nacha.file;

import edu.ucmo.nacha.file.validation.ValidationRecord;
import edu.ucmo.nacha.record.finalform.Record;
import java.util.List;

/**
 * {@link FileParseResults} factory.
 *
 * @author Grayson Kuhns
 */
public interface FileParseResultsFactory {

  /**
   * Creates a {@link FileParseResults}.
   *
   * @param records The {@link Record}s.
   * @param validationRecord The {@link ValidationRecord}.
   * @return The {@link FileParseResults}.
   */
  FileParseResults create(List<Record> records, ValidationRecord validationRecord);
}
