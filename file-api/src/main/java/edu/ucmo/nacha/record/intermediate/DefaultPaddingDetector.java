package edu.ucmo.nacha.record.intermediate;

import edu.ucmo.nacha.record.RecordType;
import javax.inject.Singleton;

/**
 * Default {@link PaddingDetector} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultPaddingDetector implements PaddingDetector {

  /**
   * Detects if an {@link IntermediateRecord} is padding.
   *
   * @param record The {@link IntermediateRecord}.
   * @return True if the {@link IntermediateRecord} is padding.
   */
  @Override
  public boolean isPadding(final IntermediateRecord record) {
    // Optimization. All padding records are file control records
    if (record.getType() != RecordType.FILE_CONTROL) {
      return false;
    }

    // Extensive check
    return record
        .getFields()
        .values()
        .stream()
        .noneMatch(field -> {
          // Check if this field is not padding
          for (char ch : field.toCharArray()) {
            if (ch != '9') {
              return true;
            }
          }

          // This field is padding
          return false;
        });
  }
}
