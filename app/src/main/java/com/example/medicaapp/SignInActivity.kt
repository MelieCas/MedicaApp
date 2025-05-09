package com.example.medicaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel

class SignInActivity : AppCompatActivity() {

    private val model: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val usuariText = findViewById<EditText>(R.id.user2)
        val nomText = findViewById<EditText>(R.id.name)
        val emailText = findViewById<EditText>(R.id.email)
        val passwordText = findViewById<EditText>(R.id.password2)
        val confirmPasswordText = findViewById<EditText>(R.id.password)
        val signInButton = findViewById<Button>(R.id.sign_in2)

        usuariText.addTextChangedListener {
            model.setUsuari(it.toString())
        }
        usuariText.setOnFocusChangeListener { v, hasFocus ->
            model.checkUser()
            if(!model.errorUser.value.equals("")) {
                usuariText.setError(model.errorUser.value)
            } else {
                usuariText.setError(null)
            }
        }

        nomText.addTextChangedListener {
            model.setNom(it.toString())
        }
        nomText.setOnFocusChangeListener { v, hasFocus ->
            model.checkNom()
            if(!model.errorName.value.equals("")) {
                nomText.setError(model.errorName.value)
            } else {
                nomText.setError(null)
            }
        }

        emailText.addTextChangedListener {
            model.setEmail(it.toString())
        }

        emailText.setOnFocusChangeListener { v, hasFocus ->
            model.checkEmail()
            if(!model.errorEmail.value.equals("")) {
                emailText.setError(model.errorEmail.value)
            } else {
                emailText.setError(null)
            }
        }

        passwordText.addTextChangedListener {
            model.setPassword(it.toString())
        }

        passwordText.setOnFocusChangeListener { v, hasFocus ->
            model.checkPassword()
            if(!model.errorPassword.value.equals("")) {
                passwordText.setError(model.errorPassword.value)
            } else {
                passwordText.setError(null)
            }
        }

        confirmPasswordText.addTextChangedListener {
            model.setConfirmPassword(it.toString())
        }

        confirmPasswordText.setOnFocusChangeListener { v, hasFocus ->
            model.checkConfirmPassword()
            if(!model.errorConfirmPassword.value.equals("")) {
                confirmPasswordText.setError(model.errorConfirmPassword.value)
            } else {
                confirmPasswordText.setError(null)
            }
        }

        signInButton.setOnClickListener {

            if (model.validForm()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Registre completat", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Dades erroneas", Toast.LENGTH_SHORT).show()
            }

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}