package org.example.br.com.alura.aluGames.modelo

data class InfoJogo(val info: InfoApiShark) {

    override fun toString(): String {
        return info.toString()
    }
}