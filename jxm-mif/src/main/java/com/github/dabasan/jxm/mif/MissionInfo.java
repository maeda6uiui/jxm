package com.github.dabasan.jxm.mif;

import java.util.ArrayList;
import java.util.List;

/**
 * Mission info
 * 
 * @author Daba
 *
 */
public class MissionInfo {
	private String missionTitle;
	private String missionFullname;
	private String pathnameOfBlock;
	private String pathnameOfPoint;
	private SkyType skyType;
	private boolean extraCollision;
	private boolean darkScreen;
	private String pathnameOfObj;
	private String pathnameOfImage1;
	private String pathnameOfImage2;
	private List<String> briefingText;// per line

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

	@Override
	public String toString() {
		return "MissionInfo [missionTitle=" + missionTitle + ", missionFullname=" + missionFullname
				+ ", pathnameOfBlock=" + pathnameOfBlock + ", pathnameOfPoint=" + pathnameOfPoint
				+ ", skyType=" + skyType + ", extraCollision=" + extraCollision + ", darkScreen="
				+ darkScreen + ", pathnameOfObj=" + pathnameOfObj + ", pathnameOfImage1="
				+ pathnameOfImage1 + ", pathnameOfImage2=" + pathnameOfImage2 + ", briefingText="
				+ briefingText + "]";
	}

	public String getMissionTitle() {
		return missionTitle;
	}

	public String getMissionFullname() {
		return missionFullname;
	}
	public String getPathnameOfBlock() {
		return pathnameOfBlock;
	}
	public String getPathnameOfPoint() {
		return pathnameOfPoint;
	}
	public SkyType getSkyType() {
		return skyType;
	}
	public boolean isExtraCollision() {
		return extraCollision;
	}
	public boolean isDarkScreen() {
		return darkScreen;
	}
	public String getPathnameOfObj() {
		return pathnameOfObj;
	}
	public String getPathnameOfImage1() {
		return pathnameOfImage1;
	}
	public String getPathnameOfImage2() {
		return pathnameOfImage2;
	}
	public List<String> getBriefingText() {
		return new ArrayList<>(briefingText);
	}

	public void setMissionTitle(String missionTitle) {
		this.missionTitle = missionTitle;
	}
	public void setMissionFullname(String missionFullname) {
		this.missionFullname = missionFullname;
	}
	public void setPathnameOfBlock(String pathnameOfBlock) {
		this.pathnameOfBlock = pathnameOfBlock;
	}
	public void setPathnameOfPoint(String pathnameOfPoint) {
		this.pathnameOfPoint = pathnameOfPoint;
	}
	public void setSkyType(SkyType skyType) {
		this.skyType = skyType;
	}
	public void setExtraCollision(boolean extraCollision) {
		this.extraCollision = extraCollision;
	}
	public void setDarkScreen(boolean darkScreen) {
		this.darkScreen = darkScreen;
	}
	public void setPathnameOfObj(String pathnameOfObj) {
		this.pathnameOfObj = pathnameOfObj;
	}
	public void setPathnameOfImage1(String pathnameOfImage1) {
		this.pathnameOfImage1 = pathnameOfImage1;
	}
	public void setPathnameOfImage2(String pathnameOfImage2) {
		this.pathnameOfImage2 = pathnameOfImage2;
	}
	public void setBriefingText(List<String> briefingText) {
		if (briefingText == null) {
			return;
		}
		this.briefingText = briefingText;
	}
}
