package edu.ucmo.nacha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    IntermediateRecordsParser intermediateRecordsParser = injector.getInstance(IntermediateRecordsParser.class);

    // Parse the records
    List<IntermediateRecord> records = intermediateRecordsParser
        .parse(getClass()
            .getClassLoader()
            .getResourceAsStream("nacha-file-no-error.txt"));

    // Display the records
    System.out.println(mapper.writeValueAsString(records));
  }
}
