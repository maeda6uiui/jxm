package com.github.dabasan.jxm.mif;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test MIFManipulator
 *
 * @author maeda6uiui
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MIFManipulatorTest {
    private static final String TARGET_DIR = "./TestData";
    private MIFManipulator manipulator;

    @BeforeAll
    public void loadMIF() {
        assertDoesNotThrow(() -> {
            manipulator = new MIFManipulator(Paths.get(TARGET_DIR, "src.mif").toString(), "Shift-JIS");
        });
    }

    @Test
    public void testRead() {
        var briefingText = new ArrayList<String>();
        briefingText.add("例の役人があまりにも無能なので、");
        briefingText.add("殺害せよとの命令が下された。");
        briefingText.add("");
        briefingText.add("警察官である自分は、先輩とともに、");
        briefingText.add("役人が潜伏しているアパートへと向かった。");
        briefingText.add("");
        briefingText.add("abcdefg");

        MissionInfo expectedMissionInfo = new MissionInfo()
                .setMissionTitle("AH KT 2")
                .setMissionFullname("Apartment House KT 2")
                .setPathnameOfBlock(".\\addon\\ApartmentHouse\\map2.bd1")
                .setPathnameOfPoint(".\\addon\\ApartmentHouse\\kt2.pd1")
                .setSkyType(SkyType.NIGHT)
                .setPathnameOfImage1(".\\addon\\ApartmentHouse\\official.jpg")
                .setPathnameOfImage2("!")
                .setPathnameOfObj("!")
                .setExtraCollision(false)
                .setDarkScreen(false)
                .setBriefingText(briefingText);
        MissionInfo actualMissionInfo = manipulator.getMissionInfo();
        assertEquals(expectedMissionInfo, actualMissionInfo);
    }

    @Test
    public void testUpdate() {
        MissionInfo currentMissionInfo = manipulator.getMissionInfo();

        MissionInfo newMissionInfo = TestUtils.generateRandomMissionInfo();
        manipulator.setMissionInfo(newMissionInfo);
        assertEquals(newMissionInfo, manipulator.getMissionInfo());

        manipulator.setMissionInfo(currentMissionInfo);
    }

    @Test
    public void saveAsMIF() {
        var saveFilepath = Paths.get(TARGET_DIR, "dst_shift_jis.mif").toString();
        assertDoesNotThrow(() -> manipulator.saveAsMIF(saveFilepath, "Shift-JIS"));

        var saveFilepath2 = Paths.get(TARGET_DIR, "dst_utf_8.mif").toString();
        assertDoesNotThrow(() -> manipulator.saveAsMIF(saveFilepath2, "UTF-8"));
    }
}
