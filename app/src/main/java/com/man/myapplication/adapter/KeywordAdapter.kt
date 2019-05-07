package com.man.myapplication.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.myapplication.R
import com.man.myapplication.delegate.KeyWordDelegate
import com.man.myapplication.utils.StringUtil
import kotlinx.android.synthetic.main.item_keyword.view.*
import java.util.*

class KeyWordAdapter(val delegate: KeyWordDelegate) : RecyclerView.Adapter<KeyWordAdapter.ViewHolder>() {

    private val data = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)
        return ViewHolder(view)
    }

    fun updateData(data: List<String>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = data[position]
        holder.bind(value)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            val background = itemView.keyword_name.background
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            (background as GradientDrawable).setColor(color)
            itemView.keyword_name.text = StringUtil.handleKeywords(item)
            itemView.setOnClickListener { delegate.onKeyWordSelected(item) }
        }
    }
}