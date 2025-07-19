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

package com.cubic.umo.pass.devices.util;

/**
 * Utility class for safely sleeping the current thread without propagating InterruptedException
 */
public class SafeSleep {

    /**
     * Sleeps for the specified number of milliseconds.
     * If the thread is interrupted, the interrupt status is restored but no exception is thrown.
     *
     * @param millis the number of milliseconds to sleep
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Restore the interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sleeps for the specified number of milliseconds and nanoseconds.
     * If the thread is interrupted, the interrupt status is restored but no exception is thrown.
     *
     * @param millis the number of milliseconds to sleep
     * @param nanos the number of nanoseconds to sleep (0-999999)
     */
    public static void sleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, nanos);
        } catch (InterruptedException e) {
            // Restore the interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
