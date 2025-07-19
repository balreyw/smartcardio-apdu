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
 * A logical channel connection to a Smart Card. It is used to exchange APDUs
 * with a Smart Card.
 * A CardChannel object can be obtained by calling the method
 * {getBasicChannel}.
 *
 * @see Card
 * @see CommandAPDU
 * @see ResponseAPDU
 */
public interface CardChannel {
    /**
     * Transmits the specified command APDU to the Smart Card and returns the
     * response APDU.
     *
     * <p>The CLA byte of the command APDU is automatically adjusted to
     * match the channel number of this CardChannel.
     *
     * <p>Implementations should transparently handle artifacts
     * of the transmission protocol.
     * For example, when using the T=0 protocol, the following processing
     * should occur as described in ISO/IEC 7816-4:
     *
     * <ul>
     * <li><p>if the response APDU has an SW1 of <code>61</code>, the
     * implementation should issue a <code>GET RESPONSE</code> command
     * using <code>SW2</code> as the <code>Le</code>field.
     * This process is repeated as long as an SW1 of <code>61</code> is
     * received. The response body of these exchanges is concatenated
     * to form the final response body.
     *
     * <li><p>if the response APDU is <code>6C XX</code>, the implementation
     * should reissue the command using <code>XX</code> as the
     * <code>Le</code> field.
     * </ul>
     *
     * <p>The ResponseAPDU returned by this method is the result
     * after this processing has been performed.
     *
     * @param command
     *         the command APDU
     * @return the response APDU received from the card
     * @throws CardException
     *         if the card operation failed
     */
    ResponseAPDU transmit(CommandAPDU command) throws CardException;
}
