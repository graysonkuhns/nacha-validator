package edu.ucmo.nacha.file.entrydetail.transactiontype;

/**
 * Invalid {@link TransactionType} exception.
 *
 * @author Grayson Kuhns
 */
public class InvalidTransactionTypeException extends RuntimeException {

  // Message templates
  private static final String MSG_TMPL =
      "The transaction code \"%d\" is invalid";

  // Properties
  private final int code;

  /**
   * Constructor.
   *
   * @param code The invalid transaction code.
   */
  public InvalidTransactionTypeException(final int code) {
    super(String.format(MSG_TMPL, code));
    this.code = code;
  }

  /**
   * Gets the invalid transaction code.
   *
   * @return The invalid transaction code.
   */
  public int getCode() {
    return code;
  }
}
