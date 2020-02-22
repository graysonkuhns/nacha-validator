package edu.ucmo.nacha.record;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

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
        install(new FactoryModuleBuilder()
                .implement(Record.class, DefaultRecord.class)
                .build(RecordFactory.class));
    }
}
