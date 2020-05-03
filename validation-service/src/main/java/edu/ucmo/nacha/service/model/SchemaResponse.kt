package edu.ucmo.nacha.service.model

import com.google.inject.Inject
import com.google.inject.assistedinject.Assisted
import edu.ucmo.nacha.file.schema.Schema
import edu.ucmo.nacha.record.RecordField
import edu.ucmo.nacha.record.RecordType
import edu.ucmo.nacha.service.format.SnakeCaseFormatter

/**
 * Schema response.
 */
class SchemaResponse @Inject constructor(
    @Assisted private val schema: Schema,
    private val formatter: SnakeCaseFormatter) {

    val fields: Map<RecordType, Map<String, RecordField>>

    init {
        val tempFields: MutableMap<RecordType, Map<String, RecordField>> = mutableMapOf()

        schema
            .fields
            .forEach { (type, fields) ->
                val labeledFields: MutableMap<String, RecordField> = mutableMapOf()
                fields.forEach { labeledFields[formatter.fromConstantForm(it.toString(), true)] = it }
                tempFields[type] = labeledFields
            }

        fields = tempFields
    }
}