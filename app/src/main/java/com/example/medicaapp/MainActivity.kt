package com.example.medicaapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        screenSplash.setOnExitAnimationListener { splashScreenViewProvider ->
            // Aplicar la animación al ícono de la SplashScreen
            val splashScreenView = splashScreenViewProvider.view

            val heartbeatAnimation = AnimationUtils.loadAnimation(this, R.anim.heartbeat)
            splashScreenView.startAnimation(heartbeatAnimation)

            // Eliminar la SplashScreen después de la animación
            splashScreenView.postDelayed({
                splashScreenViewProvider.remove()
            }, heartbeatAnimation.duration)
        }

        setContentView(R.layout.activity_main)

        val btnLogIn = findViewById<Button>(R.id.log_in)
        val btnSignIn = findViewById<Button>(R.id.sign_in)

        btnSignIn.setOnClickListener { navigateToSignIn() }

        btnLogIn.setOnClickListener { navigateToTestPage() }

    }

    private fun animateLogo() {
        val logo = findViewById<ImageView>(R.id.logo)

        val heartBeat = AnimationUtils.loadAnimation(this, R.anim.heartbeat)

        logo.startAnimation(heartBeat)
    }

    private fun navigateToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTestPage() {
        val intent = Intent(this, TestPage::class.java)
        startActivity(intent)
    }
}