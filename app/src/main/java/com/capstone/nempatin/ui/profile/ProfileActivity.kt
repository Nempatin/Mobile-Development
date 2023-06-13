package com.capstone.nempatin.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nempatin.R
import com.capstone.nempatin.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        val signOutButton: Button = findViewById(R.id.sign_out)
        signOutButton.setOnClickListener {
            // Sign out from Firebase Auth
            auth.signOut()

            // Return to login activity after signing out
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}
