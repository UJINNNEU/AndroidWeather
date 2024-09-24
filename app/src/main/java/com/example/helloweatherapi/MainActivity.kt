package com.example.helloweatherapi

import Fragments.MainFragment
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.placeHolder,
            MainFragment.newInstance()).commit()


    }

    private fun getResult(name:String){
        val API_KEY = "ea4a555e29624086952191150242107"
       val url = "https://api.weatherapi.com/v1/current.json?" +
               "key=$API_KEY&q=$name&aqi=no"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,{
                responce->
                val obj = JSONObject(responce)
                val temp = obj.getJSONObject("current")
                Log.d("Valley-Error","Responce - ${temp.getString("temp_c")}")

            },{
                Log.d("Valley-Error","Valley-Error - $it")
            })
        queue.add(stringRequest)
    }
}