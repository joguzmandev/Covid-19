package com.softhk.covid19.ui.covidcountry

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.softhk.covid19.AboutMeActivity
import com.softhk.covid19.R
import com.softhk.covid19.databinding.ActivityCovidCountryBinding
import com.softhk.covid19.di.CovidApp
import javax.inject.Inject

class CovidCountryActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: CovidCountryViewModelFactory

    private lateinit var viewModel: CovidCountryViewModel

    private val adapter by lazy { CovidCountryAdapter(this) }

    private lateinit var binding: ActivityCovidCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityCovidCountryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUp()

        binding.errorButtonView.setOnClickListener(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[CovidCountryViewModel::class.java]

        viewModel.errorViewVisible.observe(this, Observer { visible ->
            binding.errorViewLayout.visibility = if (visible) View.VISIBLE else View.GONE
        })

        viewModel.swipeRefreshLayoutWithRecyclerViewVisible.observe(this, Observer { visible ->
            binding.swipeRefeshRecyclerView.visibility =
                if (visible) View.VISIBLE else View.GONE
        })

        viewModel.hideSwipeRefresh.observe(this, Observer { hide ->
            binding.swipeRefeshRecyclerView.isRefreshing = hide
        })

        viewModel.showLoadingRetryDataAnimationVisible.observe(this, Observer { visible ->
            binding.showLoadingRetryDataAnimation.visibility =
                if (visible) View.VISIBLE else View.GONE
        })

        viewModel.showLostNetworkAnimationVisible.observe(this, Observer { visible ->
            binding.showLostNetworkAnimation.visibility =
                if (visible) View.VISIBLE else View.GONE
        })

        viewModel.covidCountryList.observe(this, Observer { covidCountryList ->
            adapter.addCovidCountriesList(covidCountryList)
        })

        if (savedInstanceState == null)
            loadData()
    }

    override fun onClick(v: View?) {
        loadDataWithRetryOrNot(true)
    }

    override fun onRefresh() {
        loadDataWithRetryOrNot(false)
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
        viewModel.loadDefaultCovidCountriesList()
    }

    private fun loadDataWithRetryOrNot(isRetry: Boolean) {
        viewModel.isRetry(isRetry)
        viewModel.loadDefaultCovidCountriesList()
    }
}