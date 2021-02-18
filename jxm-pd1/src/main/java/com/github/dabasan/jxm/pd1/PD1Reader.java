package com.github.dabasan.jxm.pd1;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

/**
 * PD1 reader
 * 
 * @author Daba
 *
 */
class PD1Reader {
	private List<PD1Point> points;

	public PD1Reader(InputStream is) throws IOException {
		points = new ArrayList<>();

		// Read all bytes from a stream
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0;

		// Number of points
		int numPoints = getUnsignedShortValueFromBinLE(bin, pos);
		pos += 2;

		// Points
		for (int i = 0; i < numPoints; i++) {
			var point = new PD1Point();

			// Point position
			float positionX = getFloatValueFromBinLE(bin, pos);
			pos += 4;
			float positionY = getFloatValueFromBinLE(bin, pos);
			pos += 4;
			float positionZ = getFloatValueFromBinLE(bin, pos);
			pos += 4;
			point.position = new Vector3f(positionX, positionY, positionZ);

			// Rotation
			float rotation = getFloatValueFromBinLE(bin, pos);
			pos += 4;
			point.rotation = rotation;

			// Parameters
			var parameters = new int[4];
			for (int j = 0; j < 4; j++) {
				parameters[j] = bin[pos];
				pos++;
			}

			point.parameters = parameters;

			points.add(point);
		}
	}

	public List<PD1Point> getPoints() {
		return points;
	}
}
