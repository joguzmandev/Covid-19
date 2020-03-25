package com.softhk.covid19.data

import com.softhk.covid19.data.remote.RemoteDataSource
import com.softhk.covid19.domain.CovidCountry
import io.reactivex.Observable

class Repository constructor(private val remoteDataSource: RemoteDataSource) {

    fun getCovidCountries(): Observable<List<CovidCountry>> {
        return remoteDataSource.getCovidCountries()
    }
}