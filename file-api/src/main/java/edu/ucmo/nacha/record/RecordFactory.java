package edu.ucmo.nacha.record;

import java.util.Map;

/**
 * The {@link Record} factory.
 *
 * @author Grayson Kuhns
 */
public interface RecordFactory {

    /**
     * Creates a {@link Record}.
     *
     * @param type The {@link RecordType}.
     * @param fields The fields.
     * @return The {@link Record}.
     */
    Record create(final RecordType type, final Map<RecordField, String> fields);
}
