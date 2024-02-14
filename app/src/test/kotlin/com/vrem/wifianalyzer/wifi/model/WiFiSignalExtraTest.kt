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

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vrem.wifianalyzer.RobolectricUtil
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
class WiFiSignalExtraTest {
    private val mainActivity = RobolectricUtil.INSTANCE.activity
    private val timestamp: Long = 123456789
    private val fixture: WiFiSignalExtra =
        WiFiSignalExtra(true, WiFiStandard.AC, timestamp, FastRoaming.entries.toList())

    @Test
    fun testWiFiSignalExtra() {
        // validate
        assertTrue(fixture.is80211mc)
        assertEquals(WiFiStandard.AC, fixture.wiFiStandard)
        assertEquals(timestamp, fixture.timestamp)
        assertEquals(FastRoaming.entries.toList(), fixture.fastRoaming)
    }

    @Test
    fun testWiFiStandardDisplay() {
        // setup
        val expected = "802.11ac"
        // execute
        val actual = fixture.wiFiStandardDisplay(mainActivity.applicationContext)
        // validate
        assertEquals(expected, actual)
    }

    @Test
    fun testFastRoamingDisplay() {
        // setup
        val expected = "802.11k 802.11r 802.11v"
        // execute
        val actual = fixture.fastRoamingDisplay(mainActivity.applicationContext)
        // validate
        assertEquals(expected, actual)
    }

    @Test
    fun testFastRoamingDisplayWithOneFastRoaming() {
        // setup
        val fixture = WiFiSignalExtra(fastRoaming = listOf(FastRoaming.FR_802_11R))
        val expected = "802.11r"
        // execute
        val actual = fixture.fastRoamingDisplay(mainActivity.applicationContext)
        // validate
        assertEquals(expected, actual)
    }

}
