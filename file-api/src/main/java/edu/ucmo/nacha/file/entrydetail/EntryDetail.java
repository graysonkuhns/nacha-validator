package edu.ucmo.nacha.file.entrydetail;

import edu.ucmo.nacha.file.entrydetail.transactiontype.TransactionType;
import java.util.Optional;

/**
 * Entry detail.
 *
 * @author Grayson Kuhns
 */
public interface EntryDetail {

  /**
   * Gets the {@link TransactionType}.
   *
   * @return The {@link TransactionType}.
   */
  TransactionType getTransactionType();

  /**
   * Gets the routing number of the receiving institution.
   *
   * @return The routing number of the receiving institution.
   */
  long getReceiverRoutingNumber();

  /**
   * Gets the account number of the receiving institution.
   *
   * @return The account number of the receiving institution.
   */
  String getReceiverAccountNumber();

  /**
   * Gets the dollar amount of the transaction.
   *
   * @return The dollar amount of the transaction.
   */
  double getTransactionAmount();

  /**
   * Gets the receiver identification number.
   *
   * @return The receiver identification number.
   */
  Optional<String> getReceiverIdNumber();

  /**
   * Gets the name of the receiver.
   *
   * @return The name of the receiver.
   */
  String getReceiverName();

  /**
   * Gets the discretionary data.
   *
   * @return The discretionary data.
   */
  Optional<String> getDiscretionaryDate();

  /**
   * Checks if the entry has an addenda.
   *
   * @return True if the entry has an addenda.
   */
  boolean hasAddenda();

  /**
   * Gets the trace number.
   *
   * @return The trace number.
   */
  long getTraceNumber();
}
