package edu.ucmo.nacha.record;

import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

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
        // Specialized record parsers
        Multibinder<SpecializedRecordParser> recordParsersMultibinder =
                Multibinder.newSetBinder(binder(), SpecializedRecordParser.class);

        ImmutableSet
                .of(
                        RecordType.ENTRY_DETAIL
                )
                .forEach(recordType -> recordParsersMultibinder
                        .addBinding()
                        .toInstance(new SpecializedRecordParser(recordType)));

        // Aggregate record parser
        bind(RecordParser.class).to(AggregateRecordParser.class);
    }
}
