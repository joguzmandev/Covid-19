package com.softhk.covid19.data.remote

import com.softhk.covid19.domain.Country
import com.softhk.covid19.domain.CovidCountry
import io.reactivex.Observable

interface RemoteDataSource {

    fun getCovidCountries(): Observable<List<CovidCountry>>
    fun getCountries(): Observable<List<Country>>
}