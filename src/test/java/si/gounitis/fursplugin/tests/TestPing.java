//********************************************************************************
//
//    Copyright (C) 2015  GoUnitis, Jurij Zelic s.p.
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU Lesser General Public License as published by
//    the Free Software Foundation; either version 2.1 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU Lesser General Public License for more details.
//
//********************************************************************************
//    Revision history:
//        12.10.2015: J. Zelic - First Version
//********************************************************************************
package si.gounitis.fursplugin.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import si.gounitis.fursplugin.FursPlugin;
import si.gounitis.fursplugin.FursPluginException;
import si.gounitis.fursplugin.impl.FursPluginJson;
import si.gounitis.fursplugin.impl.FursPluginSOAP;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
public class TestPing {

    @Value("${truststore.location}")
    public String truststoreLocation;

    @Value("${truststore.pass}")
    public String truststorePass;

    @Value("${keystore.location}")
    public String keystoreLocation;

    @Value("${keystore.pass}")
    public String keystorePass;

    @Before
    public void before() {
        System.setProperty("javax.net.ssl.trustStore", truststoreLocation);
        System.setProperty("javax.net.ssl.trustStorePassword", truststorePass);
        System.setProperty("javax.net.ssl.keyStore", keystoreLocation);
        System.setProperty("javax.net.ssl.keyStorePassword", keystorePass);
        //System.setProperty("javax.net.debug", "all");
    }

    @Ignore
    @Test
    public void testPing() {
        FursPlugin plugin = new FursPluginSOAP("https://blagajne-test.fu.gov.si:9002/v1/cash_registers");

        try {
            plugin.ping();
        } catch (FursPluginException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPingJson() {
        FursPlugin plugin = new FursPluginJson("https://blagajne-test.fu.gov.si:9002/v1/cash_registers/echo");

        try {
            plugin.ping();
        } catch (FursPluginException e) {
            throw new RuntimeException(e);
        }
    }
}
