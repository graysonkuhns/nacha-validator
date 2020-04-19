package edu.ucmo.nacha.record.finalform.entrydetail;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordType;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Default {@link EntryDetail} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultEntryDetail implements EntryDetail {

  // Properties
  private final int transactionType;
  private final long receiverRoutingNumber;
  private final int receiverRoutingNumberCheckDigit;
  private final String receiverAccountNumber;
  private final double transactionAmount;
  private final String receiverIdNumber;
  private final String receiverName;
  private final String discretionaryData;
  private final boolean hasAddenda;
  private final long traceNumber;

  /**
   * Constructor.
   *
   * @param transactionType The transaction type code.
   * @param receiverRoutingNumber The routing number of the receiving institution.
   * @param receiverRoutingNumberCheckDigit The check digit for the routing number of the receiving institution.
   * @param receiverAccountNumber The account number of the receiving institution.
   * @param transactionAmount The dollar amount of the transaction.
   * @param receiverIdNumber The receiver identification number.
   * @param receiverName The name of the receiver.
   * @param discretionaryData The discretionary data.
   * @param hasAddenda True if the entry has an addenda.
   * @param traceNumber The trace number.
   */
  @Inject
  DefaultEntryDetail(
      @Assisted("transactionType") final int transactionType,
      @Assisted("receiverRoutingNumber") final long receiverRoutingNumber,
      @Assisted("receiverRoutingNumberCheckDigit") final int receiverRoutingNumberCheckDigit,
      @Assisted("receiverAccountNumber") final String receiverAccountNumber,
      @Assisted("transactionAmount") final double transactionAmount,
      @Assisted("receiverIdNumber") @Nullable final String receiverIdNumber,
      @Assisted("receiverName") final String receiverName,
      @Assisted("discretionaryData") @Nullable final String discretionaryData,
      @Assisted("hasAddenda") final boolean hasAddenda,
      @Assisted("traceNumber") final long traceNumber) {

    this.transactionType = transactionType;
    this.receiverRoutingNumber = receiverRoutingNumber;
    this.receiverRoutingNumberCheckDigit = receiverRoutingNumberCheckDigit;
    this.receiverAccountNumber = receiverAccountNumber;
    this.transactionAmount = transactionAmount;
    this.receiverIdNumber = receiverIdNumber;
    this.receiverName = receiverName;
    this.discretionaryData = discretionaryData;
    this.hasAddenda = hasAddenda;
    this.traceNumber = traceNumber;
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  @Override
  public RecordType getType() {
    return RecordType.ENTRY_DETAIL;
  }

  /**
   * Gets the transaction type code.
   *
   * @return The transaction type code.
   */
  @Override
  public int getTransactionType() {
    return transactionType;
  }

  /**
   * Gets the routing number of the receiving institution.
   *
   * @return The routing number of the receiving institution.
   */
  @Override
  public long getReceiverRoutingNumber() {
    return receiverRoutingNumber;
  }

  /**
   * Gets the check digit for the routing number of the receiving institution.
   *
   * @return The check digit for the routing number of the receiving institution.
   */
  @Override
  public int getReceiverRoutingNumberCheckDigit() {
    return receiverRoutingNumberCheckDigit;
  }

  /**
   * Gets the account number of the receiving institution.
   *
   * @return The account number of the receiving institution.
   */
  @Override
  public String getReceiverAccountNumber() {
    return receiverAccountNumber;
  }

  /**
   * Gets the dollar amount of the transaction.
   *
   * @return The dollar amount of the transaction.
   */
  @Override
  public double getTransactionAmount() {
    return transactionAmount;
  }

  /**
   * Gets the receiver identification number.
   *
   * @return The receiver identification number.
   */
  @Override
  public Optional<String> getReceiverIdNumber() {
    return Optional.ofNullable(receiverIdNumber);
  }

  /**
   * Gets the name of the receiver.
   *
   * @return The name of the receiver.
   */
  @Override
  public String getReceiverName() {
    return receiverName;
  }

  /**
   * Gets the discretionary data.
   *
   * @return The discretionary data.
   */
  @Override
  public Optional<String> getDiscretionaryData() {
    return Optional.ofNullable(discretionaryData);
  }

  /**
   * Checks if the entry has an addenda.
   *
   * @return True if the entry has an addenda.
   */
  @Override
  public boolean hasAddenda() {
    return hasAddenda;
  }

  /**
   * Gets the trace number.
   *
   * @return The trace number.
   */
  @Override
  public long getTraceNumber() {
    return traceNumber;
  }
}
