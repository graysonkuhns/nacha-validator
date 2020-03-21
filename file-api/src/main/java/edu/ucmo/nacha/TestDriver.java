package edu.ucmo.nacha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.nacha.record.Record;
import edu.ucmo.nacha.record.RecordModule;
import edu.ucmo.nacha.record.RecordParser;

public class TestDriver {

  public static void main(String[] args) throws Exception {
    Injector injector = Guice.createInjector(new RecordModule());
    RecordParser parser = injector.getInstance(RecordParser.class);
    Record entryDetail = parser.parse("6321010000191234567890111213100000000021300           MUSTARD MISTER M      DD0101000010000002");
    System.out.println(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(entryDetail));
  }
}