package com.softhk.covid19.ui.covidcountry

import com.softhk.covid19.domain.CovidCountry

interface CovidCountryContract {

    interface View{
        fun setCovidCountryList(covidCountryList:List<CovidCountry>)
        fun hideSwipeRefresh(hide:Boolean)
        fun swipeRefreshLayoutWithRecyclerViewVisible(visible:Boolean)
        fun errorViewVisible(visible: Boolean)
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