package info.countries.countriesinfo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import info.countries.countriesinfo.R
import info.countries.countriesinfo.Util
import info.countries.countriesinfo.model.CountryDetail
import info.countries.countriesinfo.model.CountryDetail_
import kotlinx.android.synthetic.main.activity_country_detail.*


class CountryDetailActivity : AppCompatActivity() {

    private lateinit var countryDetail: CountryDetail
    private var countryDetailList = ArrayList<CountryDetail_>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        countryDetail = intent.getSerializableExtra("countryDetail") as CountryDetail

        Util.showToast(this, countryDetail.capital)

        populateCountryDetailList(countryDetail)
        populateBorderList(R.id.listView_border, countryDetail.borders)


    }

    private fun populateCountryDetailList(countryDetail: CountryDetail) {
        countryDetailList.add(CountryDetail_("Name", countryDetail.name))
        countryDetailList.add(CountryDetail_("Demonyn", countryDetail.demonym))
        countryDetailList.add(CountryDetail_("Top Level Domain", countryDetail.topLevelDomain.toString()))
        countryDetailList.add(CountryDetail_("Calling Code", countryDetail.callingCode.toString()))
        countryDetailList.add(CountryDetail_("Capital", countryDetail.capital))
        countryDetailList.add(CountryDetail_("Region", countryDetail.region))
        countryDetailList.add(CountryDetail_("Population", countryDetail.population.toString()))
        countryDetailList.add(CountryDetail_("LatLng", countryDetail.latLng.toString()))
        countryDetailList.add(CountryDetail_("Area", countryDetail.area.toString()))
        countryDetailList.add(CountryDetail_("Timezone", countryDetail.timeZone.toString()))
        countryDetailList.add(CountryDetail_("Borders", countryDetail.borders.toString()))
        countryDetailList.add(CountryDetail_("Currencies", countryDetail.currencies.toString()))
        countryDetailList.add(CountryDetail_("Languages", countryDetail.languages.toString()))


    }

    private fun populateBorderList(listViewId: Int, borderList: List<String>) {
        /*val list = ArrayList<String>()
        for (index in 0 until borderList.size) {
            val countryName = Locale("", borderList[index]).country
            list.add(countryName)
        }*/
        if (borderList.isEmpty()) {
            textView_noBorder.visibility = View.VISIBLE
        }
        val adapter =
            ArrayAdapter<String>(this, R.layout.item_list, borderList)
        findViewById<ListView>(listViewId).adapter = adapter
    }
}
