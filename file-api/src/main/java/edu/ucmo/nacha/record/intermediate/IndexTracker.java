package edu.ucmo.nacha.record.intermediate;

/**
 * Record index tracker.
 *
 * @author Grayson Kuhns
 */
public interface IndexTracker {

  /**
   * Gets the next index.
   *
   * @return The next index.
   */
  int getNext();
}
