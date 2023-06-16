package com.capstone.nempatin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.nempatin.BuildConfig.GOOGLE_PLACES_API_KEY
import com.capstone.nempatin.R
import com.capstone.nempatin.data.network.ApiConfigPlaces
import com.capstone.nempatin.data.response.geocoding.GeocodingResponse
import com.capstone.nempatin.domain.Property
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LatestAddedAdapter : PagingDataAdapter<Property, LatestAddedAdapter.PropertyViewHolder>(PropertyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = getItem(position)
        if (property != null) {
            holder.bind(property)
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
        val placeIdTextView: TextView = itemView.findViewById(R.id.text_view_placeid)

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

            // Fetch placeId from Google Geocoding API
            val callGeocoding = ApiConfigPlaces.service.getGeocoding(
                "${property.latitude},${property.longitude}",
                GOOGLE_PLACES_API_KEY
            )

            callGeocoding.enqueue(object : Callback<GeocodingResponse> {
                override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                    val geocodingResults = response.body()?.results
                    if (geocodingResults != null && geocodingResults.isNotEmpty()) {
                        val placeId = geocodingResults[0].placeId
                        property.placeId = placeId

                        // Set the placeId to TextView
                        placeIdTextView.text = "Place ID: ${property.placeId}"
                    }
                }

                override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                    // Handle the error
                }
            })
        }
    }

    class PropertyDiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }
    }
}
