package edu.ucmo.nacha.record.finalform.field;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import javax.inject.Singleton;

/**
 * Default {@link FieldParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultFieldParser implements FieldParser {

  /**
   * Gets a {@link String} field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data.
   */
  @Override
  public String getString(final IntermediateRecord record, final RecordField field) {
    final String data = getStringOrNull(record, field);

    if (data == null) {
      throw new FieldParseException(field, "Expected field to contain data");
    }

    return data;
  }

  /**
   * Gets a {@link String} field.
   *
   * @param record The {@link IntermediateRecord}.
   * @param field The {@link RecordField}.
   * @return The field data or null if the field is empty.
   */
  @Override
  public String getStringOrNull(final IntermediateRecord record, final RecordField field) {
    String data = record
        .getFields()
        .get(field);

    if (data == null) {
      return null;
    }

    data = data.trim();
    if (data.isEmpty()) {
      return null;
    }

    return data;
  }
}
