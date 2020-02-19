package com.target.androidcasestudy.view

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.target.androidcasestudy.R
import com.target.androidcasestudy.model.Item
import com.target.androidcasestudy.network.PicassoDownloader
import kotlinx.android.synthetic.main.item_list.view.*
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit




class ItemListAdapter (val activity: Activity, val itemList: List<Item>): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Item) {
            itemView.apply {
                item_name_txt.text = data.title
                item_price_txt.text = data.price
            }
            data.aisle?.let {
                itemView.aisle_text.text =" ship or " +it
            }
            Log.d("image url  ", data.image)

            PicassoDownloader.getPicasso(itemView.context).load(data.image)
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).fit().centerInside().into(itemView.item_image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        // return ViewHolder(inflatedView)
        return ViewHolder(inflatedView).listen { pos, type ->
            val item = itemList.get(pos)
            val fragmentManager =( activity as FragmentActivity).supportFragmentManager
            val TAG = DetailsFragment.javaClass.simpleName
            fragmentManager.beginTransaction().addToBackStack(TAG).add(R.id.fragment_container, DetailsFragment.newInstance(pos),TAG).commit()


        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data: Item = itemList[position]
        holder.bind(data)
    }



    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }



}