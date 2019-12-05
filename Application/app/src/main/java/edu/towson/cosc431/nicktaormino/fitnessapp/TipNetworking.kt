package edu.towson.cosc431.nicktaormino.fitnessapp

import android.graphics.Bitmap
import android.renderscript.RenderScript
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener

class NetworkHelper {
    fun fetchTip(callback: (List<DTips>) -> Unit)
    {
        AndroidNetworking.get(API_URL)
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObjectList(DTips::class.java, object: ParsedRequestListener<List<DTips>>{
                override fun onResponse(response: List<DTips>?) {
                    if(response != null) {
                        callback(response)
                    }
                    else throw Exception("Error fetching people")
                }

                override fun onError(anError: ANError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    companion object{
        val API_URL = "https://github.com/nick-taormino2/FitnessAPI/blob/master/db.json"
    }
}