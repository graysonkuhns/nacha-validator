package edu.ucmo.nacha.file.recordold;

import com.google.inject.AbstractModule;

/**
 * Record module.
 *
 * @author Grayson Kuhns
 */
public class RecordModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(RecordFieldParser.class).to(DefaultRecordFieldParser.class);
  }
}
