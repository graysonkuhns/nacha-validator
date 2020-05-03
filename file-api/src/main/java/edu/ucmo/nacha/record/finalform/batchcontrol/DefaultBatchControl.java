package edu.ucmo.nacha.record.finalform.batchcontrol;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.AbstractRecord;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Default {@link BatchControl} implementation.
 *
 * @author Garrett Ewens
 */
public class DefaultBatchControl
    extends AbstractRecord
    implements BatchControl {

  // Properties
  private final int serviceClassCode;
  private final long entryAndAddendaCount;
  private final long entryHash;
  private final double debitAmount;
  private final double creditAmount;
  private final String companyIdentification;
  private final String messageAuthentication;
  private final String reserved;
  private final String originatingDfiId;
  private final long batchNumber;

  /**
   * Constructor
   *
   * @param index The index.
   * @param serviceClassCode The service class code.
   * @param entryAndAddendaCount The entry and addenda count in batch.
   * @param entryHash The entry hash of first 8 digits of routing numbers.
   * @param debitAmount The total debit dollar amount of the transaction.
   * @param creditAmount The total credit dollar amount of the transaction.
   * @param companyIdentification The company identification number.
   * @param messageAuthentication The message authentication code.
   * @param reserved Reserved for bank use.
   * @param originatingDfiId The originating dfi id.
   * @param batchNumber The sequential batch number.
   */
  @Inject
  DefaultBatchControl(
      @Assisted("index") final int index,
      @Assisted("serviceClassCode") final int serviceClassCode,
      @Assisted("entryAndAddendaCount") final long entryAndAddendaCount,
      @Assisted("entryHash") final long entryHash,
      @Assisted("debitAmount") final double debitAmount,
      @Assisted("creditAmount") final double creditAmount,
      @Assisted("companyIdentification") final String companyIdentification,
      @Assisted("messageAuthentication") @Nullable final String messageAuthentication,
      @Assisted("reserved") @Nullable final String reserved,
      @Assisted("originatingDfiId") final String originatingDfiId,
      @Assisted("batchNumber") final long batchNumber) {

    super(index);
    this.serviceClassCode = serviceClassCode;
    this.entryAndAddendaCount = entryAndAddendaCount;
    this.entryHash = entryHash;
    this.debitAmount = debitAmount;
    this.creditAmount = creditAmount;
    this.companyIdentification = companyIdentification;
    this.messageAuthentication = messageAuthentication;
    this.reserved = reserved;
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
    return RecordType.BATCH_CONTROL;
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
   * Gets the entry and addenda count in batch.
   *
   * @return The entry and addenda count in batch.
   */
  @Override
  public long getEntryAndAddendaCount() {
    return entryAndAddendaCount;
  }

  /**
   * Gets the entry hash of first 8 digits of routing numbers.
   *
   * @return The entry hash of first 8 digits of routing numbers.
   */
  @Override
  public long getEntryHash() {
    return entryHash;
  }

  /**
   * Gets the total debit dollar amount of the transaction.
   *
   * @return The total debit dollar amount of the transaction.
   */
  @Override
  public double getDebitAmount() {
    return debitAmount;
  }

  /**
   * Gets the total credit dollar amount of the transaction.
   *
   * @return The total credit dollar amount of the transaction.
   */
  @Override
  public double getCreditAmount() {
    return creditAmount;
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
   * Gets the message authentication code.
   *
   * @return The message authentication code.
   */
  @Override
  public Optional<String> getMessageAuthentication() {
    return Optional.ofNullable(messageAuthentication);
  }

  /**
   * Reserved for bank use.
   *
   * @return Reserved for bank use.
   */
  @Override
  public Optional<String> getReserved() {
    return Optional.ofNullable(reserved);
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
