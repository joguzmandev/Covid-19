package com.softhk.covid19.di

import com.softhk.covid19.ui.covidcountry.CovidCountryActivity
import com.softhk.covid19.data.remote.di.RemoteModule
import com.softhk.covid19.ui.covidcountry.di.CovidCountryUIModule
import com.softhk.covid19.usecases.di.UseCasesModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CovidMainModule::class,
    RemoteModule::class,
    UseCasesModule::class,
    CovidCountryUIModule::class])
interface CovidComponent : AndroidInjector<CovidCountryActivity>{


}