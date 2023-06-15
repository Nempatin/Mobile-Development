import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.nempatin.R
import com.capstone.nempatin.domain.Property

class NearbyAdapter : PagingDataAdapter<Property, NearbyAdapter.PropertyViewHolder>(PropertyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property_2, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = getItem(position)
        if (property != null) {
            holder.bind(property)
        }
    }

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view_placeholder_2)
        private val titleTextView: TextView = itemView.findViewById(R.id.text_view_card_title_2)
        private val priceTextView: TextView = itemView.findViewById(R.id.text_view_price_2)
        private val contentTextView: TextView = itemView.findViewById(R.id.text_view_card_content_2)

        fun bind(property: Property) {
            // Bind property data to views
            imageView.setImageResource(R.drawable.download) // Update with actual image loading
            titleTextView.text = property.name
            priceTextView.text = property.price.toString()
            contentTextView.text = property.city
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

