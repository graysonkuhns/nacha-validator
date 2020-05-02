package edu.ucmo.nacha.record.finalform.fileheader;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.finalform.Record;
import java.util.Optional;

/**
 * File header.
 *
 * @author Grayson Kuhns
 */
public interface FileHeader extends Record {

  /**
   * Gets the priority code.
   *
   * @return The priority code.
   */
  int getPriorityCode();

  /**
   * Gets the immediate destination, the originating bank routing number.
   *
   * @return The immediate destination.
   */
  long getImmediateDestination();

  /**
   * Gets the immediate origin, the company federal tax ID.
   *
   * @return The immediate origin.
   */
  long getImmediateOrigin();

  /**
   * Gets the file creation date.
   *
   * <p>
   * Format: YYMMDD
   * </p>
   *
   * @return The file creation date.
   */
  String getFileCreationDate();

  /**
   * Gets the file creation time.
   *
   * <p>
   * Format: HHMM
   * </p>
   *
   * @return The file creation time.
   */
  Optional<String> getFileCreationTime();

  /**
   * Gets the file ID modifier.
   *
   * @return The file ID modifier.
   */
  String getFileIdModifier();

  /**
   * Gets the record size.
   *
   * @return The record size.
   */
  int getRecordSize();

  /**
   * Gets the blocking factor.
   *
   * @return The blocking factor.
   */
  int getBlockingFactor();

  /**
   * Gets the format code.
   *
   * @return The format code.
   */
  int getFormatCode();

  /**
   * Gets the immediate destination name.
   *
   * @return The immediate destination name.
   */
  Optional<String> getImmediateDestinationName();

  /**
   * Gets the immediate origin name.
   *
   * @return The immediate origin name.
   */
  Optional<String> getImmediateOriginName();

  /**
   * Gets the reference code.
   *
   * @return The reference code.
   */
  Optional<String> getReferenceCode();
}
