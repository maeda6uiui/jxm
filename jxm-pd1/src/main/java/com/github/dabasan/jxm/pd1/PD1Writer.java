package com.github.dabasan.jxm.pd1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3fc;

import com.github.dabasan.jxm.bintools.ByteFunctions;

/**
 * PD1 writer
 * 
 * @author Daba
 *
 */
class PD1Writer {
	public PD1Writer() {

	}

	public void write(OutputStream os, List<PD1Point> points) throws IOException {
		List<Byte> bin = new ArrayList<>();

		// Number of points
		int numPoints = points.size();
		ByteFunctions.addUnsignedShortValueToBinLE(bin, numPoints);

		// Point data
		for (int i = 0; i < numPoints; i++) {
			PD1Point point = points.get(i);

			// Position
			Vector3fc position = point.getPosition();

			ByteFunctions.addFloatValueToBinLE(bin, position.x());
			ByteFunctions.addFloatValueToBinLE(bin, position.y());
			ByteFunctions.addFloatValueToBinLE(bin, position.z());

			// Rotation
			double rotation = point.getRotation();

			ByteFunctions.addFloatValueToBinLE(bin, (float) rotation);

			// Parameters
			int[] parameters = point.getParameters();

			for (int j = 0; j < 4; j++) {
				bin.add((byte) parameters[j]);
			}
		}

		try (var bos = new BufferedOutputStream(os)) {
			for (Byte b : bin) {
				bos.write(b);
			}
		}
	}
}
