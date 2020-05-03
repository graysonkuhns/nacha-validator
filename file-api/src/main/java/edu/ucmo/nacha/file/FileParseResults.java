package edu.ucmo.nacha.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.ucmo.nacha.file.validation.ValidationRecord;
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

  /**
   * Gets the {@link ValidationRecord}.
   *
   * @return The {@link ValidationRecord}.
   */
  @JsonProperty("validation")
  ValidationRecord getValidationRecord();
}
