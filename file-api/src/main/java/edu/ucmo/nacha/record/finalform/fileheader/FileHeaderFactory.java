package edu.ucmo.nacha.record.finalform.fileheader;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link FileHeader} factory.
 *
 * @author Grayson Kuhns
 */
public interface FileHeaderFactory {

  /**
   * Creates a {@link FileHeader}.
   *
   * @param index The index.
   * @param priorityCode The priority code.
   * @param immediateDestination The immediate destination.
   * @param immediateOrigin The immediate origin.
   * @param fileCreationDate The file creation date.
   * @param fileCreationTime The file creation time.
   * @param fileIdModifier The file ID modifier.
   * @param recordSize The record size.
   * @param blockingFactor The blocking factor.
   * @param formatCode The format code.
   * @param immediateDestinationName The immediate destination name.
   * @param immediateOriginName The immediate origin name.
   * @param referenceCode The reference code.
   * @return The {@link FileHeader}.
   */
  FileHeader create(
      @Assisted("index") int index,
      @Assisted("priorityCode") int priorityCode,
      @Assisted("immediateDestination") long immediateDestination,
      @Assisted("immediateOrigin") long immediateOrigin,
      @Assisted("fileCreationDate") String fileCreationDate,
      @Assisted("fileCreationTime") String fileCreationTime,
      @Assisted("fileIdModifier") String fileIdModifier,
      @Assisted("recordSize") int recordSize,
      @Assisted("blockingFactor") int blockingFactor,
      @Assisted("formatCode") int formatCode,
      @Assisted("immediateDestinationName") String immediateDestinationName,
      @Assisted("immediateOriginName") String immediateOriginName,
      @Assisted("referenceCode") String referenceCode);
}
