package com.example.gamestore.data

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("listaGames") var listaGames: ArrayList<Games>
)
