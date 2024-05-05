package com.github.dabasan.jxm.bd1;

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
 * Test BD1Manipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BD1ManipulatorTest {
    private static final String TARGET_DIR = "./TestData/SnowBase";
    private BD1Manipulator manipulator;
    private List<BD1Block> origBlocks;

    @BeforeAll
    public void loadBD1() {
        assertDoesNotThrow(() -> {
            manipulator = new BD1Manipulator(Paths.get(TARGET_DIR, "map.bd1").toString());
        });

        origBlocks = new ArrayList<>();
        manipulator.getBlocks().forEach(block -> origBlocks.add(new BD1Block(block)));
    }

    @Test
    public void testNumBlocks() {
        int expected = 98;
        int actual = manipulator.getNumBlocks();
        assertEquals(expected, actual);
    }

    @Test
    public void testTextureFilenames() {
        var expected = new ArrayList<String>();
        expected.add("yuki.bmp");
        expected.add("jimen.bmp");
        expected.add("renga.bmp");
        expected.add("kabe.bmp");
        expected.add("jyouheki.bmp");
        expected.add("kaidan.bmp");
        expected.add("mado03.bmp");
        expected.add("ki2.bmp");
        expected.add("kabegami.bmp");
        expected.add("ki.bmp");

        var actual = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            actual.add(manipulator.getTextureFilename(i));
        }

        assertLinesMatch(expected, actual);
    }

    @Test
    public void transform() {
        var mat = new Matrix4f().rotate((float) Math.PI / 4.0f, 1.0f, 0.0f, 0.0f)
                .rotate((float) Math.PI / 4.0f, 0.0f, 1.0f, 0.0f)
                .rotate((float) Math.PI / 4.0f, 0.0f, 0.0f, 1.0f).scale(1.0f, 2.0f, 1.0f);
        manipulator.transform(mat).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "transform.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void translate() {
        float amountX = 50.0f;
        float amountY = 50.0f;
        float amountZ = 50.0f;
        manipulator.translate(amountX, amountY, amountZ).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "translate.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void rotX() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotX(amount).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot_x.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void rotY() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotY(amount).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot_y.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void rotZ() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rotZ(amount).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot_z.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void rot() {
        float amount = (float) Math.PI / 4.0f;
        manipulator.rot(amount, 1.0f, 1.0f, 1.0f).applyTransformation();

        var saveFilepath = Paths.get(TARGET_DIR, "rot.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void rescale() {
        float scaleX = 2.0f;
        float scaleY = 2.0f;
        float scaleZ = 2.0f;
        manipulator.rescale(scaleX, scaleY, scaleZ);

        var saveFilepath = Paths.get(TARGET_DIR, "rescale.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void invertZ() {
        manipulator.invertZ();

        var saveFilepath = Paths.get(TARGET_DIR, "invert_z.bd1").toString();
        assertDoesNotThrow(() -> manipulator.saveAsBD1(saveFilepath));

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void testUpdate() {
        List<BD1Block> tmpBlocks;
        try {
            var tmpFilepath = Paths.get(TARGET_DIR, "map_2.bd1").toString();
            var tmpManipulator = new BD1Manipulator(tmpFilepath);
            tmpBlocks = tmpManipulator.getBlocks();
        } catch (IOException e) {
            fail(e);
            return;
        }

        manipulator.setBlocks(tmpBlocks);
        assertEquals(tmpBlocks, manipulator.getBlocks());

        manipulator.setBlocks(origBlocks);
    }

    @Test
    public void saveAsOBJ() {
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
