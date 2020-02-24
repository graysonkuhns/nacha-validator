package edu.ucmo.nacha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ucmo.nacha.record.*;

public class TestDriver {

    public static void main(String[] args) throws Exception {
        RecordParser parser = new SpecializedRecordParser(RecordType.ENTRY_DETAIL);
        Record entryDetail = parser.parse("632101000019123456789011121310000000002               MUSTARD MISTER M      DD0101000010000002");

        System.out.println(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(entryDetail));
    }
}
