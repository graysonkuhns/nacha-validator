package edu.ucmo.nacha.service.model

import edu.ucmo.nacha.file.schema.Schema

/**
 * Schema response factory.
 */
interface SchemaResponseFactory {

    /**
     * Creates a schema response.
     */
    fun create(schema: Schema): SchemaResponse
}
