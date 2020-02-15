package edu.ucmo.nacha.file;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.nacha.file.entrydetail.EntryDetail;
import edu.ucmo.nacha.file.entrydetail.EntryDetailModule;
import edu.ucmo.nacha.file.entrydetail.EntryDetailParser;
import edu.ucmo.nacha.file.record.RecordModule;

public class TestDriver {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(
        new RecordModule(),
        new EntryDetailModule());

    EntryDetailParser parser = injector.getInstance(EntryDetailParser.class);

    EntryDetail entry = parser.parse(
        "632101000019123456789011121310000000002       " +
            "        MUSTARD MISTER M        0101000010000002");
    System.out.println(entry.toString());

    entry = parser.parse(
        "6321010000191234567890111213100000000021300           MUSTARD MISTER M        0101000010000002");
    System.out.println(entry.toString());

    entry = parser.parse(
        "632101000019123456789011121310000000002       " +
            "        MUSTARD MISTER M      DD0101000010000002");
    System.out.println(entry.toString());
  }
}
