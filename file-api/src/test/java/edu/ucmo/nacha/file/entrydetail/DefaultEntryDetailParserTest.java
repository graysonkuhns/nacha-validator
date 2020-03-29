package edu.ucmo.nacha.file.entrydetail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import edu.ucmo.nacha.file.entrydetail.transactiontype.TransactionType;
import edu.ucmo.nacha.file.recordold.DefaultRecordFieldParser;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultEntryDetailParser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultEntryDetailParserTest {

  // Constants
  private static final String ENTRY_DETAIL_BASIC =
      "632101000019123456789011121310000000002               MUSTARD MISTER M        0101000010000002";
  private static final String ENTRY_DETAIL_WITH_RECEIVER_ID_NUMBER =
      "6321010000191234567890111213100000000021300           MUSTARD MISTER M        0101000010000002";
  private static final String ENTRY_DETAIL_WITH_DISCRETIONARY_DATA =
      "632101000019123456789011121310000000002               MUSTARD MISTER M      DD0101000010000002";

  private static final TransactionType TRANSACTION_TYPE = TransactionType.SAV_CREDIT_DEPOSIT;
  private static final long RECEIVER_ROUTING_NUMBER = 10100001L;
  private static final int RECEIVER_ROUTING_NUMBER_CHECK_DIGIT = 9;
  private static final String RECEIVER_ACCOUNT_NUMBER = "12345678901112131";
  private static final double TRANSACTION_AMOUNT = 0.02;
  private static final String RECEIVER_ID_NUMBER = "1300";
  private static final String RECEIVER_NAME = "MUSTARD MISTER M";
  private static final String DISCRETIONARY_DATA = "DD";
  private static final boolean HAS_ADDENDA = false;
  private static final long TRACE_NUMBER = 101000010000002L;

  // Fixtures
  private EntryDetail entryDetail;
  private EntryDetailFactory entryDetailFactory;
  private DefaultEntryDetailParser entryDetailParser;

  @Test
  public void parse__BasicEntryDetail__Test() {
    // Parse the entry detail
    assertThat(entryDetailParser
        .parse(ENTRY_DETAIL_BASIC))
        .isNotNull()
        .isEqualTo(entryDetail);

    // Validate that the correct data was parsed
    verify(entryDetailFactory).create(
        eq(TRANSACTION_TYPE),
        eq(RECEIVER_ROUTING_NUMBER),
        eq(RECEIVER_ROUTING_NUMBER_CHECK_DIGIT),
        eq(RECEIVER_ACCOUNT_NUMBER),
        eq(TRANSACTION_AMOUNT),
        eq(null),
        eq(RECEIVER_NAME),
        eq(null),
        eq(HAS_ADDENDA),
        eq(TRACE_NUMBER));
  }

  @Test
  public void parse__EntryDetailWithReceiverIdNumber__Test() {
    // Parse the entry detail
    assertThat(entryDetailParser
        .parse(ENTRY_DETAIL_WITH_RECEIVER_ID_NUMBER))
        .isNotNull()
        .isEqualTo(entryDetail);

    // Validate that the correct data was parsed
    verify(entryDetailFactory).create(
        eq(TRANSACTION_TYPE),
        eq(RECEIVER_ROUTING_NUMBER),
        eq(RECEIVER_ROUTING_NUMBER_CHECK_DIGIT),
        eq(RECEIVER_ACCOUNT_NUMBER),
        eq(TRANSACTION_AMOUNT),
        eq(RECEIVER_ID_NUMBER),
        eq(RECEIVER_NAME),
        eq(null),
        eq(HAS_ADDENDA),
        eq(TRACE_NUMBER));
  }

  @Test
  public void parse__EntryDetailWithDiscretionaryData__Test() {
    // Parse the entry detail
    assertThat(entryDetailParser
        .parse(ENTRY_DETAIL_WITH_DISCRETIONARY_DATA))
        .isNotNull()
        .isEqualTo(entryDetail);

    // Validate that the correct data was parsed
    verify(entryDetailFactory).create(
        eq(TRANSACTION_TYPE),
        eq(RECEIVER_ROUTING_NUMBER),
        eq(RECEIVER_ROUTING_NUMBER_CHECK_DIGIT),
        eq(RECEIVER_ACCOUNT_NUMBER),
        eq(TRANSACTION_AMOUNT),
        eq(null),
        eq(RECEIVER_NAME),
        eq(DISCRETIONARY_DATA),
        eq(HAS_ADDENDA),
        eq(TRACE_NUMBER));
  }

  @Before
  public void setUp() {
    // Entry detail factory mocking
    entryDetail = mock(EntryDetail.class);
    entryDetailFactory = mock(EntryDetailFactory.class);
    doReturn(entryDetail)
        .when(entryDetailFactory)
        .create(
            any(TransactionType.class),
            anyLong(),
            anyInt(),
            anyString(),
            anyDouble(),
            anyString(),
            anyString(),
            anyString(),
            anyBoolean(),
            anyLong());

    // Create the entry detail parser
    entryDetailParser = new DefaultEntryDetailParser(
        new DefaultRecordFieldParser(),
        entryDetailFactory);
  }
}
