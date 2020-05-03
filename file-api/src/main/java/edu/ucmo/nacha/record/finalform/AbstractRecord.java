package edu.ucmo.nacha.record.finalform;

/**
 * Abstract {@link Record} implementation.
 *
 * @author Grayson Kuhns
 */
public abstract class AbstractRecord implements Record {

  // Properties
  private final int index;

  /**
   * Constructor.
   *
   * @param index The index.
   */
  public AbstractRecord(final int index) {
    this.index = index;
  }

  /**
   * Gets the index.
   *
   * @return The index.
   */
  @Override
  public int getIndex() {
    return index;
  }
}
