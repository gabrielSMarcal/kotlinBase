package org.example.br.com.alura.aluGames.principal

import org.example.br.com.alura.aluGames.modelo.Jogo
import org.example.br.com.alura.aluGames.servicos.ConsumoApi
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)

    println("Digite o ID do jogo que deseja buscar: ")
    val buscaId = leitura.nextLine()

    val buscaApi = ConsumoApi()

    val resultado = runCatching {
        val informacaoJogo = buscaApi.buscaJogo(buscaId)
        Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb)
    }

    resultado.onFailure { erro ->
        println("Erro ao encontrar jogo: ${erro.message ?: "erro inesperado"}")
    }

    resultado.onSuccess { meuJogo ->
        println("Deseja inserir descrição personalizada? (s/n)")
        val opcao = leitura.nextLine()

        if (opcao.equals("s", true)) {
            println("Insira a descrição personalizada: ")
            meuJogo.descricao = leitura.nextLine()
        } else {
            meuJogo.descricao = meuJogo.titulo
        }

        println(meuJogo)
        println("Busca finalizada com sucesso.")
    }

}