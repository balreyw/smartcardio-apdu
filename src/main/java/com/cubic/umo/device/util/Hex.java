/*
 *
 *  COPYRIGHT - CUBIC TRANSPORTATION SYSTEMS ("CUBIC"). ALL RIGHTS RESERVED.
 *
 *  Information Contained Herein is Proprietary and Confidential.
 *  The document is the property of "CUBIC" and may not be disclosed
 *  distributed, or reproduced without the express written permission of
 *  "CUBIC".
 *
 */

package com.cubic.umo.device.util;

/**
 * Utility class for hexadecimal string operations
 */
public class Hex {

    /**
     * Decodes a hexadecimal string into a byte array
     *
     * @param hexString The hexadecimal string to decode (without spaces or separators)
     * @return The decoded byte array
     * @throws IllegalArgumentException if the hex string is invalid
     */
    public static byte[] decodeString(String hexString) {
        if (hexString == null) {
            throw new IllegalArgumentException("Hex string cannot be null");
        }

        // Remove any whitespace
        hexString = hexString.replaceAll("\\s+", "");

        // Check for valid length (must be even)
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length");
        }

        byte[] result = new byte[hexString.length() / 2];

        for (int i = 0; i < hexString.length(); i += 2) {
            String byteString = hexString.substring(i, i + 2);
            try {
                result[i / 2] = (byte) Integer.parseInt(byteString, 16);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid hex character in string: " + byteString, e);
            }
        }

        return result;
    }

    /**
     * Encodes a byte array into a hexadecimal string
     *
     * @param bytes The byte array to encode
     * @return The hexadecimal string representation (uppercase)
     */
    public static String encodeString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }

        return result.toString();
    }
}
