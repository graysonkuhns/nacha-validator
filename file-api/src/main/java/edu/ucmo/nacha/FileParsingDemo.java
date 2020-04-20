package edu.ucmo.nacha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.nacha.record.finalform.Record;
import edu.ucmo.nacha.record.finalform.RecordsParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecordsParser;
import edu.ucmo.nacha.record.intermediate.IntermediateRecord;
import edu.ucmo.nacha.record.RecordModule;
import java.util.List;

/**
 * NACHA File parsing demo.
 *
 * @author Grayson Kuhns
 */
public class FileParsingDemo {

  public static void main(String[] args) throws Exception {
    new FileParsingDemo().run();
  }

  public void run() throws Exception {
    Injector injector = Guice.createInjector(new RecordModule());
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new Jdk8Module())
        .enable(SerializationFeature.INDENT_OUTPUT);

    IntermediateRecordsParser intermediateRecordsParser = injector.getInstance(IntermediateRecordsParser.class);
    RecordsParser recordsParser = injector.getInstance(RecordsParser.class);

    // Parse the intermediate records
    List<IntermediateRecord> intermediateRecords = intermediateRecordsParser
        .parse(getClass()
            .getClassLoader()
            .getResourceAsStream("nacha-file-no-error.txt"));

    // Parse the intermediate records to final-form
    List<Record> records = recordsParser.parse(intermediateRecords);

    // Display the records
    System.out.println(mapper.writeValueAsString(records));
  }
}
