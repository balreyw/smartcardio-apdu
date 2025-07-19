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

package com.cubic.umo.pass.devices.smartcardio;

import com.cubic.umo.pass.devices.apdu.ATR;

/**
 * A Smart Card's answer-to-reset bytes. A Card's ATR object can be obtained
 * by calling getBytes.
 * This class does not attempt to verify that the ATR encodes a semantically
 * valid structure.
 */
public class ATRImpl implements ATR {
    private final javax.smartcardio.ATR atr;

    /**
     * Constructor for ATRImpl
     * @param atr the underlying smartcardio ATR
     */
    public ATRImpl(javax.smartcardio.ATR atr) {
        this.atr = atr;
    }

    /**
     * Returns a copy of the bytes in this ATR.
     *
     * @return a copy of the bytes in this ATR.
     */
    @Override
    public byte[] getBytes() {
        return atr.getBytes();
    }
}
