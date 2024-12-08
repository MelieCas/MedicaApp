package com.example.medicaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test_page)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val appointmentButton = findViewById<Button>(R.id.appointmentsButton)
        val preferenceButton = findViewById<Button>(R.id.preferenceButton)


        registerButton.setOnClickListener {
            val intent: Intent = Intent(
                this@TestPage,
                SignInActivity::class.java
            )
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val intent: Intent = Intent(
                this@TestPage,
                MainActivity::class.java
            )
            startActivity(intent)
        }

        appointmentButton.setOnClickListener {
            val intent: Intent = Intent(
                this@TestPage,
                Appointments::class.java
            )
            startActivity(intent)
        }

        preferenceButton.setOnClickListener {
            val intent: Intent = Intent(
                this@TestPage,
                Preferences::class.java
            )
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}