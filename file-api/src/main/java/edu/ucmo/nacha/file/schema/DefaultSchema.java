package edu.ucmo.nacha.file.schema;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.List;
import java.util.Map;

/**
 * Default {@link Schema} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultSchema implements Schema {

  // Properties
  private final Map<RecordType, List<RecordField>> fields;

  /**
   * Constructor.
   *
   * @param fields The fields.
   */
  @Inject
  DefaultSchema(@Assisted final Map<RecordType, List<RecordField>> fields) {
    this.fields = fields;
  }

  /**
   * Gets the {@link RecordField}s associated to each {@link RecordType}.
   *
   * @return The {@link RecordField}s associated to each {@link RecordType}.
   */
  @Override
  public Map<RecordType, List<RecordField>> getFields() {
    return fields;
  }
}
