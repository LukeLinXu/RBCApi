package rbc.com.rbcapi

import android.util.Base64
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by lukelin on 2018-04-06.
 */
object RestClient {

    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
            .baseUrl(URLS.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        return createService(serviceClass, "ER6wU0FwrPUuh8YpsorxgHNIsGaBgGHM", "whc9GtDTHdLxvkbG")
    }

    fun <S> createService(serviceClass: Class<S>, username: String?, password: String?): S {
        if (username != null && password != null) {
            val credentials = username + ":" + password
            val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .header("Authorization", basic)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }
        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }

    interface RestfulService {

        @GET(URLS.URL_GET_PRODUCT_FAMILIES)
        fun getProductFamilies(): Call<JsonArray>

        @GET(URLS.URL_GET_PRODUCT_FAMILY+"{family}")
        fun getProductFamily(@Path("family") family: String): Call<JsonObject>

        @GET(URLS.URL_GET_PRODUCT+"{product}")
        fun getProduct(@Path("product") product: String): Call<JsonObject>

    }

    var restfulService = createService(RestfulService::class.java)
}