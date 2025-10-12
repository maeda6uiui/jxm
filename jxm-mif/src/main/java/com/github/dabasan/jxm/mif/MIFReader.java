package com.github.dabasan.jxm.mif;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * MIF reader
 *
 * @author maeda6uiui
 */
class MIFReader {
    private final MissionInfo missionInfo;

    public MIFReader(Path path, String encoding) throws IOException, NumberFormatException {
        List<String> lines = Files.readAllLines(path, Charset.forName(encoding));

        missionInfo = new MissionInfo();
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
