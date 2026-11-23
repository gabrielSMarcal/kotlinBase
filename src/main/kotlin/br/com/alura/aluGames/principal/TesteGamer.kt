package org.example.br.com.alura.aluGames.principal

import org.example.br.com.alura.aluGames.modelo.Gamer

fun main() {
    val gamer1 = Gamer("Malorc", "malorc@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Mushva", "mushva@gmail.com", "10/10/2000", "mushva")
    println(gamer2)

    gamer1.let{
        it.dataNascimento = "01/01/1990"
        it.usuario = "malorc"
        it.idInterno = it.usuario + "123456"
    }

    println(gamer1)
}