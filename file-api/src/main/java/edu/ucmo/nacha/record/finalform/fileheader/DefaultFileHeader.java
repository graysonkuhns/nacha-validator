package edu.ucmo.nacha.record.finalform.fileheader;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Default {@link FileHeader} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultFileHeader implements FileHeader {

  // Properties
  private final int priorityCode;
  private final long immediateDestination;
  private final long immediateOrigin;
  private final String fileCreationDate;
  private final String fileCreationTime;
  private final String fileIdModifier;
  private final int recordSize;
  private final int blockingFactor;
  private final int formatCode;
  private final String immediateDestinationName;
  private final String immediateOriginName;
  private final String referenceCode;

  /**
   * Constructor.
   *
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
   */
  @Inject
  public DefaultFileHeader(
      @Assisted("priorityCode") final int priorityCode,
      @Assisted("immediateDestination") final long immediateDestination,
      @Assisted("immediateOrigin") final long immediateOrigin,
      @Assisted("fileCreationDate") final String fileCreationDate,
      @Assisted("fileCreationTime") @Nullable final String fileCreationTime,
      @Assisted("fileIdModifier") final String fileIdModifier,
      @Assisted("recordSize") final int recordSize,
      @Assisted("blockingFactor") final int blockingFactor,
      @Assisted("formatCode") final int formatCode,
      @Assisted("immediateDestinationName") @Nullable final String immediateDestinationName,
      @Assisted("immediateOriginName") @Nullable final String immediateOriginName,
      @Assisted("referenceCode") @Nullable final String referenceCode) {

    this.priorityCode = priorityCode;
    this.immediateDestination = immediateDestination;
    this.immediateOrigin = immediateOrigin;
    this.fileCreationDate = fileCreationDate;
    this.fileCreationTime = fileCreationTime;
    this.fileIdModifier = fileIdModifier;
    this.recordSize = recordSize;
    this.blockingFactor = blockingFactor;
    this.formatCode = formatCode;
    this.immediateDestinationName = immediateDestinationName;
    this.immediateOriginName = immediateOriginName;
    this.referenceCode = referenceCode;
  }

  /**
   * Gets the priority code.
   *
   * @return The priority code.
   */
  @Override
  public int getPriorityCode() {
    return priorityCode;
  }

  /**
   * Gets the immediate destination, the originating bank routing number.
   *
   * @return The immediate destination.
   */
  @Override
  public long getImmediateDestination() {
    return immediateDestination;
  }

  /**
   * Gets the immediate origin, the company federal tax ID.
   *
   * @return The immediate origin.
   */
  @Override
  public long getImmediateOrigin() {
    return immediateOrigin;
  }

  /**
   * Gets the file creation date.
   *
   * <p>
   * Format: YYMMDD
   * </p>
   *
   * @return The file creation date.
   */
  @Override
  public String getFileCreationDate() {
    return fileCreationDate;
  }

  /**
   * Gets the file creation time.
   *
   * <p>
   * Format: HHMM
   * </p>
   *
   * @return The file creation time.
   */
  @Override
  public Optional<String> getFileCreationTime() {
    return Optional.ofNullable(fileCreationTime);
  }

  /**
   * Gets the file ID modifier.
   *
   * @return The file ID modifier.
   */
  @Override
  public String getFileIdModifier() {
    return fileIdModifier;
  }

  /**
   * Gets the record size.
   *
   * @return The record size.
   */
  @Override
  public int getRecordSize() {
    return recordSize;
  }

  /**
   * Gets the blocking factor.
   *
   * @return The blocking factor.
   */
  @Override
  public int getBlockingFactor() {
    return blockingFactor;
  }

  /**
   * Gets the format code.
   *
   * @return The format code.
   */
  @Override
  public int getFormatCode() {
    return formatCode;
  }

  /**
   * Gets the immediate destination name.
   *
   * @return The immediate destination name.
   */
  @Override
  public Optional<String> getImmediateDestinationName() {
    return Optional.ofNullable(immediateDestinationName);
  }

  /**
   * Gets the immediate origin name.
   *
   * @return The immediate origin name.
   */
  @Override
  public Optional<String> getImmediateOriginName() {
    return Optional.ofNullable(immediateOriginName);
  }

  /**
   * Gets the reference code.
   *
   * @return The reference code.
   */
  @Override
  public Optional<String> getReferenceCode() {
    return Optional.ofNullable(referenceCode);
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  @Override
  public RecordType getType() {
    return RecordType.FILE_HEADER;
  }
}
