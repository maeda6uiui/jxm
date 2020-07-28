package com.github.dabasan.jxm.pd1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.dabasan.ejml_3dtools.Vector;
import com.github.dabasan.jxm.bintools.ByteFunctions;

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

		// Read all bytes from a stream.
		byte[] bin;
		try (var bis = new BufferedInputStream(is)) {
			bin = bis.readAllBytes();
		}

		int pos = 0;

		// Number of points
		int numPoints = ByteFunctions.getUnsignedShortValueFromBinLE(bin, pos);
		pos += 2;

		// Points
		for (int i = 0; i < numPoints; i++) {
			var point = new PD1Point();

			// Point position
			float positionX = ByteFunctions.getFloatValueFromBinLE(bin, pos);
			pos += 4;

			float positionY = ByteFunctions.getFloatValueFromBinLE(bin, pos);
			pos += 4;

			float positionZ = ByteFunctions.getFloatValueFromBinLE(bin, pos);
			pos += 4;

			point.setPosition(new Vector(positionX, positionY, positionZ));

			// Rotation
			float rotation = ByteFunctions.getFloatValueFromBinLE(bin, pos);
			pos += 4;

			point.setRotation(rotation);

			// Parameters
			var parameters = new int[4];
			for (int j = 0; j < 4; j++) {
				parameters[j] = bin[pos];
				pos++;
			}

			point.setParameters(parameters);

			points.add(point);
		}
	}

	public List<PD1Point> getPoints() {
		return points;
	}
}
