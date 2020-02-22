package edu.ucmo.nacha.file.entrydetail;

import edu.ucmo.nacha.file.entrydetail.transactiontype.TransactionType;
import edu.ucmo.nacha.file.recordold.RecordFieldParser;
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
            fieldParser.getInt(record, "transactionType", 2, 3));

    final long receiverRoutingNumber = fieldParser.getLong(
        record, "receiverRoutingNumber", 4, 11);

    final int receiverRoutingNumberCheckDigit = fieldParser.getInt(
        record, "receiverRoutingNumberCheckDigit", 12, 12);

    final String receiverAccountNumber = fieldParser.getString(
        record, "receiverAccountNumber", 13, 29);

    final double transactionAmount = fieldParser.getDollarAmount(
        record, "transactionAmount", 30, 39);

    final String receiverIdNumber = fieldParser.getStringOrNull(
        record, "receiverIdNumber", 40, 54);

    final String receiverName = fieldParser.getString(
        record, "receiverName", 55, 76);

    final String discretionaryData = fieldParser.getStringOrNull(
        record, "discretionaryData", 77, 78);

    final boolean hasAddenda = fieldParser.getBoolean(
        record, "hasAddenda", 79, 79);

    final long traceNumber = fieldParser.getLong(
        record, "traceNumber", 80, 94);

    return entryDetailFactory.create(
        transactionType,
        receiverRoutingNumber,
        receiverRoutingNumberCheckDigit,
        receiverAccountNumber,
        transactionAmount,
        receiverIdNumber,
        receiverName,
        discretionaryData,
        hasAddenda,
        traceNumber);
  }
}
