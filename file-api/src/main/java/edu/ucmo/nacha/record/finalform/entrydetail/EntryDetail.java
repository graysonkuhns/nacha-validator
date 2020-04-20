package edu.ucmo.nacha.record.finalform.entrydetail;

import edu.ucmo.nacha.record.finalform.Record;
import java.util.Optional;

/**
 * Entry detail.
 *
 * @author Grayson Kuhns
 */
public interface EntryDetail extends Record {

  /**
   * Gets the transaction type code.
   *
   * @return The transaction type code.
   */
  int getTransactionType();

  /**
   * Gets the routing number of the receiving institution.
   *
   * @return The routing number of the receiving institution.
   */
  long getReceiverRoutingNumber();

  /**
   * Gets the check digit for the routing number of the receiving institution.
   *
   * @return The check digit for the routing number of the receiving institution.
   */
  int getReceiverRoutingNumberCheckDigit();

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
  Optional<String> getDiscretionaryData();

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
