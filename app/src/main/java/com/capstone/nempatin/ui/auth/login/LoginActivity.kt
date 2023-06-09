package com.capstone.nempatin.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.nempatin.R
import com.capstone.nempatin.databinding.ActivityLoginBinding
import com.capstone.nempatin.ui.auth.register.SignUpActivity
import com.capstone.nempatin.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            // User is already logged in, navigate to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val emailInput = findViewById<EditText>(R.id.et_email_input)
        val passwordInput = findViewById<EditText>(R.id.et_password_input)
        val loginButton = findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
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

        val signupLink = findViewById<TextView>(R.id.tv_signup)
        signupLink.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
