package com.example.gamestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamestore.data.RetrofitClient
import com.example.gamestore.databinding.FragmentAccionBinding
import com.example.gamestore.recyclerview.AccionAdapter
import com.example.gamestore.recyclerview.GameAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chromium.base.ThreadUtils.runOnUiThread


class AccionFragment : Fragment() {

    private var _binding: FragmentAccionBinding? = null
    private val binding get() = _binding!!
    private var adaptador: AccionAdapter = AccionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccionBinding.inflate(inflater, container, false)

        asignar()
        cargarGames()
        return binding.root
    }

    private fun asignar(){
        binding.rvAccion.layoutManager = GridLayoutManager(context,2)
        adaptador.setContext(requireContext())
    }

    private fun cargarGames(){
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val rpta = RetrofitClient.webService.obtenerGamesAccion()
            withContext(Dispatchers.Main) {
                // Ocultar el ProgressBar
                binding.progressBar.visibility = View.GONE

                if (rpta.isSuccessful) {
                    adaptador.setLista(rpta.body()!!.listaGames)
                    binding.rvAccion.adapter = adaptador
                } else {
                    // Manejar el error, mostrar un mensaje o algo similar
                    Toast.makeText(context, "Error al cargar los juegos", Toast.LENGTH_SHORT).show()
                }
            }
//            runOnUiThread {
//                if (rpta.isSuccessful){
//                    adaptador.setLista(rpta.body()!!.listaGames)
//                    binding.rvAccion.adapter = adaptador
//                }
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}