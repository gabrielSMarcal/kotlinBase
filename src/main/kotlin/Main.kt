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

    val meuJogo = Jogo(
        "The Witcher 3: Wild Hunt",
        "https://cdn.akamai.steamstatic.com/steam/apps/292030/capsule_616x353.jpg?t=1686146400")

    println(meuJogo)

    val novoJogo = Jogo(
        capa="https://cdn.akamai.steamstatic.com/steam/apps/292030/capsule_616x353.jpg?t=1686146400",
        titulo="The Witcher 3: Wild Hunt")

    println(novoJogo)
}