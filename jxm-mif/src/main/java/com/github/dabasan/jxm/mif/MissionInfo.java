package com.github.dabasan.jxm.mif;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mission info
 *
 * @author maeda6uiui
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
    public List<String> briefingText;//per line

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissionInfo that = (MissionInfo) o;
        return extraCollision == that.extraCollision
                && darkScreen == that.darkScreen
                && Objects.equals(missionTitle, that.missionTitle)
                && Objects.equals(missionFullname, that.missionFullname)
                && Objects.equals(pathnameOfBlock, that.pathnameOfBlock)
                && Objects.equals(pathnameOfPoint, that.pathnameOfPoint)
                && skyType == that.skyType
                && Objects.equals(pathnameOfObj, that.pathnameOfObj)
                && Objects.equals(pathnameOfImage1, that.pathnameOfImage1)
                && Objects.equals(pathnameOfImage2, that.pathnameOfImage2)
                && Objects.equals(briefingText, that.briefingText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                missionTitle,
                missionFullname,
                pathnameOfBlock,
                pathnameOfPoint,
                skyType,
                extraCollision,
                darkScreen,
                pathnameOfObj,
                pathnameOfImage1,
                pathnameOfImage2,
                briefingText
        );
    }

    public MissionInfo setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
        return this;
    }

    public MissionInfo setMissionFullname(String missionFullname) {
        this.missionFullname = missionFullname;
        return this;
    }

    public MissionInfo setPathnameOfBlock(String pathnameOfBlock) {
        this.pathnameOfBlock = pathnameOfBlock;
        return this;
    }

    public MissionInfo setPathnameOfPoint(String pathnameOfPoint) {
        this.pathnameOfPoint = pathnameOfPoint;
        return this;
    }

    public MissionInfo setSkyType(SkyType skyType) {
        this.skyType = skyType;
        return this;
    }

    public MissionInfo setExtraCollision(boolean extraCollision) {
        this.extraCollision = extraCollision;
        return this;
    }

    public MissionInfo setDarkScreen(boolean darkScreen) {
        this.darkScreen = darkScreen;
        return this;
    }

    public MissionInfo setPathnameOfObj(String pathnameOfObj) {
        this.pathnameOfObj = pathnameOfObj;
        return this;
    }

    public MissionInfo setPathnameOfImage1(String pathnameOfImage1) {
        this.pathnameOfImage1 = pathnameOfImage1;
        return this;
    }

    public MissionInfo setPathnameOfImage2(String pathnameOfImage2) {
        this.pathnameOfImage2 = pathnameOfImage2;
        return this;
    }

    public MissionInfo setBriefingText(List<String> briefingText) {
        this.briefingText = briefingText;
        return this;
    }
}
