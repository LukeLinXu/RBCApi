package rbc.com.rbcapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.restfulService.getProduct(intent.getStringExtra("data")).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Utils.handleError(t, this@ProductDetailActivity)
            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                val body = response?.body()
                if(body != null){
                    content.text = Utils.gson.toJson(body)
                }
            }

        })
    }
}
