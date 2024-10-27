package com.example.gamestore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.gamestore.data.Games
import com.example.gamestore.data.ImageItem
import com.example.gamestore.data.RetrofitClient
import com.example.gamestore.databinding.FragmentHomeBinding
import com.example.gamestore.recyclerview.Game2Adapter
import com.example.gamestore.recyclerview.GameAdapter
import com.example.gamestore.recyclerview.ImageAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chromium.base.ThreadUtils.runOnUiThread
import java.util.UUID


class HomeFragment : Fragment() {

    private lateinit var pageChangeListener:ViewPager2.OnPageChangeCallback
    private lateinit var viewPager2: ViewPager2
    private var adaptador:GameAdapter = GameAdapter()
    private var adaptador2:Game2Adapter = Game2Adapter()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewPager2 = binding.viewpager2


        val imageList = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                "https://gamestoreperu.com/files/images/slider/1729267852_info.webp"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://scontent.flim2-2.fna.fbcdn.net/v/t39.30808-6/459135043_1069704351825377_7111984258523882773_n.png?stp=dst-jpg&_nc_cat=107&ccb=1-7&_nc_sid=86c6b0&_nc_ohc=1NjgunMPrTcQ7kNvgFqVeAT&_nc_ht=scontent.flim2-2.fna&_nc_gid=AAV3sheJz6yI8WHa8Drg2dg&oh=00_AYCtRxMJd-jlZrctbPnrC6kUCPAgKJmfN7KAJP2C40wYRA&oe=671CCEDB"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://scontent.flim2-2.fna.fbcdn.net/v/t39.30808-6/452253987_1030716432390836_1423790938160580114_n.png?stp=dst-jpg&_nc_cat=106&ccb=1-7&_nc_sid=86c6b0&_nc_ohc=UJzXMfwBJ5wQ7kNvgFTdSHk&_nc_ht=scontent.flim2-2.fna&_nc_gid=AroS3Sh4zo9H5iH6cmntqiw&oh=00_AYADYY0OZq1PgDFSQIgUUQO85dbN9hs9gkMek5LSYM2VUw&oe=671CF491"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://scontent.flim2-2.fna.fbcdn.net/v/t39.30808-6/450417828_1025512299577916_2710952057453553443_n.png?stp=dst-jpg&_nc_cat=103&ccb=1-7&_nc_sid=86c6b0&_nc_ohc=pEJdGBp7vtcQ7kNvgGkLilw&_nc_ht=scontent.flim2-2.fna&_nc_gid=ABcY8_Y4ZyvLbMYBlyzVI2p&oh=00_AYDqRvdYtaQbR4jJKZJgOf9Eqifa8Tr3dGrj9KzTcUfGOg&oe=671CEBED"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://scontent.flim2-2.fna.fbcdn.net/v/t39.30808-6/419731535_899355428860271_3915773499659229933_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=86c6b0&_nc_ohc=Y7MWUyNEL-gQ7kNvgGjxXXs&_nc_ht=scontent.flim2-2.fna&_nc_gid=AiJZ1unOaWbPekgISVLKleh&oh=00_AYBj9ELmrd2ldFKxzXiaaygkAlKoGUEGbcHd-jw1qzy4Fw&oe=671CE90F"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://scontent.flim2-2.fna.fbcdn.net/v/t39.30808-6/407356648_870469385082209_3055406983902177302_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=86c6b0&_nc_ohc=8h8X0h8bQI4Q7kNvgH8_XKT&_nc_ht=scontent.flim2-2.fna&_nc_gid=AgnAWdBP8GOEC0EorZrpxQ-&oh=00_AYDTDh0rEZMGbKy8t5EKe8RZc8fmnFrZW-Cd4hi97kuMew&oe=671CFE7D"
            )
        )

        val imageAdapter = ImageAdapter()
        viewPager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val slider = binding.slider
        val sliderImage = Array(imageList.size){
            ImageView(context)
        }

        sliderImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slider.addView(it,params)
        }

        sliderImage[0].setImageResource(R.drawable.active_dot)
        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                sliderImage.mapIndexed { index, imageView ->
                    if (position == index){
                        imageView.setImageResource(R.drawable.active_dot)
                    }else{
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }

        viewPager2.registerOnPageChangeCallback(pageChangeListener)

        asignar()
        cargarGames()
        return binding.root
    }

    private fun asignar(){
        binding.rv1.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rv2.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adaptador.setContext(requireContext())
        adaptador2.setContext(requireContext())
    }

    private fun cargarGames(){
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val rpta = RetrofitClient.webService.obtenerGames()
            withContext(Dispatchers.Main) {
                // Ocultar el ProgressBar
                binding.progressBar.visibility = View.GONE

                if (rpta.isSuccessful) {
                    adaptador.setLista(rpta.body()!!.listaGames)
                    adaptador2.setLista(rpta.body()!!.listaGames)
                    binding.rv1.adapter = adaptador
                    binding.rv2.adapter = adaptador2
                } else {
                    // Manejar el error, mostrar un mensaje o algo similar
                    Toast.makeText(context, "Error al cargar los juegos", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//            runOnUiThread {
//                binding.progressBar.visibility = View.GONE
//                if (rpta.isSuccessful){
//                    adaptador.setLista(rpta.body()!!.listaGames)
//                    adaptador2.setLista(rpta.body()!!.listaGames)
//                    binding.rv1.adapter = adaptador
//                    binding.rv2.adapter = adaptador2
//                }
//
//            }