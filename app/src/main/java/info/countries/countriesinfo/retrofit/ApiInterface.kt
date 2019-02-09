package info.countries.countriesinfo.retrofit

import info.countries.countriesinfo.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("all")
    fun getAllCountries(): Call<List<Country>>
}