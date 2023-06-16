package com.capstone.nempatin.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.capstone.nempatin.databinding.ActivityProfileBinding
import com.capstone.nempatin.ui.auth.login.LoginActivity

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

            // Navigate user back to LoginActivity after signing out
            val intent = Intent(this, LoginActivity::class.java)
            // Clear all activities on the stack for a fresh start
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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
