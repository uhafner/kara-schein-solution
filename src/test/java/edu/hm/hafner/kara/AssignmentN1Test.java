package edu.hm.hafner.kara;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.i8k.karalight.Kara;
import de.i8k.karalight.test.TestKaraController;
import de.i8k.karalight.world.RepresentationMode;
import de.i8k.karalight.world.World;

import static org.assertj.core.api.Assertions.*;

class AssignmentN1Test extends AbstractKaraTest {
    @ValueSource(ints = {Integer.MIN_VALUE, -100, 1_000_000, 1_111_111, -1_111_111, Integer.MAX_VALUE})
    @ParameterizedTest(name = "{index} => Male nicht bei ungültigem Wertebereich {0}")
    @DisplayName("Male nicht bei ungültigen Werten")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldDoNothingForIllegalNumber(final int value) {
        final String fileName = "N1";

        var start = readGivenWorld(fileName);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(value));

        Kara.setController(controller);

        AssignmentN1.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(new World("Assignment" + (fileName + ".world")).getRepresentation(RepresentationMode.NONE));
    }

    @ValueSource(ints = {5, 123, 4040, 987654, 999999})
    @ParameterizedTest(name = "{index} => Male die Zahl {0}")
    @DisplayName("Male gültige Zahlen")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldDrawNumber(final int value) {
        final String fileName = "N1";

        var start = readGivenWorld(fileName);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(value));

        Kara.setController(controller);

        AssignmentN1.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(new World("Expected-" + (value + ".world")).getRepresentation(RepresentationMode.NONE));
    }
}

