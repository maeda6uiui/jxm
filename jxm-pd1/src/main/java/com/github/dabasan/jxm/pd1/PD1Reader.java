package com.github.dabasan.jxm.pd1;

import static com.github.dabasan.jxm.bintools.ByteFunctions.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
			point.position.x = getFloatValueFromBinLE(bin, pos);
			pos += 4;
			point.position.y = getFloatValueFromBinLE(bin, pos);
			pos += 4;
			point.position.z = getFloatValueFromBinLE(bin, pos);
			pos += 4;

			// Rotation
			point.rotation = getFloatValueFromBinLE(bin, pos);
			pos += 4;

			// Parameters
			for (int j = 0; j < 4; j++) {
				point.parameters[j] = bin[pos];
				pos++;
			}

			points.add(point);
		}
	}

	public List<PD1Point> getPoints() {
		return points;
	}
}
