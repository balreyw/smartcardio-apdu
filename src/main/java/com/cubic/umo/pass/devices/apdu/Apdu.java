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

package com.cubic.umo.pass.devices.apdu;

/**
 * The interface to send APDU commands.
 */
public interface Apdu {
    /**
     * Sends the apdu command to card reader.
     *
     * @param apdu
     *         the apdu command
     * @return the card reader response
     */
    ResponseAPDU sendApdu(byte[] apdu);
}
