package edu.ucmo.nacha.record;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Aggregate {@link RecordParser}.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class AggregateRecordParser implements RecordParser {

  // Constants
  private static final int RECORD_LENGTH = 94;

  // Dependencies
  private final Map<RecordType, RecordParser> parsers;

  /**
   * Constructor.
   *
   * @param parsers The {@link SpecializedRecordParser}s to delegate to.
   */
  @Inject
  AggregateRecordParser(final Set<SpecializedRecordParser> parsers) {
    // Index the parsers by their supported record type
    this.parsers = new HashMap<>();
    parsers.forEach(parser ->
        this.parsers.put(parser.getSupportedRecordType(), parser));
  }

  /**
   * Parses a {@link Record}.
   *
   * @param input The input to parse.
   * @return The {@link Record}.
   */
  @Override
  public Record parse(final String input) {
    // Ensure the input is the appropriate length
    if (input.length() != RECORD_LENGTH) {
      throw new InvalidRecordException(
          input,
          String.format("Records are expected to be %d characters long", RECORD_LENGTH));
    }

    // Determine the record type
    final RecordType recordType;
    try {
      recordType = RecordType.getForId(input.charAt(0));
    } catch (InvalidRecordTypeException ex) {
      throw new InvalidRecordException(input, ex);
    }

    // Get the appropriate record parser
    final RecordParser parser = parsers.get(recordType);
    if (parser == null) {
      throw new UnsupportedOperationException(
          String.format("Parsing records of type \"%s\" is not currently supported", recordType.toString()));
    }

    // Parse the record
    return parser.parse(input);
  }
}
