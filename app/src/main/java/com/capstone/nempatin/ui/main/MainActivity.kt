package com.capstone.nempatin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capstone.nempatin.R
import com.capstone.nempatin.ui.fragments.FavoriteFragment
import com.capstone.nempatin.ui.fragments.HomeFragment
import com.capstone.nempatin.ui.upload.UploadActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.activity_main_bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_add -> {
                    // Start AddActivity instead of replacing the fragment
                    val intent = Intent(this, UploadActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorite -> {
                    replaceFragment(FavoriteFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

        // Default selection
        bottomNavigation.selectedItemId = R.id.navigation_home
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_view, fragment)
        transaction.commit()
    }
}
