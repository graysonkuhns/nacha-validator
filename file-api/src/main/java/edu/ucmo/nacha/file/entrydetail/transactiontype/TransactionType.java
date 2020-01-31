package edu.ucmo.nacha.file.entrydetail.transactiontype;

import java.util.Arrays;

/**
 * Transaction type.
 *
 * @author Grayson Kuhns
 */
public enum TransactionType {

  // Types
  DDA_CREDIT_DEPOSIT(22),
  DDA_CREDIT_PRENOTE(23),
  DDA_DEBIT(27),
  DDA_DEBIT_PRENOTE(28),
  SAV_CREDIT_DEPOSIT(32),
  SAV_CREDIT_PRENOTE(33),
  SAV_DEBIT(37),
  SAV_DEBIT_PRENOTE(38);

  // Properties
  private final int code;

  /**
   * Constructor.
   *
   * @param code The transaction code.
   */
  TransactionType(final int code) {
    this.code = code;
  }

  /**
   * Gets the transaction code.
   *
   * @return The transaction code.
   */
  public int getCode() {
    return code;
  }

  /**
   * Gets a {@link TransactionType} matching a transaction code.
   *
   * @param code The transaction code.
   * @return The {@link TransactionType}.
   * @throws InvalidTransactionTypeException If the transaction code is invalid.
   */
  public static TransactionType getForCode(final int code) throws InvalidTransactionTypeException {
    return Arrays
        .stream(values())
        .filter(type -> type.code == code)
        .findFirst()
        .orElseThrow(() -> new InvalidTransactionTypeException(code));
  }
}
