package com.capstone.nempatin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.capstone.nempatin.data.network.ApiConfig
import com.capstone.nempatin.databinding.ActivityDetailBinding
import com.capstone.nempatin.domain.Property
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID)
        if (id != null) {
            getSingleProperty(id)
        } else {
            Toast.makeText(this, "Invalid property id", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSingleProperty(id: String) {
        val apiService = ApiConfig.getApiService()
        apiService.getSingleProperty(id).enqueue(object : Callback<Property> {
            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                if (response.isSuccessful) {
                    val property = response.body()
                    if (property != null) {
                        setPropertyData(property)
                    }
                } else {
                    Log.e("DetailActivity", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                Log.e("DetailActivity", "onFailure: ${t.message}")
            }
        })
    }

    private fun setPropertyData(property: Property) {
        Glide.with(this)
            .load(property.photo)
            .into(binding.ivPropertyImage)

        binding.tvPropertyName.text = property.name
        binding.tvPropertyLocation.text = property.city
        binding.tvTotalBed.text = property.bedrooms.toString()
        binding.tvTotalBath.text = property.bathrooms.toString()
        binding.tvPropertyLb.text = property.buildingArea.toString()
        binding.tvPropertyLt.text = property.landArea.toString()
        binding.tvCertificate.text = property.certificate
        binding.tvPropertyPrice.text = property.price.toString()
        binding.tvContactPerson.text = property.phoneNumber.toString()
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}