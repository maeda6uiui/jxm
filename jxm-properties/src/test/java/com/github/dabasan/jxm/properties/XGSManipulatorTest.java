package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.xgs.XGSManipulator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Test XGSManipulator
 *
 * @author maeda6uiui
 */
public class XGSManipulatorTest {
    private final String TARGET_DIR = "./Data/Weapon";
    private XGSManipulator manipulator;

    public static void main(String[] args) {
        new XGSManipulatorTest();
    }

    public XGSManipulatorTest() {
        var srcFilepath = Paths.get(TARGET_DIR, "weapons.xgs").toString();
        try {
            manipulator = new XGSManipulator(srcFilepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.printWeapons();
        this.saveAsXGS();
    }

    private void printWeapons() {
        System.out.println("#Weapons");
        Arrays.asList(manipulator.getWeapons()).forEach(System.out::println);
    }

    private void saveAsXGS() {
        System.out.println("#saveAsXGS()");

        var saveFilepath = Paths.get(TARGET_DIR, "weapons_2.xgs").toString();
        manipulator.saveAsXGS(saveFilepath);
    }
}
