package edu.ucmo.nacha.file.validation;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetail;
import edu.ucmo.nacha.record.finalform.filecontrol.FileControl;
import java.util.List;
import java.util.Optional;

/**
 * Hash validator.
 *
 * @author Grayson Kuhns
 */
public class HashValidator {

  /**
   * Validates the file hash.
   *
   * @param records The {@link Record}s.
   * @param validationRecord The {@link ValidationRecord}.
   */
  public void validateHash(
      final List<Record> records,
      final ValidationRecord validationRecord) {

    final Optional<FileControl> fileControlOpt = records
        .stream()
        .filter(record -> record.getType() == RecordType.FILE_CONTROL)
        .map(record -> (FileControl) record)
        .findFirst();

    if (!fileControlOpt.isPresent()) {
      return;
    }
    final FileControl fileControl = fileControlOpt.get();

    final String rawHash = records
        .stream()
        .filter(record -> record.getType() == RecordType.ENTRY_DETAIL)
        .map(record -> (EntryDetail) record)
        .map(record -> Long.toString(record.getReceiverRoutingNumber()))
        .map(number -> {
          if (number.length() <= 8) {
            return number;
          } else {
            return number.substring(0, 8);
          }
        })
        .map(Integer::parseInt)
        .reduce(0, Integer::sum)
        .toString();

    final int hash;
    if (rawHash.length() <= 10) {
      hash = Integer.parseInt(rawHash);
    } else {
      hash = Integer.parseInt(rawHash.substring(rawHash.length() - 10));
    }

    if (fileControl.getEntryHash() != hash) {
      validationRecord.addFieldValidationError(fileControl.getIndex(), RecordField.FC_ENTRY_HASH, "INCORRECT ENTRY HASH");
    }
  }
}
