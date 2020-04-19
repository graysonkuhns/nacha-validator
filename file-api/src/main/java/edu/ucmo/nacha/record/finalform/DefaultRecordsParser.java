package edu.ucmo.nacha.record.finalform;

import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link RecordsParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultRecordsParser implements RecordsParser {

  // Dependencies
  private final RecordParser recordParser;

  /**
   * Constructor.
   *
   * @param recordParser The {@link RecordParser}.
   */
  @Inject
  DefaultRecordsParser(final RecordParser recordParser) {
    this.recordParser = recordParser;
  }

  /**
   * Parses {@link IntermediateRecord}s into final-form {@link Record}s.
   *
   * @param intermediateRecords The {@link IntermediateRecord}s.
   * @return The final-form {@link Record}s.
   */
  @Override
  public List<Record> parse(final List<IntermediateRecord> intermediateRecords) {
    return intermediateRecords
        .stream()
        .map(recordParser::parse)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }
}
