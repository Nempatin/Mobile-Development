package com.capstone.nempatin.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.nempatin.R
import com.capstone.nempatin.domain.Property
import com.capstone.nempatin.ui.detail.DetailActivity

class LatestAddedAdapter : PagingDataAdapter<Property, LatestAddedAdapter.PropertyViewHolder>(
    NearbyAdapter.PropertyDiffCallback()
) {

    // define listener
    var onItemClickListener: ((Property) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = getItem(position)
        if (property != null) {
            holder.bind(property)
            // here we use the listener
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(property)

                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_ID, property.id)
                    putExtra(DetailActivity.EXTRA_NAME, property.name)
                    putExtra(DetailActivity.EXTRA_PHOTO, property.photo)
                    putExtra(DetailActivity.EXTRA_CITY, property.city)
                    putExtra(DetailActivity.EXTRA_PRICE, property.price)
                    putExtra(DetailActivity.EXTRA_BUILDING_AREA, property.buildingArea)
                    putExtra(DetailActivity.EXTRA_LAND_AREA, property.landArea)
                    putExtra(DetailActivity.EXTRA_BEDROOMS, property.bedrooms)
                    putExtra(DetailActivity.EXTRA_BATHROOMS, property.bathrooms)
                    putExtra(DetailActivity.EXTRA_GARAGE, property.garage)
                    putExtra(DetailActivity.EXTRA_CERTIFICATE, property.certificate)
                    putExtra(DetailActivity.EXTRA_PHONE_NUMBER, property.phoneNumber)
                }
                holder.itemView.context.startActivity(intent)
            }

        }
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
            titleTextView.text = property.name
            contentTextView.text = property.city
            landAreaTextView.text = property.landArea.toString()
            buildingAreaTextView.text = property.buildingArea.toString()
            bedroomTextView.text = property.bedrooms.toString()
            bathroomTextView.text = property.bathrooms.toString()

            // Set certificate icon visibility based on property's certificate value
            certificateImageView.visibility = if (property.certificate == "ada") View.VISIBLE else View.GONE

            // Set garage icon visibility based on property's garage value
            garageImageView.visibility = if (property.garage == "ada") View.VISIBLE else View.GONE

            priceTextView.text = property.price.toString()

            // Load property image
            Glide.with(itemView.context)
                .load(property.photo)
                .apply(
                    RequestOptions()
                        .override(500, 500)
                        .centerCrop()
                        .placeholder(R.drawable.download)
                )
                .into(imageView)

}}}
