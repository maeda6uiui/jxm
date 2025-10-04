package com.github.dabasan.jxm.mif;

import java.io.IOException;
import java.nio.file.Path;

/**
 * MIF manipulator
 *
 * @author maeda6uiui
 */
public class MIFManipulator {
    private MissionInfo missionInfo;

    /**
     * Creates a MIF manipulator.
     */
    public MIFManipulator() {
        missionInfo = new MissionInfo();
    }

    /**
     * Creates a MIF manipulator and loads a MIF file.
     *
     * @param path     Path of the MIF file
     * @param encoding Encoding of the MIF
     * @throws IOException If it fails to load the MIF file
     */
    public MIFManipulator(Path path, String encoding) throws IOException {
        var reader = new MIFReader(path, encoding);
        missionInfo = reader.getMissionInfo();
    }

    /**
     * Returns mission info.
     *
     * @return mission info
     */
    public MissionInfo getMissionInfo() {
        return missionInfo;
    }

    /**
     * Sets mission info.
     *
     * @param missionInfo mission info to set
     */
    public void setMissionInfo(MissionInfo missionInfo) {
        this.missionInfo = missionInfo;
    }

    /**
     * Saves the mission info in a MIF file.
     *
     * @param path     Path of the MIF file
     * @param encoding Encoding of the MIF file
     * @throws IOException If it fails to write to the file
     */
    public void saveAsMIF(Path path, String encoding) throws IOException {
        var writer = new MIFWriter();
        writer.write(path, encoding, missionInfo);
    }
}
