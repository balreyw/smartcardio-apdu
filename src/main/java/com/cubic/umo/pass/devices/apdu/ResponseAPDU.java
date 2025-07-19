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
 * A response APDU as defined in ISO/IEC 7816-4. It consists of a conditional
 * body and a two byte trailer.
 * This class does not attempt to verify that the APDU encodes a semantically
 * valid response.
 *
 * <p>Instances of this class are immutable. Where data is passed in or out
 * via byte arrays, defensive cloning is performed.
 *
 * @see CommandAPDU
 * @see CardChannel#transmit CardChannel.transmit
 */
public interface ResponseAPDU {

    /**
     * Returns the value of the status bytes SW1 and SW2 as a single
     * status word SW.
     * It is defined as
     * {@code (getSW1() << 8) | getSW2()}
     *
     * @return the value of the status word SW.
     */
    int getSW();

    /**
     * Returns the value of the status byte SW1 as a value between 0 and 255.
     *
     * @return the value of the status byte SW1 as a value between 0 and 255.
     */
    int getSW1();

    /**
     * Returns the value of the status byte SW2 as a value between 0 and 255.
     *
     * @return the value of the status byte SW2 as a value between 0 and 255.
     */
    int getSW2();

    /**
     * Returns a copy of the data bytes in the response body. If this APDU as
     * no body, this method returns a byte array with a length of zero.
     *
     * @return a copy of the data bytes in the response body or the empty
     *         byte array if this APDU has no body.
     */
    byte[] getData();

    /**
     * Returns a copy of the bytes in this APDU.
     *
     * @return a copy of the bytes in this APDU.
     */
    byte[] getBytes();

    /**
     * Returns the number of data bytes in the response body (Nr) or 0 if this
     * APDU has no body. This call is equivalent to
     * <code>getData().length</code>.
     *
     * @return the number of data bytes in the response body or 0 if this APDU
     *         has no body.
     */
    int getNr();
}
