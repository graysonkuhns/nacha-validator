package edu.ucmo.nacha.record.intermediate;

import edu.ucmo.nacha.record.InvalidRecordException;
import edu.ucmo.nacha.record.InvalidRecordTypeException;
import edu.ucmo.nacha.record.RecordType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Aggregate {@link IntermediateRecordParser}.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class AggregateIntermediateRecordParser implements IntermediateRecordParser {

  // Constants
  private static final int RECORD_LENGTH = 94;

  // Dependencies
  private final Map<RecordType, IntermediateRecordParser> parsers;

  /**
   * Constructor.
   *
   * @param parsers The {@link SpecializedIntermediateRecordParser}s to delegate to.
   */
  @Inject
  AggregateIntermediateRecordParser(final Set<SpecializedIntermediateRecordParser> parsers) {
    // Index the parsers by their supported record type
    this.parsers = new HashMap<>();
    parsers.forEach(parser ->
        this.parsers.put(parser.getSupportedRecordType(), parser));
  }

  /**
   * Parses a {@link IntermediateRecord}.
   *
   * @param input The input to parse.
   * @return The {@link IntermediateRecord} or null if the input is padding.
   */
  @Override
  public IntermediateRecord parse(final String input) {
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
    final IntermediateRecordParser parser = parsers.get(recordType);
    if (parser == null) {
      throw new UnsupportedOperationException(
          String.format("Parsing records of type \"%s\" is not currently supported", recordType.toString()));
    }

    // Parse the record
    return parser.parse(input);
  }
}
