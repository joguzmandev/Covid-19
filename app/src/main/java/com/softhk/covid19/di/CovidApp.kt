package com.softhk.covid19.di

import android.app.Application

@Suppress("DEPRECATION")
class CovidApp : Application() {

    private lateinit var covidComponent: CovidComponent

    override fun onCreate() {
        super.onCreate()
        covidComponent = DaggerCovidComponent.builder()
            .covidMainModule(CovidMainModule())
            .build()

    }

    fun getCovidComponent() = covidComponent
}