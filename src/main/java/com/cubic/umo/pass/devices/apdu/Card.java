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
 * A Smart Card with which a connection has been established. Card objects
 * are obtained by calling CardTerminal#connect CardTerminal.connect()}.
 *
 * @see CardTerminal
 */
public interface Card {
    /**
     * Requests exclusive access to this card.
     *
     * <p>Once a thread has invoked <code>beginExclusive</code>, only this
     * thread is allowed to communicate with this card until it calls
     * <code>endExclusive</code>. Other threads attempting communication
     * will receive a CardException.
     *
     * <p>Applications have to ensure that exclusive access is correctly
     * released. This can be achieved by executing
     * the <code>beginExclusive()</code> and <code>endExclusive</code> calls
     * in a <code>try ... finally</code> block.
     *
     * @throws com.cubic.umo.pass.devices.apdu.CardException
     *         if exclusive access has already been set
     *         or if exclusive access could not be established
     * @throws IllegalStateException
     *         if this card object has been disposed of
     *         via the {@linkplain #disconnect disconnect()} method
     */
    void beginExclusive() throws CardException;

    /**
     * Returns the CardChannel for the basic logical channel. The basic
     * logical channel has a channel number of 0.
     *
     * @return the CardChannel for the basic logical channel
     */
    CardChannel getBasicChannel();

    /**
     * Returns the ATR of this card.
     *
     * @return the ATR of this card.
     */
    ATR getATR();

    /**
     * Disconnects the connection with this card. After this method returns,
     * calling methods on this object or in CardChannels associated with this
     * object that require interaction with the card will raise an
     * IllegalStateException.
     *
     * @param reset
     *         whether to reset the card after disconnecting.
     * @throws com.cubic.umo.pass.devices.apdu.CardException
     *         if the card operation failed
     */
    void disconnect(boolean reset) throws CardException;
}
