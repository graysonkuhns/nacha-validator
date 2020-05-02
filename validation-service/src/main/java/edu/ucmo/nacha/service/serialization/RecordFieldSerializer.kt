package edu.ucmo.nacha.service.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import edu.ucmo.nacha.record.RecordField

/**
 * Record field serializer.
 *
 * @author Grayson Kuhns
 */
class RecordFieldSerializer : StdSerializer<RecordField>(RecordField::class.java) {

    /**
     * Serializes a RecordField.
     */
    override fun serialize(
        field: RecordField,
        jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider) {

        // Start object
        jsonGenerator.writeStartObject(field.getName())

        // Write data
        jsonGenerator.writeNumberField("startPosition", field.startPosition);
        jsonGenerator.writeNumberField("endPosition", field.endPosition);

        // End object
        jsonGenerator.writeEndObject()
    }
}
