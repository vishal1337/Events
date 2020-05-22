package com.vishal.paytminsider.data.network.services

import com.vishal.paytminsider.model.EventDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to Connect to remote Server
 */
interface PaytmService {

    /**
     * Fetch Events from Remote Server
     */
    @GET("/home?norm=1&filterBy=go-out")
    suspend fun getEvents(
        @Query("city") city: String
    ): EventDto.ResponseWrapper

}