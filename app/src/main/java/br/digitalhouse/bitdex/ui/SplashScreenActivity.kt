package br.digitalhouse.bitdex.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import br.digitalhouse.bitdex.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val logo: ImageView = findViewById(R.id.splash)
        logo.alpha=0f
        logo.animate().setDuration(3000).alpha(1f).withEndAction{
            val showLogo = Intent(this, MainActivity::class.java)
            startActivity(showLogo)
        }
    }
}