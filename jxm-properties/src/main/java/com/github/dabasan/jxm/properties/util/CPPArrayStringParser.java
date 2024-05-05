package com.github.dabasan.jxm.properties.util;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Parses string representation of an array formatted for C++ code.
 *
 * @author maeda6uiui
 */
public class CPPArrayStringParser {
    /**
     * Parses a string and returns its value as a string. Returns null if the
     * string specified has an illegal format.<br>
     * <br>
     * Format of the returned string array<br>
     * 0: Array name<br>
     * 1: Index<br>
     * 2: Field name<br>
     * 3: Value<br>
     * <br>
     * Example<br>
     * Input: members[3].name="Daba";<br>
     * Output: [members,3,name,Daba]
     *
     * @param line line
     * @return string array
     */
    public static String[] parse(String line) {
        //e.g. members[3].name = "Daba";
        String[] split = line.split("=");
        if (split.length < 2) {
            return null;
        }

        //e.g. members[3].name
        String lhsString = split[0];
        String[] lhsSplitByDot = lhsString.split(Pattern.quote("."));
        if (lhsSplitByDot.length != 2) {
            return null;
        }

        //e.g. members[3]
        String arrayNameAndIndex = lhsSplitByDot[0].trim();
        int firstIndexOfLeftSquareBracket = arrayNameAndIndex.indexOf('[');
        if (firstIndexOfLeftSquareBracket == -1) {
            return null;
        }
        int lastIndexOfRightSquareBracket = arrayNameAndIndex.lastIndexOf(']');
        if (lastIndexOfRightSquareBracket == -1) {
            return null;
        }

        //e.g. members
        String arrayName = arrayNameAndIndex.substring(0, firstIndexOfLeftSquareBracket).trim();
        //e.g. 3
        String indexString = arrayNameAndIndex
                .substring(firstIndexOfLeftSquareBracket + 1, lastIndexOfRightSquareBracket).trim();

        //e.g. name
        String fieldName = lhsSplitByDot[1].trim();

        //e.g. [ "Daba"; ]
        String[] rhsStrings = Arrays.copyOfRange(split, 1, split.length);

        //Concatenate strings.
        //e.g. "Daba";
        String valueString = "";
        for (var rhsString : rhsStrings) {
            valueString += rhsString;
        }

        //Remove comment
        int indexOfSemicolon = valueString.lastIndexOf(";");
        int indexOfSlashes = valueString.lastIndexOf("//");
        if (indexOfSemicolon != -1 && indexOfSemicolon < indexOfSlashes) {
            valueString = valueString.substring(0, indexOfSemicolon + 1);
        }

        //Remove leading and trailing spaces
        //e.g. "Daba";
        valueString = valueString.trim();

        //Error in case this string does not have a semicolon at the end of the
        //line
        char lastChar = valueString.charAt(valueString.length() - 1);
        if (lastChar != ';') {
            return null;
        }

        //Remove semicolon
        //e.g. "Daba"
        valueString = valueString.substring(0, valueString.length() - 1);

        //Remove quotation marks in case this is a string value
        //e.g. Daba
        if (valueString.charAt(0) == '\"' && valueString.charAt(valueString.length() - 1) == '\"') {
            valueString = valueString.substring(1, valueString.length() - 1);
        } else if (valueString.charAt(0) == '\"') {
            return null;
        } else if (valueString.charAt(valueString.length() - 1) == '\"') {
            return null;
        }

        return new String[]{arrayName, indexString, fieldName, valueString};
    }
}
