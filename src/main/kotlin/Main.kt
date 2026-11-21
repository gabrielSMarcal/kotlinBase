package org.example

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers


fun main() {
    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=146"))
        .header("User-Agent", "AluGames/1.0")
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()
    println(json)

    val meuJogo = Jogo()

    meuJogo.titulo = json.substring(json.indexOf("title") + 8, json.indexOf("title") + 8 + 100)
    meuJogo.capa = json.substring(json.indexOf("thumb") + 8, json.indexOf("thumb") + 8 + 100)
    meuJogo.descricao = json.substring(json.indexOf("description") + 14, json.indexOf("description") + 14 + 100)


    println(meuJogo)
}