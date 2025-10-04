package com.github.dabasan.jxm.mif;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * MIF writer
 *
 * @author maeda6uiui
 */
class MIFWriter {
    public void write(Path path, String encoding, MissionInfo missionInfo)
            throws IOException {
        var lines = new ArrayList<String>();

        lines.add(missionInfo.missionTitle);
        lines.add(missionInfo.missionFullname);
        lines.add(missionInfo.pathnameOfBlock);
        lines.add(missionInfo.pathnameOfPoint);
        lines.add(String.valueOf(missionInfo.skyType.ordinal()));

        int flags = 0;
        boolean extraCollision = missionInfo.extraCollision;
        boolean darkScreen = missionInfo.darkScreen;
        if (extraCollision) {
            flags = flags | 0b00000001;
        }
        if (darkScreen) {
            flags = flags | 0b00000010;
        }
        lines.add(String.valueOf(flags));

        lines.add(missionInfo.pathnameOfObj);
        lines.add(missionInfo.pathnameOfImage1);
        lines.add(missionInfo.pathnameOfImage2);

        List<String> briefingText = missionInfo.briefingText;
        lines.addAll(briefingText);

        var sb = new StringBuilder();
        lines.forEach(line -> {
            sb.append(line);
            sb.append(System.lineSeparator());
        });
        Files.writeString(path, sb, Charset.forName(encoding));
    }
}
