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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.cubic.umo.pass.devices.apdu.Apdu;
import com.cubic.umo.pass.devices.apdu.CardException;
import com.cubic.umo.pass.devices.apdu.Card;
import com.cubic.umo.pass.devices.apdu.CommandAPDU;
import com.cubic.umo.pass.devices.apdu.ResponseAPDU;

@Slf4j
@AllArgsConstructor
public class ApduThruSCIO implements Apdu {
    private final Card card;

    /**
     * Sends the apdu command to card reader.
     *
     * @param apdu
     *         the apdu command
     * @return the card reader response
     */
    @Override
    public ResponseAPDU sendApdu(byte[] apdu) {
        CommandAPDU  commandAPDU  = new CommandAPDUImpl().createAPDU(apdu);
        ResponseAPDU responseAPDU = null;
        try {
            if (card != null) {
                responseAPDU = card.getBasicChannel().transmit(commandAPDU);
            }
        } catch (CardException e) {
            log.debug("NFC exception: {}", e.getMessage());
        }
        return responseAPDU;
    }

}
