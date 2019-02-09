package info.countries.countriesinfo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import info.countries.countriesinfo._interface.ItemClickListener
import info.countries.countriesinfo.adapter.CountryAdapter
import info.countries.countriesinfo.model.Country
import info.countries.countriesinfo.decoration.MyDividerItemDecoration
import android.app.SearchManager
import android.content.Intent
import android.support.v7.widget.SearchView
import info.countries.countriesinfo.activity.CountryDetailActivity
import info.countries.countriesinfo.model.CountryDetail


class MainActivity : AppCompatActivity(), ItemClickListener<Country>, SearchView.OnQueryTextListener {


    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var countryList: ArrayList<Country>
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryList = intent.extras["countryList"] as ArrayList<Country>
        initView()
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(R.id.recyclerView)

        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16))


        countryAdapter = CountryAdapter(this, countryList, this)
        recyclerView.adapter = countryAdapter

    }


    override fun itemClicked(country: Country) {
        startActivity(Intent(this, CountryDetailActivity::class.java))

        val countryDetail = CountryDetail(name = country.name, demonym = country.demonym, topLevelDomain = country.topLevelDomain, callingCode = country.callingCodes, capital = country.capital, region = country.region, population = country.population, latLng = country.latlng, area = country.area, timeZone = country.timezones, borders = country.borders, currencies = country.currencies, languages = country.languages,flag = country.flag)
        Toast.makeText(this, "${countryDetail.borders}",Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = "Pakistan, Islamabad"

        searchView.setOnQueryTextListener(this)
        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        countryAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        countryAdapter.filter.filter(query)
        return false
    }
}