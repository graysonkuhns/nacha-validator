package edu.ucmo.nacha.record.finalform.batchcontrol;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link BatchControl} factory
 *
 * @author Garrett Ewens
 */
public interface BatchControlFactory {

  /**
   * Creates an {@link BatchControl}.
   *
   * @param serviceClassCode The service class code.
   * @param entryAndAddendaCount The entry and addenda count in batch.
   * @param entryHash The entry hash of first 8 digits of routing numbers.
   * @param debitAmount The total debit dollar amount of the transaction.
   * @param creditAmount The total credit dollar amount of the transaction.
   * @param companyIdentification The company identification number.
   * @param messageAuthentication The message authentication code.
   * @param reserved Reserved for bank use.
   * @param originatingDfiId The originating dfi id.
   * @param batchNumber The sequential batch number
   * @return The {@link BatchControl}.
   */
  BatchControl create(
      @Assisted("serviceClassCode") final int serviceClassCode,
      @Assisted("entryAndAddendaCount") final long entryAndAddendaCount,
      @Assisted("entryHash") final long entryHash,
      @Assisted("debitAmount") final double debitAmount,
      @Assisted("creditAmount") final double creditAmount,
      @Assisted("companyIdentification") final String companyIdentification,
      @Assisted("messageAuthentication") final String messageAuthentication,
      @Assisted("reserved") final String reserved,
      @Assisted("originatingDfiId") final String originatingDfiId,
      @Assisted("batchNumber") final long batchNumber);
}
