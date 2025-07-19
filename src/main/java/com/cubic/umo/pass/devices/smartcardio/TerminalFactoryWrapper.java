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

import jnasmartcardio.Smartcardio;
import com.cubic.umo.pass.devices.util.SafeSleep;

import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;
import java.security.Security;
import java.util.List;

/**
 * Wrapper for TerminalFactory to provide abstraction over direct javax.smartcardio usage
 */
public class TerminalFactoryWrapper {

    private static boolean providerInstalled = false;

    /**
     * Initialize the smartcard provider if not already done
     */
    public static void initializeProvider() {
        if (!providerInstalled) {
            Security.addProvider(new Smartcardio());
            providerInstalled = true;
        }
    }

    /**
     * Get a list of available card terminals, waiting for terminals to become available
     * @return List of CardTerminalImpl wrappers
     */
    public static List<CardTerminalImpl> getTerminals() throws Exception {
        initializeProvider();

        TerminalFactory factory = TerminalFactory.getInstance("PC/SC", null, Smartcardio.PROVIDER_NAME);
        CardTerminals cardTerminals = factory.terminals();

        List<javax.smartcardio.CardTerminal> terminalList;
        while (true) {
            terminalList = cardTerminals.list();
            if (terminalList != null && !terminalList.isEmpty()) {
                break;
            }
            //
            // No terminals available.  Wait a bit and try again.
            //
            SafeSleep.sleep(150);
        }

        return terminalList.stream()
                .map(CardTerminalImpl::new)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get a specific terminal by name pattern, waiting for terminals to become available
     * @param namePattern Pattern to match terminal name
     * @return CardTerminalImpl wrapper or null if not found
     */
    public static CardTerminalImpl getTerminalByName(String namePattern) throws Exception {
        initializeProvider();

        TerminalFactory factory = TerminalFactory.getInstance("PC/SC", null, Smartcardio.PROVIDER_NAME);
        CardTerminals cardTerminals = factory.terminals();

        List<javax.smartcardio.CardTerminal> terminalList;
        while (true) {
            terminalList = cardTerminals.list();
            if (terminalList != null && !terminalList.isEmpty()) {
                break;
            }
            //
            // No terminals available.  Wait a bit and try again.
            //
            SafeSleep.sleep(150);
        }

        javax.smartcardio.CardTerminal matchingTerminal = terminalList.stream()
                .filter(t -> t.getName().contains(namePattern))
                .findFirst()
                .orElse(null);

        return matchingTerminal != null ? new CardTerminalImpl(matchingTerminal) : null;
    }
}
