package com.softhk.covid19.data.remote

import com.softhk.covid19.data.remote.api.ApiCountryService
import com.softhk.covid19.data.remote.api.ApiCovidCountryService
import com.softhk.covid19.domain.Country
import com.softhk.covid19.domain.CovidCountry
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class RemoteDataSourceImpl constructor(
    private val apiCovidCountryService: ApiCovidCountryService,
    private val apiCountryService: ApiCountryService
) : RemoteDataSource {

    override fun getCovidCountries(): Observable<List<CovidCountry>> {
        return Observable.zip(
            apiCovidCountryService.getAllCovidCountries(),
            apiCountryService.getAllCountries(), BiFunction { item1, item2 ->
                reCreateListCovidCountries(item1, item2)
            })
    }

    override fun getCountries(): Observable<List<Country>> {
        return apiCountryService.getAllCountries()
    }


    private fun reCreateListCovidCountries(
        covidCountry: List<CovidCountry>, country: List<Country>
    ): List<CovidCountry> {

        covidCountry.forEach { item ->
            with(item) {
                flag = country.find { element -> element.name.equals(item.country) }?.flag ?: "N/A"
            }
        }
        return covidCountry
    }


}