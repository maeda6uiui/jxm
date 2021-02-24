package com.github.dabasan.jxm.mif;

import java.util.ArrayList;
import java.util.List;

/**
 * Mission info
 *
 * @author Daba
 */
public class MissionInfo {
    public String missionTitle;
    public String missionFullname;
    public String pathnameOfBlock;
    public String pathnameOfPoint;
    public SkyType skyType;
    public boolean extraCollision;
    public boolean darkScreen;
    public String pathnameOfObj;
    public String pathnameOfImage1;
    public String pathnameOfImage2;
    public List<String> briefingText;// per line

    /**
     * Creates a mission info instance.
     */
    public MissionInfo() {
        missionTitle = "";
        missionFullname = "";
        pathnameOfBlock = "./";
        pathnameOfPoint = "./addon/";
        skyType = SkyType.NONE;
        extraCollision = false;
        darkScreen = false;
        pathnameOfObj = "!";
        pathnameOfImage1 = "./data/briefing/np.bmp";
        pathnameOfImage2 = "!";
        briefingText = new ArrayList<>();
    }

    /**
     * Copies a mission info instance.
     *
     * @param mif Mission info
     */
    public MissionInfo(MissionInfo mif) {
        this.missionTitle = mif.missionTitle;
        this.missionFullname = mif.missionFullname;
        this.pathnameOfBlock = mif.pathnameOfBlock;
        this.pathnameOfPoint = mif.pathnameOfPoint;
        this.skyType = mif.skyType;
        this.extraCollision = mif.extraCollision;
        this.darkScreen = mif.darkScreen;
        this.pathnameOfObj = mif.pathnameOfObj;
        this.pathnameOfImage1 = mif.pathnameOfImage1;
        this.pathnameOfImage2 = mif.pathnameOfImage2;
        this.briefingText = new ArrayList<>(mif.briefingText);
    }

    @Override
    public String toString() {
        return "MissionInfo [missionTitle=" + missionTitle + ", missionFullname=" + missionFullname
                + ", pathnameOfBlock=" + pathnameOfBlock + ", pathnameOfPoint=" + pathnameOfPoint
                + ", skyType=" + skyType + ", extraCollision=" + extraCollision + ", darkScreen="
                + darkScreen + ", pathnameOfObj=" + pathnameOfObj + ", pathnameOfImage1="
                + pathnameOfImage1 + ", pathnameOfImage2=" + pathnameOfImage2 + ", briefingText="
                + briefingText + "]";
    }
}
