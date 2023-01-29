package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.character.xcs.XCSManipulator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Test XCSManipulator
 *
 * @author maeda6uiui
 */
public class XCSManipulatorTest {
    private final String TARGET_DIR = "./Data/Character";
    private XCSManipulator manipulator;

    public static void main(String[] args) {
        new XCSManipulatorTest();
    }

    public XCSManipulatorTest() {
        var srcFilepath = Paths.get(TARGET_DIR, "characters.xcs").toString();
        try {
            manipulator = new XCSManipulator(srcFilepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.printCharacters();
        this.saveAsXCS();
    }

    private void printCharacters() {
        System.out.println("#Characters");
        Arrays.asList(manipulator.getCharacters()).forEach(System.out::println);
    }

    private void saveAsXCS() {
        System.out.println("#saveAsXCS()");

        var saveFilepath = Paths.get(TARGET_DIR, "characters_2.xcs").toString();
        manipulator.saveAsXCS(saveFilepath);
    }
}
