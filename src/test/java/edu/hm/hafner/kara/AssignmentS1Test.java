package edu.hm.hafner.kara;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.i8k.karalight.Kara;
import de.i8k.karalight.test.TestKaraController;
import de.i8k.karalight.world.RepresentationMode;
import de.i8k.karalight.world.World;

import static org.assertj.core.api.Assertions.*;

class AssignmentS1Test extends AbstractKaraTest {
    @MethodSource
    @ParameterizedTest(name = "{index} => {0} ({1})")
    @DisplayName("Fülle die Quadranten oben links und rechts unten")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldSolveAssignment1a(final World start, final World expected) {
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(start.getWidth() / 2));
        controller.prepareInput(String.valueOf(start.getHeight() / 2));

        Kara.setController(controller);

        AssignmentS1.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(expected.getRepresentation(RepresentationMode.NONE));
    }

    static Stream<Arguments> shouldSolveAssignment1a() {
        return createStreamOfWorlds("S1-XS", "S1-S", "S1-M", "S1-L", "S1-XL");
    }

    @MethodSource
    @ParameterizedTest(name = "{index} => Welt: {0}, Breite: {1}, Höhe: {2}")
    @DisplayName("Male nacheinander zwei gefüllte Rechtecke - Startpunkt oben links")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldSolveAssignment1b(final String name, final int width, final int height) {
        var start = readGivenWorld(name);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(width));
        controller.prepareInput(String.valueOf(height));

        Kara.setController(controller);

        AssignmentS1.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE)).isEqualTo(
                new World(String.format("Expected%s-%d-%d.world", name, width, height)).getRepresentation(RepresentationMode.NONE));
    }

    static Stream<Arguments> shouldSolveAssignment1b() {
        return Stream.of(
                Arguments.of("S1-XL", 2, 6),
                Arguments.of("S1-XL", 7, 1)
        );
    }
}

