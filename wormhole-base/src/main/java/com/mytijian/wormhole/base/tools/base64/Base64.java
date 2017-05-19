package com.mytijian.wormhole.base.tools.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64 {
	private static final Encoder encoder = new Base64Encoder();

	public static String toBase64String(byte[] data) throws IOException {
		return toBase64String(data, 0, data.length);
	}

	public static String toBase64String(byte[] data, int off, int length) throws IOException {
		byte[] encoded = encode(data, off, length);
		return fromByteArray(encoded);
	}

	/**
	 * Convert an array of 8 bit characters into a string.
	 *
	 * @param bytes
	 *            8 bit characters.
	 * @return resulting String.
	 */
	public static String fromByteArray(byte[] bytes) {
		return new String(asCharArray(bytes));
	}

	/**
	 * Do a simple conversion of an array of 8 bit characters into a string.
	 *
	 * @param bytes
	 *            8 bit characters.
	 * @return resulting String.
	 */
	public static char[] asCharArray(byte[] bytes) {
		char[] chars = new char[bytes.length];

		for (int i = 0; i != chars.length; i++) {
			chars[i] = (char) (bytes[i] & 0xff);
		}

		return chars;
	}

	/**
	 * encode the input data producing a base 64 encoded byte array.
	 *
	 * @return a byte array containing the base 64 encoded data.
	 */
	public static byte[] encode(byte[] data) throws IOException {
		return encode(data, 0, data.length);
	}

	/**
	 * encode the input data producing a base 64 encoded byte array.
	 *
	 * @return a byte array containing the base 64 encoded data.
	 */
	public static byte[] encode(byte[] data, int off, int length) throws IOException {
		int len = (length + 2) / 3 * 4;
		ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
		encoder.encode(data, off, length, bOut);

		return bOut.toByteArray();
	}

	/**
	 * Encode the byte data to base 64 writing it to the given output stream.
	 *
	 * @return the number of bytes produced.
	 */
	public static int encode(byte[] data, OutputStream out) throws IOException {
		return encoder.encode(data, 0, data.length, out);
	}

	/**
	 * Encode the byte data to base 64 writing it to the given output stream.
	 *
	 * @return the number of bytes produced.
	 */
	public static int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
		return encoder.encode(data, off, length, out);
	}

	/**
	 * decode the base 64 encoded input data. It is assumed the input data is
	 * valid.
	 *
	 * @return a byte array representing the decoded data.
	 */
	public static byte[] decode(byte[] data) throws IOException {
		int len = data.length / 4 * 3;
		ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
		encoder.decode(data, 0, data.length, bOut);

		return bOut.toByteArray();
	}

	/**
	 * decode the base 64 encoded String data - whitespace will be ignored.
	 *
	 * @return a byte array representing the decoded data.
	 */
	public static byte[] decode(String data) throws IOException {
		int len = data.length() / 4 * 3;
		ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
		encoder.decode(data, bOut);

		return bOut.toByteArray();
	}

	/**
	 * decode the base 64 encoded String data writing it to the given output
	 * stream, whitespace characters will be ignored.
	 *
	 * @return the number of bytes produced.
	 */
	public static int decode(String data, OutputStream out) throws IOException {
		return encoder.decode(data, out);
	}

}