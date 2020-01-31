package edu.ucmo.nacha.file.entrydetail.transactiontype;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link InvalidTransactionTypeException} test case.
 *
 * @author Grayson Kuhns
 */
public class InvalidTransactionTypeExceptionTest {

  // Constants
  private static final int INVALID_CODE = 63;
  private static final String EXPECTED_MSG =
      "The transaction code \"63\" is invalid";

  // Fixtures
  private InvalidTransactionTypeException exception;

  @Test
  public void getCode__Test() {
    assertThat(exception
        .getCode())
        .isEqualTo(INVALID_CODE);
  }

  @Test
  public void getMessage__Test() {
    assertThat(exception
        .getMessage())
        .isNotNull()
        .isEqualTo(EXPECTED_MSG);
  }

  @Before
  public void setUp() {
    exception = new InvalidTransactionTypeException(INVALID_CODE);
  }
}
