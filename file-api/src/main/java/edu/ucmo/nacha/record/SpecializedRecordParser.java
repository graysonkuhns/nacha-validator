package edu.ucmo.nacha.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * General {@link RecordParser} implementation.
 *
 * @author Grayson Kuhns
 */
public class SpecializedRecordParser implements RecordParser {

    // Properties
    private final RecordType recordType;
    private final List<RecordField> recordFields;

    /**
     * Constructor.
     *
     * @param recordType The {@link RecordType}.
     */
    public SpecializedRecordParser(final RecordType recordType) {
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
     * Parses a {@link Record}.
     *
     * @param input The input to parse.
     * @return The {@link Record}.
     */
    @Override
    public Record parse(final String input) {
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

        return new DefaultRecord(recordType, fields);
    }
}
