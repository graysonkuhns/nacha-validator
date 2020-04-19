package edu.ucmo.nacha.record;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import edu.ucmo.nacha.record.finalform.entrydetail.DefaultEntryDetail;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetail;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetailFactory;
import edu.ucmo.nacha.record.finalform.field.DefaultFieldParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.intermediate.AggregateIntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.DefaultIntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.SpecializedIntermediateRecordParser;
import java.util.Arrays;

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
    // Intermediate parsers section --------------------------------------------------------------

    // Specialized record parsers
    Multibinder<SpecializedIntermediateRecordParser> intermediateRecordParsersMultibinder =
        Multibinder.newSetBinder(binder(), SpecializedIntermediateRecordParser.class);

    Arrays
        .stream(RecordType.values())
        .forEach(recordType -> intermediateRecordParsersMultibinder
            .addBinding()
            .toInstance(new SpecializedIntermediateRecordParser(recordType)));

    // Aggregate record parser
    bind(IntermediateRecordParser.class).to(AggregateIntermediateRecordParser.class);

    // Multi parser
    bind(IntermediateRecordsParser.class).to(DefaultIntermediateRecordsParser.class);

    // Final form parsers section ----------------------------------------------------------------

    // Field parser
    bind(FieldParser.class).to(DefaultFieldParser.class);

    // Entry detail
    install(new FactoryModuleBuilder()
        .implement(EntryDetail.class, DefaultEntryDetail.class)
        .build(EntryDetailFactory.class));
  }
}
