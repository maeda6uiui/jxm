package com.github.dabasan.jxm.mif;

import java.io.*;

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

    private void readConstructorBase(InputStream is, String encoding) throws IOException {
        var reader = new MIFReader(is, encoding);
        missionInfo = reader.getMissionInfo();
    }

    /**
     * Creates a MIF manipulator and loads a MIF.
     *
     * @param is       input stream to load the MIF from
     * @param encoding encoding of the MIF
     * @throws IOException if it fails to load
     */
    public MIFManipulator(InputStream is, String encoding) throws IOException {
        this.readConstructorBase(is, encoding);
    }

    /**
     * Creates a MIF manipulator and loads a MIF.
     *
     * @param file     file to load the MIF from
     * @param encoding encoding of the MIF
     * @throws IOException if it fails to load
     */
    public MIFManipulator(File file, String encoding) throws IOException {
        try (var fis = new FileInputStream(file)) {
            this.readConstructorBase(fis, encoding);
        }
    }

    /**
     * Creates a MIF manipulator and loads a MIF.
     *
     * @param filepath filepath to load the MIF from
     * @param encoding encoding of the MIF
     * @throws IOException if it fails to load
     */
    public MIFManipulator(String filepath, String encoding) throws IOException {
        try (var fis = new FileInputStream(filepath)) {
            this.readConstructorBase(fis, encoding);
        }
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

    private void saveAsMIFBase(OutputStream os, String encoding) throws IOException {
        var writer = new MIFWriter();
        writer.write(os, encoding, missionInfo);
    }

    /**
     * Saves the mission info as a MIF.
     *
     * @param os       output stream to write the MIF to
     * @param encoding encoding of the MIF
     * @throws IOException if it fails to output
     */
    public void saveAsMIF(OutputStream os, String encoding) throws IOException {
        this.saveAsMIFBase(os, encoding);
    }

    /**
     * Saves the mission info as a MIF.
     *
     * @param file     file to write the MIF to
     * @param encoding encoding of the MIF
     * @throws IOException if it fails to output
     */
    public void saveAsMIF(File file, String encoding) throws IOException {
        try (var fos = new FileOutputStream(file)) {
            this.saveAsMIFBase(fos, encoding);
        }
    }

    /**
     * Saves the mission info as a MIF.
     *
     * @param filepath filepath
     * @param encoding encoding of the MIF
     * @throws IOException if it fails to output
     */
    public void saveAsMIF(String filepath, String encoding) throws IOException {
        try (var fos = new FileOutputStream(filepath)) {
            this.saveAsMIFBase(fos, encoding);
        }
    }
}
