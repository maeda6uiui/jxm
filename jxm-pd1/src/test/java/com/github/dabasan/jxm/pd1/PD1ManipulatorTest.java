package com.github.dabasan.jxm.pd1;

import org.joml.Matrix4f;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test PD1Manipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PD1ManipulatorTest {
    private static final String TARGET_DIR = "./TestData/SnowBase";
    private PD1Manipulator manipulator;
    private List<PD1Point> origPoints;

    @BeforeAll
    public void loadPD1() {
        assertDoesNotThrow(() -> {
            manipulator = new PD1Manipulator(Paths.get(TARGET_DIR, "point.pd1").toString());
        });

        origPoints = new ArrayList<>();
        manipulator.getPoints().forEach(point -> origPoints.add(new PD1Point(point)));
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
        manipulator.transform(mat).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "transform.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void translate() {
        float amountX = 50.0f;
        float amountY = 50.0f;
        float amountZ = 50.0f;
        manipulator.translate(amountX, amountY, amountZ);

        var saveFilepath = Paths.get(TARGET_DIR, "translate.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void rotX() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotX(amount).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot_x.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void rotY() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotY(amount).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot_y.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void rotZ() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotZ(amount).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot_z.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void rot() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rot(amount, 1.0f, 1.0f, 1.0f).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void rescale() {
        float scaleX = 2.0f;
        float scaleY = 2.0f;
        float scaleZ = 2.0f;
        manipulator.rescale(scaleX, scaleY, scaleZ).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rescale.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
        manipulator.resetTransformation();
    }

    @Test
    public void invertZ() {
        manipulator.invertZ();

        var saveFilepath = Paths.get(TARGET_DIR, "invert_z.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
    }

    @Test
    public void testUpdate() {
        List<PD1Point> tmpPoints;
        try {
            var tmpFilepath = Paths.get(TARGET_DIR, "point_2.pd1").toString();
            var tmpManipulator = new PD1Manipulator(tmpFilepath);
            tmpPoints = tmpManipulator.getPoints();
        } catch (IOException e) {
            fail(e);
            return;
        }

        manipulator.setPoints(tmpPoints);
        assertEquals(tmpPoints, manipulator.getPoints());

        manipulator.setPoints(origPoints);
    }

    @Test
    public void rotateDirection() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotateDirection(amount);

        var saveFilepath = Paths.get(TARGET_DIR, "rotate_direction.pd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsPD1(saveFilepath));

        manipulator.setPoints(origPoints);
    }
}
