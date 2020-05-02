package edu.ucmo.nacha.service.resources

import edu.ucmo.nacha.file.schema.Schema
import edu.ucmo.nacha.file.schema.SchemaCollector
import edu.ucmo.nacha.service.model.SchemaResponse
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Validation API resource.
 *
 * @author Grayson Kuhns
 */
@Singleton
@Path("/schema")
@Produces(MediaType.APPLICATION_JSON)
class SchemaResource @Inject constructor (private val schemaCollector: SchemaCollector) : Resource {

    // Properties
    private var schema: Schema = schemaCollector.collect()

    @GET
    fun getSchema() : Response {
        return Response
            .ok(SchemaResponse(schema))
            .build()
    }
}
