package com.capstone.nempatin.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nempatin.R
import com.capstone.nempatin.databinding.ActivitySignupBinding
import com.capstone.nempatin.ui.auth.login.LoginActivity
import com.capstone.nempatin.ui.main.MainActivity
import com.capstone.nempatin.utils.UiUpdater
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            // User is already logged in, navigate to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val emailInput = findViewById<EditText>(R.id.et_email_input_signup)
        val passwordInput = findViewById<EditText>(R.id.et_password_input_signup)
        val confirmPasswordInput = findViewById<EditText>(R.id.et_confirm_password_input_signup)
        val signupButton = findViewById<Button>(R.id.btn_signup)

        signupButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        val loginLink = findViewById<TextView>(R.id.tv_login)
        loginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

