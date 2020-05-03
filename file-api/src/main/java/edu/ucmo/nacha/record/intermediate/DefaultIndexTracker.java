package edu.ucmo.nacha.record.intermediate;

/**
 * Default {@link IndexTracker} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultIndexTracker implements IndexTracker {

  // Properties
  private int index = 0;

  /**
   * Gets the next index.
   *
   * @return The next index.
   */
  @Override
  public synchronized int getNext() {
    return index++;
  }
}
