package com.github.dabasan.jxm.bd1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BD1 reader
 * 
 * @author Daba
 *
 */
class BD1Reader {
	private Map<Integer, String> textureFilenames;
	private List<BD1Block> blocks;

	public BD1Reader(String filename) throws IOException {
		textureFilenames = new HashMap<>();
		blocks = new ArrayList<>();

	}
}
