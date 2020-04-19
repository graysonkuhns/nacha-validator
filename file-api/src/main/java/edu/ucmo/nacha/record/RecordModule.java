package edu.ucmo.nacha.record;

import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import edu.ucmo.nacha.record.intermediate.AggregateIntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.DefaultIntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.SpecializedIntermediateRecordParser;

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
    Multibinder<SpecializedIntermediateRecordParser> recordParsersMultibinder =
        Multibinder.newSetBinder(binder(), SpecializedIntermediateRecordParser.class);

    ImmutableSet
        .copyOf(RecordType.values())
        .forEach(recordType -> recordParsersMultibinder
            .addBinding()
            .toInstance(new SpecializedIntermediateRecordParser(recordType)));

    // Aggregate record parser
    bind(IntermediateRecordParser.class).to(AggregateIntermediateRecordParser.class);

    // Multi-records parser
    bind(IntermediateRecordsParser.class).to(DefaultIntermediateRecordsParser.class);
  }
}
