package com.example.findmyip.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.findmyip.domain.model.IpAddress
import com.example.findmyip.domain.model.Resource
import org.junit.Rule
import org.junit.Test

class IpDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testIpDetailsScreenSuccess() {
        composeTestRule.setContent {
            IpDetailsScreen(
                uiState = IpDetailsScreenUiState(
                    ipAddress = Resource.Success(
                        IpAddress(
                            ip = "2405:201:e039:d904:6df8:95c:d4e6:ed62",
                            network = "2405:201:e039::/48",
                            version = "IPv6",
                            city = "Chennai",
                            region = "Tamil Nadu",
                            region_code = "TN",
                            country = "IN",
                            country_name = "India",
                            country_code = "IN",
                            country_code_iso3 = "IND",
                            country_capital = "New Delhi",
                            country_tld = ".in",
                            continent_code = "AS",
                            in_eu = false,
                            postal = "600001",
                            latitude = 12.8996,
                            longitude = 80.2209,
                            timezone = "Asia/Kolkata", utc_offset = " + 0530",
                            country_calling_code = "+91",
                            currency = "INR",
                            currency_name = "Rupee",
                            languages = "en-IN,hi,bn,te,mr,ta,ur,gu,kn,ml,or,pa,as,bh,sat,ks,ne,sd,kok,doi,mni,sit,sa,fr,lus,inc",
                            country_area = 3287590.0,
                            country_population = 1352617328,
                            asn = "AS55836",
                            org = "Reliance Jio Infocomm Limited"
                        )
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("600001").assertIsDisplayed()
    }

    @Test
    fun testIpDetailsScreenError() {
        composeTestRule.setContent {
            IpDetailsScreen(
                uiState = IpDetailsScreenUiState(
                    ipAddress = Resource.Error(
                        "Unknown Error"
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Unknown Error").assertIsDisplayed()
    }

    @Test
    fun testIpDetailsScreenLoading() {
        composeTestRule.setContent {
            IpDetailsScreen(
                uiState = IpDetailsScreenUiState(
                    ipAddress = Resource.Loading()
                )
            )
        }

        composeTestRule.onNodeWithTag(testTag = "ProgressIndicator").assertIsDisplayed()
    }
}