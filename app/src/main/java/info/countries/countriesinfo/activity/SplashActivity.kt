package info.countries.countriesinfo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import info.countries.countriesinfo.MainActivity
import info.countries.countriesinfo.R
import info.countries.countriesinfo.model.Country
import info.countries.countriesinfo.retrofit.ApiClient
import info.countries.countriesinfo.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity(), Callback<List<Country>> {

    lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        progressBar = findViewById(R.id.progressBar)
        callRestApi()

    }


    private fun callRestApi() {
        progressBar.visibility = View.VISIBLE
        var apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)

        var call: Call<List<Country>> = apiInterface.getAllCountries()
        call.enqueue(this)

    }

    override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()

    }

    override fun onResponse(call: Call<List<Country>>?, response: Response<List<Country>>?) {
        val countryList:ArrayList<Country> = response!!.body() as ArrayList<Country>
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java).putExtra("countryList",countryList))
        finish()
    }
}
