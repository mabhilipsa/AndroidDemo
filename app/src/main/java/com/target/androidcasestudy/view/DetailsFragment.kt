package com.target.androidcasestudy.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.androidcasestudy.R
import com.target.androidcasestudy.model.Item
import com.target.androidcasestudy.network.PicassoDownloader
import com.target.androidcasestudy.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.item_list.view.*

class DetailsFragment : Fragment() {

    private val TAG= javaClass.simpleName

    private val model: ItemViewModel by activityViewModels()



    companion object{
        fun newInstance(position:Int): DetailsFragment{
            var fragment = DetailsFragment()
            val args = Bundle()
            args.putInt("position", position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_details,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.get("position").let {position->

            model.itemLiveData.observe(this, Observer {
                    val item = it[position as Int]
                item_desc.text = item.description
                item_name.text=item.title
                item_price.text=item.price
                item_sale_price.text="Reg : "+item.salePrice

                context?.let { it1 ->
                    PicassoDownloader.getPicasso(it1).load(item.image)
                        .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).fit().centerInside().into(item_image)
                }

            })
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"onActivityCreated")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetach")
    }

}