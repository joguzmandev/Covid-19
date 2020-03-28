package com.softhk.covid19.ui.covidcountry.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softhk.covid19.ui.covidcountry.CovidCountryActivity
import com.softhk.covid19.ui.covidcountry.CovidCountryViewModel
import com.softhk.covid19.usecases.CovidCountryUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Module
class CovidCountryUIModule {

    @Provides
    fun providesViewModelFactory(covidCountryUseCases: CovidCountryUseCases) = ViewModelFactory(covidCountryUseCases)

}


class ViewModelFactory constructor(var covidCountryUseCases: CovidCountryUseCases)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CovidCountryViewModel(covidCountryUseCases = covidCountryUseCases) as T
    }

}
