package com.github.dabasan.jxm.properties;

import com.github.dabasan.jxm.properties.weapon.ids.IDSManipulator;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Test IDSManipulator
 *
 * @author Daba
 */
public class IDSManipulatorTest {
    private final String TARGET_DIR = "./Data/Weapon";
    private IDSManipulator manipulator;

    public static void main(String[] args) {
        new IDSManipulatorTest();
    }

    public IDSManipulatorTest() {
        var srcFilepath = Paths.get(TARGET_DIR, "mp5.ids").toString();
        try {
            manipulator = new IDSManipulator(srcFilepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.printWeapon();
        this.saveAsIDS();
    }

    private void printWeapon() {
        System.out.println("#Weapon");
        System.out.println(manipulator.getWeapon());
    }

    private void saveAsIDS() {
        System.out.println("#saveAsIDS");

        var saveFilepath = Paths.get(TARGET_DIR, "mp5_2.ids").toString();
        manipulator.saveAsIDS(saveFilepath);
    }
}
