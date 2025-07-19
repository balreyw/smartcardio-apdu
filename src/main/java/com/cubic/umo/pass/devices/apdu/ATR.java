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
 * A Smart Card's answer-to-reset bytes. A Card's ATR object can be obtained
 * by calling getBytes.
 * This class does not attempt to verify that the ATR encodes a semantically
 * valid structure.
 */
public interface ATR {
    /**
     * Returns a copy of the bytes in this ATR.
     *
     * @return a copy of the bytes in this ATR.
     */
    byte[] getBytes();
}
