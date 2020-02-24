package edu.ucmo.nacha.record;

/**
 * Invalid record exception.
 *
 * @author Grayson Kuhns
 */
public class InvalidRecordException extends RuntimeException {

    // Properties
    private final String record;

    /**
     * Constructor.
     *
     * @param record The invalid record.
     * @param reason The reason the record is invalid.
     */
    public InvalidRecordException(final String record, final Throwable reason) {
        super(reason);
        this.record = record;
    }

    /**
     * Constructor.
     *
     * @param record The invalid record.
     * @param reason The reason the record is invalid.
     */
    public InvalidRecordException(final String record, final String reason) {
        super(reason);
        this.record = record;
    }

    /**
     * Gets the invalid record.
     *
     * @return The invalid record.
     */
    public String getRecord() {
        return record;
    }
}
