package com.capstone.nempatin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.capstone.nempatin.R
import com.capstone.nempatin.domain.Property

class LatestAddedAdapter(private val properties: List<Property> = emptyList()) : RecyclerView.Adapter<LatestAddedAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]
        holder.bind(property)
    }

    override fun getItemCount(): Int {
        return properties.size
    }

    inner class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.text_view_card_title)
        private val contentTextView: TextView = itemView.findViewById(R.id.text_view_card_content)

        fun bind(property: Property) {
            titleTextView.text = property.name
            contentTextView.text = property.city
            // Bind other views as needed
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val snapHelper = LinearSnapHelper()
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            recyclerView.onFlingListener = null
            snapHelper.attachToRecyclerView(recyclerView)
        }
    }
}
