package edu.ucmo.nacha.record.finalform.entrydetailaddenda;

import edu.ucmo.nacha.record.finalform.Record;
import java.util.Optional;

/**
 * Entry detail addenda.
 *
 * @author King Butcher
 */
public interface EntryDetailAddenda extends Record {

  /**
   * Gets the record and addenda type code.
   *
   * @return The record and addenda type code.
   */
  int getTypeCode();

  /**
   * Gets any payment related information.
   *
   * @return Any payment related information
   */
  Optional<String> getPaymentInformation();

  /**
   * Gets the number which sequences the addenda record.
   *
   * @return The number which sequences the addenda record.
   */
  int getAddendaSequenceNumber();

  /**
   * Gets the number which sequences the entry record.
   *
   * @return The number which sequences the entry record.
   */
  int getEntrySequenceNumber();
}
