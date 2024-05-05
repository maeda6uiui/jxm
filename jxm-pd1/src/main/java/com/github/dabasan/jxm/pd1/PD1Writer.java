package com.github.dabasan.jxm.pd1;

import org.joml.Vector3fc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.github.dabasan.jxm.bintools.ByteFunctions.addFloatToBinLE;
import static com.github.dabasan.jxm.bintools.ByteFunctions.addUnsignedShortToBinLE;

/**
 * PD1 writer
 *
 * @author maeda6uiui
 */
class PD1Writer {
    public void write(OutputStream os, List<PD1Point> points) throws IOException {
        List<Byte> bin = new ArrayList<>();

        //Number of points
        int numPoints = points.size();
        addUnsignedShortToBinLE(bin, numPoints);

        //Point data
        for (int i = 0; i < numPoints; i++) {
            PD1Point point = points.get(i);

            //Position
            Vector3fc position = point.position;
            addFloatToBinLE(bin, position.x());
            addFloatToBinLE(bin, position.y());
            addFloatToBinLE(bin, position.z());

            //Rotation
            float rotation = point.rotation;
            addFloatToBinLE(bin, rotation);

            //Parameters
            int[] parameters = point.parameters;
            for (int j = 0; j < 4; j++) {
                bin.add((byte) parameters[j]);
            }
        }

        for (byte b : bin) {
            os.write(b);
        }
    }
}
