package com.softhk.covid19.ui.covidcountry

import com.softhk.covid19.domain.CovidCountry

interface CovidCountryContract {

    interface View{
        fun setCovidCountryList(covidCountryList:List<CovidCountry>)
        fun hideSwipeRefresh(hide:Boolean)
        fun showSwipeRefreshLayoutWithRecyclerView()
        fun hideSwipeRefreshLayoutWithRecyclerView()
        fun showErrorView()
        fun hideErrorView()
        fun showLoadingRetryA()
        fun showLostNetworkRetryA()
    }
    interface Presenter{
        fun loadDefaultCovidCountriesList()
        fun retryLoadCovidCountriesList()
        fun attach(view:CovidCountryContract.View)
        fun detach()
    }
}