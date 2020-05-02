package edu.ucmo.nacha.service

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Singleton
import edu.ucmo.nacha.file.FileModule
import edu.ucmo.nacha.record.RecordModule
import edu.ucmo.nacha.service.resources.Resource
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

/**
 * Validation service entry point.
 *
 * @author Grayson Kuhns
 */
@Singleton
class ValidationServiceApplication
    @Inject constructor(
        private val resources : Set<@JvmSuppressWildcards Resource>
    )
    : Application<ValidationServiceConfiguration>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Guice
                .createInjector(
                    ValidationServiceModule(),
                    FileModule(),
                    RecordModule())
                .getInstance(ValidationServiceApplication::class.java)
                .run(*args)
        }
    }

    override fun getName(): String {
        return "NACHA Validation Service"
    }

    override fun initialize(bootstrap: Bootstrap<ValidationServiceConfiguration>) {
        bootstrap.objectMapper.registerModule(KotlinModule())
    }

    override fun run(config: ValidationServiceConfiguration, environment: Environment) {
        // Service API resources at /api path
        environment.jersey().urlPattern = "/api/*"

        // Register resources
        resources.forEach { environment.jersey().register(it) }
    }
}
