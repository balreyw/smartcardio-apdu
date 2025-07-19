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
 * Interface for card exceptions.
 */
public interface CardExceptionInterface {
    /**
     * Returns the error code.
     *
     * @return the error code.
     */
    String getErrorCode();
    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    String getErrorMessage();
}

