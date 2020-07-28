package com.github.dabasan.jxm.mif;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * MIF writer
 * 
 * @author Daba
 *
 */
class MIFWriter {
	public MIFWriter() {

	}

	public void write(OutputStream os, String encoding, MissionInfo missionInfo)
			throws IOException {
		var lines = new ArrayList<String>();

		lines.add(missionInfo.getMissionTitle());
		lines.add(missionInfo.getMissionFullname());
		lines.add(missionInfo.getPathnameOfBlock());
		lines.add(missionInfo.getPathnameOfPoint());
		lines.add(String.valueOf(missionInfo.getSkyImage().ordinal()));

		int flags = 0;
		boolean extraCollision = missionInfo.isExtraCollision();
		boolean darkScreen = missionInfo.isDarkScreen();
		if (extraCollision == true) {
			flags = flags | 0b00000001;
		}
		if (darkScreen == true) {
			flags = flags | 0b00000010;
		}
		lines.add(String.valueOf(flags));

		lines.add(missionInfo.getPathnameOfObj());
		lines.add(missionInfo.getPathnameOfImage1());
		lines.add(missionInfo.getPathnameOfImage2());

		List<String> briefingText = missionInfo.getBriefingText();
		for (var line : briefingText) {
			lines.add(line);
		}

		try (var bw = new BufferedWriter(new OutputStreamWriter(os, encoding))) {
			for (var line : lines) {
				bw.write(line);
				bw.newLine();
			}
		}
	}
}
