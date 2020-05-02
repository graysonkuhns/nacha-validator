package edu.ucmo.nacha.service.model

import edu.ucmo.nacha.file.schema.Schema
import edu.ucmo.nacha.record.RecordField
import edu.ucmo.nacha.record.RecordType

/**
 * Schema response.
 */
class SchemaResponse(private val schema: Schema) {
    val fields: Map<RecordType, Map<String, RecordField>>

    init {
        val tempFields: MutableMap<RecordType, Map<String, RecordField>> = mutableMapOf()

        schema
            .fields
            .forEach { (type, fields) ->
                val labeledFields: MutableMap<String, RecordField> = mutableMapOf()
                fields.forEach { labeledFields[it.getName()] = it }
                tempFields[type] = labeledFields
            }

        fields = tempFields
    }
}