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
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    Record fileHeader = parser.parse("101 10100001986753075211909150700A094101Testing1 Bank          THE FAB FOUR CORP              ");
    System.out.println("File Header:");
    System.out.println(mapper.writeValueAsString(fileHeader));
    System.out.println();

    Record entryDetail = parser.parse("6321010000191234567890111213100000000021300           MUSTARD MISTER M      DD0101000010000002");
    System.out.println("Entry Detail:");
    System.out.println(mapper.writeValueAsString(entryDetail));
    System.out.println();
  }
}
