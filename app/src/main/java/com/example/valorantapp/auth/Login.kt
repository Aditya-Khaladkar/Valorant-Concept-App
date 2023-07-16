package com.example.valorantapp.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantapp.databinding.ActivityLoginBinding
import com.example.valorantapp.view.Dashboard

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.txtSignUp.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }
    }
}