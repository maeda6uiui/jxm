package com.github.dabasan.jxm.pd1;

import org.joml.Matrix4f;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test PD1Manipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PD1ManipulatorTest {
    private static final String TARGET_DIR = "./TestData";
    private PD1Manipulator manipulator;

    @BeforeAll
    public void loadPD1() {
        assertDoesNotThrow(() -> {
            manipulator = new PD1Manipulator(Paths.get(TARGET_DIR, "point.pd1").toString());
        });
    }

    @Test
    public void testNumPoints() {
        int expected = 56;
        int actual = manipulator.getNumPoints();
        assertEquals(expected, actual);

        int expected2 = 12;
        int actual2 = manipulator.getNumPoints(0, 1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void transform() {
        var mat = new Matrix4f().rotate((float) Math.PI / 4.0f, 1.0f, 0.0f, 0.0f)
                .rotate((float) Math.PI / 4.0f, 0.0f, 1.0f, 0.0f)
                .rotate((float) Math.PI / 4.0f, 0.0f, 0.0f, 1.0f).scale(1.0f, 2.0f, 1.0f);
        manipulator.transform(mat);

        var saveFilepath = Paths.get(TARGET_DIR, "transform.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        var invMat = mat.invert();
        manipulator.transform(invMat);
    }

    @Test
    public void translate() {
        float amountX = 50.0f;
        float amountY = 50.0f;
        float amountZ = 50.0f;
        manipulator.translate(amountX, amountY, amountZ);

        var saveFilepath = Paths.get(TARGET_DIR, "translate.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.translate(-amountX, -amountY, -amountZ);
    }

    @Test
    public void rotX() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotX(amount);

        var saveFilepath = Paths.get(TARGET_DIR, "rot_x.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.rotX(-amount);
    }

    @Test
    public void rotY() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotY(amount);

        var saveFilepath = Paths.get(TARGET_DIR, "rot_y.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.rotY(-amount);
    }

    @Test
    public void rotZ() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotZ(amount);

        var saveFilepath = Paths.get(TARGET_DIR, "rot_z.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.rotZ(-amount);
    }

    @Test
    public void rot() {
        var origPoints = new ArrayList<PD1Point>();
        manipulator.getPoints().forEach(p -> origPoints.add(new PD1Point(p)));

        float amount = (float) Math.PI / 4.0f;
        manipulator.rot(amount, 1.0f, 1.0f, 1.0f);

        var saveFilepath = Paths.get(TARGET_DIR, "rot.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
    }

    @Test
    public void rescale() {
        float scaleX = 2.0f;
        float scaleY = 2.0f;
        float scaleZ = 2.0f;
        manipulator.rescale(scaleX, scaleY, scaleZ);

        var saveFilepath = Paths.get(TARGET_DIR, "rescale.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.rescale(1.0f / scaleX, 1.0f / scaleY, 1.0f / scaleZ);
    }

    @Test
    public void invertZ() {
        manipulator.invertZ();

        var saveFilepath = Paths.get(TARGET_DIR, "invert_z.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.invertZ();
    }

    @Test
    public void rotateDirection() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotateDirection(amount);

        var saveFilepath = Paths.get(TARGET_DIR, "rotate_direction.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.rotateDirection(-amount);
    }
}
