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
}
