package com.github.dabasan.jxm.bd1;

/**
 * BD1 functions
 *
 * @author maeda6uiui
 */
class BD1Functions {
    public static int[] getFaceCorrespondingVertexIndices(int faceIndex) {
        return switch (faceIndex) {
            case 0 -> new int[]{0, 1, 2, 3};
            case 1 -> new int[]{5, 4, 7, 6};
            case 2 -> new int[]{1, 0, 4, 5};
            case 3 -> new int[]{2, 1, 5, 6};
            case 4 -> new int[]{3, 2, 6, 7};
            case 5 -> new int[]{0, 3, 7, 4};
            default -> throw new IllegalArgumentException(String.format("Unknown face index was given: %d", faceIndex));
        };
    }

    public static int[] getFaceCorrespondingUVIndices(int faceIndex) {
        return switch (faceIndex) {
            case 0 -> new int[]{3, 2, 1, 0};
            case 1 -> new int[]{7, 6, 5, 4};
            case 2 -> new int[]{9, 8, 11, 10};
            case 3 -> new int[]{13, 12, 15, 14};
            case 4 -> new int[]{17, 16, 19, 18};
            case 5 -> new int[]{21, 20, 23, 22};
            default -> throw new IllegalArgumentException(String.format("Unknown face index was given: %d", faceIndex));
        };
    }
}
