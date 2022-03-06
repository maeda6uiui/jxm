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
 */
class MIFWriter {
    public void write(OutputStream os, String encoding, MissionInfo missionInfo)
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
        if (extraCollision == true) {
            flags = flags | 0b00000001;
        }
        if (darkScreen == true) {
            flags = flags | 0b00000010;
        }
        lines.add(String.valueOf(flags));

        lines.add(missionInfo.pathnameOfObj);
        lines.add(missionInfo.pathnameOfImage1);
        lines.add(missionInfo.pathnameOfImage2);

        List<String> briefingText = missionInfo.briefingText;
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
