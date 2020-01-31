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

  
  double getTransactionAmount();

  Optional<String> getReceiverIdNumber();

  String getReceiverName();

  Optional<String> getDiscretionaryDate();

  boolean hasAddenda();

  long getTraceNumber();
}
