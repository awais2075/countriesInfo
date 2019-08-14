package info.countries.countriesinfo

import android.content.Context
import android.widget.Toast

object Util {

    fun showToast(context: Context, text:String, length:Int=Toast.LENGTH_SHORT) {
        Toast.makeText(context, text, length).show()
    }
}