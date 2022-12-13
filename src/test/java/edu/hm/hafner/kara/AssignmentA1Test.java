package edu.hm.hafner.kara;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.i8k.karalight.world.World;

import static edu.hm.hafner.kara.AssignmentA1.*;
import static org.assertj.core.api.Assertions.*;

class AssignmentA1Test extends AbstractKaraTest {
    @MethodSource
    @ParameterizedTest(name = "{index} => In der Welt {0} wird das Muster gefunden: {1}")
    @DisplayName("Suche nach einem Muster")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldSolveAssignment(final World start, final boolean expectedResult) {
        executeHeadlessWithWorld(start);

        AssignmentA1.main();

        assertThat(start.deliverMessages())
                .hasSize(1)
                .element(0).asString()
                .as("Kara hat nicht die korrekte Antwort mit sayResult() ausgegeben", expectedResult)
                .isEqualTo(expectedResult ? MUSTER_GEFUNDEN : MUSTER_NICHT_GEFUNDEN);
    }

    static Stream<Arguments> shouldSolveAssignment() {
        return Stream.of(
                Arguments.of("AssignmentA1-3-kein-treffer-blatt-zuviel.world", false),
                Arguments.of("AssignmentA1-3-kein-treffer-leerstelle-fehlt.world", false),
                Arguments.of("AssignmentA1-3-kein-treffer-leer.world", false),
                Arguments.of("AssignmentA1-3-kein-treffer-voll.world", false),
                Arguments.of("AssignmentA1-3-treffer-start.world", true),
                Arguments.of("AssignmentA1-3-treffer-mitte.world", true),
                Arguments.of("AssignmentA1-3-treffer-ende.world", true),
                Arguments.of("AssignmentA1-17-lange-welt.world", true),
                Arguments.of("AssignmentA1-17-lange-welt-daneben.world", false)
        );
    }
}

