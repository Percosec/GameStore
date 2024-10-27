package com.example.gamestore.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamestore.R
import com.example.gamestore.data.Games

class GameAdapter: RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var listaGames = ArrayList<Games>()
    private lateinit var context: Context

    fun setContext(context: Context){
        this.context = context
    }

    fun setLista(lista:ArrayList<Games>){
        this.listaGames = lista
    }

    class GameViewHolder(view:View):RecyclerView.ViewHolder(view){
        private var filaNombre = view.findViewById<TextView>(R.id.tvNombre)
        private var filaImagen = view.findViewById<ImageView>(R.id.ivGame)
        private var filaPrecio = view.findViewById<TextView>(R.id.tvPrecio)

        fun rellenarFila(game:Games){
            filaNombre.text = game.nombre
            filaPrecio.text = "S/${game.precio}"
            Glide.with(filaImagen).load(game.imagen).into(filaImagen)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= GameViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_games,parent,false)
    )

    override fun getItemCount(): Int {
        return listaGames.size - 5
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gamesItem = listaGames[position]
        holder.rellenarFila(gamesItem)
    }
}


