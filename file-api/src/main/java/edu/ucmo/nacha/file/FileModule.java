package edu.ucmo.nacha.file;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * File module.
 *
 * @author Grayson Kuhns
 */
public class FileModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
        .implement(FileParseResults.class, DefaultFileParseResults.class)
        .build(FileParseResultsFactory.class));

    bind(FileParser.class).to(DefaultFileParser.class);
  }
}
