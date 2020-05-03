package edu.ucmo.nacha.file.validation;

import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.batchcontrol.BatchControl;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetail;
import edu.ucmo.nacha.record.finalform.filecontrol.FileControl;
import java.util.List;

/**
 * Amount validator.
 *
 * @author Grayson Kuhns
 */
public class AmountValidator {

  // Constants
  private static final double THRESHOLD = .0001D;

  private static final int CREDIT_SAVING = 32;
  private static final int CREDIT_DDA = 22;
  private static final int DEBIT_SAVING = 37;
  private static final int DEBIT_DDA = 27;

  public void validateAmounts(
      final List<Record> records,
      final ValidationRecord validationRecord) {

    BatchControl batchControl = null;
    FileControl fileControl = null;

    double creditTotal = 0;
    double debitTotal = 0;

    for (Record record : records) {
      switch (record.getType()) {
        case BATCH_CONTROL:
          batchControl = (BatchControl) record;
          break;
        case FILE_CONTROL:
          fileControl = (FileControl) record;
          break;
        case ENTRY_DETAIL:
          EntryDetail entry = (EntryDetail) record;
          int transactionType = entry.getTransactionType();

          if (transactionType == CREDIT_DDA || transactionType == CREDIT_SAVING) {
            // Credit
            creditTotal += entry.getTransactionAmount();
          } else if (transactionType == DEBIT_DDA || transactionType == DEBIT_SAVING) {
            // Debit
            debitTotal += entry.getTransactionAmount();
          }
      }
    }

    if (batchControl != null) {
      if (Math.abs(batchControl.getCreditAmount() - creditTotal) > THRESHOLD) {
        validationRecord.addFieldValidationError(batchControl.getIndex(), RecordField.BC_CREDIT_AMOUNT, "INCORRECT CREDIT TOTAL");
      }

      if (Math.abs(batchControl.getDebitAmount() - debitTotal) > THRESHOLD) {
        validationRecord.addFieldValidationError(batchControl.getIndex(), RecordField.BC_DEBIT_AMOUNT, "INCORRECT DEBIT TOTAL");
      }
    }

    if (fileControl != null) {
      if (Math.abs(fileControl.getCreditAmount() - creditTotal) > THRESHOLD) {
        validationRecord.addFieldValidationError(fileControl.getIndex(), RecordField.FC_CREDIT_AMOUNT, "INCORRECT CREDIT TOTAL");
      }

      if (Math.abs(fileControl.getDebitAmount() - debitTotal) > THRESHOLD) {
        validationRecord.addFieldValidationError(fileControl.getIndex(), RecordField.FC_DEBIT_AMOUNT, "INCORRECT DEBIT TOTAL");
      }
    }
  }
}
