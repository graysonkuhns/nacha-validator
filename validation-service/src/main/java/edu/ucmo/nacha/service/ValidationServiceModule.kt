package edu.ucmo.nacha.service

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import edu.ucmo.nacha.service.resources.Resource
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
    }
}