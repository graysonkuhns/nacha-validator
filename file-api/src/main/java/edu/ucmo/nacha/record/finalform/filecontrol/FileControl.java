package edu.ucmo.nacha.record.finalform.filecontrol;

import edu.ucmo.nacha.record.finalform.Record;
import java.util.Optional;

/**
 * File Control.
 *
 * @author Garrett Ewens
 */
public interface FileControl extends Record {

  /**
   * Gets the batch count.
   *
   * @return The batch count.
   */
  long getBatchCount();

  /**
   * Gets the total number of blocks in file.
   *
   * @return The total number of blocks in file.
   */
  long getBlockCount();

  /**
   * Gets the entry and addenda count.
   *
   * @return The entry and addenda count.
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
   * Reserved for bank use.
   *
   * @return Reserved for bank use.
   */
  Optional<String> getReserved();
}
