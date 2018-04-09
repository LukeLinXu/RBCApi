package rbc.com.rbcapi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.restfulService.getProductFamilies().enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                Utils.handleError(t, this@MainActivity)
            }

            override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                val body = response?.body()
                if(body != null){
                    content.text = Utils.gson.toJson(body)
                    for(item in body){
                        var btn = Button(this@MainActivity)
                        var s = item.asJsonObject.get("href").asString
                        btn.text = s
                        btn.setOnClickListener {
                            var intent = Intent(this@MainActivity, FamilyDetailActivity::class.java)
                            intent.putExtra("data", s.replace("/families/", ""))
                            startActivity(intent)
                        }
                        linear_layout.addView(btn)
                    }
                }
            }

        })
    }
}
