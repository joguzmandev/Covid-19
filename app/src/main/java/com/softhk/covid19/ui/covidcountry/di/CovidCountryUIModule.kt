package com.softhk.covid19.ui.covidcountry.di

import com.softhk.covid19.ui.covidcountry.CovidCountryViewModel
import com.softhk.covid19.ui.covidcountry.CovidCountryViewModelFactory
import com.softhk.covid19.usecases.CovidCountryUseCases
import dagger.Module
import dagger.Provides


@Module
class CovidCountryUIModule {

    @Provides
    fun providesCovidCountryViewModel(covidCountryUseCases: CovidCountryUseCases): CovidCountryViewModel {
        return CovidCountryViewModel(covidCountryUseCases = covidCountryUseCases)
    }

    @Provides
    fun providesViewModelFactory(covidCountryViewModel: CovidCountryViewModel) =
        CovidCountryViewModelFactory(covidCountryViewModel)
}



