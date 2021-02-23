package com.github.dabasan.jxm.bd1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BD1 manipulator
 * 
 * @author Daba
 *
 */
public class BD1Manipulator {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private List<BD1Block> blocks;
	private Map<Integer, String> textureFilenames;

	/**
	 * Creates a BD1 manipulator.
	 */
	public BD1Manipulator() {
		blocks = new ArrayList<>();
		textureFilenames = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			textureFilenames.put(i, "");
		}
	}
	/**
	 * Creates a BD1 manipulator and loads a BD1.
	 * 
	 * @param is
	 *            input stream to load a BD1 from
	 * @throws IOException
	 *             if it fails to load
	 */
	public BD1Manipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	/**
	 * Creates a BD1 manipulator and loads a BD1.
	 * 
	 * @param file
	 *            file to load a BD1 from
	 * @throws IOException
	 *             if it fails to load
	 */
	public BD1Manipulator(File file) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis);
		}
	}
	/**
	 * Creates a BD1 manipulator and loads a BD1.
	 * 
	 * @param filepath
	 *            filepath to load a BD1 from
	 * @throws IOException
	 *             if it fails to load
	 */
	public BD1Manipulator(String filepath) throws IOException {
		try (var fis = new FileInputStream(filepath)) {
			this.readConstructorBase(fis);
		}
	}
	private void readConstructorBase(InputStream is) throws IOException {
		var reader = new BD1Reader(is);

		blocks = reader.getBlocks();
		textureFilenames = reader.getTextureFilenames();
	}

	/**
	 * Returns blocks.
	 * 
	 * @return blocks
	 */
	public List<BD1Block> getBlocks() {
		return new ArrayList<>(blocks);
	}
	/**
	 * Sets blocks.
	 * 
	 * @param blocks
	 *            blocks to set
	 */
	public void setBlocks(List<BD1Block> blocks) {
		if (blocks == null) {
			logger.warn("Null argument where non-null required");
			return;
		}

		this.blocks = blocks;
	}

	/**
	 * Returns the number of blocks.
	 * 
	 * @return number of blocks
	 */
	public int getNumBlocks() {
		return blocks.size();
	}

	/**
	 * Returns the filename of a texture. Returns null if the texture specified
	 * does not exist.
	 * 
	 * @param textureID
	 *            texture ID to set
	 * @return texture filename
	 */
	public String getTextureFilename(int textureID) {
		return textureFilenames.get(textureID);
	}
	/**
	 * Returns all filenames of the textures.
	 * 
	 * @return all filenames of the textures
	 */
	public Map<Integer, String> getTextureFilenames() {
		return new HashMap<>(textureFilenames);
	}

	/**
	 * Sets the filename of a texture.
	 * 
	 * @param textureID
	 *            texture ID
	 * @param textureFilename
	 *            texture filename
	 */
	public void setTextureFilename(int textureID, String textureFilename) {
		if (textureFilename == null) {
			logger.warn("Null argument where non-null required");
			return;
		}

		textureFilenames.put(textureID, textureFilename);
	}
	/**
	 * Sets the filenames of the textures.
	 * 
	 * @param textureFilenames
	 *            filenames of the textures
	 */
	public void setTextureFilenames(Map<Integer, String> textureFilenames) {
		if (textureFilenames == null) {
			logger.warn("Null argument where non-null required");
			return;
		}

		this.textureFilenames = textureFilenames;
	}

	/**
	 * Transforms the blocks with a matrix.
	 * 
	 * @param mat
	 *            matrix for transformation
	 * @return this
	 */
	public BD1Manipulator transform(Matrix4fc mat) {
		for (var block : blocks) {
			Vector3f[] vertexPositions = block.vertexPositions;
			for (int i = 0; i < vertexPositions.length; i++) {
				vertexPositions[i] = mat.transformPosition(vertexPositions[i]);
			}

			// block.vertexPositions = vertexPositions;
		}

		return this;
	}

	/**
	 * Translates the blocks.
	 * 
	 * @param translationX
	 *            amount of translation along the X-axis
	 * @param translationY
	 *            amount of translation along the Y-axis
	 * @param translationZ
	 *            amount of translation along the Z-axis
	 * @return this
	 */
	public BD1Manipulator translate(float translationX, float translationY, float translationZ) {
		var translationMat = new Matrix4f().translate(translationX, translationY, translationZ);
		this.transform(translationMat);

		return this;
	}

	/**
	 * Rotates the blocks around the X-axis.
	 * 
	 * @param th
	 *            rotation angle in radian
	 * @return this
	 */
	public BD1Manipulator rotX(float th) {
		var rotMat = new Matrix4f().rotate(th, 1.0f, 0.0f, 0.0f);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the blocks around the Y-axis.
	 * 
	 * @param th
	 *            rotation angle in radian
	 * @return this
	 */
	public BD1Manipulator rotY(float th) {
		var rotMat = new Matrix4f().rotate(th, 0.0f, 1.0f, 0.0f);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the blocks around the Z-axis.
	 * 
	 * @param th
	 *            rotation angle in radian
	 * @return this
	 */
	public BD1Manipulator rotZ(float th) {
		var rotMat = new Matrix4f().rotate(th, 0.0f, 0.0f, 1.0f);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the blocks around an arbitrary axis.
	 * 
	 * @param th
	 *            rotation angle in radian
	 * @param axisX
	 *            X-component of the axis
	 * @param axisY
	 *            Y-component of the axis
	 * @param axisZ
	 *            Z-component of the axis
	 * @return this
	 */
	public BD1Manipulator rot(float th, float axisX, float axisY, float axisZ) {
		var rotMat = new Matrix4f().rotate(th, axisX, axisY, axisZ);
		this.transform(rotMat);

		return this;
	}

	/**
	 * Rescales the blocks.
	 * 
	 * @param scaleX
	 *            X-axis scale
	 * @param scaleY
	 *            Y-axis scale
	 * @param scaleZ
	 *            Z-axis scale
	 * @return this
	 */
	public BD1Manipulator rescale(float scaleX, float scaleY, float scaleZ) {
		var scaleMat = new Matrix4f().scale(scaleX, scaleY, scaleZ);
		this.transform(scaleMat);

		return this;
	}

	/**
	 * Inverts the level with respect to the Z-axis.
	 * 
	 * @return this
	 */
	public BD1Manipulator invertZ() {
		for (var block : blocks) {
			Vector3f[] vertexPositions = block.vertexPositions;
			for (int i = 0; i < 8; i++) {
				var vertexPosition = new Vector3f(vertexPositions[i].x(), vertexPositions[i].y(),
						vertexPositions[i].z() * (-1.0f));
				vertexPositions[i] = vertexPosition;
			}

			block.vertexPositions = vertexPositions;
		}

		for (var block : blocks) {
			// Vertex positions
			Vector3f[] vertexPositions = block.vertexPositions;

			// Copy original vertex positions
			var origVertexPositions = new Vector3f[8];
			for (int i = 0; i < 8; i++) {
				origVertexPositions[i] = new Vector3f(vertexPositions[i]);
			}

			// Reverse the order of the vertices
			var newVertexPositions = new Vector3f[8];
			for (int i = 0; i < 4; i++) {
				newVertexPositions[i] = origVertexPositions[3 - i];
			}
			for (int i = 0; i < 4; i++) {
				newVertexPositions[i + 4] = origVertexPositions[7 - i];
			}

			block.vertexPositions = newVertexPositions;

			// UVs
			UV[] uvs = block.uvs;

			// Copy original UV
			var origUVs = new UV[24];
			for (int i = 0; i < 24; i++) {
				origUVs[i] = new UV(uvs[i]);
			}

			// Arrange UV
			var newUVs = new UV[24];
			for (int i = 0; i < 6; i++) {
				int[] uvIndices;

				if (i == 2) {
					uvIndices = BD1Functions.getFaceCorrespondingUVIndices(4);
				} else if (i == 4) {
					uvIndices = BD1Functions.getFaceCorrespondingUVIndices(2);
				} else {
					uvIndices = BD1Functions.getFaceCorrespondingUVIndices(i);
				}

				for (int j = 0; j < 4; j++) {
					newUVs[i * 4 + j] = origUVs[uvIndices[j]];
				}
			}

			block.uvs = newUVs;

			// Arrange texture IDs
			int[] textureIDs = block.textureIDs;
			var origTextureIDs = textureIDs.clone();

			var newTextureIDs = new int[6];
			for (int i = 0; i < 6; i++) {
				if (i == 2) {
					newTextureIDs[i] = origTextureIDs[4];
				} else if (i == 4) {
					newTextureIDs[i] = origTextureIDs[2];
				} else {
					newTextureIDs[i] = origTextureIDs[i];
				}
			}

			block.textureIDs = newTextureIDs;
		}

		return this;
	}

	private void saveAsBD1Base(OutputStream os) throws IOException {
		var writer = new BD1Writer();
		writer.write(os, blocks, textureFilenames);
	}
	/**
	 * Saves the blocks as a BD1.
	 * 
	 * @param os
	 *            output stream to write the blocks to
	 * @return -1: error 0: success
	 */
	public int saveAsBD1(OutputStream os) {
		int ret = 0;

		try {
			this.saveAsBD1Base(os);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as a BD1.
	 * 
	 * @param file
	 *            file to write the blocks to
	 * @return -1: error 0: success
	 */
	public int saveAsBD1(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.saveAsBD1Base(fos);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as a BD1.
	 * 
	 * @param filepath
	 *            filepath to write the blocks to
	 * @return -1: error 0: success
	 */
	public int saveAsBD1(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.saveAsBD1Base(fos);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}

	private void saveAsOBJBase(OutputStream osObj, OutputStream osMtl, String mtlFilename,
			boolean flipV) throws IOException {
		BD1OBJWriter.write(osObj, osMtl, mtlFilename, blocks, textureFilenames, flipV);
	}
	/**
	 * Saves the blocks as an OBJ.
	 * 
	 * @param osObj
	 *            output stream for OBJ
	 * @param osMtl
	 *            output stream for MTL
	 * @param mtlFilename
	 *            filename of the MTL
	 * @param flipV
	 *            flips texture V-coordinate if true
	 * @return -1: error 0: success
	 */
	public int saveAsOBJ(OutputStream osObj, OutputStream osMtl, String mtlFilename,
			boolean flipV) {
		int ret = 0;

		try {
			this.saveAsOBJBase(osObj, osMtl, mtlFilename, flipV);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as an OBJ.
	 * 
	 * @param fileObj
	 *            file for the OBJ
	 * @param fileMtl
	 *            file for the MTL
	 * @param mtlFilename
	 *            filename of the MTL
	 * @param flipV
	 *            Flips texture V-coordinate if true
	 * @return -1: error 0: success
	 */
	public int saveAsOBJ(File fileObj, File fileMtl, String mtlFilename, boolean flipV) {
		int ret = 0;

		try (var fosObj = new FileOutputStream(fileObj);
				var fosMtl = new FileOutputStream(fileMtl)) {
			this.saveAsOBJBase(fosObj, fosMtl, mtlFilename, flipV);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as an OBJ.
	 * 
	 * @param filepathObj
	 *            filepath of the OBJ
	 * @param filepathMtl
	 *            filepath of the MTL
	 * @param mtlFilename
	 *            filename of the MTL
	 * @param flipV
	 *            flips texture V-coordinate if true
	 * @return -1: error 0: success
	 */
	public int saveAsOBJ(String filepathObj, String filepathMtl, String mtlFilename,
			boolean flipV) {
		int ret = 0;

		try (var fosObj = new FileOutputStream(filepathObj);
				var fosMtl = new FileOutputStream(filepathMtl)) {
			this.saveAsOBJBase(fosObj, fosMtl, mtlFilename, flipV);
		} catch (IOException e) {
			logger.error(e.toString());
			ret = -1;
		}

		return ret;
	}

	/**
	 * Returns buffer representation of BD1 blocks.
	 * 
	 * @param flipV
	 *            flips texture V-coordinate if true
	 * 
	 * @return list containing buffers
	 */
	public List<BD1Buffer> getBuffers(boolean flipV) {
		Map<Integer, List<BD1Face>> faces = BD1FaceGenerator.generateFaces(blocks);
		List<BD1Buffer> buffers = BD1BufferGenerator.generateBuffers(faces, flipV);

		return buffers;
	}
}
