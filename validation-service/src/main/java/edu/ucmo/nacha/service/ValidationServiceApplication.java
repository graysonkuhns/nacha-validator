package edu.ucmo.nacha.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ValidationServiceApplication extends Application<ValidationServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ValidationServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "ValidationService";
    }

    @Override
    public void initialize(final Bootstrap<ValidationServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ValidationServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
