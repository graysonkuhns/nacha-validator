package edu.ucmo.nacha.file.entrydetail;

import edu.ucmo.nacha.file.entrydetail.transactiontype.TransactionType;
import edu.ucmo.nacha.file.record.RecordFieldParser;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Default {@link EntryDetailParser} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultEntryDetailParser implements EntryDetailParser {

  // Dependencies
  private final RecordFieldParser fieldParser;
  private final EntryDetailFactory entryDetailFactory;

  /**
   * Constructor.
   *
   * @param fieldParser The {@link RecordFieldParser}.
   * @param entryDetailFactory The {@link EntryDetailFactory}.
   */
  @Inject
  DefaultEntryDetailParser(
      final RecordFieldParser fieldParser,
      final EntryDetailFactory entryDetailFactory) {

    this.fieldParser = fieldParser;
    this.entryDetailFactory = entryDetailFactory;
  }

  /**
   * Parses an {@link EntryDetail}.
   *
   * @param record The record.
   * @return The {@link EntryDetail}.
   */
  @Override
  public EntryDetail parse(final String record) {
    final TransactionType transactionType =
        TransactionType.getForCode(
            fieldParser.getInt(record, 2, 3));

    final long receiverRoutingNumber = fieldParser.getLong(record, 4, 11);
    final String receiverAccountNumber = fieldParser.getString(record, 13, 29);
    final double transactionAmount = fieldParser.getDouble(record, 30, 39);
    final String receiverIdNumber = fieldParser.getString(record, 40, 54);
    final String receiverName = fieldParser.getString(record, 55, 76);
    final String discretionaryData = null;
    final boolean hasAddenda = fieldParser.getBoolean(record, 79, 79);
    final long traceNumber = fieldParser.getLong(record, 80, 94);

    return entryDetailFactory.create(
        transactionType,
        receiverRoutingNumber,
        receiverAccountNumber,
        transactionAmount,
        receiverIdNumber,
        receiverName,
        discretionaryData,
        hasAddenda,
        traceNumber);
  }
}
