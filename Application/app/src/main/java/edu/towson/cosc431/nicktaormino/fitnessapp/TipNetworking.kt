package edu.towson.cosc431.nicktaormino.fitnessapp

import android.graphics.Bitmap
import android.renderscript.RenderScript
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener

class NetworkHelper {
    fun fetchTip(i:Int):String
    {
        var ans: String = ""
        AndroidNetworking.get(API_URL)
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObjectList(DTips::class.java, object: ParsedRequestListener<List<DTips>>{
                override fun onResponse(response: List<DTips>?) {
                    if(response != null) {
                        ans = response.get(i).tip
                    }
                    else throw Exception("Error fetching tips")
                }
                override fun onError(anError: ANError?) {
                    System.out.println(anError.toString())
                }
            })
        return ans
    }

    companion object{
        val API_URL = "https://my-json-server.typicode.com/nick-taormino2/FitnessApi/tip"
    }
}