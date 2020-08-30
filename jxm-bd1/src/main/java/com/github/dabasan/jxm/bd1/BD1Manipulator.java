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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dabasan.ejml_3dtools.Matrix;
import com.github.dabasan.ejml_3dtools.Vector;

/**
 * BD1 manipulator
 * 
 * @author Daba
 *
 */
public class BD1Manipulator {
	private Logger logger = LoggerFactory.getLogger(BD1Manipulator.class);

	private List<BD1Block> blocks;
	private Map<Integer, String> textureFilenames;

	public BD1Manipulator() {
		blocks = new ArrayList<>();
		textureFilenames = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			textureFilenames.put(i, "");
		}
	}
	public BD1Manipulator(InputStream is) throws IOException {
		this.readConstructorBase(is);
	}
	public BD1Manipulator(File file) throws IOException {
		try (var fis = new FileInputStream(file)) {
			this.readConstructorBase(fis);
		}
	}
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
	 * @return Blocks
	 */
	public List<BD1Block> getBlocks() {
		return new ArrayList<>(blocks);
	}
	/**
	 * Sets blocks.
	 * 
	 * @param blocks
	 *            Blocks
	 */
	public void setBlocks(List<BD1Block> blocks) {
		if (blocks == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}

		this.blocks = blocks;
	}

	/**
	 * Returns the number of blocks.
	 * 
	 * @return Number of blocks
	 */
	public int getNumBlocks() {
		return blocks.size();
	}

	/**
	 * Returns the filepath of a texture.<br>
	 * Returns null if the texture specified does not exist.
	 * 
	 * @param textureID
	 *            Texture ID
	 * @return Texture filepath
	 */
	public String getTextureFilename(int textureID) {
		return textureFilenames.get(textureID);
	}
	/**
	 * Returns all filepaths of the textures.
	 * 
	 * @return All filepaths of the textures
	 */
	public Map<Integer, String> getTextureFilenames() {
		return new HashMap<>(textureFilenames);
	}

	/**
	 * Sets the filepath of a texture.
	 * 
	 * @param textureID
	 *            Texture ID
	 * @param textureFilename
	 *            Texture filepath
	 */
	public void setTextureFilename(int textureID, String textureFilename) {
		if (textureFilename == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}

		textureFilenames.put(textureID, textureFilename);
	}
	/**
	 * Sets the filepaths of the textures.
	 * 
	 * @param textureFilenames
	 *            Filenames of the textures
	 */
	public void setTextureFilenames(Map<Integer, String> textureFilenames) {
		if (textureFilenames == null) {
			logger.warn("Null argument where non-null required.");
			return;
		}

		this.textureFilenames = textureFilenames;
	}

	/**
	 * Transforms the blocks with a matrix.
	 * 
	 * @param mat
	 *            Matrix
	 * @return This instance
	 */
	public BD1Manipulator transform(Matrix mat) {
		for (var block : blocks) {
			Vector[] vertexPositions = block.getVertexPositions();
			for (int i = 0; i < vertexPositions.length; i++) {
				vertexPositions[i] = vertexPositions[i].transform(mat);
			}
		}

		return this;
	}

	/**
	 * Translates the blocks.
	 * 
	 * @param translationX
	 *            Translation X
	 * @param translationY
	 *            Translation Y
	 * @param translationZ
	 *            Translation Z
	 * @return This instance
	 */
	public BD1Manipulator translate(double translationX, double translationY, double translationZ) {
		var translationMat = Matrix.createTranslationMatrix(translationX, translationY,
				translationZ);
		this.transform(translationMat);

		return this;
	}

	/**
	 * Rotates the blocks around the X-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public BD1Manipulator rotX(double th) {
		var rotMat = Matrix.createRotationXMatrix(th);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the blocks around the Y-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public BD1Manipulator rotY(double th) {
		var rotMat = Matrix.createRotationYMatrix(th);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the blocks around the Z-axis.
	 * 
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public BD1Manipulator rotZ(double th) {
		var rotMat = Matrix.createRotationZMatrix(th);
		this.transform(rotMat);

		return this;
	}
	/**
	 * Rotates the blocks around an arbitrary axis.
	 * 
	 * @param axisX
	 *            X-component of the axis
	 * @param axisY
	 *            Y-component of the axis
	 * @param axisZ
	 *            Z-component of the axis
	 * @param th
	 *            Rotation angle (radian)
	 * @return This instance
	 */
	public BD1Manipulator rot(double axisX, double axisY, double axisZ, double th) {
		var rotMat = Matrix.createRotationMatrix(axisX, axisY, axisZ, th);
		this.transform(rotMat);

		return this;
	}

	/**
	 * Rescales the blocks.
	 * 
	 * @param scaleX
	 *            Scale X
	 * @param scaleY
	 *            Scale Y
	 * @param scaleZ
	 *            Scale Z
	 * @return This instance
	 */
	public BD1Manipulator rescale(double scaleX, double scaleY, double scaleZ) {
		var scaleMat = Matrix.createScalingMatrix(scaleX, scaleY, scaleZ);
		this.transform(scaleMat);

		return this;
	}

	/**
	 * Inverts the level with respect to the Z-axis.
	 * 
	 * @return This instance
	 */
	public BD1Manipulator invertZ() {
		for (var block : blocks) {
			Vector[] vertexPositions = block.getVertexPositions();
			for (int i = 0; i < 8; i++) {
				vertexPositions[i].setZ(vertexPositions[i].getZ() * (-1.0));
			}
		}

		for (var block : blocks) {
			// Vertex positions
			Vector[] vertexPositions = block.getVertexPositions();

			// Copy the original vertex positions.
			var origVertexPositions = new Vector[8];
			for (int i = 0; i < 8; i++) {
				origVertexPositions[i] = new Vector(vertexPositions[i]);
			}

			// Reverse the order of the vertices.
			var newVertexPositions = new Vector[8];
			for (int i = 0; i < 4; i++) {
				newVertexPositions[i] = origVertexPositions[3 - i];
			}
			for (int i = 0; i < 4; i++) {
				newVertexPositions[i + 4] = origVertexPositions[7 - i];
			}

			block.setVertexPositions(newVertexPositions);

			// UVs
			UV[] uvs = block.getUVs();

			// Copy the original UVs.
			var origUVs = new UV[24];
			for (int i = 0; i < 24; i++) {
				origUVs[i] = new UV(uvs[i]);
			}

			// Arrange UVs.
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

			block.setUVs(newUVs);

			// Arrange texture IDs.
			int[] textureIDs = block.getTextureIDs();
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

			block.setTextureIDs(newTextureIDs);
		}

		return this;
	}

	private void saveAsBD1Base(OutputStream os) throws IOException {
		var writer = new BD1Writer();
		writer.write(os, blocks, textureFilenames);
	}
	/**
	 * Saves the blocks as a BD1 file.
	 * 
	 * @param os
	 *            OutputStream
	 * @return -1: error 0: success
	 */
	public int saveAsBD1(OutputStream os) {
		int ret = 0;

		try {
			this.saveAsBD1Base(os);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as a BD1 file.
	 * 
	 * @param file
	 *            File
	 * @return -1: error 0: success
	 */
	public int saveAsBD1(File file) {
		int ret = 0;

		try (var fos = new FileOutputStream(file)) {
			this.saveAsBD1Base(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as a BD1 file.
	 * 
	 * @param filepath
	 *            Filepath
	 * @return -1: error 0: success
	 */
	public int saveAsBD1(String filepath) {
		int ret = 0;

		try (var fos = new FileOutputStream(filepath)) {
			this.saveAsBD1Base(fos);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}

	private void saveAsOBJBase(OutputStream osObj, OutputStream osMtl, String mtlFilename,
			boolean flipV) throws IOException {
		BD1OBJWriter.write(osObj, osMtl, mtlFilename, blocks, textureFilenames, flipV);
	}
	/**
	 * Saves the blocks as an OBJ file.
	 * 
	 * @param osObj
	 *            OutputStream for the OBJ file
	 * @param osMtl
	 *            OutputStream for the MTL file
	 * @param mtlFilename
	 *            Filename of the MTL file
	 * @param flipV
	 *            Flips texture V-coordinate if true
	 * @return -1: error 0: success
	 */
	public int saveAsOBJ(OutputStream osObj, OutputStream osMtl, String mtlFilename,
			boolean flipV) {
		int ret = 0;

		try {
			this.saveAsOBJBase(osObj, osMtl, mtlFilename, flipV);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as an OBJ file.
	 * 
	 * @param fileObj
	 *            File for the OBJ file
	 * @param fileMtl
	 *            File for the MTL file
	 * @param mtlFilename
	 *            Filename of the MTL file
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
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}
	/**
	 * Saves the blocks as an OBJ file.
	 * 
	 * @param filepathObj
	 *            Filepath of the OBJ file
	 * @param filepathMtl
	 *            Filepath of the MTL file
	 * @param mtlFilename
	 *            Filename of the MTL file
	 * @param flipV
	 *            Flips texture V-coordinate if true
	 * @return -1: error 0: success
	 */
	public int saveAsOBJ(String filepathObj, String filepathMtl, String mtlFilename,
			boolean flipV) {
		int ret = 0;

		try (var fosObj = new FileOutputStream(filepathObj);
				var fosMtl = new FileOutputStream(filepathMtl)) {
			this.saveAsOBJBase(fosObj, fosMtl, mtlFilename, flipV);
		} catch (IOException e) {
			logger.error("Failed to write.", e);
			ret = -1;
		}

		return ret;
	}

	/**
	 * Returns buffer representation of BD1 blocks.<br>
	 * May be useful when rendering blocks with OpenGL.
	 * 
	 * @param flipV
	 *            Flips texture V-coordinate if true
	 * 
	 * @return List containing buffers
	 */
	public List<BD1Buffer> getBuffers(boolean flipV) {
		Map<Integer, List<BD1Face>> faces = BD1FaceGenerator.generateFaces(blocks);
		List<BD1Buffer> buffers = BD1BufferGenerator.generateBuffers(faces, flipV);

		return buffers;
	}
}
