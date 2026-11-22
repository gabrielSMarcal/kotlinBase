package org.example

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.EmptyStackException
import java.util.Scanner
import kotlin.coroutines.EmptyCoroutineContext


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
//    println(json)

    val gson = Gson()


//    try {
//        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
//
//        val meuJogo = Jogo(
//            meuInfoJogo.info.title,
//            meuInfoJogo.info.thumb)
//
//        println(meuJogo)
//    } catch (ex: JsonSyntaxException) {
//        println("Erro ao encontrar jogo: ${ex.message}")
//    }

    var meuJogo:Jogo? = null

    val resultado = runCatching {

        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        meuJogo = Jogo(
            meuInfoJogo.info.title,
            meuInfoJogo.info.thumb)
    }

    resultado.onFailure {
        println("Erro ao encontrar jogo: ${it.message}")
    }

    resultado.onSuccess {
        println("Deseja inserir descrição personalizada? (s/n)")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {

            println("Insira a descrição personalizada: ")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada

            println(meuJogo)

        } else {
            meuJogo?.descricao = meuJogo?.titulo

            println(meuJogo)
        }
    }

    resultado.onSuccess {
        println("Busca finalizada com sucesso.")
    }

}