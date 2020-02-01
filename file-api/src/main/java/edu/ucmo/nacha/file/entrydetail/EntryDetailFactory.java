package edu.ucmo.nacha.file.entrydetail;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.file.entrydetail.transactiontype.TransactionType;

/**
 * {@link EntryDetail} factory.
 *
 * @author Grayson Kuhns
 */
public interface EntryDetailFactory {

  /**
   * Creates an {@link EntryDetail}.
   *
   * @param transactionType The {@link TransactionType}.
   * @param receiverRoutingNumber The routing number of the receiving institution.
   * @param receiverAccountNumber The account number of the receiving institution.
   * @param transactionAmount The dollar amount of the transaction.
   * @param receiverIdNumber The receiver identification number.
   * @param receiverName The name of the receiver.
   * @param discretionaryData The discretionary data.
   * @param hasAddenda True if the entry has an addenda.
   * @param traceNumber The trace number.
   * @return The {@link EntryDetail}.
   */
  EntryDetail create(
      @Assisted("transactionType") TransactionType transactionType,
      @Assisted("receiverRoutingNumber") long receiverRoutingNumber,
      @Assisted("receiverAccountNumber") String receiverAccountNumber,
      @Assisted("transactionAmount") double transactionAmount,
      @Assisted("receiverIdNumber") String receiverIdNumber,
      @Assisted("receiverName") String receiverName,
      @Assisted("discretionaryData") String discretionaryData,
      @Assisted("hasAddenda") boolean hasAddenda,
      @Assisted("traceNumber") long traceNumber);
}