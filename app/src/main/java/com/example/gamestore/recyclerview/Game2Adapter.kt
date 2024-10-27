package com.example.gamestore.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamestore.R
import com.example.gamestore.data.Games

class Game2Adapter: RecyclerView.Adapter<Game2Adapter.Game2ViewHolder>() {

    private var listaGames = ArrayList<Games>()
    private lateinit var context: Context

    fun setContext(context: Context){
        this.context = context
    }

    fun setLista(lista:ArrayList<Games>){
        this.listaGames = lista
    }

    class Game2ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private var filaNombre = view.findViewById<TextView>(R.id.tvNombre)
        private var filaImagen = view.findViewById<ImageView>(R.id.ivGame)
        private var filaPrecio = view.findViewById<TextView>(R.id.tvPrecio)

        fun rellenarFila(game:Games){
            filaNombre.text = game.nombre
            filaPrecio.text = "S/${game.precio}"
            Glide.with(filaImagen).load(game.imagen).into(filaImagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= Game2ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_games,parent,false)
    )

    override fun getItemCount(): Int {
        return if (listaGames.size > 5) {
            5 // Solo muestra 5 elementos desde el índice 6
        } else {
            0 // Si hay menos de 10 elementos, no se muestra nada
        }
    }

    override fun onBindViewHolder(holder: Game2ViewHolder, position: Int) {
//        val gamesItem = listaGames[position + 5]
//        holder.rellenarFila(gamesItem)

        val realPosition = position + 5 // Ajusta la posición para acceder al índice 6
        if (realPosition < listaGames.size) {
            val gamesItem = listaGames[realPosition]
            holder.rellenarFila(gamesItem)
        }
    }
}