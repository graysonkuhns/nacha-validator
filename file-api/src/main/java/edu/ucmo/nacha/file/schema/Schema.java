package edu.ucmo.nacha.file.schema;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.List;
import java.util.Map;

/**
 * NACHA file schema model.
 *
 * @author Grayson Kuhns
 */
public interface Schema {

  /**
   * Gets the {@link RecordField}s associated to each {@link RecordType}.
   *
   * @return The {@link RecordField}s associated to each {@link RecordType}.
   */
  Map<RecordType, List<RecordField>> getFields();
}
