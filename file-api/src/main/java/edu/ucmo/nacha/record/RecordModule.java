package edu.ucmo.nacha.record;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import edu.ucmo.nacha.record.finalform.AggregateRecordParser;
import edu.ucmo.nacha.record.finalform.DefaultRecordsParser;
import edu.ucmo.nacha.record.finalform.RecordParser;
import edu.ucmo.nacha.record.finalform.RecordsParser;
import edu.ucmo.nacha.record.finalform.SpecializedRecordParser;
import edu.ucmo.nacha.record.finalform.batchcontrol.BatchControl;
import edu.ucmo.nacha.record.finalform.batchcontrol.BatchControlFactory;
import edu.ucmo.nacha.record.finalform.batchcontrol.BatchControlParser;
import edu.ucmo.nacha.record.finalform.batchcontrol.DefaultBatchControl;
import edu.ucmo.nacha.record.finalform.entrydetail.DefaultEntryDetail;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetail;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetailFactory;
import edu.ucmo.nacha.record.finalform.entrydetail.EntryDetailParser;
import edu.ucmo.nacha.record.finalform.entrydetailaddenda.DefaultEntryDetailAddenda;
import edu.ucmo.nacha.record.finalform.entrydetailaddenda.EntryDetailAddenda;
import edu.ucmo.nacha.record.finalform.entrydetailaddenda.EntryDetailAddendaFactory;
import edu.ucmo.nacha.record.finalform.entrydetailaddenda.EntryDetailAddendaParser;
import edu.ucmo.nacha.record.finalform.field.DefaultFieldParser;
import edu.ucmo.nacha.record.finalform.field.FieldParser;
import edu.ucmo.nacha.record.finalform.filecontrol.DefaultFileControl;
import edu.ucmo.nacha.record.finalform.filecontrol.FileControl;
import edu.ucmo.nacha.record.finalform.filecontrol.FileControlFactory;
import edu.ucmo.nacha.record.finalform.filecontrol.FileControlParser;
import edu.ucmo.nacha.record.finalform.fileheader.DefaultFileHeader;
import edu.ucmo.nacha.record.finalform.fileheader.FileHeader;
import edu.ucmo.nacha.record.finalform.fileheader.FileHeaderFactory;
import edu.ucmo.nacha.record.finalform.fileheader.FileHeaderParser;
import edu.ucmo.nacha.record.finalform.batchheader.BatchHeader;
import edu.ucmo.nacha.record.finalform.batchheader.BatchHeaderFactory;
import edu.ucmo.nacha.record.finalform.batchheader.BatchHeaderParser;
import edu.ucmo.nacha.record.finalform.batchheader.DefaultBatchHeader;
import edu.ucmo.nacha.record.intermediate.AggregateIntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.DefaultIndexTracker;
import edu.ucmo.nacha.record.intermediate.DefaultIntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.DefaultPaddingDetector;
import edu.ucmo.nacha.record.intermediate.IndexTracker;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.PaddingDetector;
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
    // Intermediate records section --------------------------------------------------------------

    // Index tracker
    bind(IndexTracker.class).to(DefaultIndexTracker.class);

    // Padding detector
    bind(PaddingDetector.class).to(DefaultPaddingDetector.class);

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

    // Final-form records section ----------------------------------------------------------------

    // Field parser
    bind(FieldParser.class).to(DefaultFieldParser.class);

    // File header
    install(new FactoryModuleBuilder()
        .implement(FileHeader.class, DefaultFileHeader.class)
        .build(FileHeaderFactory.class));

    // Batch control
    install(new FactoryModuleBuilder()
        .implement(BatchControl.class, DefaultBatchControl.class)
        .build(BatchControlFactory.class));

    // Entry detail
    install(new FactoryModuleBuilder()
        .implement(EntryDetail.class, DefaultEntryDetail.class)
        .build(EntryDetailFactory.class));

    // Entry detail addenda
    install(new FactoryModuleBuilder()
        .implement(EntryDetailAddenda.class, DefaultEntryDetailAddenda.class)
        .build(EntryDetailAddendaFactory.class));
    
    // Batch header
    install(new FactoryModuleBuilder()
        .implement(BatchHeader.class, DefaultBatchHeader.class)
        .build(BatchHeaderFactory.class));

    // File control
    install(new FactoryModuleBuilder()
        .implement(FileControl.class, DefaultFileControl.class)
        .build(FileControlFactory.class));

    // Specialized record parsers
    Multibinder<SpecializedRecordParser> recordParsersMultibinder =
        Multibinder.newSetBinder(binder(), SpecializedRecordParser.class);

    recordParsersMultibinder.addBinding().to(FileHeaderParser.class);
    recordParsersMultibinder.addBinding().to(BatchControlParser.class);
    recordParsersMultibinder.addBinding().to(EntryDetailParser.class);
    recordParsersMultibinder.addBinding().to(EntryDetailAddendaParser.class);
    recordParsersMultibinder.addBinding().to(BatchHeaderParser.class);
    recordParsersMultibinder.addBinding().to(FileControlParser.class);

    // Aggregate record parser
    bind(RecordParser.class).to(AggregateRecordParser.class);

    // Multi parser
    bind(RecordsParser.class).to(DefaultRecordsParser.class);
  }
}
