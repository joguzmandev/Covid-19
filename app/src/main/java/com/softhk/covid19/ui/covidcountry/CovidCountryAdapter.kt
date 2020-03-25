package com.softhk.covid19.ui.covidcountry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.softhk.covid19.R
import com.softhk.covid19.domain.CovidCountry
import com.softhk.covid19.utils.setValueWhenIsEmpty
import kotlinx.android.synthetic.main.item_covid_country.view.*

class CovidCountryAdapter constructor(private val activity: AppCompatActivity) :
    RecyclerView.Adapter<CovidCountryAdapter.CovidCountryViewHolder>() {

    private var covidCountriesList = mutableListOf<CovidCountry>()
        set(value) {
            if (field.size > 0)
                field.clear()
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidCountryViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_covid_country, parent, false)

        return CovidCountryViewHolder(view)
    }

    override fun getItemCount() = covidCountriesList.size

    override fun onBindViewHolder(holder: CovidCountryViewHolder, position: Int) {
        var covidCountry = covidCountriesList[position]
        holder.bind(covidCountry)

    }

    public fun addCovidCountriesList(list: List<CovidCountry>) {
        this.covidCountriesList = list.toMutableList()
    }

    inner class CovidCountryViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(covidCountry: CovidCountry) {
            with(itemView) {
                SvgLoader.pluck().with(activity)
                    .load(covidCountry.flag, imageCountry)
                nameCountry.text = covidCountry.country.toUpperCase()
                nameCountry.setTextColor(ContextCompat.getColor(context,R.color.primaryText))

                totalCases.text =
                    "${resources.getString(R.string.country_total_cases)} ${covidCountry.totalCases.setValueWhenIsEmpty()}"
                newCases.text =
                    "${resources.getString(R.string.country_new_cases)} ${covidCountry.newCases.setValueWhenIsEmpty()}"
                totalDeaths.text =
                    "${resources.getString(R.string.country_total_deaths)} ${covidCountry.totalDeaths.setValueWhenIsEmpty()}"
                newDeaths.text =
                    "${resources.getString(R.string.country_new_deaths)} ${covidCountry.newDeaths.setValueWhenIsEmpty()}"
                totalRecovered.text =
                    "${resources.getString(R.string.country_total_recovered)} ${covidCountry.totalRecovered.setValueWhenIsEmpty()}"
                activeCases.text =
                    "${resources.getString(R.string.country_active_cases)} ${covidCountry.activeCases.setValueWhenIsEmpty()}"
                seriousCritical.text =
                    "${resources.getString(R.string.country_serious_critical)} ${covidCountry.seriousCritical.setValueWhenIsEmpty()}"

            }
        }
    }
}