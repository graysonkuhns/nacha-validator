package edu.ucmo.nacha.record;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Record fields.
 *
 * @author Grayson Kuhns
 */
public enum RecordField {

    // Entry Detail
    TRANSACTION_TYPE(RecordType.ENTRY_DETAIL, 2, 3),
    RECEIVER_ROUTING_NUMBER(RecordType.ENTRY_DETAIL, 4, 11),
    RECEIVER_ROUTING_NUMBER_CHECK_DIGIT(RecordType.ENTRY_DETAIL, 12, 12),
    RECEIVER_ACCOUNT_NUMBER(RecordType.ENTRY_DETAIL, 13, 29),
    TRANSACTION_AMOUNT(RecordType.ENTRY_DETAIL, 30, 39),
    RECEIVER_ID_NUMBER(RecordType.ENTRY_DETAIL, 40, 54),
    RECEIVER_NAME(RecordType.ENTRY_DETAIL, 55, 76),
    DISCRETIONARY_DATA(RecordType.ENTRY_DETAIL, 77, 78),
    HAS_ADDENDA(RecordType.ENTRY_DETAIL, 79, 79),
    TRACE_NUMBER(RecordType.ENTRY_DETAIL, 80, 94);

    // Properties
    private final RecordType recordType;
    private final int startPosition;
    private final int endPosition;

    /**
     * Constructor.
     *
     * @param recordType The {@link RecordType}.
     * @param startPosition The start position.
     * @param endPosition The end position.
     */
    RecordField(
            final RecordType recordType,
            final int startPosition,
            final int endPosition) {

        this.recordType = recordType;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    /**
     * Gets the {@link RecordType}.
     *
     * @return The {@link RecordType}.
     */
    public RecordType getRecordType() {
        return recordType;
    }

    /**
     * Gets the start position.
     *
     * @return The start position.
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Gets the end position.
     *
     * @return The end position.
     */
    public int getEndPosition() {
        return endPosition;
    }

    /**
     * Gets the {@link RecordField}s for a {@link RecordType}.
     *
     * @param recordType The {@link RecordType}.
     * @return The {@link RecordField}s.
     */
    public static List<RecordField> getFields(final RecordType recordType) {
        return Arrays
                .stream(values())
                .filter(field -> field.getRecordType().equals(recordType))
                .collect(Collectors.toList());
    }
}
