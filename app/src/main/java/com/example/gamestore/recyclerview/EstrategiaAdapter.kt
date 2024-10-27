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

class EstrategiaAdapter:RecyclerView.Adapter<EstrategiaAdapter.EstrategiaViewHolder>() {

    private var listaGamesEstrategia = ArrayList<Games>()
    private lateinit var context: Context

    fun setContext(context: Context){
        this.context = context
    }

    fun setLista(lista:ArrayList<Games>){
        this.listaGamesEstrategia = lista
    }

    class EstrategiaViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var filaNombre = view.findViewById<TextView>(R.id.tvNombre)
        private var filaImagen = view.findViewById<ImageView>(R.id.ivGame)
        private var filaPrecio = view.findViewById<TextView>(R.id.tvPrecio)

        fun rellenarFila(game: Games){
            filaNombre.text = game.nombre
            filaPrecio.text = "S/${game.precio}"
            Glide.with(filaImagen).load(game.imagen).into(filaImagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EstrategiaViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_games2,parent,false)
    )

    override fun getItemCount(): Int {
        return listaGamesEstrategia.size
    }

    override fun onBindViewHolder(holder: EstrategiaViewHolder, position: Int) {
        val gamesItem = listaGamesEstrategia[position]
        holder.rellenarFila(gamesItem)
    }
}