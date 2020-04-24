package edu.ucmo.nacha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.nacha.file.FileModule;
import edu.ucmo.nacha.file.FileParseResults;
import edu.ucmo.nacha.file.FileParser;
import edu.ucmo.nacha.record.RecordModule;
import java.time.Duration;
import java.time.Instant;

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
    // Load functionality
    Injector injector = Guice.createInjector(new RecordModule(), new FileModule());
    FileParser fileParser = injector.getInstance(FileParser.class);

    // Create object mapper
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new Jdk8Module())
        .enable(SerializationFeature.INDENT_OUTPUT);

    // Start the timer
    Instant start = Instant.now();

    // Parse the file
    FileParseResults parseResults = fileParser.parse(getClass()
        .getClassLoader()
        .getResourceAsStream("nacha-file-no-error.txt"));

    // End the timer
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();

    // Display the records
    System.out.println(mapper.writeValueAsString(parseResults.getRecords()));

    // Display the runtime of the parser
    System.out.printf("Runtime: %d ms\n", timeElapsed);
  }
}
