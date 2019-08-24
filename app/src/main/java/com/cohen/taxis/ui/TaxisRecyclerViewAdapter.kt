package com.cohen.taxis.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cohen.taxis.R
import com.cohen.taxis.model.Taxi
import kotlinx.android.synthetic.main.texi_item_list_content.view.*


class TaxisRecyclerViewAdapter(
    private val parentActivity: MainActivity, taxiClicked: (Taxi) -> Any
) :
    RecyclerView.Adapter<TaxisRecyclerViewAdapter.ViewHolder>() {
    var values: List<Taxi> = ArrayList()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            taxiClicked.invoke(v.tag as Taxi)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.texi_item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.contentView.text = "${item.eta}M"

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title_text
        val contentView: TextView = view.content
        val circle: TextView = view.circle
    }
}