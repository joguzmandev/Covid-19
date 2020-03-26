package com.softhk.covid19.ui.covidcountry

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.softhk.covid19.AboutMeActivity
import com.softhk.covid19.R
import com.softhk.covid19.databinding.ActivityCovidCountryBinding
import com.softhk.covid19.di.CovidApp
import com.softhk.covid19.domain.CovidCountry
import javax.inject.Inject

class CovidCountryActivity : AppCompatActivity(), CovidCountryContract.View,
    SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @Inject
    lateinit var presenter: CovidCountryContract.Presenter

    private val adapter by lazy { CovidCountryAdapter(this) }

    private lateinit var binding: ActivityCovidCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityCovidCountryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUp()

        binding.errorButtonView.setOnClickListener(this)
    }

    override fun setCovidCountryList(covidCountryList: List<CovidCountry>) {
        adapter.addCovidCountriesList(covidCountryList)
    }

    override fun hideSwipeRefresh(hide: Boolean) {
        binding.swipeRefeshRecyclerView.isRefreshing = hide
    }

    override fun swipeRefreshLayoutWithRecyclerViewVisible(visible: Boolean) {
        binding.swipeRefeshRecyclerView.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun errorViewVisible(visible: Boolean) {
        binding.errorViewLayout.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun showLoadingRetryA() {
        binding.showLostNetworkAnimation.visibility = View.VISIBLE
        binding.showLoadingRetryDataAnimation.visibility = View.GONE
    }

    override fun showLostNetworkRetryA() {
        binding.showLoadingRetryDataAnimation.visibility = View.VISIBLE
        binding.showLostNetworkAnimation.visibility = View.GONE
    }

    override fun onRefresh() {
        presenter.loadDefaultCovidCountriesList()
    }

    override fun onClick(v: View?) {
        presenter.retryLoadCovidCountriesList()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutMe -> {
                val intent = Intent(this, AboutMeActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    private fun setUp() {
        //Set Dagger to activity
        (application as CovidApp).getCovidComponent().inject(this)

        //Attach Activity to Presenter
        presenter.attach(this)

        with(binding) {
            //Set event to SwipeRefreshRv
            swipeRefeshRecyclerView.setOnRefreshListener(this@CovidCountryActivity)

            //Set Toolbar to Activity
            setSupportActionBar(covidToolbar)

            //Set Adapter to RecyclerView
            covidCountryRecyclerView.adapter = adapter
        }


    }

    private fun loadData() {
        binding.swipeRefeshRecyclerView.isRefreshing = true
        presenter.loadDefaultCovidCountriesList()
    }
}