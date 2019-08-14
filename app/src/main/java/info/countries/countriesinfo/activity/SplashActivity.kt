package info.countries.countriesinfo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import info.countries.countriesinfo.R
import info.countries.countriesinfo.model.Country
import info.countries.countriesinfo.retrofit.ApiClient
import info.countries.countriesinfo.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SplashActivity : AppCompatActivity(), Callback<List<Country>>, View.OnClickListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView_tryAgain: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        textView_tryAgain = findViewById(R.id.textView_tryAgain)
        progressBar = findViewById(R.id.progressBar)

        textView_tryAgain.setOnClickListener(this)
        callRestApi()

        //getAllCountries()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.textView_tryAgain -> {
                textView_tryAgain.visibility = View.GONE
                callRestApi()
            }
        }
    }

    private fun callRestApi() {
        progressBar.visibility = View.VISIBLE
        var apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)

        var call: Call<List<Country>> = apiInterface.getAllCountries()
        call.enqueue(this)

    }

    override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, t!!.message, Toast.LENGTH_SHORT).show()
        textView_tryAgain.visibility = View.VISIBLE

    }

    override fun onResponse(call: Call<List<Country>>?, response: Response<List<Country>>?) {
        val countryList: ArrayList<Country> = response!!.body() as ArrayList<Country>
        progressBar.visibility = View.INVISIBLE
        //Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java).putExtra("countryList", countryList))
        finish()
    }

    private fun getAllCountries() {
        var countriesList = Locale.getISOCountries()

        for (index in 0 until countriesList.size) {
            var country = Locale("eng", countriesList[index])
            var name = country.displayName
            var iso3Code = country.country
        }
    }
}
