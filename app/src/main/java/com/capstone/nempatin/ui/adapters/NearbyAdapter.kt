package com.capstone.nempatin.ui.adapters

import android.content.Intent
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
import com.capstone.nempatin.R
import com.capstone.nempatin.domain.Property
import com.capstone.nempatin.ui.detail.DetailActivity

class NearbyAdapter : PagingDataAdapter<Property, NearbyAdapter.PropertyViewHolder>(PropertyDiffCallback()) {

    // define listener
    var onItemClickListener: ((Property) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property_2, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = getItem(position)
        if (property != null) {
            holder.bind(property)
            // here we use the listener
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(property)

                // Now we start the DetailActivity when an item is clicked
                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_ID, property.id)
                    putExtra(DetailActivity.EXTRA_NAME, property.name)
                    putExtra(DetailActivity.EXTRA_PHOTO, property.photo)
                    putExtra(DetailActivity.EXTRA_CITY, property.city)
                    putExtra(DetailActivity.EXTRA_PRICE, property.price)
                    putExtra(DetailActivity.EXTRA_BUILDING_AREA, property.buildingArea)
                    putExtra(DetailActivity.EXTRA_LAND_AREA, property.landArea)
                    putExtra(DetailActivity.EXTRA_BATHROOMS, property.bathrooms)
                    putExtra(DetailActivity.EXTRA_BEDROOMS, property.bedrooms)
                    putExtra(DetailActivity.EXTRA_GARAGE, property.garage)
                    putExtra(DetailActivity.EXTRA_CERTIFICATE, property.certificate)
                    putExtra(DetailActivity.EXTRA_PHONE_NUMBER, property.phoneNumber)
                }
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    inner class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view_placeholder_2)
        private val titleTextView: TextView = itemView.findViewById(R.id.text_view_card_title_2)
        private val contentTextView: TextView = itemView.findViewById(R.id.text_view_card_content_2)
        private val priceTextView: TextView = itemView.findViewById(R.id.text_view_price_2)

        fun bind(property: Property) {
            // Bind property data to views
            titleTextView.text = property.name
            contentTextView.text = property.city
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
