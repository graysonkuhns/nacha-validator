package edu.ucmo.nacha.service.resources

import edu.ucmo.nacha.file.FileParseResults
import edu.ucmo.nacha.file.FileParser
import edu.ucmo.nacha.service.model.ValidationRequest
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.POST
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
@Path("/validate")
@Produces(MediaType.APPLICATION_JSON)
class ValidationResource @Inject constructor (val parser: FileParser) : Resource {

    /**
     * Validates the NACHA records.
     */
    @POST
    fun validate(request: ValidationRequest): Response {
        val parseResult: FileParseResults = parser.parse(request.records)

        return Response
                .ok(parseResult)
                .build()
    }
}
