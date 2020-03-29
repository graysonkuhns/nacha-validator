package edu.ucmo.nacha.file;

import com.google.inject.AbstractModule;

/**
 * NACHA file module.
 *
 * @author Grayson Kuhns
 */
public class FileModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(FileParser.class).to(DefaultFileParser.class);
  }
}
