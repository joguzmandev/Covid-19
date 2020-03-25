package com.softhk.covid19.ui.covidcountry

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.softhk.covid19.AboutMeActivity
import com.softhk.covid19.R
import com.softhk.covid19.di.CovidApp
import com.softhk.covid19.domain.CovidCountry
import kotlinx.android.synthetic.main.activity_covid_country.*
import javax.inject.Inject

class CovidCountryActivity : AppCompatActivity(), CovidCountryContract.View,
    SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @Inject
    lateinit var presenter: CovidCountryContract.Presenter

    private val swipeRefreshRv by lazy { swipeRefeshRecyclerView }
    private val viewError by lazy { errorViewLayout }
    private val errorButton by lazy { errorButtonView }
    private val adapter by lazy { CovidCountryAdapter(this) }
    private val recyclerView by lazy { covidCountryRecyclerView }
    private val covid19Toolbar by lazy { covidToolbar }
    private val showLoadingRetryDataAnim by lazy { showLoadingRetryDataAnimation }
    private val showLostNetworkAnim by lazy{showLostNetworkAnimation}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_country)
        setUp()
        errorButton.setOnClickListener(this)
    }

    override fun setCovidCountryList(covidCountryList: List<CovidCountry>) {
        adapter.addCovidCountriesList(covidCountryList)
    }

    override fun hideSwipeRefresh(hide: Boolean) {
        swipeRefreshRv.isRefreshing = hide
    }

    override fun showSwipeRefreshLayoutWithRecyclerView() {
        swipeRefreshRv.visibility = View.VISIBLE
    }

    override fun hideSwipeRefreshLayoutWithRecyclerView() {
        swipeRefreshRv.visibility = View.INVISIBLE
    }

    override fun showErrorView() {
        viewError.visibility = View.VISIBLE
    }

    override fun hideErrorView() {
        viewError.visibility = View.INVISIBLE
    }

    override fun showLoadingRetryA() {
        showLostNetworkAnim.visibility = View.VISIBLE
        showLoadingRetryDataAnim.visibility = View.GONE
    }

    override fun showLostNetworkRetryA() {
        showLoadingRetryDataAnim.visibility = View.VISIBLE
        showLostNetworkAnim.visibility = View.GONE
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
                var intent = Intent(this, AboutMeActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }


    private fun setUp() {
        //Set Dagger to activity
        (application as CovidApp).getCovidComponent().inject(this)

        //Set event to SwipeRefreshRv
        swipeRefreshRv.setOnRefreshListener(this)

        //Set Toolbar to Activity
        setSupportActionBar(covid19Toolbar)

        //Set Adapter to RecyclerView
        recyclerView.adapter = adapter

        //Attach Activity to Presenter
        presenter.attach(this)

    }

    private fun loadData() {
        swipeRefreshRv.isRefreshing = true
        presenter.loadDefaultCovidCountriesList()
    }
}