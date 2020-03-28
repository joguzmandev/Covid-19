package com.softhk.covid19.ui.covidcountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CovidCountryViewModelFactory constructor(var covidCountryViewModel:CovidCountryViewModel)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return covidCountryViewModel as T
    }

}
