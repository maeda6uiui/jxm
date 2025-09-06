package com.github.maeda6uiui.jxm.bd1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Test BD1Manipulator with Training Yard map
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BD1ManipulatorTrainingYardTest {
    private static final String TARGET_DIR = "./TestData/TrainingYard";
    private BD1Manipulator manipulator;

    @BeforeAll
    public void loadBD1() {
        assertDoesNotThrow(() -> {
            manipulator = new BD1Manipulator(Paths.get(TARGET_DIR, "map.bd1").toString());
        });
    }

    @Test
    public void saveAsOBJ() {
        manipulator.invertZ();

        var objFilepath = Paths.get(TARGET_DIR, "map.obj").toString();
        var mtlFilepath = Paths.get(TARGET_DIR, "map.mtl").toString();
        assertDoesNotThrow(
                () -> manipulator.saveAsOBJ(objFilepath, mtlFilepath, "map.mtl", false));

        var objFlipFilepath = Paths.get(TARGET_DIR, "map_flip.obj").toString();
        var mtlFlipFilepath = Paths.get(TARGET_DIR, "map_flip.mtl").toString();
        assertDoesNotThrow(
                () -> manipulator.saveAsOBJ(objFlipFilepath, mtlFlipFilepath, "map_flip.mtl", true));
    }
}
