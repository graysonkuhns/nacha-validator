package edu.ucmo.nacha.file.entrydetail;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * {@link EntryDetail} module.
 *
 * @author Grayson Kuhns
 */
public class EntryDetailModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
        .implement(EntryDetail.class, DefaultEntryDetail.class)
        .build(EntryDetailFactory.class));
  }
}
