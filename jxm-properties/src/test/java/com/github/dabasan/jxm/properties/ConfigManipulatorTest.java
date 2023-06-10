package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.config.Config;
import com.github.dabasan.jxm.properties.config.ConfigManipulator;
import com.github.dabasan.jxm.properties.config.KeyCode;
import com.github.dabasan.jxm.properties.config.WindowMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test ConfigManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigManipulatorTest {
    private static final String TARGET_DIR = "./Data/Config";
    private ConfigManipulator manipulator;

    @BeforeAll
    public void loadConfig() {
        assertDoesNotThrow(() -> {
            manipulator = new ConfigManipulator(Paths.get(TARGET_DIR, "config.dat").toString());
        });
    }

    @Test
    public void testRead() {
        Config config = manipulator.getConfig();

        assertAll(
                () -> assertEquals(config.turnUp, KeyCode.KEY_UP),
                () -> assertEquals(config.turnDown, KeyCode.KEY_DOWN),
                () -> assertEquals(config.turnLeft, KeyCode.KEY_LEFT),
                () -> assertEquals(config.turnRight, KeyCode.KEY_RIGHT),
                () -> assertEquals(config.moveForward, KeyCode.KEY_W),
                () -> assertEquals(config.moveBackward, KeyCode.KEY_S),
                () -> assertEquals(config.moveLeft, KeyCode.KEY_A),
                () -> assertEquals(config.moveRight, KeyCode.KEY_D),
                () -> assertEquals(config.walk, KeyCode.KEY_TAB),
                () -> assertEquals(config.jump, KeyCode.KEY_SPACE),
                () -> assertEquals(config.reload, KeyCode.KEY_R),
                () -> assertEquals(config.dropWeapon, KeyCode.KEY_G),
                () -> assertEquals(config.zoom, KeyCode.KEY_MOUSE_R),
                () -> assertEquals(config.fireMode, KeyCode.KEY_X),
                () -> assertEquals(config.switchWeapon, KeyCode.KEY_Z),
                () -> assertEquals(config.weapon1, KeyCode.KEY_1),
                () -> assertEquals(config.weapon2, KeyCode.KEY_2),
                () -> assertEquals(config.fire, KeyCode.KEY_MOUSE_L),
                () -> assertEquals(config.mouseSensitivity, 10),
                () -> assertEquals(config.brightness, 10),
                () -> assertEquals(config.windowMode, WindowMode.WINDOW),
                () -> assertEquals(config.enableSound, true),
                () -> assertEquals(config.enableBlood, true),
                () -> assertEquals(config.invertMouse, false),
                () -> assertEquals(config.frameSkip, false),
                () -> assertEquals(config.anotherGunsight, false),
                () -> assertEquals(config.name, "maeda6uiui")
        );
    }

    @Test
    public void testUpdate() {
        manipulator.getConfig().moveForward = KeyCode.KEY_ENTER;
        manipulator.getConfig().brightness = 1000;
        manipulator.getConfig().enableSound = false;
        manipulator.getConfig().name = "Test";

        assertAll(
                () -> assertEquals(manipulator.getConfig().moveForward, KeyCode.KEY_ENTER),
                () -> assertEquals(manipulator.getConfig().brightness, 1000),
                () -> assertEquals(manipulator.getConfig().enableSound, false),
                () -> assertEquals(manipulator.getConfig().name, "Test")
        );

        manipulator.getConfig().moveForward = KeyCode.KEY_W;
        manipulator.getConfig().brightness = 10;
        manipulator.getConfig().enableSound = true;
        manipulator.getConfig().name = "maeda6uiui";
    }

    @Test
    public void saveAsDAT() {
        var srcFilepath = Paths.get(TARGET_DIR, "config.dat").toString();
        var outputFilepath = Paths.get(TARGET_DIR, "config_2.dat").toString();
        assertDoesNotThrow(() -> manipulator.saveAsDAT(outputFilepath));

        String srcFileHash = TestUtils.getFileHash(srcFilepath);
        String outputFileHash = TestUtils.getFileHash(outputFilepath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
