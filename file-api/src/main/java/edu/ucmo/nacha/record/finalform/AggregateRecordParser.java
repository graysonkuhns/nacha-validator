package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Aggregate {@link Record} parser.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class AggregateRecordParser implements RecordParser<Record> {

  // Dependencies
  private final Map<RecordType, SpecializedRecordParser> parsers;

  @Inject
  AggregateRecordParser(final Set<SpecializedRecordParser> parsers) {
    // Index the specialized parsers
    this.parsers = new HashMap<>();
    parsers.forEach(parser ->
        this.parsers.put(parser.getSupportedRecordType(), parser));
  }

  /**
   * Parses an {@link IntermediateRecord} into a final-form {@link Record}.
   *
   * @param record The {@link IntermediateRecord}.
   * @return The final-form {@link Record}.
   */
  @Override
  public Record parse(final IntermediateRecord record) {
    final RecordParser parser = parsers.get(record.getType());
    if (parser == null) {
      return null;
    }

    return parser.parse(record);
  }
}
