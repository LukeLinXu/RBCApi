package rbc.com.rbcapi

import android.content.Context
import android.widget.Toast
import com.google.gson.GsonBuilder

/**
 * Created by lukelin on 2018-04-09.
 */
object Utils {
    val gson = GsonBuilder().setPrettyPrinting().create()

    fun handleError(t: Throwable?, context: Context){
        Toast.makeText(context, t?.message?:"", Toast.LENGTH_SHORT).show()
    }
}