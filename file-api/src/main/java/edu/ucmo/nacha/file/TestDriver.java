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
        "622101000019111111           00000000010200        " +
            "   JONES DESMOND           0101000010000001");
    System.out.println(entry.toString());

    entry = parser.parse(
        "6221010000199876543219875455500000000022660       " +
            "    BOYD PATTIE             0101000010000016");
    System.out.println(entry.toString());

    entry = parser.parse(
        "622101000019888999111000999  00000000092450" +
            "           SHEARS WILLIAM          0101000010000009");
    System.out.println(entry.toString());
  }
}
