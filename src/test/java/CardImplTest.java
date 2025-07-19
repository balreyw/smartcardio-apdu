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

import com.cubic.umo.pass.devices.apdu.ATR;
import com.cubic.umo.pass.devices.apdu.CardChannel;
import com.cubic.umo.pass.devices.apdu.CardException;
import com.cubic.umo.pass.devices.smartcardio.CardImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.smartcardio.Card;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardImplTest {

    private Card     mockCard;
    private CardImpl cardImpl;

    @BeforeEach
    void setUp() {
        mockCard = mock(Card.class);
        cardImpl = new CardImpl(mockCard);
    }

    @Test
    void testBeginExclusive() throws javax.smartcardio.CardException, CardException {
        doNothing().when(mockCard).beginExclusive();
        cardImpl.beginExclusive();
        verify(mockCard, times(1)).beginExclusive();
    }

    @Test
    void testBeginExclusiveThrowsException() throws javax.smartcardio.CardException {
        doThrow(new javax.smartcardio.CardException("Error")).when(mockCard).beginExclusive();
        CardException exception = assertThrows(CardException.class, () -> cardImpl.beginExclusive());
        assertEquals("CARD_EXCEPTION", exception.getErrorCode());
        assertEquals("Error while trying to begin exclusive mode", exception.getErrorMessage());
    }

    @Test
    void testGetBasicChannel() {
        javax.smartcardio.CardChannel mockCardChannel = mock(javax.smartcardio.CardChannel.class);
        when(mockCard.getBasicChannel()).thenReturn(mockCardChannel);
        CardChannel cardChannel = cardImpl.getBasicChannel();
        assertNotNull(cardChannel);
    }

    @Test
    void testGetATR() {
        // Create a real ATR object with real ATR bytes instead of mocking
        byte[] atrBytes = new byte[]{0x3B, 0x00}; // Simple valid ATR
        javax.smartcardio.ATR realATR = new javax.smartcardio.ATR(atrBytes);

        // Mock the card to return the real ATR
        when(mockCard.getATR()).thenReturn(realATR);

        // Execute the test
        ATR atr = cardImpl.getATR();

        // Verify
        assertNotNull(atr);
    }

    @Test
    void testDisconnect() throws javax.smartcardio.CardException, CardException {
        doNothing().when(mockCard).disconnect(false);
        cardImpl.disconnect(false);
        verify(mockCard, times(1)).disconnect(false);
    }

    @Test
    void testDisconnectThrowsException() throws javax.smartcardio.CardException {
        doThrow(new javax.smartcardio.CardException("Error")).when(mockCard).disconnect(false);
        CardException exception = assertThrows(CardException.class, () -> cardImpl.disconnect(false));
        assertEquals("CARD_EXCEPTION", exception.getErrorCode());
        assertEquals("Error while trying to disconnect", exception.getErrorMessage());
    }
}
