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
	 *            name of the array
	 * @param index
	 *            index
	 * @param fieldName
	 *            name of the field
	 * @param value
	 *            Value
	 * @return generated string
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
		} else if (value instanceof Float) {
			sb.append(value.toString());
			sb.append("f");
		} else {
			sb.append(value.toString());
		}

		sb.append(";");

		return sb.toString();
	}
}
