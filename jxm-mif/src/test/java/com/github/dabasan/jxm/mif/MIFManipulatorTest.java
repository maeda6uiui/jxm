package com.github.dabasan.jxm.mif;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test MIFManipulator
 *
 * @author maeda6uiui
 */
public class MIFManipulatorTest {
    private final String TARGET_DIR = "./Data";
    private MIFManipulator manipulator;

    public static void main(String[] args) {
        new MIFManipulatorTest();
    }

    public MIFManipulatorTest() {
        var srcFilepath = Paths.get(TARGET_DIR, "src.mif").toString();
        try {
            manipulator = new MIFManipulator(srcFilepath, "Shift-JIS");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.printMIF();
        this.saveAsMIF();
    }

    private void printMIF() {
        System.out.println("#Mission info");
        System.out.println("##Original");
        System.out.println(manipulator.getMissionInfo());

        System.out.println("##Modified");

        manipulator.getMissionInfo().missionTitle = "Test Mission";
        manipulator.getMissionInfo().skyType = SkyType.WILDERNESS;
        manipulator.getMissionInfo().pathnameOfBlock = "./path/to/bd1";

        List<String> briefingText = manipulator.getMissionInfo().briefingText;
        briefingText = briefingText.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        manipulator.getMissionInfo().briefingText = briefingText;

        System.out.println(manipulator.getMissionInfo());
    }

    private void saveAsMIF() {
        System.out.println("#saveAsMIF()");

        var saveFilepath = Paths.get(TARGET_DIR, "dst_shift_jis.mif").toString();
        manipulator.saveAsMIF(saveFilepath, "Shift-JIS");

        var saveFilepath2 = Paths.get(TARGET_DIR, "dst_utf_8.mif").toString();
        manipulator.saveAsMIF(saveFilepath2, "UTF-8");
    }
}
