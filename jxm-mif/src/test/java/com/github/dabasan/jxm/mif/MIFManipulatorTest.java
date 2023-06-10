package com.github.dabasan.jxm.mif;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test MIFManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MIFManipulatorTest {
    private static final String TARGET_DIR = "./Data";
    private MIFManipulator manipulator;

    @BeforeAll
    public void loadMIF() {
        assertDoesNotThrow(() -> {
            manipulator = new MIFManipulator(Paths.get(TARGET_DIR, "src.mif").toString(), "Shift-JIS");
        });
    }

    @Test
    public void testRead() {
        MissionInfo missionInfo = manipulator.getMissionInfo();
        assertEquals("AH KT 2", missionInfo.missionTitle);
        assertEquals("Apartment House KT 2", missionInfo.missionFullname);
        assertEquals(".\\addon\\ApartmentHouse\\map2.bd1", missionInfo.pathnameOfBlock);
        assertEquals(".\\addon\\ApartmentHouse\\kt2.pd1", missionInfo.pathnameOfPoint);
        assertEquals(SkyType.NIGHT, missionInfo.skyType);
        assertEquals(".\\addon\\ApartmentHouse\\official.jpg", missionInfo.pathnameOfImage1);
        assertEquals("!", missionInfo.pathnameOfImage2);
        assertEquals("!", missionInfo.pathnameOfObj);
        assertEquals(false, missionInfo.extraCollision);
        assertEquals(false, missionInfo.darkScreen);

        var briefingExpected = new ArrayList<String>();
        briefingExpected.add("例の役人があまりにも無能なので、");
        briefingExpected.add("殺害せよとの命令が下された。");
        briefingExpected.add("");
        briefingExpected.add("警察官である自分は、先輩とともに、");
        briefingExpected.add("役人が潜伏しているアパートへと向かった。");
        briefingExpected.add("");
        briefingExpected.add("abcdefg");

        assertLinesMatch(briefingExpected, missionInfo.briefingText);
    }

    @Test
    public void testUpdate() {
        manipulator.getMissionInfo().missionTitle = "Test Mission";
        manipulator.getMissionInfo().skyType = SkyType.WILDERNESS;
        manipulator.getMissionInfo().pathnameOfBlock = "./path/to/bd1";

        assertEquals("Test Mission", manipulator.getMissionInfo().missionTitle);
        assertEquals(SkyType.WILDERNESS, manipulator.getMissionInfo().skyType);
        assertEquals("./path/to/bd1", manipulator.getMissionInfo().pathnameOfBlock);
    }

    @Test
    public void saveAsMIF() {
        var saveFilepath = Paths.get(TARGET_DIR, "dst_shift_jis.mif").toString();
        assertDoesNotThrow(() -> manipulator.saveAsMIF(saveFilepath, "Shift-JIS"));

        var saveFilepath2 = Paths.get(TARGET_DIR, "dst_utf_8.mif").toString();
        assertDoesNotThrow(() -> manipulator.saveAsMIF(saveFilepath2, "UTF-8"));
    }
}
