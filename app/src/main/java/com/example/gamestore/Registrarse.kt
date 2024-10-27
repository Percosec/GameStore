package com.example.gamestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamestore.databinding.ActivityLoginBinding
import com.example.gamestore.databinding.ActivityRegistrarseBinding

class Registrarse : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        crearCuenta()
    }

    private fun crearCuenta(){


        binding.btnCrearCuenta.setOnClickListener {
            if (binding.etusuario.text!!.isEmpty() && binding.etcontrasenia.text!!.isEmpty() && binding.etConfirmarContrasenia.text!!.isEmpty()){
                binding.tfUsuario.setEndIconDrawable(R.drawable.ic_error)
                binding.tfContrasenia.setEndIconDrawable(R.drawable.ic_error)
                binding.tfConfirmarContrasenia.setEndIconDrawable(R.drawable.ic_error)
                binding.tfUsuario.helperText = "Requerido*"
                binding.tfContrasenia.helperText = "Requerido*"
                binding.tfConfirmarContrasenia.helperText = "Requerido*"
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

    }
}