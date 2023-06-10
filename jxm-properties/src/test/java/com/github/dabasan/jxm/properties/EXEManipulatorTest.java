package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.xops.EXEManipulator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test EXEManipulator
 *
 * @author maeda6uiui
 */
public class EXEManipulatorTest {
    private final String TARGET_DIR = "./Data/XOPS";
    private EXEManipulator manipulator;

    public static void main(String[] args) {
        new EXEManipulatorTest();
    }

    public EXEManipulatorTest() {
        var srcFilenames = new ArrayList<String>();
        srcFilenames.add("xops096.exe");
        srcFilenames.add("xops096t.exe");
        srcFilenames.add("xops097ft.exe");
        srcFilenames.add("xops0975t.exe");
        srcFilenames.add("xopsolt18f2.exe");
        srcFilenames.add("xopsolt19f2.exe");

        for (var srcFilename : srcFilenames) {
            var srcFilepath = Paths.get(TARGET_DIR, srcFilename).toString();
            try {
                manipulator = new EXEManipulator(srcFilepath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.printSrcXOPSVersion();
            this.printWeapons();
            this.printCharacters();

            String backupFilepath = Paths.get(TARGET_DIR, srcFilename + ".backup").toString();
            this.write(srcFilepath, backupFilepath);
        }
    }

    private void printSrcXOPSVersion() {
        System.out.println("#Src XOPS version");
        System.out.println(manipulator.getSrcXOPSVersion());
    }

    private void printWeapons() {
        System.out.println("#Weapons");
        Arrays.asList(manipulator.getWeapons()).forEach(System.out::println);
    }

    private void printCharacters() {
        System.out.println("#Characters");
        Arrays.asList(manipulator.getCharacters()).forEach(System.out::println);
    }

    private void write(String saveFilepath, String backupFilepath) {
        System.out.println("#write()");
        manipulator.write(saveFilepath, backupFilepath);
    }
}
