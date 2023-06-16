package com.capstone.nempatin.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.nempatin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        // add back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get the extras from intent
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val name = intent.getStringExtra(EXTRA_NAME)
        val photo = intent.getStringExtra(EXTRA_PHOTO)
        val city = intent.getStringExtra(EXTRA_CITY)
        val price = intent.getLongExtra(EXTRA_PRICE, 0L)
        val buildingArea = intent.getIntExtra(EXTRA_BUILDING_AREA, 0)
        val landArea = intent.getIntExtra(EXTRA_LAND_AREA, 0)
        val bedrooms = intent.getIntExtra(EXTRA_BEDROOMS, 0)
        val bathrooms = intent.getIntExtra(EXTRA_BATHROOMS, 0)
        val garage = intent.getStringExtra(EXTRA_GARAGE)
        val certificate = intent.getStringExtra(EXTRA_CERTIFICATE)
        val phoneNumber = intent.getLongExtra(EXTRA_PHONE_NUMBER, 0L)

        setPropertyData(photo, name, city, price, buildingArea, landArea, bedrooms, bathrooms, certificate, phoneNumber)

        // Dialer intent
        binding.button3.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(dialIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // go back to the previous activity
        return true
    }

    private fun setPropertyData(photo: String?, name: String?, city: String?, price: Long, buildingArea: Int, landArea: Int, bedrooms: Int, bathrooms: Int, certificate: String?, phoneNumber: Long) {
        Glide.with(this)
            .load(photo)
            .into(binding.ivPropertyImage)

        binding.tvPropertyName.text = name
        binding.tvPropertyLocation.text = city
        binding.tvTotalBed.text = bedrooms.toString()
        binding.tvTotalBath.text = bathrooms.toString()
        binding.tvPropertyLb.text = buildingArea.toString()
        binding.tvPropertyLt.text = landArea.toString()
        binding.tvCertificate.text = certificate
        binding.tvPropertyPrice.text = price.toString()
        binding.tvContactPerson.text = phoneNumber.toString()
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_PHOTO = "EXTRA_PHOTO"
        const val EXTRA_CITY = "EXTRA_CITY"
        const val EXTRA_PRICE = "EXTRA_PRICE"
        const val EXTRA_BUILDING_AREA = "EXTRA_BUILDING_AREA"
        const val EXTRA_LAND_AREA = "EXTRA_LAND_AREA"
        const val EXTRA_BEDROOMS = "EXTRA_BEDROOMS"
        const val EXTRA_BATHROOMS = "EXTRA_BATHROOMS"
        const val EXTRA_GARAGE = "EXTRA_GARAGE"
        const val EXTRA_CERTIFICATE = "EXTRA_CERTIFICATE"
        const val EXTRA_PHONE_NUMBER = "EXTRA_PHONE_NUMBER"
    }
}
