package edu.ucmo.nacha.service

import com.google.inject.AbstractModule
import com.google.inject.assistedinject.FactoryModuleBuilder
import com.google.inject.multibindings.Multibinder
import edu.ucmo.nacha.service.model.SchemaResponseFactory
import edu.ucmo.nacha.service.resources.Resource
import edu.ucmo.nacha.service.resources.SchemaResource
import edu.ucmo.nacha.service.resources.ValidationResource

/**
 * Validation service module.
 */
class ValidationServiceModule : AbstractModule() {

    override fun configure() {
        // API resources
        val resourceMultibinder: Multibinder<Resource> =
            Multibinder.newSetBinder(binder(), Resource::class.java)

        resourceMultibinder.addBinding().to(ValidationResource::class.java)
        resourceMultibinder.addBinding().to(SchemaResource::class.java)

        // Models
        install(FactoryModuleBuilder().build(SchemaResponseFactory::class.java))
    }
}
