package com.github.dabasan.jxm.properties.util;

/**
 * Generates string representation of an array formatted for C++ code.
 * 
 * @author Daba
 *
 */
public class CPPArrayStringGenerator {
	/**
	 * Generates a string from an array.
	 * 
	 * @param arrayName
	 *            Name of the array
	 * @param index
	 *            Index
	 * @param fieldName
	 *            Name of the field
	 * @param value
	 *            Value
	 * @return Generated string
	 */
	public static String generate(String arrayName, int index, String fieldName, Object value) {
		var sb = new StringBuilder();

		sb.append(arrayName);
		sb.append("[");
		sb.append(index);
		sb.append("].");
		sb.append(fieldName);
		sb.append("=");

		if (value instanceof String) {
			sb.append("\"");
			sb.append(value.toString());
			sb.append("\"");
		} else {
			sb.append(value.toString());
		}

		sb.append(";");

		return sb.toString();
	}
}
