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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import com.cubic.umo.pass.devices.smartcardio.apdu.ResponseAPDUImpl;
import com.cubic.umo.device.util.Hex;
import com.cubic.umo.pass.devices.reader.util.apdu.BankCardIdentifier;

@Slf4j
class BankCardIdentifierTests {
    @Test
    void testIsJCB() {
        byte[] apduFCIRsp = Hex.decodeString(
                "6f2f840e325041592e5359532e4444463031a51dbf0c1a61184f07a0000000651010500a4a4342204372656469748701019000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isTrue();
    }

    @Test
    void testIsMasterCard() {
        byte[] apduFCIRsp = Hex.decodeString(
                "6f23840e325041592e5359532e4444463031a511bf0c0e610c4f07a00000000410108701019000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isTrue();
    }

    @Test
    void testIsVisaCard() {
        byte[] apduFCIRsp = Hex.decodeString(
                "6f69840e325041592e5359532e4444463031a557bf0c5461284f07a000000003101050105649534120444542495420202020202087010142034761735f5502555361284f07a000000098084050105553204445424954202020202020202087010242034761735f550255539000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isTrue();
    }

    @Test
    void testIsDiscoverCard() {
        byte[] apduFCIRsp = Hex.decodeString(
                "6f35840e325041592e5359532e4444463031a523bf0c20611e4f07a0000001523010500b444953434f56455220434c8701019f2a0200069000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isTrue();
    }

    @Test
    void testIsInteracCard() {
        byte[] apduFCIRsp = Hex.decodeString(
                "6f2c840e325041592e5359532e4444463031a51abf0c1761154f07a00000027710105007496e74657261638701019000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isTrue();
    }

    @Test
    void testIsAmexCard() {
        byte[] apduFCIRsp = Hex.decodeString(
                "6f56840e325041592e5359532e4444463031a544bf0c41611f4f08a0000000250104025010414d45524943414e2045585052455353870101611e4f07a00000002910105010414d45524943414e20455850524553538701029000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isTrue();
    }

    @Test
    void testIsNotBankcardViaNoFCIResponse() {
        byte[] apduFCIRsp = Hex.decodeString(
                "7f2f840e325041592e5359532e4444463031a51dbf0c1a61184f07a1234500651010500a4a4342204372656469748701019000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isFalse();
    }

    @Test
    void testPPSESuccessfulResponseNotFCI() {
        byte[] apduFCIRsp = Hex.decodeString("8f2f840e325041592e5359532e44444630319000");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isFalse();
    }

    @Test
    void testIsNotBankcardViaBadPPSEResponse() {
        byte[] apduFCIRsp = Hex.decodeString("8f2f840e325041592e535950");

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isFalse();
    }

    @Test
    void testIsNotBankcardViaExceptionWhileProcessingAPDUResponse() {
        String badAPDUResponse = "9000";
        byte[] apduFCIRsp      = Hex.decodeString(badAPDUResponse);

        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        ResponseAPDUImpl   responseAPDU       = new ResponseAPDUImpl(apduFCIRsp);
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(responseAPDU);
        assertThat(isBankCardVer2).isFalse();
    }

    @Test
    void testIsNotBankcardViaNullAPDUResponse() {
        BankCardIdentifier bankCardIdentifier = new BankCardIdentifier();
        boolean            isBankCardVer2     = bankCardIdentifier.isBankCard(null);
        assertThat(isBankCardVer2).isFalse();
    }
}
