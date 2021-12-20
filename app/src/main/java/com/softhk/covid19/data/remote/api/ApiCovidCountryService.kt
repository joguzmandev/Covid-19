package com.softhk.covid19.data.remote.api

import com.softhk.covid19.domain.CovidCountry
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCovidCountryService {
    @GET("covid")
    fun getAllCovidCountries(@Query("fbclid")
       fbclid: String = "IwAR0IlQi5bgbxyYSCpgzxdUL3FPn32LW8zjqf3j5ITiT3SdqGOy-7o4RNquI")
    :Observable<List<CovidCountry>>
}