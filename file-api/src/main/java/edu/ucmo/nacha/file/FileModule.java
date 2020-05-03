package edu.ucmo.nacha.file;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import edu.ucmo.nacha.file.schema.DefaultSchema;
import edu.ucmo.nacha.file.schema.DefaultSchemaCollector;
import edu.ucmo.nacha.file.schema.Schema;
import edu.ucmo.nacha.file.schema.SchemaCollector;
import edu.ucmo.nacha.file.schema.SchemaFactory;

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
    // File parser
    install(new FactoryModuleBuilder()
        .implement(FileParseResults.class, DefaultFileParseResults.class)
        .build(FileParseResultsFactory.class));

    bind(FileParser.class).to(DefaultFileParser.class);

    // File schema
    install(new FactoryModuleBuilder()
        .implement(Schema.class, DefaultSchema.class)
        .build(SchemaFactory.class));

    bind(SchemaCollector.class).to(DefaultSchemaCollector.class);
  }
}
