package com.github.dabasan.jxm.mif;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * MIF manipulator
 *
 * @author Daba
 */
public class MIFManipulator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MissionInfo missionInfo;

    /**
     * Creates a MIF manipulator.
     */
    public MIFManipulator() {
        missionInfo = new MissionInfo();
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

    private void readConstructorBase(InputStream is, String encoding) throws IOException {
        var reader = new MIFReader(is, encoding);
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
        this.missionInfo = Objects.requireNonNull(missionInfo);
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
     * @return -1: error 0: success
     */
    public int saveAsMIF(OutputStream os, String encoding) {
        int ret = 0;

        try {
            this.saveAsMIFBase(os, encoding);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }

    /**
     * Saves the mission info as a MIF.
     *
     * @param file     file to write the MIF to
     * @param encoding encoding of the MIF
     * @return -1: error 0: success
     */
    public int saveAsMIF(File file, String encoding) {
        int ret = 0;

        try (var fos = new FileOutputStream(file)) {
            this.saveAsMIFBase(fos, encoding);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }

    /**
     * Saves the mission info as a MIF.
     *
     * @param filepath filepath
     * @param encoding encoding of the MIF
     * @return -1: error 0: success
     */
    public int saveAsMIF(String filepath, String encoding) {
        int ret = 0;

        try (var fos = new FileOutputStream(filepath)) {
            this.saveAsMIFBase(fos, encoding);
        } catch (IOException e) {
            logger.error("Error", e);
            ret = -1;
        }

        return ret;
    }
}
