package edu.ucmo.nacha.file.entrydetail.transactiontype;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link TransactionType} test case.
 *
 * @author Grayson Kuhns
 */
public class TransactionTypeTest {

  // Constants
  private static final TransactionType VALID_TRANSACTION_TYPE = TransactionType.DDA_CREDIT_DEPOSIT;
  private static final int VALID_TRANSACTION_CODE = 22;

  private static final int INVALID_TRANSACTION_CODE = 63;
  private static final String INVALID_TRANSACTION_CODE_EXPECTED_MSG =
      "The transaction code \"63\" is invalid";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void getCode__Test() {
    assertThat(VALID_TRANSACTION_TYPE
        .getCode())
        .isEqualTo(VALID_TRANSACTION_CODE);
  }

  @Test
  public void getForCode__ForValidCode__GetsTheType__Test() {
    assertThat(TransactionType
        .getForCode(VALID_TRANSACTION_CODE))
        .isNotNull()
        .isEqualTo(VALID_TRANSACTION_TYPE);
  }

  @Test
  public void getForCode__ForInvalidCode__ThrowsException__Test() {
    // Describe the expect to expect
    thrown.expect(InvalidTransactionTypeException.class);
    thrown.expectMessage(INVALID_TRANSACTION_CODE_EXPECTED_MSG);

    // Attempt to get the transaction type
    TransactionType.getForCode(INVALID_TRANSACTION_CODE);
  }
}
