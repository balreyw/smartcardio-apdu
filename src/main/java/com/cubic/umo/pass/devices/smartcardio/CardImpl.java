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

import lombok.RequiredArgsConstructor;

import com.cubic.umo.pass.devices.apdu.ATR;
import com.cubic.umo.pass.devices.apdu.CardChannel;
import com.cubic.umo.pass.devices.apdu.CardException;
import com.cubic.umo.pass.devices.apdu.Card;
import com.cubic.umo.pass.devices.apdu.CardTerminal;

/**
 * A Smart Card with which a connection has been established. Card objects
 * are obtained by calling CardTerminal#connect CardTerminal.connect()}.
 *
 * @see CardTerminal
 */
@RequiredArgsConstructor
public class CardImpl implements Card {
    private final javax.smartcardio.Card card;

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
    @Override
    public void beginExclusive() throws CardException {
        try {
            card.beginExclusive();
        } catch (javax.smartcardio.CardException e) {
            throw new CardException("CARD_EXCEPTION", "Error while trying to begin exclusive mode");
        }
    }

    /**
     * Returns the CardChannel for the basic logical channel. The basic
     * logical channel has a channel number of 0.
     *
     * @return the CardChannel for the basic logical channel
     */
    @Override
    public CardChannel getBasicChannel() {
        return new CardChannelImpl(card.getBasicChannel());
    }

    /**
     * Returns the ATR of this card.
     *
     * @return the ATR of this card.
     */
    @Override
    public ATR getATR() {
        return new ATRImpl(card.getATR());
    }

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
    @Override
    public void disconnect(boolean reset) throws CardException {
        try {
            card.disconnect(reset);
        } catch (javax.smartcardio.CardException e) {
            throw new CardException("CARD_EXCEPTION", "Error while trying to disconnect");
        }
    }
}
