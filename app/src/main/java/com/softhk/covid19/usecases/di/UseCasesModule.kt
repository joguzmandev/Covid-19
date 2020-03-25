package com.softhk.covid19.usecases.di

import com.softhk.covid19.data.Repository
import com.softhk.covid19.usecases.CovidCountryUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule  {

    @Provides
    @Singleton
    fun providesCovidCountryUseCases(repository: Repository):CovidCountryUseCases{
        return CovidCountryUseCases(repository)
    }

}