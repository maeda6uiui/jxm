package com.github.dabasan.jxm.bd1;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

/**
 * BD1 manipulator
 *
 * @author maeda6uiui
 */
public class BD1Manipulator {
    private List<BD1Block> blocks;
    private Map<Integer, String> textureFilenames;

    private Matrix4f transformationMat;

    /**
     * Creates a BD1 manipulator.
     */
    public BD1Manipulator() {
        blocks = new ArrayList<>();
        textureFilenames = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            textureFilenames.put(i, "");
        }

        transformationMat = new Matrix4f().identity();
    }

    private void readConstructorBase(InputStream is) throws IOException {
        var reader = new BD1Reader(is);

        blocks = reader.getBlocks();
        textureFilenames = reader.getTextureFilenames();

        transformationMat = new Matrix4f().identity();
    }

    /**
     * Creates a BD1 manipulator and loads a BD1.
     *
     * @param is input stream to load a BD1 from
     * @throws IOException if it fails to load
     */
    public BD1Manipulator(InputStream is) throws IOException {
        this.readConstructorBase(is);
    }

    /**
     * Creates a BD1 manipulator and loads a BD1.
     *
     * @param file file to load a BD1 from
     * @throws IOException if it fails to load
     */
    public BD1Manipulator(File file) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(file))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Creates a BD1 manipulator and loads a BD1.
     *
     * @param filepath filepath to load a BD1 from
     * @throws IOException if it fails to load
     */
    public BD1Manipulator(String filepath) throws IOException {
        try (var bis = new BufferedInputStream(new FileInputStream(filepath))) {
            this.readConstructorBase(bis);
        }
    }

    /**
     * Returns blocks.
     *
     * @return blocks
     */
    public List<BD1Block> getBlocks() {
        return blocks;
    }

    /**
     * Sets blocks.
     *
     * @param blocks blocks to set
     */
    public void setBlocks(List<BD1Block> blocks) {
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
     * @param textureID texture ID to set
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
     * @param textureID       texture ID
     * @param textureFilename texture filename
     */
    public void setTextureFilename(int textureID, String textureFilename) {
        textureFilenames.put(textureID, textureFilename);
    }

    /**
     * Sets the filenames of the textures.
     *
     * @param textureFilenames filenames of the textures
     */
    public void setTextureFilenames(Map<Integer, String> textureFilenames) {
        this.textureFilenames = textureFilenames;
    }

    /**
     * Applies transformation.
     */
    public void applyTransformation() {
        blocks.forEach(block -> {
            var newVertexPositions = new ArrayList<Vector3f>();
            Arrays.asList(block.vertexPositions).forEach(p -> {
                var newVertexPosition = transformationMat.transformPosition(new Vector3f(p));
                newVertexPositions.add(newVertexPosition);
            });

            block.setVertexPositions(newVertexPositions.toArray(new Vector3f[]{}));
        });
    }

    /**
     * Resets transformation.
     */
    public void resetTransformation() {
        transformationMat = new Matrix4f().identity();
    }

    /**
     * Returns current transformation matrix.
     *
     * @return Transformation matrix
     */
    public Matrix4f getTransformationMat() {
        return transformationMat;
    }

    /**
     * Transforms the blocks with a matrix.
     *
     * @param mat matrix for transformation
     * @return this
     */
    public BD1Manipulator transform(Matrix4fc mat) {
        transformationMat = transformationMat.mul(mat);
        return this;
    }

    /**
     * Translates the blocks.
     *
     * @param translationX amount of translation along the X-axis
     * @param translationY amount of translation along the Y-axis
     * @param translationZ amount of translation along the Z-axis
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
     * @param th rotation angle in radian
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
     * @param th rotation angle in radian
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
     * @param th rotation angle in radian
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
     * @param th    rotation angle in radian
     * @param axisX X-component of the axis
     * @param axisY Y-component of the axis
     * @param axisZ Z-component of the axis
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
     * @param scaleX X-axis scale
     * @param scaleY Y-axis scale
     * @param scaleZ Z-axis scale
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
        var newBlocks = new ArrayList<BD1Block>();
        blocks.forEach(block -> newBlocks.add(new BD1Block(block)));

        newBlocks.forEach(block -> {
            //Flip z-coordinate
            var flippedVertexPositions = new Vector3f[8];
            for (int i = 0; i < 8; i++) {
                flippedVertexPositions[i] = new Vector3f(block.vertexPositions[i]).mul(new Vector3f(1.0f, 1.0f, -1.0f));
            }

            //Reverse the order of the vertices
            var reorderedVertexPositions = new Vector3f[8];
            for (int i = 0; i < 4; i++) {
                reorderedVertexPositions[i] = flippedVertexPositions[3 - i];
            }
            for (int i = 0; i < 4; i++) {
                reorderedVertexPositions[i + 4] = flippedVertexPositions[7 - i];
            }

            block.setVertexPositions(reorderedVertexPositions);

            //Arrange UVs
            var origUVs = new UV[24];
            for (int i = 0; i < 24; i++) {
                origUVs[i] = new UV(block.uvs[i]);
            }

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

            block.setUvs(newUVs);

            //Arrange texture IDs
            var origTextureIDs = block.textureIDs.clone();

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
        });

        this.blocks = newBlocks;

        return this;
    }

    /**
     * Saves the block data in a BD1 file.
     *
     * @param path Path of the output file
     * @throws IOException If it fails to write to the file
     */
    public void saveAsBD1(Path path) throws IOException {
        var writer = new BD1Writer();
        writer.write(path, blocks, textureFilenames);
    }

    private void saveAsOBJBase(
            OutputStream osObj, OutputStream osMtl, String mtlFilename, boolean flipV) throws IOException {
        BD1OBJWriter.write(osObj, osMtl, mtlFilename, blocks, textureFilenames, flipV);
    }

    /**
     * Saves the blocks as an OBJ.
     *
     * @param osObj       output stream for OBJ
     * @param osMtl       output stream for MTL
     * @param mtlFilename filename of the MTL
     * @param flipV       flips texture V-coordinate if true
     * @throws IOException if it fails to output
     */
    public void saveAsOBJ(
            OutputStream osObj, OutputStream osMtl, String mtlFilename, boolean flipV) throws IOException {
        this.saveAsOBJBase(osObj, osMtl, mtlFilename, flipV);
    }

    /**
     * Saves the blocks as an OBJ.
     *
     * @param fileObj     file for the OBJ
     * @param fileMtl     file for the MTL
     * @param mtlFilename filename of the MTL
     * @param flipV       Flips texture V-coordinate if true
     * @throws IOException if it fails to output
     */
    public void saveAsOBJ(File fileObj, File fileMtl, String mtlFilename, boolean flipV) throws IOException {
        try (var bosObj = new BufferedOutputStream(new FileOutputStream(fileObj));
             var bosMtl = new BufferedOutputStream(new FileOutputStream(fileMtl))) {
            this.saveAsOBJBase(bosObj, bosMtl, mtlFilename, flipV);
        }
    }

    /**
     * Saves the blocks as an OBJ.
     *
     * @param filepathObj filepath of the OBJ
     * @param filepathMtl filepath of the MTL
     * @param mtlFilename filename of the MTL
     * @param flipV       flips texture V-coordinate if true
     * @throws IOException if it fails to output
     */
    public void saveAsOBJ(
            String filepathObj, String filepathMtl, String mtlFilename, boolean flipV) throws IOException {
        try (var bosObj = new BufferedOutputStream(new FileOutputStream(filepathObj));
             var bosMtl = new BufferedOutputStream(new FileOutputStream(filepathMtl))) {
            this.saveAsOBJBase(bosObj, bosMtl, mtlFilename, flipV);
        }
    }

    /**
     * Returns buffer representation of BD1 blocks.
     *
     * @param flipV flips texture V-coordinate if true
     * @return list containing buffers
     */
    public List<BD1Buffer> getBuffers(boolean flipV) {
        Map<Integer, List<BD1Face>> faces = BD1FaceGenerator.generateFaces(blocks);
        List<BD1Buffer> buffers = BD1BufferGenerator.generateBuffers(faces, flipV);

        return buffers;
    }
}
