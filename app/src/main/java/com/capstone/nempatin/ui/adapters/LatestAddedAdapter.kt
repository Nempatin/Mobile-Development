package com.capstone.nempatin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.nempatin.R
import com.capstone.nempatin.domain.Property

class LatestAddedAdapter(private val properties: List<Property>) : RecyclerView.Adapter<LatestAddedAdapter.PropertyViewHolder>() {

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
        private val imageView: ImageView = itemView.findViewById(R.id.image_view_placeholder)
        private val titleTextView: TextView = itemView.findViewById(R.id.text_view_card_title)
        private val contentTextView: TextView = itemView.findViewById(R.id.text_view_card_content)
        private val landAreaTextView: TextView = itemView.findViewById(R.id.text_view_land_area)
        private val buildingAreaTextView: TextView = itemView.findViewById(R.id.text_view_building_area)
        private val bedroomTextView: TextView = itemView.findViewById(R.id.text_view_bedroom)
        private val bathroomTextView: TextView = itemView.findViewById(R.id.text_view_bathroom)
        private val certificateImageView: ImageView = itemView.findViewById(R.id.certificate_icon)
        private val garageImageView: ImageView = itemView.findViewById(R.id.garage_icon)
        private val priceTextView: TextView = itemView.findViewById(R.id.text_view_price)

        fun bind(property: Property) {
            // Bind property data to views
            //imageView.setImageResource(property.imageResId)
            titleTextView.text = property.name
            contentTextView.text = property.city
            landAreaTextView.text = property.landArea.toString()
            buildingAreaTextView.text = property.buildingArea.toString()
            bedroomTextView.text = property.bedrooms.toString()
            bathroomTextView.text = property.bathrooms.toString()

            // Set certificate icon visibility based on property's certificate value
            if (property.certificate == "ada") {
                certificateImageView.visibility = View.VISIBLE
            } else {
                certificateImageView.visibility = View.GONE
            }

            // Set garage icon visibility based on property's garage value
            if (property.garage == "ada") {
                garageImageView.visibility = View.VISIBLE
            } else {
                garageImageView.visibility = View.GONE
            }

            priceTextView.text = property.price.toString()
        }
    }
}
