package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicoView
import java.time.LocalDateTime

object TopicViewTeste {
    fun build() =  TopicoView(
        id = 1,
        titulo = "Como usar teste unitario",
        mensagem = "este é um teste unitario",
        dataCriacao = LocalDateTime.now(),
        status = StatusTopico.NAO_RESPONDIDO
    )
}