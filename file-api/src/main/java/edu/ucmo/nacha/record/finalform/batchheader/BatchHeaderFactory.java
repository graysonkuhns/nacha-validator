package edu.ucmo.nacha.record.finalform.batchheader;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link BatchHeader} factory
 *
 * @author King Butcher
 */
public interface BatchHeaderFactory {

  /**
   * Creates an {@link BatchHeader}.
   *
   * @param index The index.
   * @param serviceClassCode The service class code.
   * @param companyName The company name
   * @param discretionaryData Any discretionary data for bank use.
   * @param companyIdentification The company identification number.
   * @param standardEntry The identifier for the entries in the batch.
   * @param entryDescription The description of the transaction.
   * @param descriptiveDate The date chosen to identify the record.
   * @param effectiveEntryDate The effective date the transaction should be posted.
   * @param settlementDate Thedate the transactions was settled.
   * @param originatorStatusCode The origins identifier code.
   * @param originatingDfiId The originating dfi id.
   * @param batchNumber The sequential batch number
   * @return The {@link BatchHeader}.
   */
  BatchHeader create(
      @Assisted("index") int index,
      @Assisted("serviceClassCode") final int serviceClassCode,
      @Assisted("companyName") final String companyName,
      @Assisted("discretionaryData") final String discretionaryData,
      @Assisted("companyIdentification") final String companyIdentification,
      @Assisted("standardEntry") final String standardEntry,
      @Assisted("entryDescription") final String entryDescription,
      @Assisted("descriptiveDate") final String descriptiveDate,
      @Assisted("effectiveEntryDate") final String effectiveEntryDate,
      @Assisted("settlementDate") final String settlementDate,
      @Assisted("originatorStatusCode") final String originatorStatusCode,
      @Assisted("originatingDfiId") final String originatingDfiId,
      @Assisted("batchNumber") final long batchNumber);
}
