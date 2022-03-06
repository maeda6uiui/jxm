package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.xops.EXEManipulator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Test EXEManipulator
 *
 * @author Daba
 */
public class EXEManipulatorTest {
    private final String TARGET_DIR = "./Data/XOPS";
    private EXEManipulator manipulator;

    public static void main(String[] args) {
        new EXEManipulatorTest();
    }

    public EXEManipulatorTest() {
        var srcFilepath = Paths.get(TARGET_DIR, "xops0975t.exe").toString();
        try {
            manipulator = new EXEManipulator(srcFilepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.printSrcXOPSVersion();
        this.printWeapons();
        this.printCharacters();
        this.write();
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

    private void write() {
        System.out.println("#write()");

        var saveFilepath = Paths.get(TARGET_DIR, "xops0975t.exe").toString();
        var backupFilepath = Paths.get(TARGET_DIR, "xops0975t_backup.exe").toString();
        manipulator.write(saveFilepath, backupFilepath);
    }
}
