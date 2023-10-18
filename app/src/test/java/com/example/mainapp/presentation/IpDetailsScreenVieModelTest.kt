package com.example.mainapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.findmyip.domain.IpRepository
import com.example.findmyip.domain.model.IpAddress
import com.example.findmyip.domain.model.Resource
import com.example.findmyip.presentation.IpDetailsScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class IpDetailsScreenVieModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: IpDetailsScreenViewModel

    private var repository = mock(IpRepository::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = IpDetailsScreenViewModel(repository)
    }

    @Test
    fun `assigns data to uiState success`() = runTest {
        val expectedData = Resource.Success(
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

        `when`(repository.getIpAddress()).thenReturn(expectedData)

        viewModel.fetchData()

        assertEquals(viewModel.state.ipAddress, expectedData)
    }

    @Test
    fun `assign error response success`() = runBlocking {
        val expectedError = Resource.Error<IpAddress>("An error occurred")

        `when`(repository.getIpAddress()).thenReturn(expectedError)

        viewModel.fetchData()

        assertEquals(viewModel.state.ipAddress, expectedError)
    }

}