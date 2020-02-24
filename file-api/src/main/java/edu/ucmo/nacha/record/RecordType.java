package edu.ucmo.nacha.record;

import java.util.Arrays;

/**
 * Record types.
 *
 * @author Grayson Kuhns
 */
public enum RecordType {
    FILE_HEADER(1),
    BATCH_HEADER(5),
    ENTRY_DETAIL(6),
    ENTRY_DETAIL_ADDENDA(7),
    BATCH_CONTROL(8),
    FILE_CONTROL(9);

    // Properties
    private final int typeCode;

    /**
     * Constructor.
     *
     * @param typeCode The record type code.
     */
    RecordType(final int typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * Gets the record type code.
     *
     * @return The record type code.
     */
    public int getTypeCode() {
        return typeCode;
    }

    /**
     * Gets the {@link RecordType} for an ID.
     *
     * @param typeCode The ID.
     * @return The {@link RecordType}.
     */
    public static RecordType getForId(final char typeCode) {
        return getForId(Character.toString(typeCode));
    }

    /**
     * Gets the {@link RecordType} for an ID.
     *
     * @param typeCode The ID.
     * @return The {@link RecordType}.
     */
    public static RecordType getForId(final String typeCode) {
        // Convert to string code to a numeric code
        final int code;
        try {
            code = Integer.parseInt(typeCode);
        } catch (NumberFormatException ex) {
            throw new InvalidRecordTypeException(typeCode);
        }

        // Detect the record type
        return getForId(code);
    }

    /**
     * Gets the {@link RecordType} for an ID.
     *
     * @param typeCode The ID.
     * @return The {@link RecordType}.
     */
    public static RecordType getForId(final int typeCode) {
        return Arrays
                .stream(values())
                .filter(type -> type.typeCode == typeCode)
                .findFirst()
                .orElseThrow(() -> new InvalidRecordTypeException(typeCode));
    }
}
