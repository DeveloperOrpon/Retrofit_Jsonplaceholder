package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerview.api.JsonPleaseHolderAPI
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Retrofit start
        val gson=GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();
        val baseUrl="https://jsonplaceholder.typicode.com"
        val retrofit=Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val jsonPleaseHolderAPI=retrofit.create(JsonPleaseHolderAPI::class.java)
        val call=jsonPleaseHolderAPI.getPost()
        call.enqueue(object :Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (!response.isSuccessful){
                    Toast.makeText(this@MainActivity,response.code(),Toast.LENGTH_SHORT).show()
                    return
                }
                myRecyclerView.adapter= response.body()?.let { MyAdapter(this@MainActivity, it) }
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })
        /// end

    }
}