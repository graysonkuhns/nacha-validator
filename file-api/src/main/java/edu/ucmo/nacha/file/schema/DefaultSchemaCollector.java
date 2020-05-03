package edu.ucmo.nacha.file.schema;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.ucmo.nacha.record.RecordField;
import edu.ucmo.nacha.record.RecordType;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Default {@link SchemaCollector} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultSchemaCollector implements SchemaCollector {

  // Dependencies
  private final SchemaFactory schemaFactory;

  /**
   * Constructor.
   *
   * @param schemaFactory The {@link SchemaFactory}.
   */
  @Inject
  DefaultSchemaCollector(final SchemaFactory schemaFactory) {
    this.schemaFactory = schemaFactory;
  }

  /**
   * Collects the NACHA {@link Schema}.
   *
   * @return The NACHA {@link Schema}.
   */
  @Override
  public Schema collect() {
    return schemaFactory.create(Arrays
        .stream(RecordType.values())
        .collect(Collectors.toMap(
            Function.identity(),
            RecordField::getFields)));
  }
}
