package edu.ucmo.nacha.record.finalform.entrydetailaddenda;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link EntryDetailAddenda} factory.
 *
 * @author King Butcher
 */
public interface EntryDetailAddendaFactory {

  /**
   * Creates an {@link EntryDetailAddenda}.
   *
   * @param index The index.
   * @param typeCode The record and addenda type code.
   * @param paymentInfo Any payment related information.
   * @param addendaSequenceNumber The number which sequences the addenda record.
   * @param entrySequenceNumber The number which sequences the entry record.
   * @return The {@link EntryDetailAddenda}.
   */
  EntryDetailAddenda create(
      @Assisted("index") int index,
      @Assisted("typeCode") int typeCode,
      @Assisted("paymentInfo") String paymentInfo,
      @Assisted("addendaSequenceNumber") int addendaSequenceNumber,
      @Assisted("entrySequenceNumber") int entrySequenceNumber);
}
