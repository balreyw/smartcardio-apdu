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

package com.cubic.umo.pass.devices.reader.util.apdu;

import com.cubic.umo.pass.devices.apdu.ResponseAPDU;
import com.cubic.umo.pass.devices.smartcardio.apdu.ResponseAPDUImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for identifying bank cards based on APDU responses
 */
@Slf4j
public class BankCardIdentifier {

    // Known bank card AIDs (Application Identifiers)
    private static final String VISA_AID = "A0000000031010";
    private static final String MASTERCARD_AID = "A0000000041010";
    private static final String AMEX_AID_1 = "A000000025010402";
    private static final String AMEX_AID_2 = "A0000000291010";
    private static final String JCB_AID = "A0000000651010";
    private static final String DISCOVER_AID = "A0000001523010";
    private static final String INTERAC_AID = "A0000002771010";

    // FCI template tag
    private static final byte FCI_TEMPLATE = 0x6F;

    /**
     * Determines if the given APDU response indicates a bank card
     *
     * @param responseAPDU The response APDU to analyze
     * @return true if the response indicates a bank card, false otherwise
     */
    public boolean isBankCard(ResponseAPDU responseAPDU) {
        return isBankCardInternal(responseAPDU);
    }

    /**
     * Overloaded method to accept ResponseAPDUImpl directly
     *
     * @param responseAPDU The response APDU implementation to analyze
     * @return true if the response indicates a bank card, false otherwise
     */
    public boolean isBankCard(ResponseAPDUImpl responseAPDU) {
        return isBankCardInternal(responseAPDU);
    }

    private boolean isBankCardInternal(ResponseAPDU responseAPDU) {
        if (responseAPDU == null) {
            log.debug("Response APDU is null");
            return false;
        }

        try {
            byte[] data = responseAPDU.getData();
            if (data == null || data.length == 0) {
                log.debug("Response APDU data is null or empty");
                return false;
            }

            // Check if this is a valid FCI response (starts with 0x6F)
            if (data.length < 1 || (data[0] & 0xFF) != (FCI_TEMPLATE & 0xFF)) {
                log.debug("Response does not start with FCI template tag (0x6F)");
                return false;
            }

            // Convert data to hex string for AID matching
            String hexData = bytesToHex(data).toUpperCase();

            // Check for known bank card AIDs
            boolean isKnownBankCard = hexData.contains(VISA_AID) ||
                                    hexData.contains(MASTERCARD_AID) ||
                                    hexData.contains(AMEX_AID_1) ||
                                    hexData.contains(AMEX_AID_2) ||
                                    hexData.contains(JCB_AID) ||
                                    hexData.contains(DISCOVER_AID) ||
                                    hexData.contains(INTERAC_AID);

            if (isKnownBankCard) {
                log.debug("Detected known bank card AID in response");
                return true;
            }

            // Additional check: look for general payment system environment
            // Check for "2PAY.SYS.DDF01" which is common in bank card responses
            String paymentSystemIdentifier = "325041592E5359532E4444463031"; // "2PAY.SYS.DDF01" in hex
            if (hexData.contains(paymentSystemIdentifier)) {
                log.debug("Detected payment system environment identifier");
                return true;
            }

            log.debug("No bank card indicators found in response");
            return false;

        } catch (Exception e) {
            log.error("Exception while processing APDU response", e);
            return false;
        }
    }

    /**
     * Converts byte array to hex string
     *
     * @param bytes The byte array to convert
     * @return Hex string representation
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
