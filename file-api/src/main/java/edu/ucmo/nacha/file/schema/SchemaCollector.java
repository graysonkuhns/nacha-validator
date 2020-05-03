package edu.ucmo.nacha.file.schema;

/**
 * NACHA {@link Schema} collector.
 *
 * @author Grayson Kuhns
 */
public interface SchemaCollector {

  /**
   * Collects the NACHA {@link Schema}.
   *
   * @return The NACHA {@link Schema}.
   */
  Schema collect();
}
