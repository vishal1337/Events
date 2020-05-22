package com.vishal.paytminsider.data.network.repository

import com.vishal.paytminsider.data.network.services.PaytmService
import com.vishal.paytminsider.model.EventDto
import javax.inject.Inject

/**
 * Repository to Fetch Events data
 */
interface PaytmRepository {
    suspend fun getEvents(city: String): HashMap<String, EventDto.Event>
}


class PaytmRepositoryImpl @Inject constructor(
    private val paytmService: PaytmService
) : PaytmRepository {

    /**
     * Extract and return Events from Wrapped Response
     */
    override suspend fun getEvents(city: String): HashMap<String, EventDto.Event> =
        paytmService.getEvents(city).listWrapper.masterList

}