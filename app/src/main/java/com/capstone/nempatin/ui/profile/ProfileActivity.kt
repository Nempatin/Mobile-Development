package com.capstone.nempatin.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.capstone.nempatin.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Load current user information
        loadUserInfo()

        binding.signOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            // You might want to navigate the user back to the Login screen after signing out
        }
    }

    private fun loadUserInfo() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            binding.email.text = user.email
            // You can also load other user info here
        }
    }
}
