package edu.towson.cosc431.nicktaormino.fitnessapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pro_tip.view.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class ProTip : Fragment() {

    private var mutableTips: MutableList<DTips> = mutableListOf()
    private var Tips: List<DTips>? = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pro_tip, container, false)
        val i = (Math.random() * 9).toInt()
        view.Tip.text = NetworkHelper().fetchTip(i)
        return view
    }
}
