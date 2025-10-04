package com.github.dabasan.jxm.bd1;

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
            manipulator = new BD1Manipulator(Paths.get(TARGET_DIR, "map.bd1"));
        });
    }

    @Test
    public void exportAsOBJ() {
        manipulator.invertZ();

        var objPath = Paths.get(TARGET_DIR, "map.obj");
        var mtlPath = Paths.get(TARGET_DIR, "map.mtl");
        assertDoesNotThrow(
                () -> manipulator.exportAsOBJ(objPath, mtlPath, false));

        var objFlipPath = Paths.get(TARGET_DIR, "map_flip.obj");
        var mtlFlipPath = Paths.get(TARGET_DIR, "map_flip.mtl");
        assertDoesNotThrow(
                () -> manipulator.exportAsOBJ(objFlipPath, mtlFlipPath, true));
    }
}
