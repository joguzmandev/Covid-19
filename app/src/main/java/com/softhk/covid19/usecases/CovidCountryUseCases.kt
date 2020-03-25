package com.softhk.covid19.usecases

import com.softhk.covid19.data.Repository
import com.softhk.covid19.domain.CovidCountry
import io.reactivex.Observable


class CovidCountryUseCases constructor(private val repository: Repository) {

    fun getCovidCountries(): Observable<List<CovidCountry>>{
        return repository.getCovidCountries()
    }
}