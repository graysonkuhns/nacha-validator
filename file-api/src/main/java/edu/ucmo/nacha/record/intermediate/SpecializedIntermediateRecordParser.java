package edu.ucmo.nacha.record.intermediate;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Specialized {@link IntermediateRecordParser} for a specific  {@link RecordType}.
 *
 * @author Grayson Kuhns
 */
public class SpecializedIntermediateRecordParser implements IntermediateRecordParser {

  // Properties
  private final RecordType recordType;
  private final List<RecordField> recordFields;

  /**
   * Constructor.
   *
   * @param recordType The {@link RecordType}.
   */
  public SpecializedIntermediateRecordParser(final RecordType recordType) {
    this.recordType = recordType;
    this.recordFields = RecordField.getFields(recordType);
  }

  /**
   * Gets the supported {@link RecordType}.
   *
   * @return The supported {@link RecordType}.
   */
  public RecordType getSupportedRecordType() {
    return recordType;
  }

  /**
   * Parses a {@link IntermediateRecord}.
   *
   * @param input The input to parse.
   * @return The {@link IntermediateRecord}.
   */
  @Override
  public IntermediateRecord parse(final String input) {
    final Map<RecordField, String> fields = new HashMap<>();

    recordFields.forEach(field -> {
      // Start and stop positions should be inclusive
      final int startPosition = field.getStartPosition() - 1;
      final int stopPosition = field.getEndPosition();

      // Get the field data
      final String fieldData = input.substring(startPosition, stopPosition);

      // Save the field data
      fields.put(field, fieldData);
    });

    return new DefaultIntermediateRecord(recordType, fields);
  }
}
