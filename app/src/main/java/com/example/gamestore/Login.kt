package com.example.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamestore.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash: SplashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        screenSplash.setKeepOnScreenCondition{false}
        login()
        registrarse()
    }

    private fun login(){

        binding.btniniciarSesion.setOnClickListener {

            if (binding.etusuario.text!!.isEmpty() && binding.etcontrasenia.text!!.isEmpty()){
                binding.tfUsuario.setEndIconDrawable(R.drawable.ic_error)
                binding.tfContrasenia.setEndIconDrawable(R.drawable.ic_error)
                binding.tfUsuario.helperText = "Requerido*"
                binding.tfContrasenia.helperText = "Requerido*"
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun registrarse(){
        binding.btnRegistrar.setOnClickListener {
            val intent = Intent(this, Registrarse::class.java)
            startActivity(intent)
            finish()
        }
    }
}