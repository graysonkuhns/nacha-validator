package edu.ucmo.nacha.record.finalform.entrydetailaddenda;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.nacha.record.RecordType;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Default {@link EntryDetailAddenda} implementation.
 *
 * @author King Butcher
 */
public class DefaultEntryDetailAddenda implements EntryDetailAddenda {

  // Properties
  private final int typeCode;
  private final String paymentInfo;
  private final int addendaSequenceNumber;
  private final int entrySequenceNumber;

  /**
   * Constructor.
   *
   * @param typeCode The record and addenda type code.
   * @param paymentInfo Any payment related information.
   * @param addendaSequenceNumber The number which sequences the addenda record.
   * @param entrySequenceNumber The number which sequences the entry record.
   */
  @Inject
  DefaultEntryDetailAddenda(
      @Assisted("typeCode") final int typeCode,
      @Assisted("paymentInfo") @Nullable final String paymentInfo,
      @Assisted("addendaSequenceNumber") final int addendaSequenceNumber,
      @Assisted("entrySequenceNumber") final int entrySequenceNumber) {

    this.typeCode = typeCode;
    this.paymentInfo = paymentInfo;
    this.addendaSequenceNumber = addendaSequenceNumber;
    this.entrySequenceNumber = entrySequenceNumber;
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  @Override
  public RecordType getType() {
    return RecordType.ENTRY_DETAIL_ADDENDA;
  }

  /**
   * Gets the record and addenda type code.
   *
   * @return The record and addenda type code.
   */
  @Override
  public int getTypeCode() {
    return typeCode;
  }

  /**
   * Gets any payment related information.
   *
   * @return Any payment related information
   */
  @Override
  public Option<String> getPaymentInformation() {
    return Optional.ofNullable(paymentInfo);
  }

  /**
   * Gets the number which sequences the addenda record.
   *
   * @return The number which sequences the addenda record.
   */
  @Override
  public int getAddendaSequenceNumber() {
    return addendaSequenceNumber;
  }

  /**
   * Gets the number which sequences the entry record.
   *
   * @return The number which sequences the entry record.
   */
  @Override
  public int getEntrySequenceNumber() {
    return entrySequenceNumber;
  }
}
