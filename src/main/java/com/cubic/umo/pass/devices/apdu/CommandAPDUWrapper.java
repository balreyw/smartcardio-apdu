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

import lombok.Getter;

/**
 * Wrapper for APDU commands.
 */
@Getter
public class CommandAPDUWrapper {
    private final byte[] apdu;

    public CommandAPDUWrapper(byte[] apdu) {
        this.apdu = apdu;
    }
}
