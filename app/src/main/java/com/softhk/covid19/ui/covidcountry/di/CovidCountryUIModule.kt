package com.softhk.covid19.ui.covidcountry.di

import com.softhk.covid19.ui.covidcountry.CovidCountryContract
import com.softhk.covid19.ui.covidcountry.CovidCountryPresenter
import com.softhk.covid19.usecases.CovidCountryUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CovidCountryUIModule {

    @Provides
    @Singleton
    fun providesCovidCountryPresenter(usesCases: CovidCountryUseCases):CovidCountryContract.Presenter{
        return CovidCountryPresenter(usesCases)
    }
}