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

package com.cubic.umo.pass.devices.smartcardio.apdu;

import com.cubic.umo.pass.devices.apdu.CardChannel;
import com.cubic.umo.pass.devices.apdu.CommandAPDU;
import com.cubic.umo.pass.devices.apdu.ResponseAPDU;

/**
 * A command APDU following the structure defined in ISO/IEC 7816-4.
 * It consists of a four byte header and a conditional body of variable length.
 * This class does not attempt to verify that the APDU encodes a semantically
 * valid command.
 *
 * <p>Instances of this class are immutable. Where data is passed in or out
 * via byte arrays, defensive cloning is performed.
 *
 * @see ResponseAPDU
 * @see CardChannel#transmit CardChannel.transmit
 */
public class CommandAPDUImpl implements CommandAPDU {
    private javax.smartcardio.CommandAPDU commandAPDU;

    /**
     * Constructs a new CommandAPDU from a byte array.
     * The byte array is cloned to protect against subsequent modification.
     *
     * @param apdu
     *         the byte array containing the complete APDU.
     * @throws NullPointerException
     *         if apdu is null
     * @throws IllegalArgumentException
     *         if the length of the byte array is less than 4
     */
    @Override
    public CommandAPDU createAPDU(byte[] apdu) {
        commandAPDU = new javax.smartcardio.CommandAPDU(apdu);
        return this;
    }

    /**
     * Returns the APDU as a byte array.
     * The byte array is cloned to protect against subsequent modification.
     *
     * @return the byte array containing the complete APDU
     */
    @Override
    public byte[] getBytes() {
        return commandAPDU.getBytes();
    }

    /**
     * Returns the instruction byte of the APDU.
     *
     * @return the instruction byte of the APDU
     */
    @Override
    public int getINS() {
        return commandAPDU.getINS();
    }
}
