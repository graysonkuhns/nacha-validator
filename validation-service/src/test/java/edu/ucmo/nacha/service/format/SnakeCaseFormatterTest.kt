package edu.ucmo.nacha.service.format

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

/**
 * SnakeCaseFormatter test case.
 *
 * @author Grayson Kuhns
 */
class SnakeCaseFormatterTest {

    // Fixtures
    private var formatter = SnakeCaseFormatter()

    @Test
    fun fromConstantForm__ConvertsConstantCaseToSnakeCase__Test() {
        assertThat(formatter
            .fromConstantForm("SUPER_AWESOME_ENVIRONMENT_VARIABLE"))
            .isEqualTo("superAwesomeEnvironmentVariable")
    }

    @Test
    fun fromConstantForm__DropsTheFirstSection__WhenDirected__Test() {
        assertThat(formatter
            .fromConstantForm("FH_FILE_CREATION_TIME", true))
            .isEqualTo("fileCreationTime")
    }
}