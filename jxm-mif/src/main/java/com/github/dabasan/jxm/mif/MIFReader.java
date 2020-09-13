package com.github.dabasan.jxm.mif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * MIF reader
 * 
 * @author Daba
 *
 */
class MIFReader {
	private MissionInfo missionInfo;

	public MIFReader(InputStream is, String encoding) throws IOException, NumberFormatException {
		missionInfo = new MissionInfo();

		// Read all lines from a file.
		var lines = new ArrayList<String>();
		try (var br = new BufferedReader(new InputStreamReader(is, encoding))) {
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				lines.add(line);
			}
		}

		missionInfo.setMissionTitle(lines.get(0));
		missionInfo.setMissionFullname(lines.get(1));
		missionInfo.setPathnameOfBlock(lines.get(2));
		missionInfo.setPathnameOfPoint(lines.get(3));

		int skyTypeIndex = Integer.parseInt(lines.get(4));
		var skyType = SkyType.values()[skyTypeIndex];
		missionInfo.setSkyType(skyType);

		int flags = Integer.parseInt(lines.get(5));
		if ((flags & 0b00000010) != 0) {
			missionInfo.setDarkScreen(true);
		} else {
			missionInfo.setDarkScreen(false);
		}
		if ((flags & 0b00000001) != 0) {
			missionInfo.setExtraCollision(true);
		} else {
			missionInfo.setExtraCollision(false);
		}

		missionInfo.setPathnameOfObj(lines.get(6));
		missionInfo.setPathnameOfImage1(lines.get(7));
		missionInfo.setPathnameOfImage2(lines.get(8));

		var briefingText = new ArrayList<String>();
		for (int i = 9; i < lines.size(); i++) {
			briefingText.add(lines.get(i));
		}
		missionInfo.setBriefingText(briefingText);
	}

	public MissionInfo getMissionInfo() {
		return missionInfo;
	}
}
