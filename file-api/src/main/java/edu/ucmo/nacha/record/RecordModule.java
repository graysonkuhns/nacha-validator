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
        // Bind record parsers
        Multibinder<RecordParser> recordParserMultibinder = Multibinder.newSetBinder(binder(), RecordParser.class);
        ImmutableSet
                .of(
                        RecordType.ENTRY_DETAIL
                )
                .forEach(recordType -> recordParserMultibinder
                        .addBinding()
                        .toInstance(new SpecializedRecordParser(recordType)));
    }
}
