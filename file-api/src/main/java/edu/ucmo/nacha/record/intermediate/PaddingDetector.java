package edu.ucmo.nacha.record.intermediate;

/**
 * Padding detector.
 *
 * <p>
 * Padding records consisting of all 9's are added to the end of NACHA files
 * so that the number of records is consistent with the blocking factor.
 * </p>
 *
 * @author Grayson Kuhns
 */
public interface PaddingDetector {

  /**
   * Detects if an {@link IntermediateRecord} is padding.
   *
   * @param record The {@link IntermediateRecord}.
   * @return True if the {@link IntermediateRecord} is padding.
   */
  boolean isPadding(IntermediateRecord record);
}
