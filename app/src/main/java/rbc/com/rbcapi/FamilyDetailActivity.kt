package rbc.com.rbcapi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FamilyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.restfulService.getProductFamily(intent.getStringExtra("data")).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Utils.handleError(t, this@FamilyDetailActivity)
            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                val body = response?.body()
                if(body != null){
                    content.text = Utils.gson.toJson(body)
                    for(item in body.get("products").asJsonArray){
                        var btn = Button(this@FamilyDetailActivity)
                        var s = item.asJsonObject.get("href").asString
                        btn.text = s
                        btn.setOnClickListener {
                            var intent = Intent(this@FamilyDetailActivity, ProductDetailActivity::class.java)
                            intent.putExtra("data", s.replace("/products/", ""))
                            startActivity(intent)
                        }
                        linear_layout.addView(btn)
                    }
                }
            }

        })
    }
}
