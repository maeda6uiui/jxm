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
	private SkyImage skyImage;
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
		skyImage = SkyImage.NONE;
		extraCollision = false;
		darkScreen = false;
		pathnameOfObj = "!";
		pathnameOfImage1 = "./data/briefing/np.bmp";
		pathnameOfImage2 = "!";
		briefingText = new ArrayList<>();
	}

	/*
	@Override
	public String toString() {
		var sb = new StringBuilder();
	
		// Mission title
		sb.append("missionTitle=");
		sb.append(missionTitle);
		sb.append("\n");
		// Mission fullname
		sb.append("missionFullname=");
		sb.append(missionFullname);
		sb.append("\n");
		// Pathname of block
		sb.append("pathnameOfBlock=");
		sb.append(pathnameOfBlock);
		sb.append("\n");
		// Pathname of point
		sb.append("pathnameOfPoint=");
		sb.append(pathnameOfPoint);
		sb.append("\n");
		// Sky image
		sb.append("skyImage=");
		sb.append(skyImage.toString());
		sb.append("\n");
		// Extra collision
		sb.append("extraCollision=");
		sb.append(extraCollision);
		sb.append("\n");
		// Dark screen
		sb.append("darkScreen=");
		sb.append(darkScreen);
		sb.append("\n");
		// Pathname of obj
		sb.append("pathnameOfObj=");
		sb.append(pathnameOfObj);
		sb.append("\n");
		// Pathname of Image1
		sb.append("pathnameOfImage1=");
		sb.append(pathnameOfImage1);
		sb.append("\n");
		// Pathname of Image2
		sb.append("pathnameOfImage2=");
		sb.append(pathnameOfImage2);
		sb.append("\n");
	
		// Briefing text
		sb.append("briefingText=");
		sb.append("\n");
	
		for (var line : briefingText) {
			sb.append(line);
			sb.append("\n");
		}
	
		sb.setLength(sb.length() - 1);
	
		return sb.toString();
	}
	*/
	@Override
	public String toString() {
		return "MissionInfo [missionTitle=" + missionTitle + ", missionFullname=" + missionFullname
				+ ", pathnameOfBlock=" + pathnameOfBlock + ", pathnameOfPoint=" + pathnameOfPoint
				+ ", skyImage=" + skyImage + ", extraCollision=" + extraCollision + ", darkScreen="
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
	public SkyImage getSkyImage() {
		return skyImage;
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
	public void setSkyImage(SkyImage skyImage) {
		this.skyImage = skyImage;
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
