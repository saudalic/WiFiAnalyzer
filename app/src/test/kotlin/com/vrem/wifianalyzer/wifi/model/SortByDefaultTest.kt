/*
 * WiFiAnalyzer
 * Copyright (C) 2015 - 2024 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.vrem.wifianalyzer.wifi.model

import org.junit.Assert.assertEquals
import org.junit.Test

class SortByDefaultTest {
    private val fixture = sortByDefault()

    @Test
    fun testSortByDefault() {
        // setup
        val wiFiDetail1 = WiFiDetail(
            WiFiIdentifier("SSID1", "BSSID1"),
            WiFiSecurity.EMPTY,
            WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55),
            WiFiAdditional.EMPTY
        )
        val wiFiDetail2 = WiFiDetail(
            WiFiIdentifier("SSID1", "BSSID1"),
            WiFiSecurity.EMPTY,
            WiFiSignal(2432, 2432, WiFiWidth.MHZ_40, -35),
            WiFiAdditional.EMPTY
        )
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        assertEquals(0, actual)
    }

    @Test
    fun testSortByDefaultWithDifferentSSID() {
        // setup
        val wiFiDetail1 = WiFiDetail(
            WiFiIdentifier("ssid1", "BSSID1"),
            WiFiSecurity.EMPTY,
            WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55),
            WiFiAdditional.EMPTY
        )
        val wiFiDetail2 = WiFiDetail(
            WiFiIdentifier("SSID1", "BSSID1"),
            WiFiSecurity.EMPTY,
            WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55),
            WiFiAdditional.EMPTY
        )
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        assertEquals(32, actual)
    }

    @Test
    fun testSortByDefaultWithDifferentBSSID() {
        // setup
        val wiFiDetail1 = WiFiDetail(
            WiFiIdentifier("SSID1", "bssid1"),
            WiFiSecurity.EMPTY,
            WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55),
            WiFiAdditional.EMPTY
        )
        val wiFiDetail2 = WiFiDetail(
            WiFiIdentifier("SSID1", "BSSID1"),
            WiFiSecurity.EMPTY,
            WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55),
            WiFiAdditional.EMPTY
        )
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        assertEquals(32, actual)
    }
}