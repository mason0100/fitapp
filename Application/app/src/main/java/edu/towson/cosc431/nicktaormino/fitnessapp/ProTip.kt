package edu.towson.cosc431.nicktaormino.fitnessapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pro_tip.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProTip : Fragment() {

    interface IProTips{
        enum class tips{ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN}
        fun tipCycle()
    }

    val mutableTips: MutableList<DTips> = mutableListOf()
    val Tips: List<DTips> = mutableTips
    private var listener:IProTips? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pro_tip, container, false)
        NetworkHelper()
            .fetchTip {
                tips->
                mutableTips.clear()
                mutableTips.addAll(tips)
            }
        cycleTips(view)
        return view
    }
    fun cycleTips(view:View?)
    {
        //view?.Tip?.text = mutableTips[0].tip
    }
}
