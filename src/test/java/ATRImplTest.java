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

import com.cubic.umo.pass.devices.smartcardio.ATRImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.smartcardio.ATR;

import static org.junit.jupiter.api.Assertions.*;

class ATRImplTest {

    private ATR realATR;
    private ATRImpl atrImpl;

    @BeforeEach
    void setUp() {
        // Create a real ATR object instead of mocking since ATR is a final class
        byte[] atrBytes = {0x01, 0x02, 0x03};
        realATR = new ATR(atrBytes);
        atrImpl = new ATRImpl(realATR);
    }

    @Test
    void testGetBytes() {
        byte[] expectedBytes = {0x01, 0x02, 0x03};
        byte[] actualBytes = atrImpl.getBytes();
        assertArrayEquals(expectedBytes, actualBytes);
    }
}
