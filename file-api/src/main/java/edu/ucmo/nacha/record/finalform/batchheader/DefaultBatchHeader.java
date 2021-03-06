package edu.ucmo.nacha.record.finalform.batchheader;


import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.AbstractRecord;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Default {@link BatchHeader} implementation.
 *
 * @author King Butcher
 */
public class DefaultBatchHeader
    extends AbstractRecord
    implements BatchHeader {

  // Properties
  private final int serviceClassCode;
  private final String companyName;
  private final String discretionaryData;
  private final String companyIdentification;
  private final String standardEntry;
  private final String entryDescription;
  private final String descriptiveDate;
  private final String effectiveEntryDate;
  private final String settlementDate;
  private final String originatorStatusCode;
  private final String originatingDfiId;
  private final long batchNumber;

  /**
   * Constructor.
   *
   * @param index The index.
   * @param serviceClassCode The service class code.
   * @param companyName The company name.
   * @param discretionaryData The discretionary data.
   * @param companyIdentification The company identification.
   * @param standardEntry The standard entry.
   * @param entryDescription The entry description.
   * @param descriptiveDate The descriptive date.
   * @param effectiveEntryDate The effective entry date.
   * @param settlementDate The settlement date.
   * @param originatorStatusCode The originator status code.
   * @param originatingDfiId The originating dfi field.
   * @param batchNumber The batch number.
   */
  @Inject
  DefaultBatchHeader(
      @Assisted("index") final int index,
      @Assisted("serviceClassCode") final int serviceClassCode,
      @Assisted("companyName") final String companyName,
      @Assisted("discretionaryData") @Nullable final String discretionaryData,
      @Assisted("companyIdentification") final String companyIdentification,
      @Assisted("standardEntry") final String standardEntry,
      @Assisted("entryDescription") final String entryDescription,
      @Assisted("descriptiveDate") @Nullable final String descriptiveDate,
      @Assisted("effectiveEntryDate") final String effectiveEntryDate,
      @Assisted("settlementDate") @Nullable final String settlementDate,
      @Assisted("originatorStatusCode") final String originatorStatusCode,
      @Assisted("originatingDfiId") final String originatingDfiId,
      @Assisted("batchNumber") final long batchNumber) {

    super(index);
    this.serviceClassCode = serviceClassCode;
    this.companyName = companyName;
    this.discretionaryData = discretionaryData;
    this.companyIdentification = companyIdentification;
    this.standardEntry = standardEntry;
    this.entryDescription = entryDescription;
    this.descriptiveDate = descriptiveDate;
    this.effectiveEntryDate = effectiveEntryDate;
    this.settlementDate = settlementDate;
    this.originatorStatusCode = originatorStatusCode;
    this.originatingDfiId = originatingDfiId;
    this.batchNumber = batchNumber;
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  @Override
  public RecordType getType() {
    return RecordType.BATCH_HEADER;
  }

  /**
   * Gets the service class code.
   *
   * @return The service class code.
   */
  @Override
  public int getServiceClassCode() {
    return serviceClassCode;
  }

  /**
   * Gets the company name 
   *
   * @return The company name
   */
  @Override
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Gets any discretionary data for bank use.
   *
   * @return The discretionary data for bank use.
   */
  @Override
  public Optional<String> getDiscretionaryData() {
    return Optional.ofNullable(discretionaryData);
  }

  /**
   * Gets the company identification number.
   *
   * @return The company identification number.
   */
  @Override
  public String getCompanyIdentification() {
    return companyIdentification;
  }

  /**
   * Gets the identifier for the entries in the batch.
   *
   * @return The identifier for the entries in the batch.
   */
  @Override
  public String getStandardEntry() {
    return standardEntry;
  }

  /**
   * Gets the description of the transaction.
   *
   * @return The description of the transaction.
   */
  @Override
  public String getEntryDescription() {
    return entryDescription;
  }

  /**
   * Gets the date chosen to identify the record.
   *
   * @return The date chosen to identify the record.
   */
  @Override
  public Optional<String> getDescriptiveDate() {
    return Optional.ofNullable(descriptiveDate);
  }

  /**
   * Gets the effective date the transaction should be posted.
   *
   * <p>
   * Format: YYMMDD
   * </p>
   *
   * @return The effective date the transaction should be posted.
   */
  @Override
  public String getEffectiveEntryDate() {
    return effectiveEntryDate;
  }

  /**
   * Gets the date the transactions was settled.
   *
   * @return The date the transactions was settled.
   */
  @Override
  public Optional<String> getSettlementDate() {
    return Optional.ofNullable(settlementDate);
  }

  /**
   * Gets the origins identifier code.
   *
   * @return The origins identifier code.
   */
  @Override
  public String getOriginatorStatusCode() {
    return originatorStatusCode;
  }

  /**
   * Gets the originating dfi id.
   *
   * @return The originating dfi id.
   */
  @Override
  public String getOriginatingDfiId() {
    return originatingDfiId;
  }

  /**
   * Gets the sequential batch number.
   *
   * @return The sequential batch number.
   */
  @Override
  public long getBatchNumber() {
    return batchNumber;
  }
}
