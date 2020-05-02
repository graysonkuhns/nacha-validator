package edu.ucmo.nacha.file.schema;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.List;
import java.util.Map;

/**
 * {@link Schema} factory.
 *
 * @author Grayson Kuhns
 */
public interface SchemaFactory {

  /**
   * Creates a {@link Schema}.
   *
   * @param fields The fields.
   * @return The {@link Schema}.
   */
  Schema create(Map<RecordType, List<RecordField>> fields);
}
