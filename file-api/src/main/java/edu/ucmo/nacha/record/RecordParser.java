package edu.ucmo.nacha.record;

/**
 * Record parser.
 *
 * @author Grayson Kuhns
 */
public interface RecordParser {

    /**
     * Gets the supported {@link RecordType}.
     *
     * @return The supported {@link RecordType}.
     */
    RecordType getSupportedRecordType();

    /**
     * Parses a {@link Record}.
     *
     * @param input The input to parse.
     * @return The {@link Record}.
     */
    Record parse(String input);
}
