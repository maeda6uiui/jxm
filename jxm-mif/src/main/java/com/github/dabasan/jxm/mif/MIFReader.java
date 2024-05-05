package com.github.dabasan.jxm.mif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * MIF reader
 *
 * @author maeda6uiui
 */
class MIFReader {
    private final MissionInfo missionInfo;

    public MIFReader(InputStream is, String encoding) throws IOException, NumberFormatException {
        missionInfo = new MissionInfo();

        //Read all lines from a file
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

        missionInfo.missionTitle = lines.get(0);
        missionInfo.missionFullname = lines.get(1);
        missionInfo.pathnameOfBlock = lines.get(2);
        missionInfo.pathnameOfPoint = lines.get(3);

        int skyTypeIndex = Integer.parseInt(lines.get(4));
        missionInfo.skyType = SkyType.values()[skyTypeIndex];

        int flags = Integer.parseInt(lines.get(5));
        missionInfo.darkScreen = (flags & 0b00000010) != 0;
        missionInfo.extraCollision = (flags & 0b00000001) != 0;

        missionInfo.pathnameOfObj = lines.get(6);
        missionInfo.pathnameOfImage1 = lines.get(7);
        missionInfo.pathnameOfImage2 = lines.get(8);

        var briefingText = new ArrayList<String>();
        for (int i = 9; i < lines.size(); i++) {
            briefingText.add(lines.get(i));
        }
        missionInfo.briefingText = briefingText;
    }

    public MissionInfo getMissionInfo() {
        return missionInfo;
    }
}
