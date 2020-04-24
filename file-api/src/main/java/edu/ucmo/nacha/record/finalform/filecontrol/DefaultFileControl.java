package edu.ucmo.nacha.record.finalform.filecontrol;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordType;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Default {@link FileControl} implementation.
 *
 * @author Garrett Ewens
 */
public class DefaultFileControl implements FileControl {

  // Properties
  private final long batchCount;
  private final long blockCount;
  private final long entryAndAddendaCount;
  private final long entryHash;
  private final double debitAmount;
  private final double creditAmount;
  private final String reserved;

  /**
   * Constructor.
   *
   * @param batchCount the batch count.
   * @param blockCount the total number of blocks in file.
   * @param entryAndAddendaCount the entry and addenda count.
   * @param entryHash the entry hash of first 8 digits of routing numbers.
   * @param debitAmount the total debit dollar amount of the transaction.
   * @param creditAmount the total credit dollar amount of the transaction.
   * @param reserved Reserved for bank use.
   */
  @Inject
  DefaultFileControl(
      @Assisted("batchCount") final long batchCount,
      @Assisted("blockCount") final long blockCount,
      @Assisted("entryAndAddendaCount") final long entryAndAddendaCount,
      @Assisted("entryHash") final long entryHash,
      @Assisted("debitAmount") final double debitAmount,
      @Assisted("creditAmount") final double creditAmount,
      @Assisted("reserved") @Nullable final String reserved) {

    this.batchCount = batchCount;
    this.blockCount = blockCount;
    this.entryAndAddendaCount = entryAndAddendaCount;
    this.entryHash = entryHash;
    this.debitAmount = debitAmount;
    this.creditAmount = creditAmount;
    this.reserved = reserved;
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  @Override
  public RecordType getType() {
    return RecordType.FILE_CONTROL;
  }

  /**
   * Gets the batch count.
   *
   * @return The batch count.
   */
  @Override
  public long getBatchCount() {
    return batchCount;
  }

  /**
   * Gets the total number of blocks in file.
   *
   * @return The total number of blocks in file.
   */
  @Override
  public long getBlockCount() {
    return blockCount;
  }

  /**
   * Gets the entry and addenda count.
   *
   * @return The entry and addenda count.
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
   * Reserved for bank use.
   *
   * @return Reserved for bank use.
   */
  @Override
  public Optional<String> getReserved() {
    return Optional.ofNullable(reserved);
  }
}
