package edu.ucmo.nacha.record.finalform.batchcontrol;

import edu.ucmo.nacha.record.finalform.Record;
import java.util.Optional;

/**
 * Batch control.
 *
 * @author Garrett Ewens
 */
public interface BatchControl extends Record {

  /**
   * Gets the service class code.
   *
   * @return The service class code.
   */
  int getServiceClassCode();

  /**
   * Gets the entry and addenda count in batch.
   *
   * @return The entry and addenda count in batch.
   */
  long getEntryAndAddendaCount();

  /**
   * Gets the entry hash of first 8 digits of routing numbers.
   *
   * @return The entry hash of first 8 digits of routing numbers.
   */
  long getEntryHash();

  /**
   * Gets the total debit dollar amount of the transaction.
   *
   * @return The total debit dollar amount of the transaction.
   */
  double getDebitAmount();

  /**
   * Gets the total credit dollar amount of the transaction.
   *
   * @return The total credit dollar amount of the transaction.
   */
  double getCreditAmount();

  /**
   * Gets the company identification number.
   *
   * @return The company identification number.
   */
  String getCompanyIdentification();

  /**
   * Gets the message authentication code.
   *
   * @return The message authentication code.
   */
  Optional<String> getMessageAuthentication();

  /**
   * Reserved for bank use.
   *
   * @return Reserved for bank use.
   */
  Optional<String> getReserved();

  /**
   * Gets the originating dfi id.
   *
   * @return The originating dfi id.
   */
  String getOriginatingDfiId();

  /**
   * Gets the sequential batch number.
   *
   * @return The sequential batch number.
   */
  long getBatchNumber();
}
