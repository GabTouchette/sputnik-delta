package pl.touk.sputnik.processor.tslint;

import com.google.common.collect.ImmutableMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.touk.sputnik.configuration.Configuration;
import pl.touk.sputnik.configuration.ConfigurationSetup;
import pl.touk.sputnik.configuration.GeneralOption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TSLintScriptTest {
    @Test
    void shouldFailWhenConfigFileIsMissing() {
        final String configFile = "tslint.xml.not.json";

        new ConfigurationSetup().setUp(ImmutableMap.of(
                GeneralOption.TSLINT_CONFIGURATION_FILE.getKey(), configFile));
        TSLintScript tsLint = new TSLintScript(null, configFile);

        Throwable thrown = catchThrowable(tsLint::validateConfiguration);

        assertThat(thrown).isInstanceOf(TSLintException.class)
                .hasMessageContaining("Could not find tslint configuration file: " + configFile);
    }

    @Test
    void shouldPassWhenConfigFileIsValid() {
        final String configFile = "src/main/resources/tslint.json";

        new ConfigurationSetup().setUp(ImmutableMap.of(GeneralOption.TSLINT_CONFIGURATION_FILE.getKey(), configFile));
        TSLintScript tsLint = new TSLintScript(null, configFile);

        Throwable thrown = catchThrowable(tsLint::validateConfiguration);

        assertThat(thrown).isNull();
    }

    @Test
    void shouldReturnViolationsInJSONFormat() {
        final String configFile = "src/main/resources/tslint.json";
        new ConfigurationSetup().setUp(ImmutableMap.of(GeneralOption.TSLINT_CONFIGURATION_FILE.getKey(), configFile));
        Configuration config = new ConfigurationSetup().setUp(ImmutableMap.of(
                GeneralOption.TSLINT_SCRIPT.getKey(), "tslint",
                GeneralOption.TSLINT_CONFIGURATION_FILE.getKey(), "src/main/resources/tslint.json"));

        String tsScript = config.getProperty(GeneralOption.TSLINT_SCRIPT);
        TSLintScript tsLint = new TSLintScript(tsScript, configFile);

        String filePath = "src/main/resources/tslint/testFile.ts";
        String violationsJson = tsLint.reviewFile(filePath);

        assertThat(violationsJson).isNotNull();
    }
}
