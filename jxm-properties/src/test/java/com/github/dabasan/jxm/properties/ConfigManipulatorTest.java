package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.config.ConfigManipulator;
import com.github.dabasan.jxm.properties.config.JXMConfig;
import com.github.dabasan.jxm.properties.config.KeyCode;
import com.github.dabasan.jxm.properties.config.WindowMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test ConfigManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigManipulatorTest {
    private static final String TARGET_DIR = "./TestData/Config";
    private ConfigManipulator manipulator;

    @BeforeAll
    public void loadConfig() {
        assertDoesNotThrow(() -> {
            manipulator = new ConfigManipulator(Paths.get(TARGET_DIR, "config.dat"));
        });
    }

    @Test
    public void testRead() {
        JXMConfig expectedConfig = new JXMConfig()
                .setTurnUp(KeyCode.KEY_UP)
                .setTurnDown(KeyCode.KEY_DOWN)
                .setTurnLeft(KeyCode.KEY_LEFT)
                .setTurnRight(KeyCode.KEY_RIGHT)
                .setMoveForward(KeyCode.KEY_W)
                .setMoveBackward(KeyCode.KEY_S)
                .setMoveLeft(KeyCode.KEY_A)
                .setMoveRight(KeyCode.KEY_D)
                .setWalk(KeyCode.KEY_TAB)
                .setJump(KeyCode.KEY_SPACE)
                .setReload(KeyCode.KEY_R)
                .setDropWeapon(KeyCode.KEY_G)
                .setZoom(KeyCode.KEY_MOUSE_R)
                .setFireMode(KeyCode.KEY_X)
                .setSwitchWeapon(KeyCode.KEY_Z)
                .setWeapon1(KeyCode.KEY_1)
                .setWeapon2(KeyCode.KEY_2)
                .setFire(KeyCode.KEY_MOUSE_L)
                .setMouseSensitivity(10)
                .setBrightness(10)
                .setWindowMode(WindowMode.WINDOW)
                .setEnableSound(true)
                .setEnableBlood(true)
                .setInvertMouse(false)
                .setFrameSkip(false)
                .setAnotherGunsight(false)
                .setName("maeda6uiui");
        JXMConfig actualConfig = manipulator.getConfig();
        assertEquals(expectedConfig, actualConfig);
    }

    @Test
    public void testUpdate() {
        JXMConfig currentConfig = manipulator.getConfig();

        JXMConfig newConfig = TestUtils.generateRandomConfig();
        manipulator.setConfig(newConfig);
        assertEquals(newConfig, manipulator.getConfig());

        manipulator.setConfig(currentConfig);
    }

    @Test
    public void saveAsDAT() {
        var srcPath = Paths.get(TARGET_DIR, "config.dat");
        var outputPath = Paths.get(TARGET_DIR, "config_2.dat");
        assertDoesNotThrow(() -> manipulator.save(outputPath));

        String srcFileHash = TestUtils.getFileHash(srcPath);
        String outputFileHash = TestUtils.getFileHash(outputPath);
        assertEquals(srcFileHash, outputFileHash);
    }
}
