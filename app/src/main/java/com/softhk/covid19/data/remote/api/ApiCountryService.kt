package com.softhk.covid19.data.remote.api

import com.softhk.covid19.domain.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiCountryService {
    @GET("all")
    fun getAllCountries(): Observable<List<Country>>
}