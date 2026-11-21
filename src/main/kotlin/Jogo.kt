package org.example

class Jogo {

    var titulo = ""
    var capa = ""
    var descricao = ""


    override fun toString(): String {
        return "Meu Jogo: $titulo\n" +
                "Capa:$capa\n" +
                "Descricao:$descricao"
    }


}