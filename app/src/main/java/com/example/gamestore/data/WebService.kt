package com.example.gamestore.data

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object AppConstantes {
    const val BASE_URL = "http://192.168.0.3:3000"
}

interface WebService {

    @GET("/games")
    suspend fun obtenerGames(): Response<GamesResponse>

    @GET("/games-accion")
    suspend fun obtenerGamesAccion(): Response<GamesResponse>

    @GET("/games-estrategia")
    suspend fun obtenerGamesEstrategia(): Response<GamesResponse>

    @GET("/games-rpg")
    suspend fun obtenerGamesRpg(): Response<GamesResponse>

    @GET("/games-shooter")
    suspend fun obtenerGamesShooter(): Response<GamesResponse>

    @GET("/games-aventura")
    suspend fun obtenerGamesAventura(): Response<GamesResponse>
}

object RetrofitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}