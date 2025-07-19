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

import com.cubic.umo.pass.devices.apdu.CardException;
import com.cubic.umo.pass.devices.apdu.Card;
import com.cubic.umo.pass.devices.apdu.CardTerminal;

@RequiredArgsConstructor
public class CardTerminalImpl implements CardTerminal {
    private final javax.smartcardio.CardTerminal cardTerminal;

    /**
     * Establishes a connection to the card in this card terminal.
     *
     * @param var1
     *         the protocol to use ("T=0", "T=1", or "T=CL", or "direct"), or "*"
     *         to connect using any available protocol.
     * @return the card object
     * @throws CardException
     *         if the operation failed
     */
    @Override
    public Card connect(String var1) throws CardException {
        Card card;
        try {
            card = new CardImpl(cardTerminal.connect(var1));
        } catch (javax.smartcardio.CardException e) {
            throw new CardException("CARD_EXCEPTION", "Error while trying to connect");
        }
        return card;
    }

    /**
     * Waits until a card is present in this terminal or the timeout
     * expires. If the method returns due to an expired timeout, it returns
     * false. Otherwise it return true.
     *
     * <P>If a card is present in this terminal when this
     * method is called, it returns immediately.
     *
     * @param timeout
     *         if positive, block for up to <code>timeout</code>
     *         milliseconds; if zero, block indefinitely; must not be negative
     * @return false if the method returns due to an expired timeout,
     *         true otherwise.
     * @throws CardException
     *         if the operation failed
     */
    @Override
    public boolean waitForCardPresent(long timeout) throws CardException {
        try {
            return cardTerminal.waitForCardPresent(timeout);
        } catch (javax.smartcardio.CardException e) {
            throw new CardException("CARD_EXCEPTION", "Error while trying to wait for card present");
        }
    }

    /**
     * Waits until a card is absent in this terminal or the timeout
     * expires. If the method returns due to an expired timeout, it returns
     * false. Otherwise it return true.
     *
     * <P>If no card is present in this terminal when this
     * method is called, it returns immediately.
     *
     * @param timeout
     *         if positive, block for up to <code>timeout</code>
     *         milliseconds; if zero, block indefinitely; must not be negative
     * @return false if the method returns due to an expired timeout,
     *         true otherwise.
     * @throws CardException
     *         if the operation failed
     */
    @Override
    public boolean waitForCardAbsent(long timeout) throws CardException {
        try {
            return cardTerminal.waitForCardAbsent(timeout);
        } catch (javax.smartcardio.CardException e) {
            throw new CardException("CARD_EXCEPTION", "Error while trying to wait for card absent");
        }
    }
}
