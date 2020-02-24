package edu.ucmo.nacha.record;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;
import java.util.Map;

/**
 * Default {@link Record} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultRecord implements Record {

    // Properties
    private final RecordType type;
    private final Map<RecordField, String> fields;

    /**
     * Constructor.
     *
     * @param type The {@link RecordType}.
     * @param fields The fields.
     */
    @Inject
    DefaultRecord(
            final RecordType type,
            final Map<RecordField, String> fields) {

        this.type = type;
        this.fields = fields;
    }

    @Override
    public RecordType getType() {
        return type;
    }

    @Override
    public Map<RecordField, String> getFields() {
        return fields;
    }
}
