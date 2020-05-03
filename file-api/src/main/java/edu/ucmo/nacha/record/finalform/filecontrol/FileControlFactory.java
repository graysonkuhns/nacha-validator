package edu.ucmo.nacha.record.finalform.filecontrol;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link FileControl} factory.
 *
 * @author Garrett Ewens
 */
public interface FileControlFactory {

  /**
   * Creates an {@link FileControl}.
   *
   * @param index The index.
   * @param batchCount the batch count.
   * @param blockCount the total number of blocks in file.
   * @param entryAndAddendaCount the entry and addenda count.
   * @param entryHash the entry hash of first 8 digits of routing numbers.
   * @param debitAmount the total debit dollar amount of the transaction.
   * @param creditAmount the total credit dollar amount of the transaction.
   * @param reserved Reserved for bank use.
   * @return The {@link FileControl}.
   */
  FileControl create(
      @Assisted("index") int index,
      @Assisted("batchCount") final long batchCount,
      @Assisted("blockCount") final long blockCount,
      @Assisted("entryAndAddendaCount") final long entryAndAddendaCount,
      @Assisted("entryHash") final long entryHash,
      @Assisted("debitAmount") final double debitAmount,
      @Assisted("creditAmount") final double creditAmount,
      @Assisted("reserved") final String reserved);
}
