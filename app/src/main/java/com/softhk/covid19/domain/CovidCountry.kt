package com.softhk.covid19.domain

import com.google.gson.annotations.SerializedName

data class CovidCountry(
    @SerializedName("Country")
    val country: String,
    @SerializedName("TotalCases")
    val totalCases: String,
    @SerializedName("NewCases")
    val newCases: String,
    @SerializedName("TotalDeaths")
    val totalDeaths: String,
    @SerializedName("NewDeaths")
    val newDeaths: String,
    @SerializedName("TotalRecovered")
    val totalRecovered: String,
    @SerializedName("ActiveCases")
    val activeCases: String,
    @SerializedName("SeriousCritical")
    val seriousCritical: String,
    @SerializedName("TotCasesx1Mpop")
    val totCasesx1Mpop: String,
    var flag:String
)