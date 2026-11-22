package org.example

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)

    println("Digite o ID do jogo que deseja buscar: ")
    val buscaId = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$buscaId"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .header("User-Agent", "AluGames/1.0")
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()
    println(json)

    val gson = Gson()
    val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

    val meuJogo = Jogo(
        meuInfoJogo.info.title,
        meuInfoJogo.info.thumb)

    println(meuJogo)
}