package com.example.gamestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamestore.databinding.FragmentCategoriasBinding
import com.example.gamestore.recyclerview.GameAdapter

class Categorias : Fragment() {


    private var _binding: FragmentCategoriasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriasBinding.inflate(inflater, container, false)


        binding.cvAccion.setOnClickListener {
            val otroFragment = AccionFragment() // Reemplaza con tu fragmento de destino
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, otroFragment) // Asegúrate de que el ID sea correcto
                .addToBackStack(null) // Permite volver al fragmento anterior
                .commit()
        }

        binding.cvEstrategia.setOnClickListener {
            val otroFragment = EstrategiaFragment() // Reemplaza con tu fragmento de destino
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, otroFragment) // Asegúrate de que el ID sea correcto
                .addToBackStack(null) // Permite volver al fragmento anterior
                .commit()
        }

        binding.cvRpg.setOnClickListener {
            val otroFragment = RpgFragment() // Reemplaza con tu fragmento de destino
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, otroFragment) // Asegúrate de que el ID sea correcto
                .addToBackStack(null) // Permite volver al fragmento anterior
                .commit()
        }

        binding.cvShooter.setOnClickListener {
            val otroFragment = ShooterFragment() // Reemplaza con tu fragmento de destino
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, otroFragment) // Asegúrate de que el ID sea correcto
                .addToBackStack(null) // Permite volver al fragmento anterior
                .commit()
        }


        binding.cvAventura.setOnClickListener {
            val otroFragment = AventuraFragment() // Reemplaza con tu fragmento de destino
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, otroFragment) // Asegúrate de que el ID sea correcto
                .addToBackStack(null) // Permite volver al fragmento anterior
                .commit()
        }

        return binding.root
    }


}