package edu.ucmo.nacha.record.finalform.batchheader;

import edu.ucmo.nacha.record.finalform.Record;
import java.util.Optional;

/**
 * Batch header.
 *
 * @author King Butcher
 */
public interface BatchHeader extends Record {

  /**
   * Gets the service class code.
   *
   * @return The service class code.
   */
  int getServiceClassCode();

  /**
   * Gets the company name 
   *
   * @return The company name
   */
  String getCompanyName();

  /**
   * Gets any discretionary data for bank use.
   *
   * @return The discretionary data for bank use.
   */
  Optional<String> getDiscretionaryData();

  /**
   * Gets the company identification number.
   *
   * @return The company identification number.
   */
  String getCompanyIdentification();

  /**
   * Gets the identifier for the entries in the batch.
   *
   * @return The identifier for the entries in the batch.
   */
  String getStandardEntry();

  /**
   * Gets the description of the transaction.
   *
   * @return The description of the transaction.
   */
  String getEntryDescription();

  /**
   * Gets the date chosen to identify the record.
   *
   * @return The date chosen to identify the record.
   */
  Optional<String> getDescriptiveDate();

  /**
   * Gets the effective date the transaction should be posted.
   *
   * <p>
   * Format: YYMMDD
   * </p>
   *
   * @return The effective date the transaction should be posted.
   */
  String getEffectiveEntryDate();

  /**
   * Gets the date the transactions was settled.
   *
   * @return The date the transactions was settled.
   */
  Optional<String> getSettlementDate();

  /**
   * Gets the origins identifier code.
   *
   * @return The origins identifier code.
   */
  String getOriginatorStatusCode();

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
