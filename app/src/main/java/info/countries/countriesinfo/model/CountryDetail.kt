package info.countries.countriesinfo.model

import java.io.Serializable

data class CountryDetail (
    val name: String,
    val demonym: String,
    val topLevelDomain: List<String>,
    val callingCode: List<String>,
    val capital: String,
    val region: String,
    val population: Double,
    val latLng: List<Double>,
    val area: Double,
    val timeZone: List<String>,
    val borders: List<String>,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val flag: String
) : Serializable