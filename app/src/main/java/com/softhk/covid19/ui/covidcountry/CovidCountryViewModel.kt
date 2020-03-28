package com.softhk.covid19.ui.covidcountry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhk.covid19.domain.CovidCountry
import com.softhk.covid19.usecases.CovidCountryUseCases
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CovidCountryViewModel constructor(val covidCountryUseCases: CovidCountryUseCases) :
    ViewModel() {

    private var _errorViewVisible = MutableLiveData<Boolean>()
    var errorViewVisible: LiveData<Boolean> = _errorViewVisible

    private var _hideSwipeRefresh = MutableLiveData<Boolean>()
    var hideSwipeRefresh: LiveData<Boolean> = _hideSwipeRefresh

    private var _swipeRefreshLayoutWithRecyclerViewVisible = MutableLiveData<Boolean>()
    var swipeRefreshLayoutWithRecyclerViewVisible: LiveData<Boolean> =
        _swipeRefreshLayoutWithRecyclerViewVisible

    private var _covidCountryList = MutableLiveData<List<CovidCountry>>()
    var covidCountryList: LiveData<List<CovidCountry>> = _covidCountryList

    private var compisite: CompositeDisposable = CompositeDisposable()

    private var _showLostNetworkAnimationVisible = MutableLiveData<Boolean>()
    var showLostNetworkAnimationVisible: LiveData<Boolean> = _showLostNetworkAnimationVisible

    private var _showLoadingRetryDataAnimationVisible = MutableLiveData<Boolean>()
    var showLoadingRetryDataAnimationVisible: LiveData<Boolean> =
        _showLoadingRetryDataAnimationVisible


    private var _isRetry  = false


    fun isRetry(isRetry:Boolean){
        _isRetry = isRetry
    }

    fun loadDefaultCovidCountriesList() {

        when(_isRetry){
            true->{
                _showLoadingRetryDataAnimationVisible.value = true
                _showLostNetworkAnimationVisible.value = false
            }
        }

        var dispo = covidCountryUseCases
            .getCovidCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext {
                when(_isRetry){
                    false ->{
                        _errorViewVisible.value = false
                        _hideSwipeRefresh.value = false
                        _swipeRefreshLayoutWithRecyclerViewVisible.value = true
                    }
                    true ->{
                        _showLoadingRetryDataAnimationVisible.value = false
                        _showLostNetworkAnimationVisible.value = false
                        _errorViewVisible.value = false
                        _hideSwipeRefresh.value = false
                        _swipeRefreshLayoutWithRecyclerViewVisible.value = true
                    }
                }
            }
            .doOnError {
               when(_isRetry){
                   false ->{
                       _hideSwipeRefresh.value = false
                       _swipeRefreshLayoutWithRecyclerViewVisible.value = false
                       _errorViewVisible.value = true
                   }
                   true ->{
                       _showLoadingRetryDataAnimationVisible.value = false
                       _showLostNetworkAnimationVisible.value = true
                   }
               }

            }
            .subscribe(
                { items ->
                    _covidCountryList.value = items.sortedBy { it.country }
                },
                {
                    Log.e("Error", "Error: ${it.message}")
                }
            )
        compisite.add(dispo)
    }


    override fun onCleared() {
        super.onCleared()
        this.compisite.dispose()
    }
}