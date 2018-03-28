package rbc.com.rbcapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.restfulService.getProductFamilies().enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                Toast.makeText(this@MainActivity, response?.body()?.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}
