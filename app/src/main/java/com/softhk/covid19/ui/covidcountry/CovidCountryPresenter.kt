package com.softhk.covid19.ui.covidcountry

import android.util.Log
import com.softhk.covid19.usecases.CovidCountryUseCases
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CovidCountryPresenter constructor(private val covidCountryUseCases: CovidCountryUseCases) :
    CovidCountryContract.Presenter {

    private var view: CovidCountryContract.View? = null
    private var compisite:CompositeDisposable = CompositeDisposable()

    override fun loadCovidCountry() {
       var dispo =  covidCountryUseCases
            .getCovidCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items ->
                    view?.showSwipeRefreshLayoutWithRecyclerView()
                    view?.hideErrorView()
                    view?.setCovidCountryList(items.sortedBy { it.country })
                    view?.hideSwipeRefresh(false)
                },
                {
                    view?.hideSwipeRefresh(false)
                    view?.hideSwipeRefreshLayoutWithRecyclerView()
                    view?.showErrorView()
                    Log.e("RESULT-ACTIVITY","WE HAVE A PROBLEM ${it.message}")
                }
            )
        compisite.add(dispo)
    }

    override fun attach(view: CovidCountryContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
        this.compisite.dispose()
    }
}