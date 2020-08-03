package com.github.dabasan.jxm.properties.util;

/**
 * Generates string representation of an array formatted for C++ code.
 * 
 * @author Daba
 *
 */
class CPPArrayStringGenerator {
	private String arrayName;

	public CPPArrayStringGenerator(String arrayName) {
		this.arrayName = arrayName;
	}

	public String generate(int index, String fieldName, Object value) {
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
