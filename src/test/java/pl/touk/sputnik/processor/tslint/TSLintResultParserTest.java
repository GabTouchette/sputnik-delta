package pl.touk.sputnik.processor.tslint;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.touk.sputnik.review.Severity;
import pl.touk.sputnik.review.Violation;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TSLintResultParserTest {

    private TSLintResultParser fixture;

    @BeforeEach
    void setUp() {
        fixture = new TSLintResultParser();
    }

    @Test
    void shouldReturnBasicViolationsOnSimpleFunction() throws IOException {
        String jsonResponse = IOUtils.toString(getClass().getResourceAsStream("/json/tslint-results.json"));

        List<Violation> violations = fixture.parse(jsonResponse);

        assertThat(violations).hasSize(1);

        Violation violation = violations.get(0);
        assertThat(violation.getMessage()).isEqualTo("unused variable: 'greeter'");
        assertThat(violation.getLine()).isEqualTo(10);
        assertThat(violation.getSeverity()).isEqualTo(Severity.ERROR);
    }

    @Test
    void shouldNotModifyReviewResultWhenNoViolation() {
        String jsonViolations = "";

        List<Violation> violations = fixture.parse(jsonViolations);

        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    void shouldThrowTSLintExceptionWhenJsonParsingFails() throws IOException {
        TSLintResultParser fixture = new TSLintResultParser();
        String invalidJson = IOUtils.toString(getClass().getResourceAsStream("/json/invalid.json"));

        assertThrows(TSLintException.class, () -> {
            fixture.parse(invalidJson);
        });
    }
  
    @Test
    void shouldReturnEmptyListWhenJsonIsEmpty() throws IOException {
        TSLintResultParser fixture = new TSLintResultParser();
        String emptyJson = "";

        List<Violation> violations = fixture.parse(emptyJson);

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldReturnEmptyListWhenJsonContainsNoViolations() throws IOException {
        TSLintResultParser fixture = new TSLintResultParser();
        String jsonWithoutViolations = "[]";

        List<Violation> violations = fixture.parse(jsonWithoutViolations);

        assertThat(violations).isEmpty();
    }
}
