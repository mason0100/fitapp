package edu.towson.cosc431.nicktaormino.fitnessapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.fragment_pro_tip.view.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class ProTip : Fragment() {

    var answer:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pro_tip, container, false)
        val i = (Math.random() * 9).toInt()
        fetchTip(i)
        return view
    }

    fun fetchTip(i:Int):String
    {
        AndroidNetworking.get(API_URL)
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObjectList(DTips::class.java, object: ParsedRequestListener<List<DTips>> {
                override fun onResponse(response: List<DTips>?) {
                    if(response != null) {
                        view?.Tip?.text = response.get(i).tip
                    }
                    else throw Exception("Error fetching tips")
                }
                override fun onError(anError: ANError?) {
                    System.out.println(anError.toString())
                }
            })
        return answer
    }
    companion object{
        val API_URL = "https://my-json-server.typicode.com/nick-taormino2/FitnessApi/tip"
    }
}
